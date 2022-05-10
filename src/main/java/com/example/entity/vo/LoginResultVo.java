package com.example.entity.vo;


import com.fasterxml.jackson.annotation.JsonInclude;

// 为了过滤不正常的输出（当输出为空的时候不输出）
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResultVo {
    private int code;
    private String info;

    public LoginResultVo() {

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public LoginResultVo(int code, String info) {
        this.code = code;
        this.info = info;
    }
}
