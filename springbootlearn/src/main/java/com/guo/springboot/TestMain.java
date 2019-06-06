package com.guo.springboot;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class TestMain {

    static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException {

        short ROW_HEIGHT = 550;
        int CELL_WIDTH = 20 * 256;

        List<Order> orders = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            Order order = new Order();
            order.setOrderName("订单"+i);
            order.setTotalMoney(i);
            order.setId(i);
            List<Goods> goodsList = new ArrayList<>(2);
            for (int j = 0; j < 2; j++) {
                Goods goods = new Goods();
                goods.setCount(j);
                goods.setName("商品"+j);
                goods.setMoney(j);
                goods.setOrderId(i);
                goodsList.add(goods);
            }
            order.setGoods(goodsList);

            orders.add(order);
        }
        System.out.println(JSON.toJSONString(orders));

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setFontName("宋体");
        // 设置为文本格式，防止身份证号变成科学计数法
        DataFormat format = workbook.createDataFormat();

        // 1.初始化 execl sheet
        XSSFSheet spreadsheet = workbook.createSheet("订单信息");

        spreadsheet.setDefaultRowHeight(ROW_HEIGHT);

        XSSFRow row = spreadsheet.createRow(0);
        row.createCell(0).setCellValue("订单名称");
        row.createCell(1).setCellValue("实际金额");
        row.createCell(2).setCellValue("商品名称");
        row.createCell(3).setCellValue("商品数量");
        row.createCell(4).setCellValue("商品金额");

        // 处理合并单元格
        int startIndex = 1;
        int endIndex = 0;
        for (int i = 0, len = orders.size(); i < len; i++) {
            Order orderMainVO = orders.get(i);
            if (CollectionUtils.isNotEmpty(orderMainVO.getGoods())) {
                endIndex = orderMainVO.getGoods().size() + endIndex;
                System.out.println("startIndex"+startIndex+"---endIndex"+endIndex);
                for (int j = 0; j < 2; j++) {
                    spreadsheet.addMergedRegion(new CellRangeAddress(startIndex, endIndex, j, j));
                }
                startIndex = endIndex + 1;
            }
        }

        List<Goods> allGoods = orders.stream().flatMap(e -> e.getGoods().stream()).collect(Collectors.toList());
        for (int i = 1, rowsTotal = allGoods.size(); i <= rowsTotal; i++) {
            Goods goods = allGoods.get(i - 1);
            Order order = orders.stream().filter(e -> e.getId() == goods.getOrderId()).collect(Collectors.toList()).get(0);

            XSSFRow rowtemp = spreadsheet.createRow(i);
            rowtemp.createCell(0).setCellValue(order.getOrderName());
            rowtemp.createCell(1).setCellValue(order.getTotalMoney());
            rowtemp.createCell(2).setCellValue(goods.getName());
            rowtemp.createCell(3).setCellValue(goods.getCount());
            rowtemp.createCell(4).setCellValue(goods.getMoney());

        }

//        //第二行数据
//
//        //row.createCell(0).setCellValue("工作站");//因为和上面的行合并了，所以不用再次 赋值了
//        row2.createCell(1).setCellValue("位置");
//        row2.createCell(2).setCellValue("序号");
//        //row.createCell(3).setCellValue("订单号");//因为和上面的行合并了，所以不用再次 赋值了
//
//        XSSFRow row4 = spreadsheet.createRow(4);
//        row4.createCell(0).setCellValue("工作站");//因为和上面的行合并了，所以不用再次 赋值了
//        row4.createCell(1).setCellValue("位置");
//        row4.createCell(2).setCellValue("序号");
//        row4.createCell(3).setCellValue("订单号");//因为和上面的行合并了，所以不用再次 赋值了

        File file = new File("d:\\test.xlsx");



        OutputStream filterOutputStream = new FileOutputStream(file.getPath());
        workbook.write(filterOutputStream);


//        ExecutorService executor = Executors.newFixedThreadPool(10);
//        for (int i = 0; i < 10; i++) {
//            Runnable runnable = () -> {
//                try {
//                    System.out.println("开始等待执行-----------");
//                    countDownLatch.await();
//                    System.out.println("开始执行-----------");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            };
//            executor.execute(runnable);
//        }
//        System.out.println("还未开始执行------------------");
//        Thread.sleep(2000);
//        countDownLatch.countDown();
//        executor.shutdown();
    }

    public static class Goods {
        private String name;

        private int count;

        private int money;

        private int orderId;

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Order {

        private int id;

        private String orderName;

        private int totalMoney;

        private List<Goods> goods;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrderName() {
            return orderName;
        }

        public void setOrderName(String orderName) {
            this.orderName = orderName;
        }

        public int getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(int totalMoney) {
            this.totalMoney = totalMoney;
        }

        public List<Goods> getGoods() {
            return goods;
        }

        public void setGoods(List<Goods> goods) {
            this.goods = goods;
        }
    }

    public static class TimeWheel {
        // 指向当前槽
        int currentSlotIndex = 0;

        private ScheduledExecutorService scheduledExecutorService;

        public TimeWheel() {
//            scheduledExecutorService = new ScheduledThreadPoolExecutor();
        }

    }
    public static class Task implements Runnable{

        @Override
        public void run() {

        }
    }
}
