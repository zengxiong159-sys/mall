package com.qdbank.mall.model.earlybird.employee;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeQueryInfo implements Serializable {

    private String idNo;
    private String mobile;
}
