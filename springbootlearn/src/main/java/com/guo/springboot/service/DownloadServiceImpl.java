package com.guo.springboot.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class DownloadServiceImpl implements DownloadService {


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
            zipOutputStream.flush();
        }

        zipOutputStream.close();

        outputStream.flush();
        outputStream.close();
    }

}
