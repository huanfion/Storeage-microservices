package com.zegobird.oauth2center.redis;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * PasswordEncoder密码验证clientId的时候会报错，因为5.0新特性中需要在密码前方需要加上{Xxx}来判别。
 * 所以需要自定义一个类，重新BCryptPasswordEncoder的match方法
 * @author huanfion
 * @version 1.0
 * @date 2019/8/9 10:17
 */
public class ZBBCrpytPasswordEncoder extends BCryptPasswordEncoder {
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        PasswordEncoder passwordEncoder= PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String presentedPassword=passwordEncoder.encode(rawPassword);
        return passwordEncoder.matches(rawPassword, presentedPassword);
    }
}
