package com.guo.springboot.service;

import com.guo.springboot.dao.ProfileMapper;
import com.guo.springboot.domain.Profile;
import com.guo.springboot.order.*;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Date: 2020/9/1 15:28
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class OrderBaseInfoWraperHandler extends SimpleWrapHandler<OrderContext>{
    @Override
    public void handler0(OrderContext ctx) {

    }

//    @Override
//    public void handler(AbstractWrapContext ctx) {
//
//        ProfileMapper profileMapper = (ProfileMapper) AppContext.getBean("profileMapper");
//
//        if (ctx instanceof OrderContext) {
//            OrderContext orderContext = (OrderContext) ctx;
//            List<OrderMain> content = (List<OrderMain>) orderContext.content;
//            content.forEach(e -> {
//                Profile profile = profileMapper.selectByPrimaryKey(e.getCreatedById());
//                e.setCreatedByName(profile.getUsername());
//            });
//        }
//    }
}
