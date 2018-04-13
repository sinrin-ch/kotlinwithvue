import kotlin.test.Test
import kotlin.test.assertEquals

class TestMessage {

    @Test
    fun message(){
        val message: Message = Message("Hello World")
        assertEquals("Hello World",message.msg)
    }
}