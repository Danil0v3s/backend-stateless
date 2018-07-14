package br.com.firstsoft.backendstateless.security;

import br.com.firstsoft.backendstateless.security.filter.FacebookAuthenticationFilter;
import br.com.firstsoft.backendstateless.security.filter.TokenBasedAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment environment;

    @Autowired
    private JwtManager jwtManager;

    @Bean
    public FacebookAuthenticationFilter facebookAuthenticationFilter() throws Exception {
        return new FacebookAuthenticationFilter(environment, authManager(), jwtManager);
    }

    @Bean
    public TokenBasedAuthorizationFilter tokenBasedAuthorizationFilter() throws Exception {
        return new TokenBasedAuthorizationFilter(authManager(), environment, jwtManager);
    }

    @Bean
    public AuthenticationManager authManager() throws Exception {
        return authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeRequests().antMatchers("/api/account/login/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(tokenBasedAuthorizationFilter());
        http.addFilterBefore(facebookAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/api/account/login/**");
//    }
}
