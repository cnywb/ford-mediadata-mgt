package io.dabing.shiro.controller;

/**
 * Created by yangkui on 15/10/29.
 */
public class LoginVo {
    private String userName;
    private String password;
    private String validCode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }
}
