package com.example.administrator.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/1/7 0007.
 */

public class Mess {
    private String msg;
    private List<CityInfo> result;
    private String retCode;

    public Mess() {
    }

    public Mess(String msg, List<CityInfo> result, String retCode) {
        this.msg = msg;
        this.result = result;
        this.retCode = retCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<CityInfo> getResult() {
        return result;
    }

    public void setResult(List<CityInfo> result) {
        this.result = result;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }
}
