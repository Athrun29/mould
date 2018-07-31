package org.zuel.mould.bean;

import lombok.Data;

import java.util.Date;

@Data
public class ReplaceRecord {
    private Long id;

    private String srcName;

    private Double srcDia;

    private Double srcRad;

    private Double srcLen;

    private String tarName;

    private Double tarDia;

    private Double tarRad;

    private Double tarLen;

    private String remark;

    private Date createTime;
}