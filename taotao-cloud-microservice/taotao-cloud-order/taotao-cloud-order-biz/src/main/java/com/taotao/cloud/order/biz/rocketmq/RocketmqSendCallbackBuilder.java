package com.taotao.cloud.order.biz.rocketmq;

/**
 * @author paulG
 * @since 2020/11/4
 **/
public class RocketmqSendCallbackBuilder {


    public static RocketmqSendCallback commonCallback() {
        return new RocketmqSendCallback();
    }

}