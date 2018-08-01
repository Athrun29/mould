package org.zuel.mould.util;

import lombok.Data;

@Data
public class BasePager {

    private int pageNum;

    private int pageSize;

    private long totalNum;
}
