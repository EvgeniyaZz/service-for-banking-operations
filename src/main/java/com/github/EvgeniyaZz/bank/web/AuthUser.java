package com.github.EvgeniyaZz.bank.web;

import com.github.EvgeniyaZz.bank.model.User;
import lombok.Getter;
import org.springframework.lang.NonNull;

@Getter
public class AuthUser extends org.springframework.security.core.userdetails.User {

    private final User user;

    public AuthUser(@NonNull User user) {
        super(user.getLogin(), user.getPassword(), user.getRoles());
        this.user = user;
    }

    public int id() {
        return user.id();
    }

    @Override
    public String toString() {
        return "AuthUser:" + id() + '[' + user.getLogin() + ']';
    }
}