package com.guo.springboot.controller;

import com.guo.springboot.pay.ThirdyPay;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * Created by Enzo Cotter on 2018/6/26.
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @Resource(name = "thirdyPay")
    private ThirdyPay thirdyPay;

    @RequestMapping(value = "/login")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("/index");
        mv.addObject("payUrl", "test");

        return mv;
    }

    @RequestMapping(value = "/pay")
    public ModelAndView pay() throws UnsupportedEncodingException {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("/index");
        mv.addObject("payUrl", thirdyPay.aliPay(null));

        return mv;
    }

    @RequestMapping(value = "/paysuccess")
    public ModelAndView paySuccess() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("/success");
        return mv;
    }


    @RequestMapping(value = "notify")
    public String payNotify(String trade_no, String out_trade_no) {
        System.out.println("trade_no---------------"+ trade_no);
        System.out.println("out_trade_no--------------"+ out_trade_no);
        return "ok";
    }
}
