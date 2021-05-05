package com.example.demo.components;

import com.example.demo.dao.Client;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.ClientService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class ClientEditor extends VerticalLayout implements KeyNotifier {
    private final ClientRepository clientRepository;
    private final ClientService clientService;

    private Client client;
    private TextField name = new TextField("Имя");
    private TextField telephone = new TextField("Номер телефона");
    private TextField email = new TextField("Адрес электронной почты");
    private TextField document = new TextField("Номер паспорта");
    private Button save = new Button("Сохранить", VaadinIcon.CHECK.create());
    private Button cancel = new Button("Отмена", VaadinIcon.CHECK.create());
    private Button delete = new Button("Удалить", VaadinIcon.TRASH.create());
    private HorizontalLayout fields = new HorizontalLayout(name, telephone, email, document);
    private HorizontalLayout actions = new HorizontalLayout (save, cancel, delete);
    private Binder<Client> binder = new Binder<>(Client.class);
    @Setter
    private ChangeHandler changeHandler;

    @Autowired
    public ClientEditor(ClientRepository clientRepository, ClientService clientService) {
        this.clientRepository = clientRepository;
        this.clientService = clientService;

        add(fields, actions);
        binder.bindInstanceFields(this);

        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, c -> save());

        save.addClickListener(c -> save());
        delete.addClickListener(c -> delete());
        cancel.addClickListener(c -> cancel());
        setVisible(false);
    }

    private void delete() {
        clientRepository.delete(client);
        changeHandler.onChange();
    }

    private void save() {
        clientService.saveClient(client);
        changeHandler.onChange();
    }

    private void cancel() {
        changeHandler.onChange();
    }

    public void editClient(Client newClient) {
        if (newClient == null) {
            setVisible(false);
            return;
        }
        if (newClient.getId() != null) {
            client = clientRepository.findById(newClient.getId()).orElse(newClient);
        } else {
            client = newClient;
        }
        binder.setBean(client);
        setVisible(true);
        name.focus();

    }

}
