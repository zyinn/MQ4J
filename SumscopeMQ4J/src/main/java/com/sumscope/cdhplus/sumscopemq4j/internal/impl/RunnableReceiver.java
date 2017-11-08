package com.sumscope.cdhplus.sumscopemq4j.internal.impl;

import com.rabbitmq.client.*;
import com.sumscope.cdhplus.sumscopemq4j.CreateOptions;
import com.sumscope.cdhplus.sumscopemq4j.internal.MqReceiverCallback;
import com.sumscope.cdhplus.sumscopemq4j.internal.Receiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by wenshuai.li on 2016/9/28.
 */

public class RunnableReceiver implements Receiver,Runnable{

    public final static Logger receiverLog = LoggerFactory.getLogger("receiverLog");

    private Connection connection;
    private Channel channel;
    private MqReceiverCallback mqReceiverCallback;
    private CreateOptions createOptions;
    private Thread currentThread = null;

    public RunnableReceiver(Connection connection, Channel channel, MqReceiverCallback mqReceiverCallback, CreateOptions createOptions) throws IOException {
        this.connection = connection;
        this.channel = channel;
        this.mqReceiverCallback = mqReceiverCallback;
        this.createOptions = createOptions;
        this.channel.basicQos(1);
    }

    @Override
    public void receive() {//自动开启一个接收线程，每次接到一条数据，回调mqReceiverCallback
        currentThread = new Thread(this);
        currentThread.start();
    }

    @Override
    public void stop() {
        try{
            currentThread.interrupt();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close();
        }
    }

    @Override
    public void run() {


        try {
            DefaultConsumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag,
                                           Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body) throws IOException {
                    boolean success = false;
                    try{
                        String contentType = properties.getContentType();

                        if(CreateOptions.MessageType.TEXT.value().equals(contentType)){
                            String message = new String(body);
                            success = mqReceiverCallback.processString(message);
                        }else {
                            byte[] message = body;
                            success = mqReceiverCallback.processBytes(message);
                        }

                    }catch (Exception e){
                        success = false;
                        e.printStackTrace();
                    }finally {
                        if(!createOptions.isAutoAck()){
                            if(success){
                                channel.basicAck(envelope.getDeliveryTag(), false);
                            }else{
                                //如果走到这里createOptions.isAckRequeue() == true,回调函数会重新拿到前一次的消息，
                                //如果回调函数中有bug，就会造成死循环。请调用者自行评估风险。
                                channel.basicNack(envelope.getDeliveryTag(), false,createOptions.isAckRequeue());
                            }
                        }
                    }

                }
            };
            channel.basicConsume(createOptions.getQueueName(), createOptions.isAutoAck(),consumer);
        } catch (Exception e) {
            receiverLog.info("RunnableReceiver.error");
            //TODO handler the exception
            e.printStackTrace();
        }
    }

    private void close(){
        try {
            connection.close();
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
