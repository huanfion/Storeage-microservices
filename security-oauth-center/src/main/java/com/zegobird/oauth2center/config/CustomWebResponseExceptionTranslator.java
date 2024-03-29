package com.zegobird.oauth2center.config;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * 自定义异常处理类
 * @author huanfion
 * @version 1.0
 * @date 2019/8/13 15:05
 */
@Component("customWebResponseExceptionTranslator")
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator{
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        OAuth2Exception oAuth2Exception= (OAuth2Exception) e;
        return ResponseEntity.status(oAuth2Exception.getHttpErrorCode())
                .body(new CustomOauthException(oAuth2Exception.getMessage()));
    }
}
