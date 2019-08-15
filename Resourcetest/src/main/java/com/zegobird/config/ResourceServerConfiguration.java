package com.zegobird.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.web.cors.CorsConfiguration;

import javax.servlet.http.HttpServletResponse;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/8/9 11:43
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    // 改资源服务器id必须在数据库记录中有配置，也就是对应token的用户必须该资源访问权限(密文：test_resource_secret)
    //例如，我的数据库记录：clientid	test_resource_id	$2a$10$Hf4DeNPAdcLSrGE.PZ5aK.e5qbWS1t2CyuC45DwiXOV1tBOKIGQL6	user_info	authorization_code,refresh_token	http://www.baidu.com	''	3600	7200	''	true
    private static final String DEMO_RESOURCE_ID = "test_resource_id";

    /**
     * 以代码形式配置资源服务器id，配置文件配置不生效
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/add/**").hasAuthority("CarAlarmInsert")
        .antMatchers("/user").hasAuthority("SystemUser")
        .antMatchers("/user/add").hasAuthority("SystemUserMyInsert")
        .antMatchers("/user/current").hasAuthority("SystemUserView");
        http
//                .exceptionHandling()
//                .authenticationEntryPoint(((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED)))
//                .and()
                .csrf().disable()
                //配置跨域
                .cors().configurationSource(httpServletRequest -> {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.addAllowedHeader("*");
            corsConfiguration.addAllowedOrigin(httpServletRequest.getHeader("Origin"));
            corsConfiguration.addAllowedMethod("*");
            corsConfiguration.setAllowCredentials(true);
            corsConfiguration.setMaxAge(3600L);
            return corsConfiguration;
        });
    }
}
