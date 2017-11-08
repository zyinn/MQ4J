package com.sumscope.cdhplus.sumscopemq4j.internal.impl;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sumscope.cdhplus.sumscopemq4j.CreateOptions;

import java.io.IOException;

/**
 * Created by wenshuai.li on 2016/10/10.
 */
public class FanoutSender extends AbstractSender {

    public FanoutSender(Connection connection, Channel channel, CreateOptions createOptions){
        super(connection,channel,createOptions);
    }

    @Override
    public void send(String message) throws IOException {
        reSend(message.getBytes(),createOptions,basicProperties[0]);
    }

    @Override
    public void send(byte[] message) throws IOException {
        reSend(message,createOptions,basicProperties[1]);
    }
}
