package xyz.sinrin.vuedemo

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer
import xyz.sinrin.vuedemo.config.SpringCoreConfig
import xyz.sinrin.vuedemo.config.SpringMVCConfig

/**
 * WebApp的启动类,初始化DispatherServlet，代替在web.xml中到DispatherServlet配置,
 * Created by sinrin on 2018/4/14.
 */
class WebAppInitializer : AbstractAnnotationConfigDispatcherServletInitializer() {
    //在这里配置父容器,给ContextListener使用
    override fun getRootConfigClasses(): Array<Class<*>> {
        return arrayOf(SpringCoreConfig::class.java)
    }

    //这里配置的是DispatcherServlet的捕获路径,所有匹配该路径的访问
    //都将被HandlerMapping进行映射处理到具体Controller的方法上.
    override fun getServletMappings(): Array<String> {
        return arrayOf("/")
    }

    //在这里配置子容器,给DispatchServlet使用
    override fun getServletConfigClasses(): Array<Class<*>> {
        return arrayOf(SpringMVCConfig::class.java)
    }
}