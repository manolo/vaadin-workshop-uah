package com.example.ui;

import com.example.repository.Customer;
import com.example.service.GreetingService;
import com.vaadin.annotations.Theme;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("valo")
public class WorkshopUI extends UI implements FieldGroup.CommitHandler {

    @Autowired
    private GreetingService greetingService;

    private BeanItemContainer<Customer> container = new BeanItemContainer<>(Customer.class);

    private TextField textField;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        TabSheet tabs = new TabSheet();
        tabs.setSizeFull();
        tabs.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabs.addStyleName(ValoTheme.TABSHEET_EQUAL_WIDTH_TABS);
        tabs.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        tabs.addTab(buildGreetingComponent(), "Greeting", FontAwesome.COMMENTS);
        tabs.addTab(buildAdminComponent(), "Admin", FontAwesome.USERS);
        setContent(tabs);

        textField.focus();

        updateContainer();
    }

    private Component buildGreetingComponent() {
        textField = new TextField("Name");
        textField.setNullRepresentation("");

        Button button = new Button("Greet", FontAwesome.COMMENT);
        button.addStyleName(ValoTheme.BUTTON_PRIMARY);
        button.addClickListener(e -> greet(textField.getValue()));
        button.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        VerticalLayout panelContent = new VerticalLayout(textField, button);
        panelContent.setMargin(true);
        panelContent.setSpacing(true);

        Panel panel = new Panel("Greeting form", panelContent);
        panel.setSizeUndefined();

        VerticalLayout layout = new VerticalLayout(panel);
        layout.setSizeFull();
        layout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

        return layout;
    }

    private void greet(String name) {
        Notification.show("Greetings!", greetingService.greet(name), Notification.Type.TRAY_NOTIFICATION);
        textField.setValue("");
        textField.focus();
        updateContainer();
    }

    private Component buildAdminComponent() {
        Grid grid = new Grid(container);
        grid.setColumnOrder("id", "name", "address", "phone", "email");
        grid.setEditorEnabled(true);
        grid.getColumn("id").setEditable(false);
        grid.getEditorFieldGroup().addCommitHandler(this);

        VerticalLayout layout = new VerticalLayout(grid);
        layout.setMargin(true);

        return layout;
    }

    private void updateContainer() {
        container.removeAllItems();
        container.addAll(greetingService.findAllCustomers());
    }

    @Override
    public void preCommit(FieldGroup.CommitEvent commitEvent) throws FieldGroup.CommitException {
    }

    @Override
    public void postCommit(FieldGroup.CommitEvent commitEvent) throws FieldGroup.CommitException {
        BeanItem<Customer> item = (BeanItem<Customer>) commitEvent.getFieldBinder().getItemDataSource();
        Customer customer = item.getBean();
        greetingService.saveCustomer(customer);
    }

}
