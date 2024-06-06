package com.github.EvgeniyaZz.bank.service;

import com.github.EvgeniyaZz.bank.dto.UserDto;
import com.github.EvgeniyaZz.bank.model.*;
import com.github.EvgeniyaZz.bank.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.github.EvgeniyaZz.bank.config.SecurityConfig.PASSWORD_ENCODER;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;
    private final PhoneRepository phoneRepository;
    private final MailRepository mailRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public User save(UserDto userDto) {
        UserDetail userDetail = userDetailRepository.save(new UserDetail(userDto.getFirstname(), userDto.getLastname(),
                userDto.getMiddlename(), userDto.getBirthDate()));
        Account account = accountRepository.save(new Account(userDto.getAccount()));
        User user = userRepository.save(new User(userDto.getLogin().toLowerCase(),
                PASSWORD_ENCODER.encode(userDto.getPassword()),
                userDetail, account, Role.USER));
        mailRepository.save(new Mail(userDto.getEmail(), user));
        phoneRepository.save(new Phone(userDto.getNumber(), user));

        return user;
    }

    public User getByLogin(String login) {
        return userRepository.getExistedByLogin(login);
    }
}
