package br.com.firstsoft.backendstateless.security.filter;

import br.com.firstsoft.backendstateless.business.vo.User;
import br.com.firstsoft.backendstateless.oauth.GoogleProvider;
import br.com.firstsoft.backendstateless.security.JwtManager;
import br.com.firstsoft.backendstateless.services.UserService;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoogleAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private JwtManager jwtManager;
    private Environment environment;
    private UserService userService;

    public GoogleAuthenticationFilter(Environment environment, AuthenticationManager authenticationManager, JwtManager jwtManager, UserService userService) {
        super(new AntPathRequestMatcher(environment.getProperty("backend-stateless.google-login-url")));
        super.setAuthenticationManager(authenticationManager);
        this.environment = environment;
        this.jwtManager = jwtManager;
        this.userService = userService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (accessToken != null && !accessToken.isEmpty()) {
            GoogleProvider provider = new GoogleProvider(accessToken);
            User user = provider.auth();

            Authentication managedUser = getAuthentication(user);
            if (managedUser != null) return managedUser;
        }

        return null;
    }

    private Authentication getAuthentication(User user) {
        if (user != null) {

            User managedUser = userService.findByEmail(user.getEmail());

            if (managedUser == null) {
                managedUser = userService.save(user);
            }

            return new UsernamePasswordAuthenticationToken(managedUser, null, AuthorityUtils.createAuthorityList("USER"));
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = new StringBuilder(environment.getProperty("backend-stateless.token-prefix"))
                .append(" ")
                .append(jwtManager.encode(((User) authResult.getPrincipal()).getEmail()))
                .toString();

        response.addHeader(HttpHeaders.SET_COOKIE, token);
    }
}
