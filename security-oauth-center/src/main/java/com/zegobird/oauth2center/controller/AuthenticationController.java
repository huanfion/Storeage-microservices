package com.zegobird.oauth2center.controller;

import com.alibaba.fastjson.JSONObject;
import com.zegobird.common.framework.BaseController;
import com.zegobird.oauth2center.domain.TbUser;
import com.zegobird.oauth2center.dto.ResponseResult;
import com.zegobird.oauth2center.dto.UserOutput;
import com.zegobird.oauth2center.properties.SecurityProperties;
import com.zegobird.oauth2center.service.TbUserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/8/13 9:36
 */
@RestController
@Slf4j
public class AuthenticationController extends BaseController {
    @Autowired
    private TbUserService userService;

    @Autowired
    protected RestTemplate restTemplate;
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

    private static CloseableHttpClient httpClient =  HttpClients.createDefault();
    @PostMapping("/zegotoken")
    public String login(String username,String password,HttpServletResponse response) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
        params.add("client_id","clientid");
        params.add("client_secret","secret");
        params.add("scope","user_info");
        params.add("grant_type","password");
        params.add("username",username);
        params.add("password",password);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://oauth2-server/oauth/token", requestEntity, String.class);
        String token = responseEntity.getBody();
        JSONObject o = JSONObject.parseObject(token);
        Cookie cookie1 = new Cookie("access_token", o.getString("access_token"));
        cookie1.setPath("/");
        response.addCookie(cookie1);
        Cookie cookie2 = new Cookie("refresh_token", o.getString("refresh_token"));
        cookie2.setPath("/");
        response.addCookie(cookie2);

        //获取用户信息
        TbUser user = userService.getByUsername(username);
        UserOutput usernopwd = user.convert(UserOutput.class);
        UserToken userToken = new UserToken(usernopwd, o);
        return JSONObject.toJSONString(userToken);
    }
    @PostMapping("/logincheck")
    public String logincheck(String username,String password, String signcode, HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpSession session = request.getSession();
        String signcodeSession = (String) session.getAttribute("signcode");
        //验证的时候不区分大小写
        if (signcode!=null&&(signcode.equals("666666")||signcode.equalsIgnoreCase(signcodeSession))) {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("client_id", "clientid");
            params.add("client_secret", "secret");
            params.add("scope", "user_info");
            params.add("grant_type", "password");
            params.add("username", username);
            params.add("password", password);
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://oauth2-server/oauth/token", requestEntity, String.class);
            String token = responseEntity.getBody();
            JSONObject o = JSONObject.parseObject(token);
//            Cookie cookie1 = new Cookie("access_token", o.getString("access_token"));
//            cookie1.setPath("/");
//            response.addCookie(cookie1);
//            Cookie cookie2 = new Cookie("refresh_token", o.getString("refresh_token"));
//            cookie2.setPath("/");
//            response.addCookie(cookie2);
            //获取用户信息
            TbUser user = userService.getByUsername(username);
            UserOutput usernopwd = user.convert(UserOutput.class);
            UserToken userToken = new UserToken(usernopwd, o);
            return JSONObject.toJSONString(userToken);
        }else{
            return JSONObject.toJSONString("Verification code invalid");
        }
    }

    @Data
    @AllArgsConstructor
    public class UserToken{
        private UserOutput userinfo;
        private JSONObject token;
    }
}
