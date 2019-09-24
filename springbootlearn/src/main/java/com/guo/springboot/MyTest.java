package com.guo.springboot;

import com.alibaba.fastjson.JSON;

import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class MyTest {

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("12.45");

        System.out.println(bigDecimal.setScale(2, BigDecimal.ROUND_UP));
//        System.out.println("".equals(""));
//        List<M> mList = new ArrayList<>(4);
//
//        M m1 = new M();
//        m1.setA(2l);
//        m1.setB(1l);
//        m1.setC(0l);
//
//        m1.setD("小麦");
//        m1.setE("优质");
//        m1.setF("基地1");
//        mList.add(m1);
//
//        M m2 = new M();
//        m2.setA(2l);
//        m2.setB(1l);
//        m2.setC(0l);
//
//        m2.setD("小麦");
//        m2.setE("优质");
//        m2.setF("基地3");
//        mList.add(m2);
//
//        M m3 = new M();
//        m3.setA(2l);
//        m3.setB(1l);
//        m3.setC(0l);
//
//        m3.setD("小麦");
//        m3.setE("优质");
//        m3.setF("基地3");
//        mList.add(m3);
//
//        M m4 = new M();
//        m4.setA(3l);
//        m4.setB(1l);
//        m4.setC(3l);
//
//        m4.setD("大葱");
//        m4.setE("优质");
//        m4.setF("基地3");
//        mList.add(m4);
//
//        // 根据基地分组
//        Map<String, List<M>> fMap = mList.stream().collect(Collectors.groupingBy(e -> e.getF()));
//
//        List<M> countList = new ArrayList<>();
//        fMap.forEach((fk, fv) -> {
//
//            // 根据品种分组
//            Map<String, List<M>> eMap = fv.stream().collect(Collectors.groupingBy(e -> e.getE()));
//
//            eMap.forEach((ek, ev) -> {
//
//                // 根据种类分组
//                Map<String, List<M>> dMap = ev.stream().collect(Collectors.groupingBy(e -> e.getD()));
//
//                dMap.forEach((dk, dv) -> {
//
//                    // 开始计算
//                    System.out.println(dk);
//                    System.out.println(JSON.toJSONString(dv));
//                    double avalue = dv.stream().mapToDouble(e -> e.getA()).sum();
//                    double bvalue = dv.stream().mapToDouble(e -> e.getB()).sum();
//
//                    double cvalue = avalue - bvalue;
//
//                    M m = new M();
//                    m.setD(dk);
//                    m.setE(ek);
//                    m.setF(fk);
//                    m.setC(cvalue);
//                    countList.add(m);
//                });
//
//            });
//        });
//
//        System.out.println("--------------------------------------------------------");
//        countList.forEach(e -> {
//            System.out.println(JSON.toJSONString(e));
//        });


    }


    static class M {
        private double a;
        private double b;
        private double c;
        private String d;
        private String e;
        private String f;

        public double getA() {
            return a;
        }

        public void setA(double a) {
            this.a = a;
        }

        public double getB() {
            return b;
        }

        public void setB(double b) {
            this.b = b;
        }

        public double getC() {
            return c;
        }

        public void setC(double c) {
            this.c = c;
        }

        public String getD() {
            return d;
        }

        public void setD(String d) {
            this.d = d;
        }

        public String getE() {
            return e;
        }

        public void setE(String e) {
            this.e = e;
        }

        public String getF() {
            return f;
        }

        public void setF(String f) {
            this.f = f;
        }
    }
}


