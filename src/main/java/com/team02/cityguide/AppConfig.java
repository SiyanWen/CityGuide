package com.team02.cityguide;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    UserDetailsManager users(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setCreateUserSql("INSERT INTO USERS (email, password, enabled) VALUES (?, ?, ?)");
        userDetailsManager.setCreateAuthoritySql("INSERT INTO authorities (email, authority) VALUES (?, ?)");
        userDetailsManager.setUsersByUsernameQuery("SELECT email, password, enabled FROM USERS WHERE email = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery("SELECT email, authority FROM authorities WHERE email = ?");
        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        TODO: other endpoints need to be added.
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/login", "/signup", "/logout").permitAll()
                        .anyRequest().authenticated()
                )
                        .formLogin((form) -> form
                                .loginPage("/login")
                                .permitAll()
                        )
                                .logout((logout) -> logout.permitAll())
                                        .csrf((csrf) -> csrf
                                                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                                                .ignoringRequestMatchers("/login", "/signup", "/logout")
                                        );


//        http.csrf().disable()
//                .authorizeHttpRequests(auth ->
//                        auth.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
//                                .requestMatchers(HttpMethod.GET, "/", "index.html", "/*.json", "/*.png", "/static/**").permitAll()
//                                .requestMatchers(HttpMethod.POST, "/login", "/logout", "/signup").permitAll()
//                                .requestMatchers(HttpMethod.GET, "/Cart").permitAll()
//                                .anyRequest().authenticated()
//                        )
//                .exceptionHandling()
//                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
//                .and()
//                .formLogin()
//                .successHandler((request, response, authentication) -> response.setStatus(HttpStatus.OK.value()))
//                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
//                .and()
//                .logout()
//                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK));
        return http.build();
    }
}
