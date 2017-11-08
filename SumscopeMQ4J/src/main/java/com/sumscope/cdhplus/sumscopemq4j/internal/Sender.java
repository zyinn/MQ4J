package com.sumscope.cdhplus.sumscopemq4j.internal;

import java.io.IOException;

/**
 * Created by wenshuai.li on 2016/9/28.
 */
public interface Sender {
    void send(String message) throws IOException;
    void send(byte[] message) throws IOException;
    void close();
}
