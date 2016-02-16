package com.example.ui;

import com.example.service.GreetingService;
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
        TabSheet tabs = new TabSheet();
        tabs.addTab(buildGreetingComponent(), "Greeting");
        tabs.addTab(buildAdminComponent(), "Admin");
        setContent(tabs);
    }

    private Component buildGreetingComponent() {
        TextField textField = new TextField("Name");

        Button button = new Button("Greet");
        button.addClickListener(e -> greet(textField.getValue()));

        return new VerticalLayout(textField, button);
    }

    private void greet(String name) {
        Notification.show(greetingService.greet(name));
    }

    private Component buildAdminComponent() {
        return new Label("TODO");
    }

}
