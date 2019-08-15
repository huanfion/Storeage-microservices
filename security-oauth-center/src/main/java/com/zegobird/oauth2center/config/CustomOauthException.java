package com.zegobird.oauth2center.config;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * https://blog.csdn.net/dandandeshangni/article/details/80472147
 * 自定义异常类，指定json 序列化方式
 * @author huanfion
 * @version 1.0
 * @date 2019/8/13 15:17
 */
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {
    public CustomOauthException(String msg){
        super(msg);
    }
}
