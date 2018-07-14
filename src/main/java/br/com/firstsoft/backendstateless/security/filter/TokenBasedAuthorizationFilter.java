package br.com.firstsoft.backendstateless.security.filter;

import br.com.firstsoft.backendstateless.security.JwtManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class TokenBasedAuthorizationFilter extends BasicAuthenticationFilter {

    private Environment environment;

    private JwtManager jwtManager;

    public TokenBasedAuthorizationFilter(AuthenticationManager authenticationManager, Environment environment, JwtManager jwtManager) {
        super(authenticationManager);
        this.environment = environment;
        this.jwtManager = jwtManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String authorizationToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationToken != null && !authorizationToken.isEmpty()) {
            authorizationToken = authorizationToken.replaceFirst(environment.getProperty("backend-stateless.token-prefix"), "");

            String username = jwtManager.decode(authorizationToken);

            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList()));
        }

        chain.doFilter(request, response);
    }
}
