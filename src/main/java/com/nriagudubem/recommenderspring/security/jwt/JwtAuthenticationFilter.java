package com.nriagudubem.recommenderspring.security.jwt;

import com.nriagudubem.recommenderspring.security.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Log4j2
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    private final MyUserDetailsService myUserDetailsService;

    private static final String BEARER = "Bearer";

    private static final String AUTH_TOKEN_NAME = "Authorization";

    @Override
    protected void doFilterInternal(javax.servlet.http.HttpServletRequest request,
                                    javax.servlet.http.HttpServletResponse response,
                                    javax.servlet.FilterChain filterChain) throws ServletException, IOException {
        final Optional<String> optionalJwt = getJwtFromHeader(request.getHeader(AUTH_TOKEN_NAME));
        if (optionalJwt.isPresent()) {
            final String jwt = optionalJwt.get();
            try {
                if (jwtProvider.validateToken(jwt)) {
                    UserDetails userDetails = myUserDetailsService.loadUserByUsername(jwtProvider.getUsernameFrom(jwt));
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } catch (RuntimeException ex) {
                log.error("process=doFilterInternal, status=failure, exception={}", ex.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }

    private Optional<String> getJwtFromHeader(String authHeader) {
        return (StringUtils.hasText(authHeader) && authHeader.startsWith(BEARER)) ? Optional.of(authHeader.substring(7))
                : Optional.empty();
    }
}
