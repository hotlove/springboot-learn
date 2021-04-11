package com.guo.springboot.timewheel;

/**
 * @Date: 2021/1/21 16:40
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class TimerTaskList {
    private TimerTask head;
    private TimerTask tail;

    public TimerTaskList() {
        head = new TimerTask();
        tail = new TimerTask();

        head.next = tail;
        tail.prev = head;
    }

    public void addNext(TimerTask nextNode) {
        TimerTask prevNode = this.tail.prev;
        prevNode.next = nextNode;
        nextNode.next = tail;
        nextNode.prev = prevNode;
        tail.prev = nextNode;
    }
}
