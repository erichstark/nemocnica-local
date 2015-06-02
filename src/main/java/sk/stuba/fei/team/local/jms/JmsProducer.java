package sk.stuba.fei.team.local.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component("jmsProducer")
public class JmsProducer {

    @Autowired
    JmsTemplate jmsTopicTemplate;

    public void sendMessage(String message) {
        MessageCreator messageCreator = session ->
                session.createTextMessage(message);
        System.out.println("Sending message to topic: " + message);
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
