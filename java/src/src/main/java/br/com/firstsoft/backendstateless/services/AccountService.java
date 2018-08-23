package br.com.firstsoft.backendstateless.services;

import br.com.firstsoft.backendstateless.business.vo.User;
import br.com.firstsoft.backendstateless.security.JwtManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final Environment environment;
    private final JwtManager jwtManager;

    @Autowired
    public AccountService(Environment environment, JwtManager jwtManager) {
        this.environment = environment;
        this.jwtManager = jwtManager;
    }

    public User fetchProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            User currentUser = (User) authentication.getPrincipal();

            if (currentUser != null) {
                return currentUser;
            }
        }

        return null;
    }


}
