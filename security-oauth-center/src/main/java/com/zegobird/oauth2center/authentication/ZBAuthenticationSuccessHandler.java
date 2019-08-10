package com.zegobird.oauth2center.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zegobird.oauth2center.properties.LoginType;
import com.zegobird.oauth2center.properties.SecurityPorperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/8/7 11:58
 */
@Component("zbAuthenticationSuccessHandler")
public class ZBAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SecurityPorperties securityPorperties;
    @Autowired
    private ObjectMapper objectMapper;
    //private static final  String DEFAULT_CSRF_TOKEN_ATTR_NAME= HttpSessionCsrfTokenRepository.class.getName().concat(".CSRF_TOKEN");
    private RequestCache requestCache = new HttpSessionRequestCache();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        logger.info("登录成功");
        SavedRequest savedRequest = this.requestCache.getRequest(request, response);

        if(LoginType.JSON.equals(securityPorperties.getBrowser().getLoginType())){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        }
        else{
            super.onAuthenticationSuccess(request,response,authentication);
        }

//        String targetUrl = "/user";
//        this.logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
//        this.getRedirectStrategy().sendRedirect(request, response, targetUrl);
//        if (savedRequest == null) {
//            super.onAuthenticationSuccess(request, response, authentication);
//        } else {
//            String targetUrlParameter = this.getTargetUrlParameter();
//            if (!this.isAlwaysUseDefaultTargetUrl() && (targetUrlParameter == null || !StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
//                this.clearAuthenticationAttributes(request);
//                String targetUrl = "/user";
//                this.logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
//                this.getRedirectStrategy().sendRedirect(request, response, targetUrl);
//            } else {
//                this.requestCache.removeRequest(request, response);
//                super.onAuthenticationSuccess(request, response, authentication);
//            }
//        }
    }
}
