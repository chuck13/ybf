package org.simple.service.jms;

import java.io.Serializable;
import java.util.Map;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.util.ObjectUtils;

/**
 * JMS发送信息实现
 */
public class SampleSender {

    @Autowired(required = false)
    JmsTemplate jmsTemplate;

    public void setConnectionFactory(ConnectionFactory cf) {
        this.jmsTemplate = new JmsTemplate(cf);
    }

    public void simpleSend(final Map<?, ?> map) {
        this.jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
//                return session.createTextMessage(message);
                MapMessage mm = session.createMapMessage();
                for (Map.Entry entry : map.entrySet()) {
                    if (!(entry.getKey() instanceof String)) {
                        throw new MessageConversionException("Cannot convert non-String key of type [" +
                                ObjectUtils.nullSafeClassName(entry.getKey()) + "] to JMS MapMessage entry");
                    }
                    mm.setObject((String) entry.getKey(), entry.getValue());
                }
                return mm;
            }
        });
    }
}