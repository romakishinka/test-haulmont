package com.example.demo.service;

import com.example.demo.dao.Client;
import com.example.demo.dao.Credit;
import com.example.demo.dao.KeyForOffer;
import com.example.demo.dao.Offer;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.CreditRepository;
import com.example.demo.repository.OfferRepository;
import com.vaadin.flow.component.textfield.IntegerField;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.demo.Utils.CreditConstant.AUTO_CREDIT;
import static com.example.demo.Utils.CreditConstant.CONSUMER_CREDIT;
import static com.example.demo.Utils.CreditConstant.MORTGAGE_CREDIT;

@Service
@AllArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;
    private final ClientRepository clientRepository;
    private final CreditRepository creditRepository;

    public void saveOffer(String clientNameValue, String creditNameValue, IntegerField amount) {
        Client client = clientRepository.findByName(clientNameValue).get(0);
        Credit credit = creditRepository.findByName(creditNameValue).get(0);
        KeyForOffer key = new KeyForOffer();
        key.setClientID(client.getId());
        key.setCreditID(credit.getId());
        checkLimitCredit(credit, amount.getValue());
        offerRepository.save(new Offer(key, client, credit, amount.getValue()));
    }

    private void checkLimitCredit(Credit credit, Integer amount) {

        int limit;
        if(credit.getName().equals(AUTO_CREDIT.getName()))
            limit = AUTO_CREDIT.getLimit();
        else if(credit.getName().equals(MORTGAGE_CREDIT.getName()))
            limit = MORTGAGE_CREDIT.getLimit();
        else
            limit = CONSUMER_CREDIT.getLimit();

        if(limit < amount)
            throw new IllegalArgumentException("Превышен лимит по кредиту");
    }

}
