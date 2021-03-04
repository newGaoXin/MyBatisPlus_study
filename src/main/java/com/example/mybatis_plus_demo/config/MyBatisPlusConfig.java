package com.example.mybatis_plus_demo.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * MyBatis-Plus 配置类
 */
@Configuration
@MapperScan("com.example.mybatis_plus_demo.mapper")
public class MyBatisPlusConfig {

    /**
     * 实现乐观锁
     *
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor getOptimisticLockerInnerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 实现分页
     * @return
     */
    @Bean
    public PaginationInterceptor getPaginationInterceptor(){
        return new PaginationInterceptor();
    }

    /**
     * 实现逻辑删除
     * @return
     */
    @Bean
    public ISqlInjector getISqlInjector(){
        return new LogicSqlInjector();
    }

    /**
     * 实现性能分析查询
     * @return
     */
    @Bean
    @Profile({"dev","test"})
    public PerformanceInterceptor getPerformanceInterceptor(){
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(100L);    //
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }
}
