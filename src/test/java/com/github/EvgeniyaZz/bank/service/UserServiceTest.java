package com.github.EvgeniyaZz.bank.service;

import com.github.EvgeniyaZz.bank.dto.SignUpRequest;
import com.github.EvgeniyaZz.bank.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static com.github.EvgeniyaZz.bank.TestData.*;

@SpringBootTest
class UserServiceTest extends AbstractServiceTest {

    @Autowired
    UserService service;

    @Test
    void save() {
        User user = service.save(new SignUpRequest("Екатерина", "Смирнова", "Петровна",
                LocalDate.of(1975, 3, 5), "kate555", "password", "79112458989",
                "kate@yandex.ru", 5000));

        USER_MATCHER.assertMatch(user, createNew());
    }

    @Test
    void getByLogin() {
        User user = service.getByLogin("user1");
        USER_MATCHER.assertMatch(user, user1);
    }
}