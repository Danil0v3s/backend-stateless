package br.com.firstsoft.backendstateless.oauth;

import br.com.firstsoft.backendstateless.business.vo.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

public class FacebookProvider extends ApiBinding {

    private static String GRAPH_API_URL = "https://graph.facebook.com/v2.12/me";
    private static List<String> FIELDS = Arrays.asList("name", "email", "picture.width(400).height(400)");

    public FacebookProvider(String token) {
        super(token);
    }

    public User auth() {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromHttpUrl(GRAPH_API_URL)
                .queryParam("fields", String.join(",", FIELDS));
        restTemplate.setInterceptors(getAuthInterceptors());

        ResponseEntity<User> userEntity = restTemplate.getForEntity(uriBuilder.toUriString(), User.class);
        return userEntity.getBody();
    }

}
