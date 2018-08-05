package com.guo.springboot.controller;

import com.guo.springboot.dao.TimeTaskMapper;
import com.guo.springboot.domain.Profile;
import com.guo.springboot.domain.TimeTask;
import com.guo.springboot.domain.TimeTaskExample;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/fastjson")
public class FastjsonController {

    private volatile int currentSlotIndex = 0;

    @Resource
    private TimeTaskMapper timeTaskMapper;

    @RequestMapping("/profile")
    @ResponseBody
    public Profile getProfile() {
        Profile profile = new Profile();

        profile.setId(12);
        profile.setUsername("test");
        profile.setPassword("test");

        return profile;
    }

    @RequestMapping("/time")
    public String timeWheel() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(100);

        scheduledExecutorService.scheduleAtFixedRate(() -> {

            // 1.查询到slot中的任务 然后全部取消
            TimeTaskExample example = new TimeTaskExample();
            example.createCriteria().andSlotIndexEqualTo(currentSlotIndex);

            List<TimeTask> list = timeTaskMapper.selectByExample(example);
            if (list != null && !list.isEmpty()) {
                new Runnable() {
                    @Override
                    public void run() {
                        // 批量取消订单状态
                        System.out.println("取消订单");
                    }
                };
            }

            // 2.currentSlotIndex ++
            currentSlotIndex++;
            System.out.println("currentSlotIndex::" + currentSlotIndex);
            if (currentSlotIndex == 29) {
                // 说明一圈结束了
                currentSlotIndex = 0;
            }

        }, 0, 1, TimeUnit.MINUTES);

        return "hello, world";
    }

    // 时间论问题：
    // 1.添加订单时往数据库中添加一条待取消订单信息，主要的时currentSlotIndex-1
    // 2.如果再定时任务执行时（也即客户再30分钟的时候付款）这时候数据就会出问题，批量任务取消订单 但是用户开始付款了
    // 解决：在下单付款前进行检测订单是否已取消，另外在定时任务开始处理时也需要检测 订单是否已经付款了，如果已经付款则不让取消
    // 3.支付时耗时的，如果在这个期间用户付款了状态还没有修改，可是这个时间到了，需要取消订单。
    // 解决：在下单拿到支付信息后，就设置slot_set表状态为支付中状态 这个方案不是很好（考虑到获取支付码后不付款）
}
