package com.qdbank.mall.model.earlybird.employee;

import lombok.Data;

@Data
public class EmployeeInfoDTO {

    /**员工编号*/
    private String empId;

    /**工号*/
    private String empacctNo;

    /**姓名*/
    private String empNm;

    /**性别代码*/
    private String empsexId;

    /**证件类型代码*/
    private String primcertypedes;

    /**证件号码*/
    private String primceryNo;

    /**移动电话*/
    private String empmobileNo;

    /**员工子状态
     *
     * 00121	离职-正常离职
     * 00117	离岗-下岗
     * 00116	离岗-内退
     * 00115	离岗-长期病假
     * 00114	在职-退休返聘
     * 00111	在职-正常在岗
     * 00112	在职-借调
     * 00123	离职-死亡
     * 00122	离职-退休
     * */
    private String empsubstsId;

    /**员工子状态描述*/
    private String empsubstsdes;

    /**员工类型
     * 2	正式员工
     * 5	外包员工
     * 8	正式员工-试用期社招
     * 7	正式员工-试用期校招
     * 4	派遣员工
     * 6	其他
     * 3	劳务员工
     * */
    private String empclassId;

    /**员工类型描述*/
    private String empclassdes;

}
