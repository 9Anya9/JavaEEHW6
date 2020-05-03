package com.example.demo.config;

import com.example.demo.repositories.UserRepository;
import com.example.demo.services.NewUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.http.HttpMethod.POST;

@RequiredArgsConstructor
@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .addFilter(logoutFilter())
                .addFilterBefore(customLoginFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new NewUserDetailsService(userRepository);
    }

    @Bean
    @SneakyThrows
    @Override
    public AuthenticationManager authenticationManagerBean() {
        return super.authenticationManagerBean();
    }

    private LoginConfig customLoginFilter() {
        final LoginConfig loginConfig = new LoginConfig(authenticationManagerBean(), objectMapper);
        loginConfig.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", POST.name()));

        return loginConfig;
    }

    private LogoutFilter logoutFilter() {
        final LogoutHandler[] logoutHandlers = new LogoutHandler[]{
                new SecurityContextLogoutHandler()
        };

        return new LogoutFilter(new LogoutConfig(), logoutHandlers);
    }
}