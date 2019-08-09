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
@EnableAuthorizationServer
public class ZBAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
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
    //为什么自动注入就会报错呢
//    @Autowired
//    @Qualifier("userDetaillServiceImpl")
//   private UserDetailsService userDetailsService;


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
        security.tokenKeyAccess("permitAll()");
        security.checkTokenAccess("isAuthenticated()");
        security.allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(jdbcClientDetails());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(redisTokenStore())
              .userDetailsService(userDetailsService())
              .authenticationManager(authenticationManager);
        endpoints.tokenServices(defaultTokenServices());
    }
}
