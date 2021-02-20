package com.guo.springboot;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2021/2/20 12:05
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class MatTest {
    public static void main(String[] args) {
        List<Mat> matList = new ArrayList<>();
        int count = 0;
        while (true) {
            matList.add(new Mat("name"+count++));
        }
    }
}

class Mat {
    byte [] padding = new byte[1024*1024];
    public String name;

    public Mat(String name) {
        this.name = name;
    }
}
