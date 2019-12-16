package com.dei.xpand.crew.config;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class JwtRequestFilter extends OncePerRequestFilter
{
    private final JwtTokenUtil jwtTokenUtil;

    public JwtRequestFilter(JwtTokenUtil jwtTokenUtil)
    {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response,
            FilterChain chain)
            throws ServletException, IOException
    {
        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;
        // JWT Token is in the form "Bearer token".
        // Remove Bearer word and get only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer "))
        {
            jwtToken = requestTokenHeader.substring(7);
            try
            {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Unable to get JWT Token");
            }
            catch (ExpiredJwtException e)
            {
                System.out.println("JWT Token has expired");
            }
        }
        else
        {
            logger.warn("JWT Token does not begin with Bearer String");
        }
        // Once we get the token validate it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            String finalUsername = username;
            UserDetails userDetails = new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() { return null; }

                @Override
                public String getPassword() { return ""; }

                @Override
                public String getUsername() { return finalUsername; }

                @Override
                public boolean isAccountNonExpired() { return false; }

                @Override
                public boolean isAccountNonLocked() { return false; }

                @Override
                public boolean isCredentialsNonExpired() { return false; }

                @Override
                public boolean isEnabled() { return false; }
            };
            // if token is valid configure Spring Security to manually set authentication
            if (jwtTokenUtil.validateToken(jwtToken, userDetails))
            {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the
                // Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}