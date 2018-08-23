package br.com.firstsoft.backendstateless.controllers;

import br.com.firstsoft.backendstateless.business.vo.User;
import br.com.firstsoft.backendstateless.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/profile/me")
    public User fetchProfile() {
        return accountService.fetchProfile();
    }

}
