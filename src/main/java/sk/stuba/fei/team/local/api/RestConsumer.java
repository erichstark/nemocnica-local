package sk.stuba.fei.team.local.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.stereotype.Component;
import sk.stuba.fei.team.local.domain.Facility;
import sk.stuba.fei.team.local.service.FacilityService;

import java.util.Collections;

@Component("restConsumer")
public class RestConsumer {

    private static Log log = LogFactory.getLog(RestConsumer.class);

    private boolean globalEnabled;

    private OAuth2RestTemplate restTemplate;

    private String BASE_URL;

    @Autowired
    public RestConsumer(FacilityService facilityService, @Value("${global.host}") String globalHost, @Value("${global.port}") int globalPort, @Value("${global.enabled}") boolean globalEnabled) {
        this.globalEnabled = globalEnabled;
        if (globalEnabled) {
            log.info("Starting ");
            BASE_URL = String.format("http://%s:%d/", globalHost, globalPort);
            ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();
            details.setAccessTokenUri(BASE_URL + "oauth/token");

            Facility facility = facilityService.getFacility();
//            if (facility != null) {
//                details.setUsername(facility.getUsername());
//                details.setPassword(facility.getPassword());
//                details.setClientId(facility.getClientID());
//                details.setClientSecret(facility.getClientSecret());
//            } else {
            details.setUsername("user");
            details.setPassword("user123");
            details.setClientId("local");
            details.setClientSecret("secret");
//            }

            DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
            restTemplate = new OAuth2RestTemplate(details, clientContext);
            restTemplate.setMessageConverters(Collections.singletonList(new MappingJackson2HttpMessageConverter()));
        }
    }

    public Object get(String url, Class responseType) {
        if (globalEnabled) {
            return restTemplate.getForObject(BASE_URL + "ws/" + url, responseType);
        } else {
            return null;
        }
    }

    public Object post(String url, Object request, Class responseType) {
        if (globalEnabled) {
            return restTemplate.postForObject(BASE_URL + "ws/" + url, request, responseType);
        } else {
            return null;
        }
    }

}
