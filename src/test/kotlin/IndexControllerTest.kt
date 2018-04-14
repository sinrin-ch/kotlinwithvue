import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.support.WebApplicationContextUtils
import xyz.sinrin.vuedemo.config.DataBaseConfig
import xyz.sinrin.vuedemo.config.SpringCoreConfig
import xyz.sinrin.vuedemo.config.SpringMVCConfig
import javax.annotation.Resource
import kotlin.test.assertEquals

/**
 * Created by sinrin on 2018/4/14.
 */
@RunWith(SpringJUnit4ClassRunner::class)
// 加载spring核心配置和springmvc配置
@ContextConfiguration(classes = [SpringMVCConfig::class, SpringCoreConfig::class])
// 这个不能去掉..
@WebAppConfiguration
class IndexControllerTest {


    private lateinit var mockMvc: MockMvc

    @Resource
    private lateinit var webApplicationContext: WebApplicationContext

    @Before
    fun init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
    }

    @Test
    fun getMessageTest() {
        val mvcResult: MvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/message")
                .accept(MediaType.TEXT_PLAIN))
                .andDo { println(it.response.contentAsString) }.andReturn()
        val result: String = mvcResult.response.contentAsString
        assertEquals(result, "com.microsoft.sqlserver.jdbc.SQLServerDriver")
    }
}