package org.zuel.mould.util;

import lombok.Data;

@Data
public class ReqPager<T> {

    private BasePager pager;

    private T queryModel;
}
