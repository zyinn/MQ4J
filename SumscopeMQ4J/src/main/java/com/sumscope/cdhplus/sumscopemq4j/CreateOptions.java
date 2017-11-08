package com.sumscope.cdhplus.sumscopemq4j;

/**
 * Created by wenshuai.li on 2016/9/28.
 */
public class CreateOptions {
    private SenderType senderType = SenderType.QUEUE;

    private String virtualHost;
    private String host;
    private int port = 5672;
    private int requestedHeartbeat;

    private String username;
    private String password;

    private String queueName;
    private String exchangeName;

    private String routingKey;

    private boolean autoReconnect = true;

    private boolean ackRequeue;

    /**
     * 如果设置成false,调用close方法会关闭所有channel
     */
    private boolean isSingleConnection = true;
    /**
     * If you have a queue that is exclusive, then when the
     * channel that declared the queue is closed, the queue is deleted.
     */
    private boolean exclusive;

    private boolean durable;

    /**
     * If you have a queue that is auto-deleted,
     * then when there are no subscriptions left on that queue it will be deleted.
     */
    private boolean autoDelete;

    private boolean autoAck;

    /**
     * 重试次数，如果设为1，最多发2次
     */
    private int reSendTimes;

    public SenderType getSenderType() {
        return senderType;
    }

    public void setSenderType(SenderType senderType) {
        this.senderType = senderType;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public void setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getRequestedHeartbeat() {
        return requestedHeartbeat;
    }

    public void setRequestedHeartbeat(int requestedHeartbeat) {
        this.requestedHeartbeat = requestedHeartbeat;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public boolean isAutoReconnect() {
        return autoReconnect;
    }

    public void setAutoReconnect(boolean autoReconnect) {
        this.autoReconnect = autoReconnect;
    }

    public boolean isExclusive() {
        return exclusive;
    }

    public void setExclusive(boolean exclusive) {
        this.exclusive = exclusive;
    }

    public boolean isDurable() {
        return durable;
    }

    public void setDurable(boolean durable) {
        this.durable = durable;
    }

    /**
     * 如果durable=true,autoDelete自动设置成false,不容许手动设置
     * 否者相反
     * @return
     */
    public boolean isAutoDelete() {
        return autoDelete;
    }

    private void setAutoDelete(boolean autoDelete) {
        this.autoDelete = autoDelete;
    }

    public boolean isAutoAck() {
        return autoAck;
    }

    public void setAutoAck(boolean autoAck) {
        this.autoAck = autoAck;
    }

    public boolean isAckRequeue() {
        return ackRequeue;
    }

    public void setAckRequeue(boolean ackRequeue) {
        this.ackRequeue = ackRequeue;
    }

    public boolean isSingleConnection() {
        return isSingleConnection;
    }

    public void setSingleConnection(boolean singleConnection) {
        isSingleConnection = singleConnection;
    }

    public int getReSendTimes() {
        return reSendTimes;
    }

    public void setReSendTimes(int reSendTimes) {
        this.reSendTimes = reSendTimes;
    }

    public enum SenderType {
        QUEUE("queue"),
        TOPIC("topic"),
        FANOUT("fanout");

        private String value;
        SenderType(String value) {
            this.value = value;
        }
        public String value() {
            return value;
        }
    }

    public enum MessageType {
        TEXT("text/plain"),
        STREAM("application/octet-stream");

        private String value;
        MessageType(String value) {
            this.value = value;
        }
        public String value() {
            return value;
        }
    }

}
