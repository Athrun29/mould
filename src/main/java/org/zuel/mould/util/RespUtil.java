package org.zuel.mould.util;

import org.zuel.mould.constant.RespEnum;

public class RespUtil {

    public static RespMsg success(){
        return success(null);
    }

    public static RespMsg success(Object object){
        return success(RespEnum.SUCCESS.getMsg(), object);
    }

    public static RespMsg success(String msg, Object object) {
        RespMsg respMsg = new RespMsg();
        respMsg.setCode(RespEnum.SUCCESS.getCode());
        respMsg.setMsg(msg);
        respMsg.setSuccess(1);
        respMsg.setData(object);
        return respMsg;
    }

    public static RespMsg error(Integer code, String msg){
        RespMsg respMsg = new RespMsg();
        respMsg.setCode(code);
        respMsg.setMsg(msg);
        respMsg.setSuccess(0);
        return respMsg;
    }
}
