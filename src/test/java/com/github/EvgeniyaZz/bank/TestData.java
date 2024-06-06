package com.github.EvgeniyaZz.bank;

import com.github.EvgeniyaZz.bank.model.*;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestData {

    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class, "password", "userDetail", "account", "phones", "mails");

    public static final MatcherFactory.Matcher<User> USER_ALL_MATCHER = MatcherFactory.usingAssertions(User.class,
            (a, e) -> assertThat(a).usingRecursiveComparison().ignoringFields("phones.user", "mails.user").isEqualTo(e),
            (a, e) -> assertThat(a).usingRecursiveFieldByFieldElementComparatorIgnoringFields("phones.user", "mails.user").isEqualTo(e));

    public static UserDetail userDetail1 = new UserDetail(1, "Захарова", "Евгения", "Владимировна", LocalDate.of(1992, 2, 19));
    public static UserDetail userDetail2 = new UserDetail(2, "Иванов", "Иван", "Иванович", LocalDate.of(1971, 11, 2));

    public static Account account1 = new Account(1, 1000);
    public static Account account2 = new Account(1, 500);

    public static User user1 = new User(1, "user1", "1234500", userDetail1, account1, List.of(Role.USER));
    public static User user2 = new User(2, "user2", "1230000", userDetail2, account2, List.of(Role.USER));


    public static Phone phone1 = new Phone(1, "79500024743");
    public static Phone phone2 = new Phone(2, "79113456789");

    public static Mail email1 = new Mail(1, "user@yandex.ru");
    public static Mail email2 = new Mail(2, "user2@gmail.com");

    static {
        user1.setUserDetail(userDetail1);
        user1.setAccount(account1);
        user1.setPhones(List.of(phone1));
        user1.setMails(List.of(email1));

        user2.setUserDetail(userDetail2);
        user2.setAccount(account2);
        user2.setPhones(List.of(phone2));
        user2.setMails(List.of(email2));
    }

    public static User createNew() {
        UserDetail userDetail = new UserDetail(3, "Екатерина", "Смирнова", "Петровна", LocalDate.of(1975, 3, 5));
        Account account = new Account(3, 5000);
        User user = new User(3, "kate555", "password", userDetail, account, List.of(Role.USER));
        Phone phone = new Phone("79112458989");
        Mail email = new Mail("kate@yandex.ru");

        user.setUserDetail(userDetail);
        user.setAccount(account);
        user.setPhones(List.of(phone));
        user.setMails(List.of(email));
        return user;
    }
}
