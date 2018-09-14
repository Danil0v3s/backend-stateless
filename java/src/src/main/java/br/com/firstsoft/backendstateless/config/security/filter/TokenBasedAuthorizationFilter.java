package br.com.firstsoft.backendstateless.config.security.filter;

import br.com.firstsoft.backendstateless.business.vo.User;
import br.com.firstsoft.backendstateless.config.security.JwtManager;
import br.com.firstsoft.backendstateless.services.UserService;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenBasedAuthorizationFilter extends BasicAuthenticationFilter {

    private Environment environment;
    private JwtManager jwtManager;
    private UserService userService;

    public TokenBasedAuthorizationFilter(AuthenticationManager authenticationManager, Environment environment, JwtManager jwtManager, UserService userService) {
        super(authenticationManager);
        this.environment = environment;
        this.jwtManager = jwtManager;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String authorizationToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationToken != null && !authorizationToken.isEmpty()) {
            authorizationToken = authorizationToken.replaceFirst(environment.getProperty("backend-stateless.token-prefix"), "");

            String userEmail = jwtManager.decode(authorizationToken);

            if (userEmail != null) {
                User managedUser = userService.findByEmail(userEmail);

                if (managedUser != null) {
                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(managedUser, null, AuthorityUtils.createAuthorityList("USER")));
                } else {
                    throw new ServletException("Invalid token");
                }
            }

        }

        chain.doFilter(request, response);
    }
}
