package com.exception;

public class ResultException extends RuntimeException{

    //状态码
    private int code;
    public ResultException(int code, String msg){
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
