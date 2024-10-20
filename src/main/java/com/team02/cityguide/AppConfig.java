package com.team02.cityguide;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
        userDetailsManager.setCreateUserSql("INSERT INTO users (email, password, enabled) VALUES (?,?,?)");
        userDetailsManager.setCreateAuthoritySql("INSERT INTO authorities (email, authority) values (?,?)");
        userDetailsManager.setUsersByUsernameQuery("SELECT email, password, enabled FROM users WHERE email = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery("SELECT email, authorities FROM authorities WHERE email = ?");
        return userDetailsManager;
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .requestMatchers(HttpMethod.GET, "/", "/index.html", "/*.json", "/*.png", "/static/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/login", "/logout", "/signup").permitAll()
//                                .requestMatchers(HttpMethod.GET, "/restaurants/**", "/restaurant/**").permitAll()
                                .anyRequest().authenticated()
                )
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                .formLogin()
                .successHandler((req, res, auth) -> res.setStatus(HttpStatus.OK.value()))
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .and()
                .logout()
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK));
        return http.build();
    }
//@Bean
//public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    http
//            .csrf().disable()  // Disable CSRF for simplicity in testing (but keep it enabled in production)
//            .authorizeHttpRequests()
//            .requestMatchers("/login", "/signup", "/public/**").permitAll()  // Allow access to login page
//            .anyRequest().authenticated()  // All other requests require authentication
//            .and()
//            .formLogin()  // Use default form-based login
//            .loginPage("/login")  // Optional: Specify the custom login page URL
//            .defaultSuccessUrl("/home", true)  // Redirect to /home on successful login
//            .failureUrl("/login?error=true")  // Redirect to /login on failure with error
//            .and()
//            .logout()
//            .logoutUrl("/logout")
//            .logoutSuccessUrl("/login?logout=true");  // Redirect to login page on logout
//
//    return http.build();
//}

}
