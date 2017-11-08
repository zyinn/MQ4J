package com.sumscope.cdhplus.sumscopemq4j.internal;

import java.io.IOException;

/**
 * Created by wenshuai.li on 2016/9/28.
 */

public interface Component {
    Sender createSender() throws IOException;
    Receiver createReceiver(MqReceiverCallback mqReceiverCallback) throws IOException;
}
