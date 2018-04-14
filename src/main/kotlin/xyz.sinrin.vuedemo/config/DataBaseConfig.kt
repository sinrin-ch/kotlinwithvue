package xyz.sinrin.vuedemo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.annotation.Resource
import javax.sql.DataSource

/**
 * 配置数据库
 * Created by sinrin on 2018/4/14.
 */
@Configuration
@PropertySource(value = ["classpath:/jdbc.properties"], encoding = "utf-8")
open class DataBaseConfig {

    @Resource
    private lateinit var env: Environment

    /**
     * 数据源
     */
    @Bean
    open fun dataSource(): DataSource {
        val url = env["jdbc.url"]
        val username = env["jdbc.username"]
        val password = env["jdbc.password"]
        val driver = env["jdbc.driver"]
        return DriverManagerDataSource(url, username, password)
                .apply { setDriverClassName(driver) }
    }

    @Bean
    open fun jdbcTemplate(dataSource: DataSource) = JdbcTemplate(dataSource)
}