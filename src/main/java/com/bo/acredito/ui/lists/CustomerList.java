package com.bo.acredito.ui.lists;

import com.bo.acredito.domain.Customer;
import com.bo.acredito.ui.forms.CustomerForm;
import com.bo.acredito.util.Constants;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import org.tepi.filtertable.paged.PagedFilterTable;

import java.util.Collection;

/**
 * Created by asejas on 2/7/14.
 */
public class CustomerList extends CustomComponent{
    PagedFilterTable<IndexedContainer> filterTable;
    private String[] tablePropertyIds={"code", "firstName", "lastName", "idNumber", "address.state.name", "address.province","address.address1","address.phone", "address.mobile"};
    private String[] tableHeaders={"Código", "Nombres", "Apellidos", "identificación", "Departamento", "Provincia","Dirección1","Teléfono","Celular"};
    final private Button addButton;
    private TextField searchTextField=new TextField();
    private Button searchButton=new Button("Buscar");
    private final JPAContainer container;

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
                CustomerForm customerForm=new CustomerForm("Nuevo cliente", null, container);
                getUI().addWindow(customerForm);
            }
        });

        container = JPAContainerFactory.make(Customer.class, Constants.PERSISTENCE_UNIT);
        container.addNestedContainerProperty("occupation.name");
        container.addNestedContainerProperty("address.address1");
        container.addNestedContainerProperty("address.province");
        container.addNestedContainerProperty("address.phone");
        container.addNestedContainerProperty("address.mobile");
        container.addNestedContainerProperty("address.workPhone");
        container.addNestedContainerProperty("address.state.name");
        filterTable=buildPagedTable(container);
        filterTable.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                Object itemCollection = event.getProperty().getValue();
                Object itemId=((Collection)itemCollection).iterator().next();
                CustomerForm customerForm = new CustomerForm("Editar cliente", (Long)itemId, container);
                getUI().addWindow(customerForm);
            }
        });
        VerticalLayout verticalLayout=new VerticalLayout();
        verticalLayout.setSpacing(true);
        verticalLayout.addComponent(addButton);
        verticalLayout.addComponent(filterTable);
        verticalLayout.addComponent(filterTable.createControls());
        customerListPanel.setContent(verticalLayout);

        mainLayout.addComponent(customerListPanel);

        setCompositionRoot(mainLayout);
        //setSizeFull();
    }
    private PagedFilterTable buildPagedTable(JPAContainer container){
        filterTable= new PagedFilterTable<IndexedContainer>();
        filterTable.setWidth("100%");

        filterTable.setFilterDecorator(new CustomerFilterDecorator());
        filterTable.setFilterGenerator(new CustomerFilterGenerator());

        filterTable.setFilterBarVisible(true);

        filterTable.setSelectable(true);
        filterTable.setImmediate(true);
        filterTable.setMultiSelect(true);

        filterTable.setRowHeaderMode(CustomTable.RowHeaderMode.INDEX);

        filterTable.setColumnCollapsingAllowed(true);

        filterTable.setColumnCollapsed("state", true);

        filterTable.setColumnReorderingAllowed(true);

        filterTable.setContainerDataSource(container);

        filterTable.setVisibleColumns(tablePropertyIds);
        filterTable.setColumnHeaders(tableHeaders);

        return filterTable;
    }

}
