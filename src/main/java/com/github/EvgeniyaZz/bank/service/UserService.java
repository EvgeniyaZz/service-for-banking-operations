package com.github.EvgeniyaZz.bank.service;

import com.github.EvgeniyaZz.bank.dto.PostUserDto;
import com.github.EvgeniyaZz.bank.model.Account;
import com.github.EvgeniyaZz.bank.model.Mail;
import com.github.EvgeniyaZz.bank.model.Phone;
import com.github.EvgeniyaZz.bank.model.User;
import com.github.EvgeniyaZz.bank.repository.AccountRepository;
import com.github.EvgeniyaZz.bank.repository.MailRepository;
import com.github.EvgeniyaZz.bank.repository.PhoneRepository;
import com.github.EvgeniyaZz.bank.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final PhoneRepository phoneRepository;
    private final MailRepository mailRepository;

    @Transactional
    public User save(PostUserDto userDto) {
        User user = userRepository.save(new User(userDto.getFirstname(), userDto.getLastname(), userDto.getMiddlename(),
                userDto.getBirthDate()));
        accountRepository.save(new Account(userDto.getLogin(), userDto.getPassword(), user, userDto.getDepositMoney()));
        phoneRepository.save(new Phone(userDto.getNumber(), user));
        mailRepository.save(new Mail(userDto.getEmail(), user));
        return user;
    }
}