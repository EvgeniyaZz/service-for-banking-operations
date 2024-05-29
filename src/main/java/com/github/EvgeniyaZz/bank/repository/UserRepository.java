package com.github.EvgeniyaZz.bank.repository;

import com.github.EvgeniyaZz.bank.model.User;
import com.github.EvgeniyaZz.bank.util.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    default User getExisted(int id) {
        return findById(id).orElseThrow(() -> new NotFoundException("Entity with id=" + id + " not found"));
    }

}
