/*
 * Copyright (c)  2016, dabing.io
 * All rights reserved.
 * OSSUtil.java 2016-03-25 下午2:10
 */

package io.dabing.common.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;

import java.io.ByteArrayInputStream;

/**
 * 描述:阿里云OSS文件上传工具类
 *
 * @author yangkui create on 2016-03-25.
 * @since 1.0
 */
public class OSSUtil {
    public static final String SCHEMA = "http://";
    public static final String ENDPOINT="oss-cn-hangzhou.aliyuncs.com";
    public static final String ACCESSKEYID="dZHQBYITNzOJADS3";
    public static final String ACCESSKEYSECRET="01X2E8NLHPI2tMTj0qv1WII09nNzVI";
    public static final String BUCKET = "cd2016";

    public static void main(String[] args) {
        OSSClient client = new OSSClient(SCHEMA+ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET);
        for (Bucket bkt : client.listBuckets()) {
            System.out.println(" - " + bkt.getName());
        }
        client.shutdown();
    }

    /**
     * 文件上传,并返回url地址
     * @param file 文件字节流
     * @param key 文件名称
     * @return 外网访问URL
     */
    public static String put(byte[] file,String key){
        OSSClient client = new OSSClient(SCHEMA+ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET);
        client.putObject(BUCKET,key, new ByteArrayInputStream(file));
        client.shutdown();
        return SCHEMA+BUCKET+"."+ENDPOINT+"/"+key;
    }

}