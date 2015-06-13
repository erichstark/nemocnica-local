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

    private static final Log logger = LogFactory.getLog(RestConsumer.class);

    private OAuth2RestTemplate restTemplate;

    private String baseUrl;

    @Autowired
    public RestConsumer(FacilityService facilityService, @Value("${global.host}") String globalHost, @Value("${global.port}") int globalPort) {
        logger.info("Starting ");
        baseUrl = String.format("http://%s:%d/", globalHost, globalPort);
        configure(facilityService.getFacility());
    }

    public void configure(Facility facility) {
        if (facility != null) {
            ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();
            details.setAccessTokenUri(baseUrl + "oauth/token");
            details.setUsername(facility.getUsername());
            details.setPassword(facility.getPassword());
            details.setClientId(facility.getClientID());
            details.setClientSecret(facility.getClientSecret());
            DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
            restTemplate = new OAuth2RestTemplate(details, clientContext);
            restTemplate.setMessageConverters(Collections.singletonList(new MappingJackson2HttpMessageConverter()));
        } else {
            restTemplate = null;
        }
    }

    public Object get(String url, Class responseType) {
        if (restTemplate != null) {
            return restTemplate.getForObject(baseUrl + "ws/" + url, responseType);
        } else {
            return null;
        }
    }

    public Object post(String url, Object request, Class responseType) {
        if (restTemplate != null) {
            return restTemplate.postForObject(baseUrl + "ws/" + url, request, responseType);
        } else {
            return null;
        }
    }

}
