package com.sumscope.cdhplus.sumscopemq4j;

import com.sumscope.cdhplus.sumscopemq4j.internal.MqReceiverCallback;
import com.sumscope.cdhplus.sumscopemq4j.internal.Receiver;
import com.sumscope.cdhplus.sumscopemq4j.internal.Sender;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by wenshuai.li on 2016/9/28.
 */
public class ReceiverTest {
    /*CreateOptions createOptions = null;
    MqReceiverCallback mqReceiverCallback = null;

    @Before
    public void init(){
        System.out.println("init");
        createOptions = new CreateOptions();
        createOptions.setHost("172.16.73.102");
        createOptions.setPort(5672);

        mqReceiverCallback = new MqReceiverCallback(){

            @Override
            public boolean processString(String message) {
                System.out.println("messagemessage==" + message);
                return true;
            }

            @Override
            public boolean processBytes(byte[] message) {
                System.out.println("messagemessage==" + new String(message));
                return true;
            }
        };
    }

    @Test
    public void testQueueDurable() throws Exception{
        System.out.println("testQueueDurable");
        createOptions.setDurable(true);
        createOptions.setQueueName("liwenshuai-queue");
        createOptions.setSenderType(CreateOptions.SenderType.QUEUE);

        Receiver receiver = ReceiverFactory.newReceiver(createOptions,mqReceiverCallback);
        receiver.receive();

        Thread.sleep(10 * 1000);
    }

    @Test
    public void testQueueNotDurable() throws Exception{
        System.out.println("testQueueNotDurable");
        createOptions.setDurable(false);
        createOptions.setQueueName("liwenshuai-queue");
        createOptions.setSenderType(CreateOptions.SenderType.QUEUE);

        Receiver receiver = ReceiverFactory.newReceiver(createOptions,mqReceiverCallback);
        receiver.receive();
        Thread.sleep(10 * 1000);
    }

    @Test
    public void testTopicDurable() throws Exception{
        System.out.println("testTopicDurable");
        createOptions.setDurable(true);
        createOptions.setExchangeName("liwenshuai-exchange-topic");
        createOptions.setQueueName("liwenshuai-exchange-queue");
        createOptions.setRoutingKey("bond.#");
        createOptions.setSenderType(CreateOptions.SenderType.TOPIC);

        Receiver receiver = ReceiverFactory.newReceiver(createOptions,mqReceiverCallback);
        receiver.receive();

        Thread.sleep(100 * 1000);
    }

    @Test
    public void testTopicNotDurable() throws Exception{
        System.out.println("testTopicNotDurable");
        createOptions.setDurable(false);
        createOptions.setExchangeName("liwenshuai-exchange-topic");
        createOptions.setQueueName("liwenshuai-exchange-queue");
        createOptions.setRoutingKey("bond.#");
        createOptions.setSenderType(CreateOptions.SenderType.TOPIC);

        Receiver receiver = ReceiverFactory.newReceiver(createOptions,mqReceiverCallback);
        receiver.receive();

        Thread.sleep(100 * 1000);
    }*/

    CreateOptions createOptions = null;
    MqReceiverCallback mqReceiverCallback = null;

    @Before
    public void init(){
        System.out.println("init");
        createOptions = new CreateOptions();
        createOptions.setHost("172.16.17.114");
        createOptions.setPort(5672);

        mqReceiverCallback = new MqReceiverCallback(){

            @Override
            public boolean processString(String message) {
                System.out.println("messagemessage==" + message);
                return true;
            }

            @Override
            public boolean processBytes(byte[] message) {
                System.out.println("messagemessage==" + new String(message));
                return true;
            }
        };
    }
    @Test
    public void testTopicNotDurable() throws Exception{
        System.out.println("testTopicNotDurable");
        createOptions.setDurable(false);
        createOptions.setExchangeName("liwenshuai-exchange-topic");
        createOptions.setQueueName("liwenshuai-exchange-queue");
        createOptions.setRoutingKey("bond.#");
        createOptions.setSenderType(CreateOptions.SenderType.TOPIC);

        Receiver receiver = ReceiverFactory.newReceiver(createOptions,mqReceiverCallback);
        receiver.receive();

        Thread.sleep(100 * 1000);
    }
}
