package com.guo.springboot.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * @Auther: hotlove_linx
 * @Date: 2018/9/15 13:52
 * @Description:
 */
@Service("thirdyPay")
public class AaliPayServiceImpl implements ThirdyPay{


    @Override
    public String aliPay(String money) throws UnsupportedEncodingException {
        String serverUrl = "https://openapi.alipaydev.com/gateway.do";
        String appId = "2016091300503955"; //  2018062860434427
        String privateKey = "";
        String format = "json";
        String charset = "utf-8";
        String publickey = "";
        String signType = "RSA2";
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, publickey, signType);

        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();

        request.setReturnUrl("http://mkinjd.natappfree.cc/index/paysuccess");
        request.setNotifyUrl("http://mkinjd.natappfree.cc/index/notify");

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = System.currentTimeMillis()+"";
        //付款金额，必填
        String total_amount = "88.88";
        //订单名称，必填
        String subject = "Iphone6 16G";
        //商品描述，可空
        String body = "Iphone6 16G";
        request.setBizContent("{" +
                "    \"out_trade_no\":\"20150320010101001\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":0.01," +
                "    \"subject\":\"Iphone6 16G\"," +
                "    \"body\":\"Iphone6 16G\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088102175488822\"" +
                "    }"+
                "  }");//填充业务参数

        AlipayTradePagePayResponse response = null;
        try {
            System.out.println(request.toString());
            response = alipayClient.pageExecute(request);
            System.out.println(response.getBody());
            System.out.println(response.getTradeNo());

            return response.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }
}
