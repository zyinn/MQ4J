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
public class Topic extends AbstractComponent {

    public Topic(Connection connection,CreateOptions createOptions) throws IOException{
        super(connection,createOptions);
        channel.exchangeDeclare(createOptions.getExchangeName(), CreateOptions.SenderType.TOPIC.value()
                ,createOptions.isDurable(),createOptions.isAutoDelete(),null);
    }
    @Override
    public Sender createSender() throws IOException {
        queueDeclare();
        return new TopicSender(connection,channel,createOptions);
    }

    @Override
    public Receiver createReceiver(MqReceiverCallback mqReceiverCallback) throws IOException {
        String queueName = queueDeclare().getQueue();
        channel.queueBind(queueName, createOptions.getExchangeName(), createOptions.getRoutingKey());
        channel.basicQos(1);//公平调度
        return new RunnableReceiver(connection,channel,mqReceiverCallback,createOptions);
    }

}
