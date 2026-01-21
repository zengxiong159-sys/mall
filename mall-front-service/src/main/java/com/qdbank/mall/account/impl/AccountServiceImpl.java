package com.qdbank.mall.account.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qd.bank.pojo.allinpay.req.*;
import com.qd.bank.pojo.allinpay.req.gw11010.GwRequestBody11010;
import com.qd.bank.pojo.allinpay.resp.*;
import com.qd.bank.pojo.allinpay.resp.gw11010.GwResponseBody11010;
import com.qd.bank.pojo.allinpay.resp.gw12000.GwAccountInfoDTO;
import com.qd.bank.pojo.allinpay.resp.gw12000.GwResponseBody12000;
import com.qdbank.mall.account.AccountService;
import com.qdbank.mall.feign.mix.GwInvokeService;
import com.qdbank.mall.response.account.AccountInfo;
import com.qdbank.mall.response.card.CardInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private GwInvokeService gwInvokeService;

    private static final String ACCOUNT_EXCEPTION_MESSAGE = "信用卡账户异常，请咨询我行客服400-66-96588";
    private static final String ACCOUNT_EXPIRE_MESSAGE = "信用卡账户逾期，请咨询我行客服400-66-96588";
    private static final String ACCOUNT_ACTIVATION_MESSAGE = "信用卡未面签激活，请前往青岛银行任一网点面签激活卡片。客服热线400-66-96588";
    private static final String CARD_EXCEPTION_MESSAGE = "卡片异常，请咨询我行客服400-66-96588";
    private static final String CARD_NOT_ACTIVITE_MESSAGE = "信用卡未激活，请先激活卡片。客服热线400-66-96588";
    //账户层需要提示的管制码
    private List<String> accountExceptionList = new ArrayList<String>(Arrays.asList("C", "P", "D","W","U","c","p","d","w","u"));
    private String accountExpireReg = "^[3-9]$";//信用卡逾期

    @Override
    public List<AccountInfo> queryAccountInfo(String idNo){
        return Lists.newArrayList(query(idNo).values());
    }

    /**
     *  查询不同类型的最新账户信息
     * */
    public Map<String,AccountInfo> query(String idNo){
        Map<String,List<GwAccountInfoDTO>> accountTypeMap=queryAllAccountInfo(idNo).stream().collect(Collectors.groupingBy(GwAccountInfoDTO::getAcctType));
        accountTypeMap.values().stream().forEach(list->list.sort(Comparator.comparing(GwAccountInfoDTO::getSetupDate).reversed()));
        //添加卡列表信息
        Map<String,AccountInfo> result= Maps.newHashMap();
        accountTypeMap.forEach((k,v)->{
            if(CollectionUtils.isNotEmpty(v)){
                GwAccountInfoDTO gwAccountInfoDTO=v.get(0);
                List<CardInfo> cardInfoList=Lists.newArrayList();
                if(!StringUtils.equals(gwAccountInfoDTO.getBlockCode(),"P")){
                    cardInfoList=queryCardList(gwAccountInfoDTO.getAcctNo());
                }
                result.put(k,convert2AccountInfo(gwAccountInfoDTO,cardInfoList));
            }
        });
        //添加通联核心客户号
        if(!result.isEmpty()){
            String custId=getCustId(idNo);
            result.values().stream().forEach(account->account.setCustId(custId));
        }

        return result;
    }

    /**
     *  账户信息转换
     * */
    private AccountInfo convert2AccountInfo(GwAccountInfoDTO gwAccountInfoDTO,List<CardInfo> cardInfoList){
        AccountInfo accountInfo=new AccountInfo();
        accountInfo.setAccountType(gwAccountInfoDTO.getAcctType());
        accountInfo.setBlockCode(gwAccountInfoDTO.getBlockCode());
        accountInfo.setAcctOtb(gwAccountInfoDTO.getAcctOtb());
        accountInfo.setBlockCodeMsg(returnBlkcdeAccountMsg(getFirstBlockCode(gwAccountInfoDTO.getBlockCode())));
        accountInfo.setCardInfoList(cardInfoList);
        return accountInfo;
    }

    /**
     *  查询所有账户信息
     * */
    private List<GwAccountInfoDTO> queryAllAccountInfo(String idNo){
        GwRequestBody12000 reqBody = new GwRequestBody12000();
        reqBody.setIdType("I");
        reqBody.setIdNo(idNo);
        reqBody.setCurrCd("156");
        int start = 0;
        List<GwAccountInfoDTO> accounts = new ArrayList<>();
        while (true) {
            reqBody.setFirstrow(String.valueOf(start));
            reqBody.setLastrow(String.valueOf(start + 9));
            GwRequest<GwRequestBody12000> gwRequest = new GwRequest<>("12000", reqBody);
            GwResponse<GwResponseBody12000> response= gwInvokeService.execute(gwRequest, GwResponseBody12000.class);
            GwResponseBody12000 gwResponseBody12000=response.getRespBody();
            if (gwResponseBody12000 != null) {
                List<GwAccountInfoDTO> gwAccounts = gwResponseBody12000.getAccounts();
                if (!org.springframework.util.CollectionUtils.isEmpty(gwAccounts)) {
                    accounts.addAll(gwAccounts);
                }
                // 还有新数据页
                if ("Y".equals(gwResponseBody12000.getNextpageFlg())) {
                    start += 10;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        return accounts.stream()
                .sorted(Comparator.comparing(GwAccountInfoDTO::getSetupDate).reversed())
                .collect(Collectors.toList());
    }


    private List<CardInfo> queryCardList(String accountNo){
        List<CardInfoItem14003> cardList=new ArrayList<>();
        GwRequestBody14003 requestBody14003 = new GwRequestBody14003();
        requestBody14003.setAcctNo(accountNo);

        GwRequest<GwRequestBody14003> gwRequest = new GwRequest<>("14003", requestBody14003);
        int start = 0;
        while (true) {
            requestBody14003.setFirstrow(String.valueOf(start));
            requestBody14003.setLastrow(String.valueOf(start + 9));
            GwResponse<GwResponseBody14003> response = gwInvokeService.execute(gwRequest, GwResponseBody14003.class);
            if (!response.success()) {
                break;
            }
            cardList.addAll(response.getRespBody().getCards());
            // 还有新数据页
            if ("Y".equals(response.getRespBody().getNextpageFlg())) {
                start += 10;
            } else {
                break;
            }
            if (start > 20) {
                break;
            }
        }

       return cardList.stream()
               .filter(cardInfoItem14003 -> !StringUtils.equalsAny(cardInfoItem14003.getBlockCode(),"R","S","C"))
               .map(this::convert2CardInfo)
               .collect(Collectors.toList());
    }

    private CardInfo convert2CardInfo(CardInfoItem14003 cardInfoItem){
        CardInfo cardInfo=new CardInfo();
        cardInfo.setCardNo(cardInfoItem.getCardNo());
        cardInfo.setProdCd(cardInfoItem.getProductCd());
        cardInfo.setProdName(cardInfoItem.getProductName());
        cardInfo.setCardMsg(returnBlkcdeCardMsg(getFirstBlockCode(cardInfoItem.getBlockCode()),cardInfoItem.getActivateInd()));

        return cardInfo;
    }


    /**
     *  查询通联核心客户号
     * */
    private String getCustId(String idCde){
        GwRequestBody11010 reqParam = new GwRequestBody11010();
        reqParam.setIdNo(idCde);
        reqParam.setIdType("I");
        reqParam.setOpt("0");
        GwRequest<GwRequestBody11010> gwRequest = new GwRequest<>("11010", reqParam);
        GwResponse<GwResponseBody11010> response = gwInvokeService.execute(gwRequest, GwResponseBody11010.class);
        if (response.success()) {
            return response.getRespBody().getCustId();
        }
        return null;
    }


    private String getFirstBlockCode(String blkcdeCard){
        if(StringUtils.isBlank(blkcdeCard)){
            return "";
        }
        if(blkcdeCard.length() > 1){
            blkcdeCard = blkcdeCard.substring(0,1);
        }
        return blkcdeCard;
    }

    private String returnBlkcdeCardMsg(String blkcdeCard,String isSts){
        //未激活状态提示用户去激活
        if(StringUtils.equalsIgnoreCase(isSts,"N")) {
            return CARD_NOT_ACTIVITE_MESSAGE;
        }
        if(StringUtils.isBlank(blkcdeCard) ||
                StringUtils.equalsAnyIgnoreCase(blkcdeCard,"V","Q")){
            return "";
        }

        return CARD_EXCEPTION_MESSAGE;
    }


    private String returnBlkcdeAccountMsg(String blkCdeAccount){
        if(StringUtils.isBlank(blkCdeAccount)
                || StringUtils.equalsAny(blkCdeAccount,"1","2")){
            return "";
        }
        if(accountExceptionList.contains(blkCdeAccount)){
            return ACCOUNT_EXCEPTION_MESSAGE;
        }
        if(blkCdeAccount.matches(accountExpireReg)) {
            return ACCOUNT_EXPIRE_MESSAGE;
        }
        if("M".equalsIgnoreCase(blkCdeAccount)) {
            return ACCOUNT_ACTIVATION_MESSAGE;
        }
        return CARD_EXCEPTION_MESSAGE;//统一兜底，业务要求提示卡片异常
    }

}
