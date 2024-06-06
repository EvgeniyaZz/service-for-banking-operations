package com.github.EvgeniyaZz.bank.service;

import com.github.EvgeniyaZz.bank.model.Phone;
import com.github.EvgeniyaZz.bank.model.User;
import com.github.EvgeniyaZz.bank.repository.PhoneRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PhoneService {

    private final PhoneRepository phoneRepository;
    private final EntityManager em;

    public Phone save(String number, int userId) {
        phoneRepository.checkExist(number);
        Phone newPhone = new Phone(number);
        newPhone.setUser(em.find(User.class, userId));
        return phoneRepository.save(newPhone);
    }

    public void delete(int id, int userId) {
        phoneRepository.checkLast(userId);
        phoneRepository.deleteExisted(id);
    }
}
