package br.com.firstsoft.backendstateless.controllers;

import br.com.firstsoft.backendstateless.business.vo.User;
import br.com.firstsoft.backendstateless.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/login/facebook")
    public ResponseEntity<User> facebookOAuth(@RequestHeader(HttpHeaders.AUTHORIZATION) String authToken) {
        return accountService.facebookOAuth(authToken);
    }

    @PostMapping("/login/google")
    public ResponseEntity<User> googleOAuth(@RequestHeader(HttpHeaders.AUTHORIZATION) String authToken) {
        return accountService.facebookOAuth(authToken);
    }

}
