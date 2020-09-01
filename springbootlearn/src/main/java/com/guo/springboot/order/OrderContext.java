package com.guo.springboot.order;

import java.util.List;

/**
 * @Date: 2020/9/1 15:06
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class OrderContext extends AbstractWrapContext {

    private String orderName;

    @Override
    public Object getContent() {
        return this.content;
    }

    @Override
    public void setContent(Object content) {
        this.content = content;
    }
}
