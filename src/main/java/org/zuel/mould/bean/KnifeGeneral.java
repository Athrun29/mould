package org.zuel.mould.bean;

import lombok.Data;

import java.util.Date;

@Data
public class KnifeGeneral {
    private Long id;

    private String code;

    private String name;

    private Double dia;

    private Double rad;

    private Double len;

    private Integer isDel;

    private String remark;

    private Date timestamp;
}