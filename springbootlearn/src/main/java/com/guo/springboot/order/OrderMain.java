package com.guo.springboot.order;

/**
 * @Date: 2020/9/1 15:35
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class OrderMain {

    private String createdByName;

    private String orderNo;

    private Integer createdById;

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public Integer getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Integer createdById) {
        this.createdById = createdById;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String toString() {
        return orderNo + "-" +createdByName;
    }
}
