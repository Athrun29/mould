package org.zuel.mould.bean;

import lombok.Data;

@Data
public class BaseDic {
    private Long id;

    private String code;

    private String name;

    private Long parent;

    private String remark;
}