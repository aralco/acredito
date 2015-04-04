package com.bo.acredito.ui.lists;

import com.bo.acredito.domain.Product;
import com.bo.acredito.domain.Supplier;
import com.bo.acredito.ui.forms.ProductForm;
import com.bo.acredito.ui.util.GenericFilterDecorator;
import com.bo.acredito.ui.util.GenericFilterGenerator;
import com.bo.acredito.ui.util.ListTableUtil;
import com.bo.acredito.util.Constants;
import com.bo.acredito.util.RefreshableTabComponent;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.JPAContainerItem;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import org.tepi.filtertable.paged.PagedFilterTable;

/**
 * Created by asejas on 29/03/15.
 */
public class SupplierList extends RefreshableTabComponent {
    PagedFilterTable<IndexedContainer> filterTable;
    private String[] tablePropertyIds={"code", "firstName", "lastName", "companyName", "nit"};
    private String[] tableHeaders={"Código", "Nombre", "Apellido", "Empresa", "Nit"};
    private JPAContainer container;
    private Button addButton;
    public SupplierList() {

        paintComponent();
    }

    @Override
    public void paintComponent() {
        container = JPAContainerFactory.make(Supplier.class, Constants.PERSISTENCE_UNIT);
        filterTable=ListTableUtil.buildPagedTable(tablePropertyIds, tableHeaders, filterTable, container);
        filterTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                /*Product selectedProduct = ((JPAContainerItem<Product>)event.getItem()).getEntity();
                ProductForm productForm = new ProductForm("Editar producto", selectedProduct.getId(), container);
                getUI().addWindow(productForm);*/
            }
        });
        addButton = new Button("Registrar nuevo proveedor",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        /*ProductForm productForm=new ProductForm("Nuevo proveedor", null, container);
                        getUI().addWindow(productForm);*/
                    }
                }
        );
        addButton.setDescription("Crea un nuevo proveedor");
        setCompositionRoot(
                ListTableUtil.createListLayout("Lista de proveedores", addButton, filterTable)
        );
    }
}
