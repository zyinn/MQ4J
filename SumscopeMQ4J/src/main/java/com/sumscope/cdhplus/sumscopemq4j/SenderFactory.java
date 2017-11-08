package com.sumscope.cdhplus.sumscopemq4j;

import com.rabbitmq.client.Connection;
import com.sumscope.cdhplus.sumscopemq4j.internal.Component;
import com.sumscope.cdhplus.sumscopemq4j.internal.Sender;
import com.sumscope.cdhplus.sumscopemq4j.internal.impl.ShutdownListenerHandler;

/**
 * Created by wenshuai.li on 2016/9/28.
 */
public class SenderFactory extends AbstractFactory{

    public synchronized static Sender newSender(CreateOptions createOptions) throws Exception {
        Connection connection = getConnection(createOptions);
        connection.addShutdownListener(new ShutdownListenerHandler());
        Component component = newComponent(connection,createOptions);
        assert component != null;
        Sender sender = component.createSender();
        assert sender != null;
        return sender;
    }

    /**
     * sender 的顺序和CreateOptions的顺序一致
     * @param createOptions
     * @return
     * @throws Exception
     */
    public synchronized static Sender[] newSenders(CreateOptions... createOptions) throws Exception {
        assert createOptions != null;
        Sender[] senders = new Sender[createOptions.length];
        for(int i=0;i<createOptions.length;i++){
            senders[i] = newSender(createOptions[i]);
        }
        return senders;
    }
}
