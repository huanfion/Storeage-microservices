package com.zegobird.servicehi.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 分页参数
 * @author huanfion
 * @version 1.0
 * @date 2019/7/29 15:37
 */
@Configuration
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // paginationInterceptor.setLimit(你的最大单页限制数量，默认 500 条，小于 0 如 -1 不受限制);
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        // 开启 count 的 join 优化,只针对 left join !!!
        return paginationInterceptor;
    }
}
