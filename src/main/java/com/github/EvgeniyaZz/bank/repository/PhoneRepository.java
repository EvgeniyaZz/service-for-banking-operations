package com.github.EvgeniyaZz.bank.repository;

import com.github.EvgeniyaZz.bank.model.Phone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface PhoneRepository extends ContactRepository<Phone> {

    @Query("SELECT p FROM Phone p WHERE p.number = :number")
    Optional<Phone> findByNumber(String number);

    default void checkExist(String number) {
        if (findByNumber(number).isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "number already exist");
        }
    }
}
