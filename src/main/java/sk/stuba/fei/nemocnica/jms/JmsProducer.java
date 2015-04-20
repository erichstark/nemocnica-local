package sk.stuba.fei.nemocnica.jms;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * Created by loucher on 20.4.2015.
 */
@Component
public class JmsProducer {

    private JmsTemplate jmsTemplate;

    public JmsProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessages(String textMessage) throws JMSException {
        jmsTemplate.send(session -> {
            TextMessage message = session.createTextMessage(textMessage);
            message.setStringProperty("text", "Hello World");
            return message;
        });
    }

}