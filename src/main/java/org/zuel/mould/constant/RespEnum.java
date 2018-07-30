package org.zuel.mould.constant;

public enum RespEnum {

    SUCCESS(200, "请求成功"),
    LONGIN_ERROR(401,"登录认证异常"),
    NOT_FOUND(404,"未找到服务"),
    SERVER_ERROR(500,"服务异常"),
    PATH_ERROR(500,"文件路径");

    private Integer code;
    private String msg;

    private RespEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
