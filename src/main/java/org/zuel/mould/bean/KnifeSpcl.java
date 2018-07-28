package org.zuel.mould.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class KnifeSpcl {
    private Long id;

    private String code;

    private String name;

    private BigDecimal dia;

    private BigDecimal rad;

    private BigDecimal len;

    private Integer isDel;

    private String remark;

    private Date timestamp;
}