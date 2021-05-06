package com.example.demo.service;

import com.example.demo.dao.Client;
import com.example.demo.repository.ClientRepository;

import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public void saveClient(Client client){
        checkPhoneNumber(client.getTelephone());
        checkEmail(client.getEmail());
        clientRepository.save(client);
    }

    private void checkPhoneNumber(String telephone) {
        if(telephone != null) {
            Pattern pattern = Pattern.compile("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$");
            Matcher matcher = pattern.matcher(telephone);
            if (!matcher.matches())
                throw new IllegalArgumentException("Неверный формат номера");
        }
    }

    private void checkEmail(String email) {

        if (email != null) {
            Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches())
                throw new IllegalArgumentException("Неверный формат email");
        }
    }
}
