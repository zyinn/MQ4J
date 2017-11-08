package com.sumscope.cdhplus.sumscopemq4j.internal;

/**
 * Created by wenshuai.li on 2016/9/28.
 * 命令模式，调用者自己实现
 */
public interface MqReceiverCallback {
    boolean processString(String message);
    boolean processBytes(byte[] message);
}
