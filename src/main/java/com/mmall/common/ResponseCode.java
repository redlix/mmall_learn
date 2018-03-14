package com.mmall.common;

/**
 * @author redli
 * @time
 * @description: 枚举类型声明返回值,响应的扩展枚举类
 *
 */
public enum ResponseCode {

    /**
     * 0代表SUCCESS
     * 1代表ERROR
     * 10代表需要登录
     */
    SUCCESS(0, "SUCCESS"),
    ERROR(1, "ERROR"),
    NEED_LOGIN(10, "NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT");

    private final int code;
    private final String desc;

    /**
    * 构造器
    */
    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /*
    * 将构造器开放，以供调用
    * */
    public int getCode(){
        return code;
    }

    public String getDesc(){
        return desc;
    }
}
