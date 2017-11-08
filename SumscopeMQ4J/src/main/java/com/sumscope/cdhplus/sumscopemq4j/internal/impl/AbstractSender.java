package com.sumscope.cdhplus.sumscopemq4j.internal.impl;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.sumscope.cdhplus.sumscopemq4j.CreateOptions;
import com.sumscope.cdhplus.sumscopemq4j.internal.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by wenshuai.li on 2016/9/28.
 */
public abstract class AbstractSender implements Sender {
    public final static Logger senderLog = LoggerFactory.getLogger("senderLog");

    protected Connection connection;
    protected Channel channel;
    protected CreateOptions createOptions;

    protected AMQP.BasicProperties[] basicProperties = new AMQP.BasicProperties[2];

    protected AbstractSender(Connection connection,Channel channel, CreateOptions createOptions){
        this.connection = connection;
        this.channel = channel;
        this.createOptions = createOptions;
        if(createOptions.isDurable()){
            basicProperties[0] = MessageProperties.PERSISTENT_TEXT_PLAIN;
            basicProperties[1] = MessageProperties.PERSISTENT_BASIC;
        }else {
            basicProperties[0] = MessageProperties.TEXT_PLAIN;
            basicProperties[1] = MessageProperties.BASIC;
        }
    }

    protected void reSend(byte[] message, CreateOptions createOptions, AMQP.BasicProperties basicProperties) throws IOException {
        int reSendTimes = createOptions.getReSendTimes();
        while (reSendTimes >= 0){
            reSendTimes--;
            try {
                if(CreateOptions.SenderType.FANOUT == createOptions.getSenderType()){
                    channel.basicPublish(createOptions.getExchangeName(), "", basicProperties, message);
                }else if(CreateOptions.SenderType.QUEUE == createOptions.getSenderType()){
                    channel.basicPublish("", createOptions.getQueueName(), basicProperties, message);
                }else if(CreateOptions.SenderType.TOPIC == createOptions.getSenderType()){
                    channel.basicPublish(createOptions.getExchangeName(), createOptions.getRoutingKey(), basicProperties, message);
                }
                break;
            }catch (IOException e){
                senderLog.debug("reSendTimes:" + reSendTimes + " \t " + e.getMessage());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                if(reSendTimes < 0){
                    throw e;
                }
            }
        }
    }

    @Override
    public abstract void send(String message) throws IOException;

    @Override
    public abstract void send(byte[] message) throws IOException;

    @Override
    public void close() {
        try {
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
