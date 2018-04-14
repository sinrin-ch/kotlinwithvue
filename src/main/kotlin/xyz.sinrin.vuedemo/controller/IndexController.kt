package xyz.sinrin.vuedemo.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
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

    @GetMapping("/a")
    fun a() = "a/index"


    @GetMapping("/b")
    fun b() = "b/index"

    @GetMapping("/msg")
    @ResponseBody
    fun msg() = ResponseEntity<String>("哈哈哈", HttpStatus.OK)
}