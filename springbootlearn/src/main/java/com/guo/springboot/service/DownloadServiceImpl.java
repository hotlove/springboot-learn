package com.guo.springboot.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class DownloadServiceImpl implements DownloadService {


    /**
     * 将不同得文件打包压缩下载
     * @param httpServletRequest
     * @param httpServletResponse
     * @param data
     * @throws IOException
     * @throws WriterException
     */
    @Override
    public void download(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String data) throws IOException, WriterException {
        httpServletResponse.setContentType("application/zip");
        httpServletResponse.setHeader("Content-disposition",
                "attachment; filename=" + new String(("二维码列表").getBytes(),"ISO-8859-1") + ".zip");

        OutputStream outputStream = httpServletResponse.getOutputStream();

        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);


        for (int i = 0; i < 10 ; i++) {
            BitMatrix bitMatrix = new MultiFormatWriter().encode("http://www.test.vip/dowlaod/giftcard/code_r="+i, BarcodeFormat.QR_CODE, 900, 900);
            BufferedImage buffImg = MatrixToImageWriter.toBufferedImage(bitMatrix);

            ZipEntry entry = new ZipEntry("qrcode"+i + ".png");
            zipOutputStream.putNextEntry(entry);

            ImageIO.write(buffImg, "png", zipOutputStream);
        }

        XSSFWorkbook xssfWorkbook = this.getXsl();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        xssfWorkbook.write(byteArrayOutputStream);

        InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        String fileName = "test.xlsx";
        zipOutputStream.putNextEntry(new ZipEntry(fileName));

        byte[] buffer = new byte[5*1024];
        int length = 0;
        while ((length = inputStream.read(buffer)) != -1) {
            zipOutputStream.write(buffer,0,length);
        }

        zipOutputStream.flush();
        zipOutputStream.close();
        outputStream.flush();
        outputStream.close();
    }

    private XSSFWorkbook getXsl() {
        int totalSize = 5;

        short ROW_HEIGHT = 550;
        int CELL_WIDTH = 20 * 256;

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

        return workbook;
    }

}
