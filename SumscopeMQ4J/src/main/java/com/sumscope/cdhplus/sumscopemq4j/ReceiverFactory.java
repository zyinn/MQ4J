package com.sumscope.cdhplus.sumscopemq4j;

import com.rabbitmq.client.Connection;
import com.sumscope.cdhplus.sumscopemq4j.internal.Component;
import com.sumscope.cdhplus.sumscopemq4j.internal.MqReceiverCallback;
import com.sumscope.cdhplus.sumscopemq4j.internal.Receiver;
import com.sumscope.cdhplus.sumscopemq4j.internal.Sender;
import com.sumscope.cdhplus.sumscopemq4j.internal.impl.ShutdownListenerHandler;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by wenshuai.li on 2016/9/28.
 */
public class ReceiverFactory extends  AbstractFactory{

    public synchronized static Receiver newReceiver(CreateOptions createOptions, MqReceiverCallback mqReceiverCallback) throws IOException, TimeoutException {
        Connection connection = getConnection(createOptions);
        connection.addShutdownListener(new ShutdownListenerHandler());
        Component component = newComponent(connection,createOptions);
        assert component != null;
        Receiver receiver = component.createReceiver(mqReceiverCallback);
        assert receiver != null;
        return receiver;
    }
}
