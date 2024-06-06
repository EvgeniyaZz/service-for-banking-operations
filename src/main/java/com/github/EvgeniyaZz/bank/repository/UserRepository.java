package com.github.EvgeniyaZz.bank.repository;

import com.github.EvgeniyaZz.bank.model.User;
import com.github.EvgeniyaZz.bank.util.exception.NotFoundException;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {

    @Query("SELECT u FROM User u WHERE u.login = :login")
    Optional<User> findByLogin(String login);

    default User getExistedByLogin(String login) {
        return findByLogin(login).orElseThrow(() -> new NotFoundException("User with login=" + login + " not found"));
    }
}
