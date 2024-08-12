package ir.reyreey.myspringexample.controller.customization;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.naming.AuthenticationException;
import java.io.IOException;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 8/6/2024, Tuesday
 **/
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtHandler jwtHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        doBefore(request,response);
        filterChain.doFilter(request, response);
    }

    private void doBefore(HttpServletRequest request, HttpServletResponse response) {
        var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        var jwtToken = authHeader.replace("Bearer ", "").trim();
        try {
            var decodedToken = jwtHandler.verifyToken(jwtToken);

            SecurityContextHolder.getContext()
                    .setAuthentication(UsernamePasswordAuthenticationToken
                            .authenticated(decodedToken.getSubject(),
                                    null,
                                    AuthorityUtils.createAuthorityList(decodedToken.getClaim("authorities").asList(String.class))
                            )
                    );
        }catch (JWTVerificationException jwtException){
            throw new TokenVerificationException(jwtException.getMessage());
        }
    }
}
