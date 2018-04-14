package xyz.sinrin.vuedemo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.view.InternalResourceViewResolver

/**
 * SpringMVC的配置
 */
@EnableWebMvc
@ComponentScan(basePackages = ["xyz.sinrin.vuedemo.controller"])  // 配置spring扫描的路径,只扫描controller
class SpringMVCConfig : WebMvcConfigurer {

    // 视图解析器
    @Bean
    fun viewResolver(): ViewResolver = InternalResourceViewResolver().apply {
        setPrefix("/WEB-INF/jsp/")
        setSuffix(".jsp")
        setExposeContextBeansAsAttributes(true)
    }

    // 实现父类接口,自动处理静态资源的映射
    override fun configureDefaultServletHandling(configurer: DefaultServletHandlerConfigurer) {
        configurer.enable()
    }
}