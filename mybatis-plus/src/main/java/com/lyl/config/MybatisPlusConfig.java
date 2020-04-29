package com.lyl.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MybatisPlusConfig
 *
 * @author liyl
 * @date 2020-4-11
 */
// @MapperScan 扫描Mapper生效
@MapperScan("com.lyl.mapper")
@EnableTransactionManagement
@Configuration //配置类
public class MybatisPlusConfig {

    // 注册乐观锁的插件
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    // 分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }

    // 逻辑删除 3.1.1版本不需要配置
    // @Bean
    // public ISqlInjector sqlInjector() {
    //     return new LogicSqlInjector();
    // }

    /**
     * SQL执行效率插件 3.2.0以上移除该功能
     */
    // @Bean
    // @Profile({"dev","test"}) // 设置 dev test 环境开启
    // public PerformanceInterceptor performanceInterceptor() {
    //     return new PerformanceInterceptor();
    // }

}

