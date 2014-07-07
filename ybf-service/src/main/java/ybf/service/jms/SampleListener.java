package org.simple.service.jms;

import java.io.UnsupportedEncodingException;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JMS接收信息监听程序
 */
public class SampleListener implements MessageListener {
    Logger log = LoggerFactory.getLogger(this.getClass());

    public void onMessage(Message message) {
        if (message instanceof MapMessage) {
            MapMessage mm = (MapMessage) message;
            try {
                log.info("******************" + new String(mm.toString().getBytes(), "utf-8"));
                System.out.println("----");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Message must be of type TextMessage");
        }
    }
}