package org.zuel.mould.util;

import lombok.Data;

@Data
public class RespPager<T> {

    private BasePager pager;

    private T datas;

}
