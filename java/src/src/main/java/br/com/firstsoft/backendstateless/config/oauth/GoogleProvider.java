package br.com.firstsoft.backendstateless.config.oauth;

import br.com.firstsoft.backendstateless.business.vo.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class GoogleProvider extends ApiBinding {

    private static String GRAPH_API_URL = "https://www.googleapis.com/oauth2/v3/userinfo";

    public GoogleProvider(String token) {
        super(token);
    }

    public User auth() {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromHttpUrl(GRAPH_API_URL);
        restTemplate.setInterceptors(getAuthInterceptors());

        ResponseEntity<User> userEntity = restTemplate.getForEntity(uriBuilder.toUriString(), User.class);
        return userEntity.getBody();
    }

}
