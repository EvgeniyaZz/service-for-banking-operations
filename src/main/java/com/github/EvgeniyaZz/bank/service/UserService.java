package com.github.EvgeniyaZz.bank.service;

import com.github.EvgeniyaZz.bank.dto.SignUpRequest;
import com.github.EvgeniyaZz.bank.model.*;
import com.github.EvgeniyaZz.bank.repository.*;
import com.github.EvgeniyaZz.bank.web.AuthUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.github.EvgeniyaZz.bank.config.LoginSecurityConfiguration.PASSWORD_ENCODER;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService{

    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;
    private final PhoneRepository phoneRepository;
    private final MailRepository mailRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public User save(SignUpRequest signUpRequest) {
        UserDetail userDetail = userDetailRepository.save(new UserDetail(signUpRequest.getFirstname(), signUpRequest.getLastname(),
                signUpRequest.getMiddlename(), signUpRequest.getBirthDate()));
        Account account = accountRepository.save(new Account(signUpRequest.getAccount()));
        User user = userRepository.save(new User(signUpRequest.getLogin().toLowerCase(),
                PASSWORD_ENCODER.encode(signUpRequest.getPassword()),
                userDetail, account, Role.USER));
        mailRepository.save(new Mail(signUpRequest.getEmail(), user));
        phoneRepository.save(new Phone(signUpRequest.getNumber(), user));

        return user;
    }

    public User getByLogin(String login) {
        return userRepository.getExistedByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getExistedByLogin(username);
        return new AuthUser(user);
    }
}
