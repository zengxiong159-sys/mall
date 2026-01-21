import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

public class Vivo_WxTest {
	
	public static void main(String[] args)throws Exception{
	 

		String url="http://103.254.77.25:8080/tariffe/wx/charge/wxChargeOrderProcess.do";
	//	String url="http://tel.wxdigital.cn:8181/tariffe/wx/charge/wxChargeOrderProcess.do";


		long date = System.currentTimeMillis();
		String  appid = "2018092228550";//
		String  reqno = "9PyvRGEZFKBpuTUFmajNbPxlgPZcAEi6";//
		String  timestamp = "1591605603222";
		String  signnonce = "9PyvRGEZFKBpuTUFmajNbPxlgPZcAEi6";//
		String  type = "1";
		String  sign = "";
		String  productid = "CU10";
		String  phone = "18669757858"; // 
		String  extcontent = "";
		String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJxeT/TOnma3fnPxumPGIkFW7kqxGXAApPXVjKKYD4Dn+57HZrV6XqOuS1JkVL8fMw1I8KdyuwpjvjV/f6VCsXiMjLUT+DVakAad0anUqoWQXY67IokPSvOShen2pW8seMAe27f6MFY2sjHU41YNjzJZRFHh9Ql87iEaXVBx5pmtAgMBAAECgYBzS07lEFvXHMDOOJPX8UVcq/4zcHVzEeaPpI93a2sy/tmWbywZ70pMobzD2VprOVPpHbjjnkKvHHAo/gNeFNjJBLxrVjN4zMbGRtR5xwCcLPNkgCS5EOLG3Wf8360aogk/JvjsSGe3r+OoY86MiynbqVZxoJ7xfRqRGWh2n08sgQJBAMjsnvPc4FLVUkOE3/mRaOemwf3u/PPs4vPxLRcjwU5hkOd4rk6F6Cb75XBIBmN+ZtxNv+xDxU0s9di3+wKAZv0CQQDHOxbCA39TkyVFonNbMlrg78EDIDF+2antEZAbxj3YTla55G9e3nF0PGUKigncsyWODaRpXz7NwXQ3wHq+UPRxAkEAohunM4Hly28pbChsvOLBDQXSRprno7ZgdgkEJENI14CIdPk9SUVoxOs/dpPl86gsPVrNmkYjjdzUjgICl0YZ0QJBAJiqG69BlNDK4XWxnOmV9XOF5IXrpmHIkuB5Y88UopFcvjIl7H1qwM9L9DUWP6ZhlHZ8165y8KIkClUqZiPtztECQF0OF0yNLY5Ajg03O7eZ6LJwIg99kRmkFunOzeBd6bnQRA/OFKpLrC2NDWbLqZPQ+4Ajq3jQQe+FOOTjPcfYbxo=";

		String  orderid = "order1120"+ System.currentTimeMillis()+ (int)((Math.random()*9+1)*100);


		Map<String,String> signMap = new HashMap<String,String>();
        signMap.put("app_id",appid);
        signMap.put("req_no",reqno);
        signMap.put("timestamp",timestamp);
        signMap.put("sign_nonce",signnonce);
        signMap.put("product_id",productid);
        signMap.put("phone",phone);
        signMap.put("type",type);
        signMap.put("kh_order_id",orderid);
        if(StringUtils.isNotEmpty(extcontent)){
        	signMap.put("ext_content",extcontent);
        }
        String signStr = getParamsByAscending(signMap);
        signStr = signStr.substring(0,signStr.length()-1);
        System.out.println(signStr);
		sign = RSASignature.sign(signStr,privateKey, "UTF-8");
		System.out.println(sign);

	
		
		mzChargeOrderProcess(url,appid,reqno,timestamp,signnonce,sign,type,productid,phone,orderid,extcontent);


}



	public static void mzChargeOrderProcess(String url,String appid,String reqno,String timestamp,String signnonce,String sign,String type,String productid,String phone,String orderid,String extcontent){
		HashMap<Object, Object> he = new HashMap<Object, Object>();
		he.put("app_id", appid);
		he.put("req_no", reqno);
		he.put("timestamp",timestamp);
		he.put("sign_nonce", signnonce);
		he.put("sign", sign);
		HashMap<Object, Object> bo = new HashMap<Object, Object>();
		bo.put("product_id",productid);
		bo.put("phone",phone);
		bo.put("type",type);
        bo.put("kh_order_id",orderid);
        bo.put("ext_content",extcontent);
		System.out.println(sign);
		HashMap<Object, Object> headerbody = new HashMap<Object, Object>();
		headerbody.put("body", bo);
		headerbody.put("header", he);
		JSONObject se = JSONObject.fromObject(headerbody);
		System.out.println(se);
		String request = se.toString();
							
		System.out.print("请求的url为"+url);
		System.out.print("请求的参数为"+request);
		String response=null;
		try {
	//		response = HttpRequest.sendPostJson(url, request);//post方法自己封装或者模拟下边的post方法
			System.out.println(response);
			response = response.toString();

			JSONObject jo = JSONObject.fromObject(response);
			//得到Header每个参数信息
			String header = jo.getString("header");
			//得到Body每个参数信息
			String body = jo.getString("body");
			JSONObject joheader = JSONObject.fromObject(header);
			JSONObject jobody = JSONObject.fromObject(body);
			//解析Header每个参数信息
			String  appids = joheader.getString("app_id");
			String  reqnos = joheader.getString("req_no");
			String  timestamps = joheader.getString("timestamp");
			String  signnonces = joheader.getString("sign_nonce");
			String  signs = joheader.getString("sign");
			//解析Body每个参数参数信息
			String  code = jobody.getString("code");
			String  msg = jobody.getString("msg");
			String  order_id = jobody.getString("order_id");
			
			long date = System.currentTimeMillis();
			Map<String,String> mzSignMap = new HashMap<String,String>();
			mzSignMap.put("app_id",appids);
			mzSignMap.put("req_no",reqnos);
			mzSignMap.put("timestamp",timestamps);
			mzSignMap.put("sign_nonce",signnonces);
			
			mzSignMap.put("code", code);
			if(StringUtils.isNotEmpty(msg)){
				mzSignMap.put("msg", msg);
			}
			if(StringUtils.isNotEmpty(order_id)){
				mzSignMap.put("order_id",order_id);
			}
		  	String mzSignStr = getParamsByAscending(mzSignMap);
		  	mzSignStr = mzSignStr.substring(0,mzSignStr.length()-1);
		    System.out.println("|mzSignStr"+mzSignStr);
		    String publickey1="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCehNnXO+XeZvjUHLSjp6cuylvZo0P68ucuuRFiVIWVOUaf7NfP8G+zTzQfA0UO1GtjtO/btCTKiuM2+/6FkwmxgnmdsQ0QBcyZ3DgPoIdM7rwdIuKkLWgoFvkwzRV6CMp26rvTuLx8lZi7+OWDA96JXbCGf4UEzqORglXTvCfPNQIDAQAB";
		    boolean signq = RSASignature.doCheck(mzSignStr, signs, publickey1, "UTF-8");
			
		    if(signq == true){
		    	System.out.println("验签通过");
		    }

			
			System.out.print("请求报文为"+response);
		}catch (Exception e) {
			System.out.println("请注意，当做异常订单处理，不要当做提交失败处理-------订单提交异常");
			e.printStackTrace();
		}
	}
	
	
	public static String getParamsByAscending(Map<String, String> params) {// 升序
		List<Entry<String, String>> entryList = new ArrayList<Entry<String, String>>(
				params.entrySet());
		Collections.sort(entryList,
				Comparator.comparing(Entry::getKey));
		StringBuffer param = new StringBuffer();
		for (Entry<String, String> entry : entryList) {
			param.append(entry.getKey()).append("=").append(entry.getValue());
			param.append("&");
		}
		return param.toString();
	}
	
	
	//发送请求
	public static String post(String url, String requestContext,Map<String, String> headers, String charSet) {
		// 创建默认的httpClient实例.    
        CloseableHttpClient httpClient = HttpClients.createDefault();  
		StringEntity stringEntity = new StringEntity(requestContext, charSet);
		stringEntity.setContentEncoding(charSet);
		String httpStr = null;
		CloseableHttpResponse response = null;
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(stringEntity);
		Header[] heads = new BasicHeader[headers.size()];
		int i = 0;
		for (String str : headers.keySet()) {
			heads[i] = new BasicHeader(str, headers.get(str));
			i++;
		}
		httpPost.setHeaders(heads);
		try {
			try {
				response = httpClient.execute(httpPost);
			} catch (Exception e) {
				e.printStackTrace();
			}
			HttpEntity entity = response.getEntity();
			try {
				httpStr = EntityUtils.toString(entity, charSet);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}finally {
			if (response != null) {
				try {
					//释放资源
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}finally {
					//关闭CloseableHttpResponse
					try {
						response.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			httpPost.releaseConnection();
			//关闭HttpClient
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return httpStr;
	
	}
	
	
	
	// 将Map转换成JSON
			public static String map2json(Object object) {
				JSONObject jsonObject = JSONObject.fromObject(object);
				return jsonObject.toString();
			}
			
			/***
			 * 将对象转换为HashMap
			 * 
			 * @param object
			 * @return
			 */
			public static HashMap toHashMap(Object object) {
				HashMap<String, Object> data = new HashMap<String, Object>();
				JSONObject jsonObject = JSONObject.fromObject(object);
				Iterator it = jsonObject.keys();
				while (it.hasNext()) {
					String key = String.valueOf(it.next());
					Object value = jsonObject.get(key);
					data.put(key, value);
				}

				return data;
			}		
			/* MD5加密：
			* 将utf-8格式的content字符串进行MD5加密
			* 步骤：1：UTF-8格式的字符串 content
			* 步骤：2：步骤1的 content 获取字节流（也是UTF-8的字节流）
			* 步骤：3：步骤2的字节流进行 MD5
			* 步骤：4：步骤3的 md5流封装成 UTF-8格式的字符串 result
			     */
			    public static String md5(String content) throws SecurityException {
			        String result = null;
			        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			                'a', 'b', 'c', 'd', 'e', 'f'};
			        try {
			            MessageDigest md = MessageDigest.getInstance("MD5");
			            md.update(content.getBytes("UTF-8"));

			            byte tmp[] = md.digest();
			            char str[] = new char[16 * 2];
			            int k = 0;
			            for (int i = 0; i < 16; i++) {
			                byte byte0 = tmp[i];
			                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
			                str[k++] = hexDigits[byte0 & 0xf];
			            }
			            result = new String(str);
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
			        return result;
			    }
			
			
}