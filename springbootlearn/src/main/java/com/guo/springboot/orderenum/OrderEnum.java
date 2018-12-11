package com.guo.springboot.orderenum;

/**
 * @author: hotlove_linx
 * @date: 2018-12-11 22:27
 * @description: ${description}
 **/
public enum OrderEnum {

    /**
     * APPROVE, COMMIT, UNPAY 就相当于是new OrderEnum的实例
     *
     *
     */
    APPROVE(1), COMMIT(2), UNPAY(3);

    private int value;

    private OrderEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
