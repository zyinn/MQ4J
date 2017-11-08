package com.sumscope.cdhplus.sumscopemq4j.internal.impl;

import com.rabbitmq.client.Connection;
import com.sumscope.cdhplus.sumscopemq4j.CreateOptions;
import com.sumscope.cdhplus.sumscopemq4j.internal.MqReceiverCallback;
import com.sumscope.cdhplus.sumscopemq4j.internal.Receiver;
import com.sumscope.cdhplus.sumscopemq4j.internal.Sender;

import java.io.IOException;

/**
 * Created by wenshuai.li on 2016/9/28.
 */
public class Queue extends AbstractComponent {

    public Queue(Connection connection,CreateOptions createOptions) throws IOException{
        super(connection,createOptions);
        queueDeclare();
    }

    @Override
    public Sender createSender() throws IOException{
        return new QueueSender(connection,channel,createOptions);
    }

    @Override
    public Receiver createReceiver(MqReceiverCallback mqReceiverCallback) throws IOException {
        return new RunnableReceiver(connection,channel,mqReceiverCallback,createOptions);
    }
}
