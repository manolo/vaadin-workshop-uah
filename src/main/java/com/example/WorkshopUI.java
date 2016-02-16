package com.example;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

@SpringUI
@Theme("valo")
public class WorkshopUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Label label = new Label("Hello World!");
        setContent(label);
    }

}
