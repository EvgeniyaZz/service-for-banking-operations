package com.github.EvgeniyaZz.bank.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PhoneServiceTest extends AbstractServiceTest {

    @Autowired
    PhoneService service;

    @Test
    void saveExisted() {
        assertThrows(ResponseStatusException.class, () -> service.save("79500024743", 1));
    }

    @Test
    void deleteLast() {
        assertThrows(ResponseStatusException.class, () -> service.delete(1, 1));
    }
}