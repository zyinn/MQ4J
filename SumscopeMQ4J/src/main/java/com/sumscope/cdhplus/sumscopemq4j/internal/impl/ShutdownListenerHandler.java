package com.sumscope.cdhplus.sumscopemq4j.internal.impl;

import com.rabbitmq.client.ShutdownListener;
import com.rabbitmq.client.ShutdownSignalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wenshuai.li on 2016/9/30.
 */
public class ShutdownListenerHandler implements ShutdownListener {
    public final static Logger receiverLog = LoggerFactory.getLogger("receiverLog");

    @Override
    public void shutdownCompleted(ShutdownSignalException cause) {
        receiverLog.info("掉线，准备重连");
    }
}
