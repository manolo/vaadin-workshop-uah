package com.example;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("valo")
public class WorkshopUI extends UI {

    @Autowired
    private GreetingService greetingService;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        TextField textField = new TextField("Name");

        Button button = new Button("Greet");
        button.addClickListener(e -> Notification.show(greetingService.greet(textField.getValue())));

        VerticalLayout layout = new VerticalLayout(textField, button);
        setContent(layout);
    }

}
