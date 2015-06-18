package sk.stuba.fei.team.local.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component("jmsProducer")
public class JmsProducer {

    @Autowired
    JmsTemplate jmsTopicTemplate;
    private Logger logger = LoggerFactory.getLogger(JmsProducer.class);

    public void sendMessage(String message) {
        MessageCreator messageCreator = session ->
                session.createTextMessage(message);
        logger.debug("Sending message to JMS: " + message);
        jmsTopicTemplate.send(messageCreator);
    }

    public void sendMessage(CallInMessage message) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            sendMessage(mapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            logger.error("Failed to send Call In message", e);
        }
    }

}
