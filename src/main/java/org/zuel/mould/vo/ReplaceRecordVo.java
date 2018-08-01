package org.zuel.mould.vo;

import lombok.Data;

@Data
public class ReplaceRecordVo {
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

    private String createTime;
}
