package com.bo.acredito.ui.lists;

import com.bo.acredito.domain.Customer;
import com.bo.acredito.ui.forms.CustomerForm;
import com.bo.acredito.util.Constants;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Property;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;

/**
 * Created by asejas on 2/7/14.
 */
public class CustomerList extends CustomComponent{
    final private Table customerTable;
    private String[] tablePropertyIds={"code", "fullName", "address", "address.phoneNumbers"};
    private String[] tableHeaders={"Código", "Nombre completo", "Dirección", "Teléfonos"};
    final private Button addButton;

    public CustomerList() {
        Panel customerListPanel=new Panel("Lista de clientes");
        customerListPanel.setSizeFull();

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        mainLayout.setMargin(true);

        addButton = new Button("Registrar nuevo cliente");
        addButton.setDescription("Crea un nuevo cliente");
        addButton.setStyleName(Reindeer.BUTTON_SMALL);
        addButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                CustomerForm customerForm=new CustomerForm("Nuevo cliente", null);
                getUI().addWindow(customerForm);
            }
        });

        JPAContainer container = JPAContainerFactory.make(Customer.class, Constants.PERSISTENCE_UNIT);
        container.addNestedContainerProperty("occupation.name");
        container.addNestedContainerProperty("address.phoneNumbers");
        customerTable =new Table(null, container);
        //customerTable.setContainerDataSource(container);
        customerTable.setVisibleColumns(tablePropertyIds);
        customerTable.setSelectable(true);
        customerTable.setImmediate(true);
        customerTable.setColumnHeaders(tableHeaders);
        customerTable.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                Object itemId = event.getProperty().getValue();
                CustomerForm customerForm = new CustomerForm("Editar cliente", (Long)itemId);
                getUI().addWindow(customerForm);
            }
        });
        VerticalLayout verticalLayout=new VerticalLayout();
        verticalLayout.addComponent(addButton);
        verticalLayout.addComponent(customerTable);
        customerListPanel.setContent(verticalLayout);

        mainLayout.addComponent(customerListPanel);

        setCompositionRoot(mainLayout);
        //setSizeFull();
    }
}
