package com.github.EvgeniyaZz.bank.config;

import com.github.EvgeniyaZz.bank.repository.UserRepository;
import com.github.EvgeniyaZz.bank.web.AuthUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Slf4j
@AllArgsConstructor
//https://stackoverflow.com/questions/72493425/548473
public class LoginSecurityConfiguration {
    public static final PasswordEncoder PASSWORD_ENCODER = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    private final UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PASSWORD_ENCODER;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return login -> {
            log.debug("Authenticating '{}'", login);
            return new AuthUser(userRepository.getExistedByLogin(login));
        };
    }

    //  https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter#configuring-websecurity
    //  https://stackoverflow.com/a/61147599/548473
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/", "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**");
    }

    @Bean
    @Order(2)
    public SecurityFilterChain tokenFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/token")
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}