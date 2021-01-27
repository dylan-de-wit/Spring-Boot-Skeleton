package com.dylandewit.skeleton.config;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String[] SWAGGER_URIS = {
            "/swagger-ui/",
            "/swagger-ui/**",
            "/swagger-ui/**.**",
            "/v2/api-docs",
            "/swagger-resources/**"
    };
    private final String apiAudience;
    private final String issuer;

    public SecurityConfig(@Value(value = "${auth0.apiAudience}") String apiAudience, @Value(value = "${auth0.issuer}") String issuer) {
        this.apiAudience = apiAudience;
        this.issuer = issuer;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtWebSecurityConfigurer
                .forRS256(apiAudience, issuer)
                .configure(http)
                .cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET, SWAGGER_URIS).permitAll()
                .anyRequest().fullyAuthenticated();
    }
}