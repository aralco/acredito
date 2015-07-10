package com.bo.acredito.ui.lists;

import com.bo.acredito.domain.Customer;
import com.bo.acredito.ui.util.GenericFilterDecorator;
import com.bo.acredito.ui.util.GenericFilterGenerator;
import com.bo.acredito.util.Constants;
import com.bo.acredito.util.RefreshableTabComponent;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.*;
import org.tepi.filtertable.paged.PagedFilterTable;

/**
 * Created by alvaro on 9/7/15.
 */
public class CustomerForChargesList extends RefreshableTabComponent {
    PagedFilterTable<IndexedContainer> filterTableCustomer;
    private String[] tablePropertyIdsCustomer ={"code", "firstName", "lastName", "idNumber", "address.state.name", "address.province","address.address1","address.phone", "address.mobile"};
    private String[] tableHeadersCustomer ={"Código", "Nombres", "Apellidos", "identificación", "Departamento", "Provincia","Dirección1","Teléfono","Celular"};
    private JPAContainer containerCustomer;

    public CustomerForChargesList() {
        paintComponent();
    }
    private PagedFilterTable buildPagedTable(JPAContainer container){
        filterTableCustomer = new PagedFilterTable<IndexedContainer>();
        filterTableCustomer.setWidth("100%");

        filterTableCustomer.setFilterDecorator(new GenericFilterDecorator());
        filterTableCustomer.setFilterGenerator(new GenericFilterGenerator());

        filterTableCustomer.setFilterBarVisible(true);

        filterTableCustomer.setSelectable(true);
        filterTableCustomer.setImmediate(true);
        filterTableCustomer.setMultiSelect(true);
        filterTableCustomer.setRowHeaderMode(CustomTable.RowHeaderMode.INDEX);
        filterTableCustomer.setColumnCollapsingAllowed(true);
        filterTableCustomer.setColumnReorderingAllowed(true);
        filterTableCustomer.setContainerDataSource(container);

        filterTableCustomer.setVisibleColumns(tablePropertyIdsCustomer);
        filterTableCustomer.setColumnHeaders(tableHeadersCustomer);

        return filterTableCustomer;
    }

    @Override
    public void paintComponent() {
        Panel customerListPanel=new Panel("Venta por cliente");
        customerListPanel.setSizeFull();

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        mainLayout.setMargin(true);

        containerCustomer = JPAContainerFactory.make(Customer.class, Constants.PERSISTENCE_UNIT);
        containerCustomer.addNestedContainerProperty("occupation.name");
        containerCustomer.addNestedContainerProperty("address.address1");
        containerCustomer.addNestedContainerProperty("address.province");
        containerCustomer.addNestedContainerProperty("address.phone");
        containerCustomer.addNestedContainerProperty("address.mobile");
        containerCustomer.addNestedContainerProperty("address.workPhone");
        containerCustomer.addNestedContainerProperty("address.state.name");
        filterTableCustomer =buildPagedTable(containerCustomer);
        filterTableCustomer.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                System.out.println("CLICK!!!!!!!!!!!!!!!!!!!!!!!!");
                /*Customer selectedCustomer = ((JPAContainerItem<Customer>)event.getItem()).getEntity();
                CustomerForm customerForm = new CustomerForm("Editar cliente", selectedCustomer.getId(), containerCustomer);
                getUI().addWindow(customerForm);*/
            }
        });
        VerticalLayout verticalLayout=new VerticalLayout();
        verticalLayout.setSpacing(true);
        verticalLayout.addComponent(filterTableCustomer);
        verticalLayout.addComponent(filterTableCustomer.createControls());
        customerListPanel.setContent(verticalLayout);

        mainLayout.addComponent(customerListPanel);
        setCompositionRoot(mainLayout);
    }
}