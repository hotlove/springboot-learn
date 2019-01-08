package com.guo.springboot.controller;

import com.google.zxing.WriterException;
import com.guo.springboot.pay.ThirdyPay;
import com.guo.springboot.service.DownloadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Enzo Cotter on 2018/6/26.
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @Resource(name = "thirdyPay")
    private ThirdyPay thirdyPay;

    @Resource
    private DownloadService downloadService;

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

    @RequestMapping(value = "/testdown")
    public void testdown(HttpServletRequest request, HttpServletResponse response) throws IOException, WriterException {
        downloadService.download(request, response, null);
    }
}
