package xyz.sinrin.vuedemo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import javax.annotation.Resource

/**
 * 配置数据库
 * Created by sinrin on 2018/4/14.
 */
@Configuration
@PropertySource(value = ["classpath:/jdbc.properties"], encoding = "utf-8")
open class DataBaseConfig {

    @Resource
    private lateinit var env:Environment

    @Bean
    open fun driver():String = env["jdbc.driver"]
}