package com.bo.acredito.ui.lists;

import com.bo.acredito.domain.Sale;
import com.bo.acredito.ui.forms.SaleForm;
import com.bo.acredito.ui.util.GenericFilterDecorator;
import com.bo.acredito.ui.util.GenericFilterGenerator;
import com.bo.acredito.util.Constants;
import com.bo.acredito.util.RefreshableTabComponent;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.JPAContainerItem;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import org.tepi.filtertable.paged.PagedFilterControlConfig;
import org.tepi.filtertable.paged.PagedFilterTable;

/**
 * @author aralco
 */
public class SaleList extends RefreshableTabComponent {

    PagedFilterTable<IndexedContainer> filterTable;
    private String[] tablePropertyIds ={"code", "customer.firstName","customer.lastName","customer.idNumber", "customer.address.address1", "date", "saleType", "saleStatus"};
    private String[] tableHeaders ={"Código de venta", "Nombres","Apellidos","Identificación", "Dirección", "Fecha de venta", "Tipo de venta", "Estado de venta"};
    private Button addButton;
    private JPAContainer saleContainer;

    public SaleList() {
        paintComponent();
    }

    @Override
    public void paintComponent() {
        Panel saleListPanel=new Panel("Lista de ventas");
        saleListPanel.setSizeFull();

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        mainLayout.setMargin(true);

        addButton = new Button("Registrar nueva venta");
        addButton.setDescription("Crea una nueva venta");
        addButton.setStyleName(Reindeer.BUTTON_SMALL);
        addButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                SaleForm productForm = new SaleForm("Nueva venta", null, saleContainer);
                getUI().addWindow(productForm);
            }
        });

        saleContainer = JPAContainerFactory.make(Sale.class, Constants.PERSISTENCE_UNIT);
        saleContainer.addNestedContainerProperty("customer.firstName");
        saleContainer.addNestedContainerProperty("customer.lastName");
        saleContainer.addNestedContainerProperty("customer.idNumber");
        saleContainer.addNestedContainerProperty("customer.idType");
        saleContainer.addNestedContainerProperty("customer.address.state.name");
        saleContainer.addNestedContainerProperty("customer.address.address1");
        filterTable =buildPagedTable(saleContainer);
        filterTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                Sale selectedCustomer = ((JPAContainerItem<Sale>)event.getItem()).getEntity();
                SaleForm saleForm = new SaleForm("Editar Venta", selectedCustomer.getId(), saleContainer);
                getUI().addWindow(saleForm);
            }
        });
        VerticalLayout verticalLayout=new VerticalLayout();
        verticalLayout.setSpacing(true);
        verticalLayout.addComponent(addButton);
        verticalLayout.addComponent(filterTable);
        verticalLayout.addComponent(filterTable.createControls(new PagedFilterControlConfig()));
        saleListPanel.setContent(verticalLayout);

        mainLayout.addComponent(saleListPanel);
        setCompositionRoot(mainLayout);

    }

    private PagedFilterTable buildPagedTable(JPAContainer container){
        filterTable = new PagedFilterTable<IndexedContainer>();
        filterTable.setWidth("100%");

        filterTable.setFilterDecorator(new GenericFilterDecorator());
        filterTable.setFilterGenerator(new GenericFilterGenerator());

        filterTable.setFilterBarVisible(true);

        filterTable.setSelectable(true);
        filterTable.setImmediate(true);
        filterTable.setMultiSelect(true);
        filterTable.setRowHeaderMode(CustomTable.RowHeaderMode.INDEX);
        filterTable.setColumnCollapsingAllowed(true);
        filterTable.setColumnReorderingAllowed(true);
        filterTable.setContainerDataSource(container);

        filterTable.setVisibleColumns(tablePropertyIds);
        filterTable.setColumnHeaders(tableHeaders);

        return filterTable;
    }

}
