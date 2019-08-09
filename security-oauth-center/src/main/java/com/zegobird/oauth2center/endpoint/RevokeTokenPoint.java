package com.zegobird.oauth2center.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 注销token配置
 *
 * @author huanfion
 * @version 1.0
 * @date 2019/8/9 10:23
 */
@FrameworkEndpoint
public class RevokeTokenPoint {
    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @RequestMapping(value = "/oauth/token", method= RequestMethod.DELETE)
    public @ResponseBody  String RemoveToken(String access_token) {
        String msg = "";
        if (consumerTokenServices.revokeToken(access_token)) {
            msg = "注销成功";
        } else {
            msg = "注销失败";
        }
        return msg;
    }
}
