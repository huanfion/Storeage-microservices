package com.zegobird.oauth2center.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 用于统一管理项目中所有由yml或properties文件传入的变量值
 * @author huanfion
 * @version 1.0
 * @date 2019/8/7 15:26
 */
@ConfigurationProperties(prefix = "zegobird.security")
@Data
@Component
public class SecurityProperties {
    /**
     * 浏览器环境配置
     */
    private BrowserProperties browser = new BrowserProperties();
}
