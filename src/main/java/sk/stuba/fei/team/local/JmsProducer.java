package sk.stuba.fei.team.local;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import sk.stuba.fei.team.local.domain.CallInMessage;

@Component("jmsProducer")
public class JmsProducer {

    @Autowired
    JmsTemplate jmsTopicTemplate;

    public void sendMessage(String message) {
        MessageCreator messageCreator = session ->
                session.createTextMessage(message);
        jmsTopicTemplate.send(messageCreator);
    }

    public void sendMessage(CallInMessage message) {
        System.out.println("Publishing topic Call In message");
        ObjectMapper mapper = new ObjectMapper();
        try {
            sendMessage(mapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
