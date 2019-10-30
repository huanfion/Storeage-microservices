package com.zegobird.oauth2center.config;

import com.zegobird.oauth2center.authentication.filter.LindTokenAuthenticationFilter;
import com.zegobird.oauth2center.properties.SecurityProperties;
import com.zegobird.oauth2center.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/8/9 9:33
 */
@Configuration
@EnableWebSecurity
@Order(2)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ZBWebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 不拦截的路径
     */
    public static final List<String> IGNORE_PATHS;
    static {
        IGNORE_PATHS = new ArrayList<>();
        IGNORE_PATHS.add("/v2/api-docs");
        IGNORE_PATHS.add("/swagger-ui.html");
        IGNORE_PATHS.add("/swagger-resources/**");
        IGNORE_PATHS.add("/webjars/**");
        IGNORE_PATHS.add("/actuator/**");
    }
   @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler zbAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler zbAuthenticationFailureHandler;

    /**
     * token过滤器.
     */
    @Autowired
    LindTokenAuthenticationFilter lindTokenAuthenticationFilter;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //,"/role/**","/user/**","/menu/**","/company/**","/permission/**"
        web.ignoring().antMatchers("/zegotoken","/logincheck","/favor.ioc","/static/**","/employee/**","/vericode/**");//static下的资源以及登录页不需要验证
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 需要配置这个支持password模式
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
        @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
                .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")//和自定义页面的post路径一致
                .defaultSuccessUrl("/user/success",true)
                .failureForwardUrl("/user/error")
                .successHandler(zbAuthenticationSuccessHandler)
                .failureHandler(zbAuthenticationFailureHandler)
                .and()
                .authorizeRequests()//定义哪些url需要被保护，哪些不需要保护
                .antMatchers("/authentication/require",securityProperties.getBrowser().getLoginPage(),"/login","/success","/error").permitAll()
                .antMatchers("/authentication/form").permitAll()
                .antMatchers("/oauth/token" , "oauth/check_token").permitAll()
                .antMatchers(IGNORE_PATHS.toArray(new String[IGNORE_PATHS.size()])).permitAll()
                .anyRequest().authenticated()
                    .and()
                    .addFilterBefore(lindTokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                    .csrf().disable();
    }
}
