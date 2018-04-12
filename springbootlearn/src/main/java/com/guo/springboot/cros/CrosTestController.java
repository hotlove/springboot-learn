package com.guo.springboot.cros;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CrosTestController {

    /**
     * 细粒度的控制 跨域问题
     * @return
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping("/croshello")
    public String helloWorld() {
        return "hello world";
    }
}
