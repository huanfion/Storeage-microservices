package com.zegobird.oauth2center.properties;

import lombok.Data;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/8/7 15:33
 */
@Data
public class BrowserProperties {
    private  String loginPage="/login.html";
    private LoginType loginType=LoginType.JSON;
}
