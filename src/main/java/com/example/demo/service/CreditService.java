package com.example.demo.service;

import com.example.demo.dao.Credit;
import com.example.demo.repository.CreditRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreditService {

    private final CreditRepository creditRepository;

    public void saveCredit(Credit credit){
        checkForNull(credit);
        creditRepository.save(credit);
    }

    private void checkForNull(Credit credit) {
        if(credit.getPercent() < 0)
            throw new IllegalArgumentException("Проценты по кредиту не могут быть отрицательными");
        else if(credit.getLimit() < 0)
            throw new IllegalArgumentException("Лимит по кредиту не может быть отрицательным");
    }
}
