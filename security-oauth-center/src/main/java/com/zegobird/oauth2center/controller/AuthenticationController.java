package com.zegobird.oauth2center.controller;

import com.zegobird.oauth2center.dto.ResponseResult;
import com.zegobird.oauth2center.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/8/13 9:36
 */
@RestController
@Slf4j
public class AuthenticationController {
    //用户在访问我们的项目时,如果需要身份认证,spring-security会根据
    //我在SecurityConfig中loginPage的配置跳转到我自定义的url即/authentication/require,
    //但在这个跳转之前spring-security会将我们的请求缓存到RequestCache的session里,
    //通过该类可以从session中再将缓存的请求信息拿出来
    private RequestCache requestCache = new HttpSessionRequestCache();

    //spring-security提供的类,可以方便的进行重定向
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    //如果用户输入以.html结尾的url时,跳转到从配置文件yml或properties里拿出配置的登陆页面
    @Autowired
    private SecurityProperties securityProperties;
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ResponseResult<String> requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if(savedRequest!=null){
            String targetUrl=savedRequest.getRedirectUrl();
            log.info("引发跳转的url是："+targetUrl);
            //if(StringUtils.endsWithIgnoreCase(targetUrl,".html")){
            redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
            //}
        }
        return new ResponseResult<String>(HttpStatus.UNAUTHORIZED.value(),"访问的服务需要身份认证，请引导用户到登录页",null);
    }
}
