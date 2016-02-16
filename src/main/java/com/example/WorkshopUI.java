package com.example;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

@SpringUI
@Theme("valo")
public class WorkshopUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Button button = new Button("Greet");
        button.addClickListener(e -> Notification.show("Hello World!"));
        setContent(button);
    }

}
