package br.com.firstsoft.backendstateless.config.security;

import br.com.firstsoft.backendstateless.config.security.filter.FacebookAuthenticationFilter;
import br.com.firstsoft.backendstateless.config.security.filter.GoogleAuthenticationFilter;
import br.com.firstsoft.backendstateless.config.security.filter.TokenBasedAuthorizationFilter;
import br.com.firstsoft.backendstateless.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final Environment environment;
    private final JwtManager jwtManager;
    private final UserService userService;

    @Autowired
    public SecurityConfiguration(Environment environment, JwtManager jwtManager, UserService userService) {
        this.environment = environment;
        this.jwtManager = jwtManager;
        this.userService = userService;
    }

    @Bean
    public FacebookAuthenticationFilter facebookAuthenticationFilter() throws Exception {
        return new FacebookAuthenticationFilter(environment, authManager(), jwtManager, userService);
    }

    @Bean
    public GoogleAuthenticationFilter googleAuthenticationFilter() throws Exception {
        return new GoogleAuthenticationFilter(environment, authManager(), jwtManager, userService);
    }

    @Bean
    public TokenBasedAuthorizationFilter tokenBasedAuthorizationFilter() throws Exception {
        return new TokenBasedAuthorizationFilter(authManager(), environment, jwtManager, userService);
    }

    @Bean
    public AuthenticationManager authManager() throws Exception {
        return authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(tokenBasedAuthorizationFilter());
        http.addFilterBefore(facebookAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(googleAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
