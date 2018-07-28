package org.zuel.mould.util;

import lombok.Data;

@Data
public class RespMsg<T> {
    Integer success;
    Integer code;
    String msg;
    T data;
}
