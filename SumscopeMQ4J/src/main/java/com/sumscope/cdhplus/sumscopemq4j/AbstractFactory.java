package com.sumscope.cdhplus.sumscopemq4j;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.sumscope.cdhplus.sumscopemq4j.internal.Component;
import com.sumscope.cdhplus.sumscopemq4j.internal.impl.Fanout;
import com.sumscope.cdhplus.sumscopemq4j.internal.impl.Queue;
import com.sumscope.cdhplus.sumscopemq4j.internal.impl.Topic;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by wenshuai.li on 2016/9/28.
 */
public abstract class AbstractFactory {

    protected static Connection connection = null;

    protected static ConnectionFactory newConnection(CreateOptions createOptions){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(createOptions.getHost());
        factory.setPort(createOptions.getPort());
        factory.setAutomaticRecoveryEnabled(createOptions.isAutoReconnect());
        //用户如果有配置心跳时间，就用用户的；否则用默认的60s.
        if(createOptions.getRequestedHeartbeat() > 0){
            factory.setRequestedHeartbeat(createOptions.getRequestedHeartbeat());
        }
        if(createOptions.getVirtualHost() != null ){
            factory.setVirtualHost(createOptions.getVirtualHost());
        }
        if(createOptions.getUsername() != null){
            factory.setUsername(createOptions.getUsername());
        }
        if(createOptions.getPassword() != null){
            factory.setPassword(createOptions.getPassword());
        }

        factory.setNetworkRecoveryInterval(5000);
        return factory;
    }

    protected static Component newComponent(Connection connection, CreateOptions createOptions) throws IOException{
        Component component = null;
        if(createOptions.getSenderType() == CreateOptions.SenderType.QUEUE){
            component = new Queue(connection,createOptions);
        }else if(createOptions.getSenderType() == CreateOptions.SenderType.TOPIC){
            component = new Topic(connection,createOptions);
        }else if(createOptions.getSenderType() == CreateOptions.SenderType.FANOUT) {
            component = new Fanout(connection,createOptions);
        }

        return component;

    }

    protected static Connection getConnection(CreateOptions createOptions) throws IOException, TimeoutException {
        if(connection == null || !createOptions.isSingleConnection()){
            return newConnection(createOptions).newConnection();
        }else{
            return connection;
        }
    }
}
