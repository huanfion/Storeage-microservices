package com.zegobird.oauth2center.config;

import com.zegobird.oauth2center.properties.SecurityProperties;
import com.zegobird.oauth2center.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/8/9 9:33
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ZBWebSecurityConfig extends WebSecurityConfigurerAdapter {

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
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/favor.ioc","/user/login","/static/**","/user/index");//static下的资源以及登录页不需要验证
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
//                .defaultSuccessUrl("/user/success",true)
//                .failureForwardUrl("/user/error")
                .successHandler(zbAuthenticationSuccessHandler)
                .failureHandler(zbAuthenticationFailureHandler)
                .and()
                .authorizeRequests()//定义哪些url需要被保护，哪些不需要保护
                //.withObjectPostProcessor()//自定义处理
                .antMatchers("/authentication/require",securityProperties.getBrowser().getLoginPage(),"/user/login","/user/success","/user/error").permitAll()
                .antMatchers("/authentication/form").permitAll()
                .antMatchers("/oauth/token" , "oauth/check_token").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
//            http.authorizeRequests().anyRequest().permitAll();
    }
}
