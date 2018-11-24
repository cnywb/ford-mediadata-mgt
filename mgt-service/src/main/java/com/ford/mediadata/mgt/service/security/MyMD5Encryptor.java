package com.ford.mediadata.mgt.service.security;

import org.springframework.stereotype.Component;

/**
 * Created by yangkui on 15/10/30.
 */
@Component(value = "myMD5Encryptord5")
public class MyMD5Encryptor extends io.dabing.core.web.encrypt.encryptor.MD5Encryptor {
    //密码盐值
    private static final String MD5_SALT="ysqwFzRv0Zzq";
    @Override
    public String digest(String msg) {
        return super.digest(msg+MD5_SALT);
    }

    public static void main(String[] args) {
        MyMD5Encryptor encryptor  = new MyMD5Encryptor();
        System.out.println(encryptor.digest("123456"));//4e1d1518b61ec19d272f7a02523cd60c

    }
}
