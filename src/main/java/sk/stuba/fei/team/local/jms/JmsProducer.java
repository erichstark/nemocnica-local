package sk.stuba.fei.team.local.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component("jmsProducer")
public class JmsProducer {

    private static Log log = LogFactory.getLog(JmsProducer.class);

    @Autowired
    JmsTemplate jmsTopicTemplate;

    public void sendMessage(String message) {
        MessageCreator messageCreator = session ->
                session.createTextMessage(message);
        log.debug("Sending message to JMS: " + message);
        jmsTopicTemplate.send(messageCreator);
    }

    public void sendMessage(CallInMessage message) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            sendMessage(mapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
