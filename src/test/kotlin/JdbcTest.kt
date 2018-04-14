import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.queryForList
import org.springframework.jdbc.core.queryForObject
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
class JdbcTest {

    @Resource
    private lateinit var jdbcTemplate: JdbcTemplate

    @Test
    fun selectTest() {
        val sql = "SELECT plant_name FROM iop.common.plant where id = 1"
        val name = this.jdbcTemplate.queryForObject<String>(sql)
        assertEquals("东莞HDI", name)
//        jdbcTemplate.dataSource.connection.close()
    }
}