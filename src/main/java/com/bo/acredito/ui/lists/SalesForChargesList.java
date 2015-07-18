package com.bo.acredito.ui.lists;

import com.bo.acredito.domain.Sale;
import com.bo.acredito.ui.forms.SimpleChargeForm;
import com.bo.acredito.ui.util.GenericFilterDecorator;
import com.bo.acredito.ui.util.GenericFilterGenerator;
import com.bo.acredito.util.Constants;
import com.bo.acredito.util.RefreshableTabComponent;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.JPAContainerItem;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import org.tepi.filtertable.paged.PagedFilterControlConfig;
import org.tepi.filtertable.paged.PagedFilterTable;

/**
 * This class implements the Sales for charges list (cobranzas)
 */
public class SalesForChargesList extends RefreshableTabComponent {
    PagedFilterTable<IndexedContainer> filterTableCustomer;
    private String[] tablePropertyIdsCustomer ={"code", "customer.firstName","customer.lastName","customer.idNumber", "customer.address.address1", "date", "saleType", "saleStatus"};
    private String[] tableHeadersCustomer ={"Código de venta", "Nombres","Apellidos","Identificación", "Dirección", "Fecha de venta", "Tipo de venta", "Estado de venta"};
    private JPAContainer containerSale;

    public SalesForChargesList() {
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

        containerSale = JPAContainerFactory.make(Sale.class, Constants.PERSISTENCE_UNIT);
        containerSale.addNestedContainerProperty("customer.firstName");
        containerSale.addNestedContainerProperty("customer.lastName");
        containerSale.addNestedContainerProperty("customer.idNumber");
        containerSale.addNestedContainerProperty("customer.idType");
        containerSale.addNestedContainerProperty("customer.address.state.name");
        containerSale.addNestedContainerProperty("customer.address.address1");
        filterTableCustomer =buildPagedTable(containerSale);
        filterTableCustomer.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                Sale selectedSale = ((JPAContainerItem<Sale>)event.getItem()).getEntity();

                SimpleChargeForm simpleChargeForm = new SimpleChargeForm("Confirmar pago", selectedSale.getId(), containerSale);
                getUI().addWindow(simpleChargeForm);
            }
        });
        VerticalLayout verticalLayout=new VerticalLayout();
        verticalLayout.setSpacing(true);
        verticalLayout.addComponent(filterTableCustomer);
        verticalLayout.addComponent(filterTableCustomer.createControls(new PagedFilterControlConfig()));
        customerListPanel.setContent(verticalLayout);

        mainLayout.addComponent(customerListPanel);
        setCompositionRoot(mainLayout);
    }
}