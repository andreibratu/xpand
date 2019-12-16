package com.dei.xpand.planets.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    private final UnauthorizedRequestEntryPoint unauthorizedRequestEntryPoint;

    private final JwtRequestFilter jwtRequestFilter;

    public WebSecurityConfig(
            UnauthorizedRequestEntryPoint unauthorizedRequestEntryPoint,
            JwtRequestFilter jwtRequestFilter)
    {
        this.unauthorizedRequestEntryPoint = unauthorizedRequestEntryPoint;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        // Dummy, in-memory user. Authentication is verified by checking the secret received in the JWT token
        // against our stored jwt.secret variable
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("password"))
                .authorities("ROLE_USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception
    {
        // We don't need CSRF for this example
        httpSecurity.csrf().disable()
            // do not authenticate this particular requests
            // all other requests need to be authenticated
            .authorizeRequests().antMatchers("/api/*").authenticated()
            // make sure we use stateless session; session won't be used to store user's state.
            .and().exceptionHandling().authenticationEntryPoint(unauthorizedRequestEntryPoint)
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            // Add a filter to validate the tokens with every request
            httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}