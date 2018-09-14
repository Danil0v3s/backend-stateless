package br.com.firstsoft.backendstateless.config.oauth;

import br.com.firstsoft.backendstateless.config.security.HeaderRequestInterceptor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;

import java.util.ArrayList;
import java.util.List;

public class ApiBinding {

    protected String token;

    protected ApiBinding(String token) {
        this.token = token;
    }

    protected List<ClientHttpRequestInterceptor> getAuthInterceptors() {
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE));
        interceptors.add(new HeaderRequestInterceptor(HttpHeaders.AUTHORIZATION, token));
        return interceptors;
    }

}
