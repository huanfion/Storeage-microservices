package com.zegobird.oauth2center.config;

import com.zegobird.oauth2center.redis.ZBRedisTokenStore;
import com.zegobird.oauth2center.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

import javax.sql.DataSource;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/8/8 19:12
 */
@Configuration
@EnableAuthorizationServer //提供/oauth/authorize,/oauth/token,/oauth/check_token,/oauth/confirm_access,/oauth/error
public class ZBAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    /**
     * 注入authenticationManager 来支持password grant type
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    @Primary //spring boot 默认有个配置，使用了@Bean，又配置了，所以这里要使用Primary 指定这个是主配置。
    @ConfigurationProperties(prefix = "spring.datasource")//指定数据源，否则会有冲突
    public DataSource dataSource() {
        //配置数据源
        return DataSourceBuilder.create().build();
    }

    @Bean
    public ClientDetailsService jdbcClientDetails() {
        //基于jdbc实现，需要事先在数据库配置客户端信息
        return new JdbcClientDetailsService(dataSource());
    }

    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Bean
    @Primary
    public ZBRedisTokenStore redisTokenStore() {
        return new ZBRedisTokenStore(connectionFactory);
    }

    @Autowired
    private CustomWebResponseExceptionTranslator customWebResponseExceptionTranslator;

    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(redisTokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(jdbcClientDetails());
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 12); // token有效期自定义设置，默认12小时
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);//默认30天，这里修改
        return tokenServices;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()");//开启/oauth/token_key 验证端口无权限访问
        security.checkTokenAccess("isAuthenticated()");
        security.allowFormAuthenticationForClients();//允许表单登录
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(jdbcClientDetails());
    }

    /**
     * 用来配置授权（authorizatio）以及令牌（token）的访问端点和令牌服务   核心配置  在启动时就会进行配置
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(redisTokenStore())
                .userDetailsService(userDetailsService())
                .authenticationManager(authenticationManager);//开启密码授权模式
        endpoints.tokenServices(defaultTokenServices());
        //自定义登录异常信息 未起作用？？
        endpoints.exceptionTranslator(customWebResponseExceptionTranslator);
    }
}
