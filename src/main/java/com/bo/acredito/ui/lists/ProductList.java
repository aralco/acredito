package com.bo.acredito.ui.lists;

import com.bo.acredito.domain.Product;
import com.bo.acredito.service.ProductService;
import com.bo.acredito.ui.forms.ProductForm;
import com.bo.acredito.ui.util.GenericFilterDecorator;
import com.bo.acredito.ui.util.GenericFilterGenerator;
import com.bo.acredito.util.Constants;
import com.bo.acredito.util.RefreshableTabComponent;
import com.bo.acredito.web.JEE6VaadinServlet;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.JPAContainerItem;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import org.tepi.filtertable.paged.PagedFilterControlConfig;
import org.tepi.filtertable.paged.PagedFilterTable;

/**
 * Created by asejas on 29/03/15.
 */
public class ProductList extends RefreshableTabComponent {
    PagedFilterTable<IndexedContainer> filterTable;
    private String[] tablePropertyIds={"code", "name", "price", "available"};
    private String[] tableHeaders={"Código", "Nombre", "Precio", "Disponible"};
    private Button addButton;
    private JPAContainer container;

    public ProductList() {
        paintComponent();
    }

    private PagedFilterTable buildPagedTable(JPAContainer container){
        filterTable= new PagedFilterTable<IndexedContainer>();
        filterTable.setWidth("100%");

        filterTable.setFilterDecorator(new GenericFilterDecorator());
        filterTable.setFilterGenerator(new GenericFilterGenerator());

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

    @Override
    public void paintComponent() {
        Panel productListPanel=new Panel("Lista de productos");
        productListPanel.setSizeFull();

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        mainLayout.setMargin(true);

        addButton = new Button("Registrar nuevo producto");
        addButton.setDescription("Crea un nuevo producto");
        addButton.setStyleName(Reindeer.BUTTON_SMALL);
        addButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                ProductForm productForm = new ProductForm("Nuevo producto", null, container);
                getUI().addWindow(productForm);
            }
        });
        container = JPAContainerFactory.make(Product.class, Constants.PERSISTENCE_UNIT);
        filterTable=buildPagedTable(container);
        filterTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                Product selectedProduct = ((JPAContainerItem<Product>)event.getItem()).getEntity();
                ProductForm productForm = new ProductForm("Editar producto", selectedProduct.getId(), container);
                getUI().addWindow(productForm);
            }
        });
        VerticalLayout verticalLayout=new VerticalLayout();
        verticalLayout.setSpacing(true);
        verticalLayout.addComponent(addButton);
        verticalLayout.addComponent(filterTable);
        verticalLayout.addComponent(filterTable.createControls(new PagedFilterControlConfig()));
        productListPanel.setContent(verticalLayout);

        mainLayout.addComponent(productListPanel);

        setCompositionRoot(mainLayout);
    }
}
