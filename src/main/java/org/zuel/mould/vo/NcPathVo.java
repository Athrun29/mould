package org.zuel.mould.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class NcPathVo implements Serializable {

    private String inputPath;

    private String outputPath;
}
