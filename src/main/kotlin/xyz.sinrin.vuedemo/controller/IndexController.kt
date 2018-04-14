package xyz.sinrin.vuedemo.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

/**
 * Created by sinrin on 2018/4/14.
 */
@Controller
class IndexController {


    @GetMapping("/")
    fun index(): ModelAndView {
        return ModelAndView("hello").apply {
        }
    }


}