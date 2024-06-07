package com.github.EvgeniyaZz.bank.service;

import com.github.EvgeniyaZz.bank.dto.SignInRequest;
import com.github.EvgeniyaZz.bank.dto.SignUpRequest;
import com.github.EvgeniyaZz.bank.model.User;
import com.github.EvgeniyaZz.bank.dto.JwtAuthenticationResponse;
import com.github.EvgeniyaZz.bank.web.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signUp(SignUpRequest request) {

        User user = userService.save(request);

        var jwt = jwtService.generateToken(new AuthUser(user));
        return new JwtAuthenticationResponse(jwt);
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getLogin(),
                request.getPassword()
        ));

        var user = userService
                .loadUserByUsername(request.getLogin());

        var jwt = jwtService.generateToken((AuthUser) user);
        return new JwtAuthenticationResponse(jwt);
    }
}
