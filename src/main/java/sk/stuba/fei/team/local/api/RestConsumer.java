package sk.stuba.fei.team.local.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.stereotype.Component;
import sk.stuba.fei.team.local.service.FacilityService;

import java.util.Collections;

@Component("restConsumer")
public class RestConsumer {

    private final OAuth2RestTemplate restTemplate;

    private final String BASE_URL;

    @Autowired
    public RestConsumer(FacilityService facilityService, @Value("${global.host}") String globalHost, @Value("${global.port}") int globalPort) {

        BASE_URL = String.format("http://%s:%d/", globalHost, globalPort);
        ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();
        details.setAccessTokenUri(BASE_URL + "oauth/token");
        //todo fix facility
       /*Facility facility = facilityService.findAll().iterator().next();
        details.setUsername(facility.getUsername());
        details.setPassword(facility.getPassword());
        details.setClientId(facility.getClientID());
        details.setClientSecret(facility.getClientSecret());*/

        details.setUsername("user");
        details.setPassword("user123");
        details.setClientId("local");
        details.setClientSecret("secret");

        DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
        restTemplate = new OAuth2RestTemplate(details, clientContext);
        restTemplate.setMessageConverters(Collections.singletonList(new MappingJackson2HttpMessageConverter()));
    }

    public Object get(String url, Class responseType) {
        return restTemplate.getForObject(BASE_URL + "ws/" + url, responseType);
    }

    public Object post(String url, Object request, Class responseType) {
        return restTemplate.postForObject(BASE_URL + "ws/" + url, request, responseType);
    }

}
