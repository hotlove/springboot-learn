package com.guo.springboot;

import com.alibaba.fastjson.JSON;
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
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class TestMain {

    public static void main(String[] args) throws IOException {

        short ROW_HEIGHT = 550;
        int CELL_WIDTH = 20 * 256;

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setFontName("宋体");
        // 设置为文本格式，防止身份证号变成科学计数法
        DataFormat format = workbook.createDataFormat();

        // 1.初始化 execl sheet
        XSSFSheet spreadsheet = workbook.createSheet("订单信息");

        spreadsheet.setDefaultRowHeight(ROW_HEIGHT);
        spreadsheet.addMergedRegion(new CellRangeAddress(0, 3, 0, 0));
        spreadsheet.addMergedRegion(new CellRangeAddress(0, 3, 3, 3));
        spreadsheet.addMergedRegion(new CellRangeAddress(0, 3, 4, 5));

        XSSFRow row = spreadsheet.createRow(0);
        row.createCell(0).setCellValue("工作站");
        row.createCell(1).setCellValue("位置");
        row.createCell(2).setCellValue("序号");
        row.createCell(3).setCellValue("订单号");
        row.createCell(4).setCellValue("成品号/型号");


        //第二行数据
        XSSFRow row2 = spreadsheet.createRow(1);
        //row.createCell(0).setCellValue("工作站");//因为和上面的行合并了，所以不用再次 赋值了
        row2.createCell(1).setCellValue("位置");
        row2.createCell(2).setCellValue("序号");
        //row.createCell(3).setCellValue("订单号");//因为和上面的行合并了，所以不用再次 赋值了

        XSSFRow row4 = spreadsheet.createRow(4);
        row4.createCell(0).setCellValue("工作站");//因为和上面的行合并了，所以不用再次 赋值了
        row4.createCell(1).setCellValue("位置");
        row4.createCell(2).setCellValue("序号");
        row4.createCell(3).setCellValue("订单号");//因为和上面的行合并了，所以不用再次 赋值了

        File file = new File("d:\\test.xlsx");



        OutputStream filterOutputStream = new FileOutputStream(file.getPath());
        workbook.write(filterOutputStream);

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
