package xyz.sinrin.vuedemo.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Controller

/**
 * spring核心配置类
 */
@Configuration
// 排除扫描controller
@ComponentScan(basePackages = ["xyz.sinrin.vuedemo"], excludeFilters = [ComponentScan.Filter(classes = [Controller::class])])
open class SpringCoreConfig {
}