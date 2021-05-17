package com.example.demo.components;

import com.example.demo.dao.Client;
import com.example.demo.dao.Credit;
import com.example.demo.dao.KeyForOffer;
import com.example.demo.dao.Offer;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.CreditRepository;
import com.example.demo.repository.OfferRepository;
import com.example.demo.service.OfferService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class OfferEditor extends VerticalLayout implements KeyNotifier {
    private final OfferService offerService;
    private ComboBox clientName = new ComboBox("Имя клиента");
    private ComboBox creditName = new ComboBox("Тип кредита");
    private IntegerField amount = new IntegerField("Сумма займа");
    private Button save = new Button("Сохранить", VaadinIcon.CHECK.create());
    private Button cancel = new Button("Отмена");
    private HorizontalLayout actions = new HorizontalLayout(save, cancel);
    private Binder<Offer> binder = new Binder<>(Offer.class);
    @Setter
    private ChangeHandler changeHandler;

    @Autowired
    public OfferEditor(OfferService offerService, ClientRepository clientRepository, CreditRepository creditRepository) {
        this.offerService = offerService;

        add(clientName, creditName, amount, actions);
        clientName.setItems(clientRepository.findAll().stream().map(Client::getName));
        creditName.setItems(creditRepository.findAll().stream().map(Credit::getName));
        binder.bindInstanceFields(this);

        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        addKeyPressListener(Key.ENTER, c -> saveOffer());

        save.addClickListener(c -> saveOffer());
        cancel.addClickListener(c -> cancel());
        setVisible(false);
    }

    private void saveOffer() {
        String clientNameValue = (String) clientName.getValue();
        String creditNameValue = (String) creditName.getValue();
        if ((clientNameValue != null) || (creditNameValue != null)) {
            offerService.saveOffer(clientNameValue, creditNameValue, amount);
            changeHandler.onChange();
        }
    }

    private void cancel() {
        changeHandler.onChange();
    }

    public void openAddingForm() {
        setVisible(true);
        clientName.focus();
    }

}
