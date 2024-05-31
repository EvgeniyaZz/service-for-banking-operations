package com.github.EvgeniyaZz.bank;

import com.github.EvgeniyaZz.bank.model.Account;
import com.github.EvgeniyaZz.bank.model.Mail;
import com.github.EvgeniyaZz.bank.model.Phone;
import com.github.EvgeniyaZz.bank.model.User;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestData {

    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class, "account", "phones", "mails");

    public static final MatcherFactory.Matcher<User> USER_ALL_MATCHER = MatcherFactory.usingAssertions(User.class,
            (a, e) -> assertThat(a).usingRecursiveComparison().ignoringFields("account.user", "phones.user", "mails.user").isEqualTo(e),
            (a, e) -> assertThat(a).usingRecursiveFieldByFieldElementComparatorIgnoringFields("account.user", "phones.user", "mails.user").isEqualTo(e));

    public static User user1 = new User(1, "Захарова", "Евгения", "Владимировна", LocalDate.of(1992, 2, 19));
    public static User user2 = new User(2, "Иванов", "Иван", "Иванович", LocalDate.of(1971, 11, 2));

    public static Account account1 = new Account("user1", "1234500", 1000);
    public static Account account2 = new Account("user2", "1230000", 500);

    public static Phone phone1 = new Phone("79500024743");
    public static Phone phone2 = new Phone("79113456789");

    public static Mail email1 = new Mail("user@yandex.ru");
    public static Mail email2 = new Mail("user2@gmail.com");

    static {
        user1.setAccount(account1);
        user1.setPhones(List.of(phone1));
        user1.setMails(List.of(email1));

        user2.setAccount(account2);
        user2.setPhones(List.of(phone2));
        user2.setMails(List.of(email2));
    }

}
