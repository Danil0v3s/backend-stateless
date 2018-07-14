package br.com.firstsoft.backendstateless.services;

import br.com.firstsoft.backendstateless.business.vo.User;
import br.com.firstsoft.backendstateless.oauth.FacebookProvider;
import br.com.firstsoft.backendstateless.security.JwtManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private Environment environment;

    @Autowired
    private JwtManager jwtManager;

    public ResponseEntity<User> facebookOAuth(String authToken) {
        if (authToken != null) {
            return null;
        } else {
            return null;
        }
    }


}
