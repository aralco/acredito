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
    private String[] tablePropertyIds={"firstName","lastName", "code", "birthday", "occupation.name"};
    private String[] tableHeaders={"Nombre","Apellido", "Código de cliente", "Fecha de nacimiento", "Ocupación",};
    final private Button addButton;

    public CustomerList() {
        addButton = new Button("+");
        addButton.setDescription("Crear nuevo cliente");
        addButton.setStyleName(Reindeer.BUTTON_SMALL);
        addButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                CustomerForm customerForm=new CustomerForm("Nuevo cliente", null);
                getUI().addWindow(customerForm);
            }
        });

        JPAContainer container = JPAContainerFactory.make(Customer.class, Constants.PersistenceUnit);
        container.addNestedContainerProperty("occupation.name");
        customerTable =new Table(null, container);
        customerTable.setContainerDataSource(container);
        customerTable.setVisibleColumns(tablePropertyIds);
        customerTable.setSelectable(true);
        customerTable.setImmediate(true);
        setSizeFull();
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
        Panel panel=new Panel("Lista de clientes");
        panel.setSizeFull();
        panel.setContent(verticalLayout);
        setCompositionRoot(panel);
    }
}
