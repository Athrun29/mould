package org.zuel.mould.exception;

import lombok.Data;
import org.zuel.mould.constant.RespEnum;

@Data
public class DefinedException extends RuntimeException {


    private static final long serialVersionUID = 3777951420510508243L;

    private Integer code;

    public DefinedException(RespEnum respEnum) {
        super(respEnum.getMsg());
        this.code = respEnum.getCode();
    }
}
