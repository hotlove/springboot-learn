package com.guo.springboot.service;

import com.google.zxing.WriterException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface DownloadService {
    void download(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String data) throws IOException, WriterException;

}
