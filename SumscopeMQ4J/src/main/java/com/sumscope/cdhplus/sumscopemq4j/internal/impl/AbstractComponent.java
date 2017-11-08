package com.sumscope.cdhplus.sumscopemq4j.internal.impl;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sumscope.cdhplus.sumscopemq4j.CreateOptions;
import com.sumscope.cdhplus.sumscopemq4j.internal.Component;
import com.sumscope.cdhplus.sumscopemq4j.internal.MqReceiverCallback;
import com.sumscope.cdhplus.sumscopemq4j.internal.Receiver;
import com.sumscope.cdhplus.sumscopemq4j.internal.Sender;

import java.io.IOException;

/**
 * Created by wenshuai.li on 2016/9/28.
 */
public abstract class AbstractComponent implements Component {

    protected Connection connection;
    protected CreateOptions createOptions;
    protected Channel channel = null;

    protected AbstractComponent(Connection connection,CreateOptions createOptions) throws IOException {
        this.connection = connection;
        this.createOptions = createOptions;
        this.channel = connection.createChannel();
        this.channel.addShutdownListener(new ShutdownListenerHandler());
    }

    protected AMQP.Queue.DeclareOk queueDeclare() throws IOException{
        if(createOptions.getQueueName() == null){
            return channel.queueDeclare();
        }else {
            return channel.queueDeclare(createOptions.getQueueName(), createOptions.isDurable(),
                    createOptions.isExclusive(), createOptions.isAutoDelete(), null);
        }

    }
    @Override
    public abstract Sender createSender() throws IOException;

    @Override
    public abstract Receiver createReceiver(MqReceiverCallback mqReceiverCallback) throws IOException;
}
