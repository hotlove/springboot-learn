package com.guo.springboot.order;

/**
 * @Date: 2020/9/2 11:36
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class HandlerConfig {
    // 普通顺序执行
    public static final int EXECUTE_SERIAL = 0;

    // 并行执行
    public static final int EXECUTE_PARALLEL = 1;

    // handler执行类型
    private int executeType = 0;

    // handler名称
    private String handlerName;

    public HandlerConfig(){};

    public HandlerConfig(int executeType) {
        this(executeType, null);
    }

    public HandlerConfig(int executeType, String name) {
        this.executeType = executeType;
        this.handlerName = name;
    }

    public int getExecuteType() {
        return executeType;
    }

    public void setExecuteType(int executeType) {
        this.executeType = executeType;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }
}
