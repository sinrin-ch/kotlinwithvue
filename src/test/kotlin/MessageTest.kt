import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import xyz.sinrin.vuedemo.config.SpringCoreConfig
import javax.annotation.Resource
import kotlin.test.assertEquals

/**
 * Created by sinrin on 2018/4/14.
 */
@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(classes = [SpringCoreConfig::class])
class MessageTest {

    @Resource(name = "driver")
    private lateinit var message: String

    @Test
    fun messageTest(){
        assertEquals(this.message,"com.microsoft.sqlserver.jdbc.SQLServerDriver")
    }
}