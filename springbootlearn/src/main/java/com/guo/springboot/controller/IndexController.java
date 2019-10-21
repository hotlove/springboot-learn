package com.guo.springboot.controller;

import com.google.zxing.WriterException;
import com.guo.springboot.pay.ThirdyPay;
import com.guo.springboot.service.DownloadService;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

    @RequestMapping(value = "/down")
    public void down(HttpServletRequest request, HttpServletResponse response) throws IOException, WriterException {

        String mimetype = "application/octet-stream";
        response.setContentType(mimetype);
        String downFileName = "gift_card.zip";
        String inlineType = "attachment"; // 是否内联附件
        response.setHeader("Content-Disposition", inlineType + ";filename=\"" + downFileName + "\"");


        int totalSize = 5;

        short ROW_HEIGHT = 550;
        int CELL_WIDTH = 20 * 256;

        ByteOutputStream byteOutputStream = new ByteOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();
        // Create a new font and alter it.
        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setFontName("宋体");
        // 设置为文本格式，防止身份证号变成科学计数法
        DataFormat format = workbook.createDataFormat();

        // 普通单元格格式
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setAlignment(CellStyle.ALIGN_CENTER);// 水平居中
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
        style.setWrapText(true);
        style.setDataFormat(format.getFormat("@"));

        // 注释单元格格式
        XSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFont(font);
        style2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
        style2.setWrapText(true);
        style2.setDataFormat(format.getFormat("@"));

        // 标题格式
        XSSFCellStyle titleStyle = workbook.createCellStyle();
        XSSFFont titleFont = workbook.createFont();
        titleFont.setFontHeightInPoints((short) 12);
        titleFont.setFontName("宋体");
        titleFont.setColor(HSSFColor.WHITE.index);
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(CellStyle.ALIGN_CENTER);// 水平居中
        titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
        titleStyle.setWrapText(true);
        titleStyle.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
        titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        titleStyle.setDataFormat(format.getFormat("@"));

        XSSFSheet spreadsheet = workbook.createSheet("test");
        for (int i = 0; i < totalSize; i++) {
            spreadsheet.setColumnWidth(i, CELL_WIDTH);
            spreadsheet.setDefaultColumnStyle(i, style);
        }

        spreadsheet.setDefaultRowHeight(ROW_HEIGHT);
        // 设置标题
        XSSFRow row = spreadsheet.createRow((short) 0);
        row.setHeight((short) (ROW_HEIGHT * 2));
        row.setHeight(ROW_HEIGHT);
        XSSFCell cell = (XSSFCell) row.createCell((short) 0);
        XSSFCell cell1 = (XSSFCell) row.createCell((short) 1);
        XSSFCell cell2 = (XSSFCell) row.createCell((short) 2);
        XSSFCell cell3 = (XSSFCell) row.createCell((short) 3);
        XSSFCell cell4 = (XSSFCell) row.createCell((short) 4);
        XSSFCell cell5 = (XSSFCell) row.createCell((short) 5);

        cell.setCellValue("名称");
        cell1.setCellValue("批次号");
        cell2.setCellValue("卡号");
        cell3.setCellValue("密码");
        cell4.setCellValue("卡面值");
        cell5.setCellValue("二维码链接");

        cell.setCellStyle(titleStyle);
        cell1.setCellStyle(titleStyle);
        cell2.setCellStyle(titleStyle);
        cell3.setCellStyle(titleStyle);
        cell4.setCellStyle(titleStyle);
        cell5.setCellStyle(titleStyle);


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);

        InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());


        OutputStream out = response.getOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(out);
        String fileName = "test.xlsx";
        zipOutputStream.putNextEntry(new ZipEntry(fileName));
        byte[] buffer = new byte[5*1024];
        int length = 0;
        while ((length = inputStream.read(buffer)) != -1) {
            zipOutputStream.write(buffer,0,length);
        }

        inputStream.close();
        // 关闭输出流
        zipOutputStream.flush();
        zipOutputStream.close();
    }
}
