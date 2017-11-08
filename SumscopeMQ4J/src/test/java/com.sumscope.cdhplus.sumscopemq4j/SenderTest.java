package com.sumscope.cdhplus.sumscopemq4j;

import com.sumscope.cdhplus.sumscopemq4j.internal.Sender;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by wenshuai.li on 2016/9/28.
 */

public class SenderTest {

    CreateOptions createOptions = null;

    @Before
    public void init(){
        System.out.println("init");
        createOptions = new CreateOptions();
        createOptions.setHost("127.0.0.1");
        createOptions.setPort(8888);

    }

//    @Test
//    public void testQueueDurable() throws Exception{
//        System.out.println("testQueueDurable");
//        createOptions.setDurable(true);
//        createOptions.setQueueName("liwenshuai-queue");
//        createOptions.setSenderType(CreateOptions.SenderType.QUEUE);
//
//        Sender sender = SenderFactory.newSender(createOptions);
//
//        send(sender);
//        sender.close();
//    }

//    @Test
//    public void testQueueNotDurable() throws Exception{
//        System.out.println("testQueueNotDurable");
//        createOptions.setDurable(false);
//        createOptions.setQueueName("liwenshuai-queue");
//        createOptions.setSenderType(CreateOptions.SenderType.QUEUE);
//
//        Sender sender = SenderFactory.newSender(createOptions);
//        send(sender);
//
//        sender.close();
//    }

//    @Test
//    public void testTopicDurable() throws Exception{
//        System.out.println("testQueueDurable");
//        createOptions.setDurable(true);
//        createOptions.setExchangeName("liwenshuai-exchange-topic");
//        createOptions.setQueueName("liwenshuai-exchange-queue");
//        createOptions.setSenderType(CreateOptions.SenderType.TOPIC);
//        createOptions.setRoutingKey("bond.aab.ll");
//
//
//        Sender sender = SenderFactory.newSender(createOptions);
//        send(sender);
//        sender.close();
//    }

    @Test
    public void testTopicNotDurable() throws Exception{
        System.out.println("testQueueDurable");
        createOptions.setDurable(false);
        createOptions.setExchangeName("liwenshuai-exchange-topic");
        createOptions.setQueueName("liwenshuai-exchange-queue");
        createOptions.setSenderType(CreateOptions.SenderType.FANOUT);
        createOptions.setRoutingKey("bond.#");
        createOptions.setRequestedHeartbeat(5);

        Sender sender = SenderFactory.newSender(createOptions);
        send(sender);
        sender.close();
    }

    private void send(Sender sender) throws Exception{
        int i=100;
        while (i>0){
            System.out.println("sender:" + i);
            sender.send(i+"");
            i--;
            Thread.sleep(1000);
        }
    }
}
