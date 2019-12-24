package com.tim.mall.admin.common;





public class WebResult {

    private Integer code;
    private String msg;

    private static final Integer SUCCESSCODE=1;
    private static final String SUCCESS="请求成功";


    private static final int error=2;
    private static final String errormsg="更新失败";


    private static final int UNKNOWNCODE=1001;
    private static final String UNKNOWN="未知异常";

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public WebResult(String msg) {
        this.msg = msg;
    }

    public
    WebResult(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public static WebResult success(){
        return new WebResult(SUCCESSCODE,SUCCESS);
    }

    public static WebResult error(String msg){
        return new WebResult(error,msg);
    }

    public static WebResult unknown(){
        return new WebResult(UNKNOWNCODE,UNKNOWN);
    }







}
