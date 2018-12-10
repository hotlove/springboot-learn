package com.guo.springboot.pay;

import java.io.UnsupportedEncodingException;

/**
 * @Auther: hotlove_linx
 * @Date: 2018/9/15 14:08
 * @Description:
 */
public interface ThirdyPay {

    String aliPay(String money) throws UnsupportedEncodingException;
}
