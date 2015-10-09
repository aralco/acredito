package com.bo.acredito.ui.forms;

/**
 * This is the CustomerForm
 * Created by asejas on 29/3/15.
 */

import com.bo.acredito.MyVaadinUI;
import com.bo.acredito.domain.Office;
import com.bo.acredito.domain.Product;
import com.bo.acredito.domain.Supplier;
import com.bo.acredito.service.ProductService;
import com.bo.acredito.ui.customfields.SupplierSelector;
import com.bo.acredito.web.JEE6VaadinServlet;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

public class ProductForm extends Window {
    private JPAContainer<Product> productContainer;
    public ProductForm(String caption, String productId, JPAContainer<Product> parentContainer) {
        super(caption);
        productContainer =parentContainer;
        setModal(true);

        FormLayout formLayout = new FormLayout();

        HorizontalLayout mainLayout=new HorizontalLayout();
        mainLayout.addComponent(formLayout);

        Product product=new Product();
        if(productId!=null){
            product = productContainer.getItem(productId).getEntity();
        }

        final FieldGroup fieldGroup = new FieldGroup(new BeanItem<Product>(product)) {
            /*
             * Override configureField to add a bean validator to each field.
             */
            @Override
            protected void configureField(Field<?> field) {
                super.configureField(field);
                // Add Bean validators if there are annotations
                // Note that this requires a bean validation implementation to
                // be available.
                String propertyName=getPropertyId(field).toString();
                BeanValidator validator = new BeanValidator(Product.class,
                        propertyName);
                field.addValidator(validator);
                if (field.getLocale() != null) {
                    validator.setLocale(field.getLocale());
                }
            }
        };

        /*
         * This is an example of a field factory that constructs a complex
         * field.
         */
        fieldGroup.setFieldFactory(new DefaultFieldGroupFieldFactory() {
            @Override
            public <T extends Field> T createField(Class<?> type,
                                                   Class<T> fieldType) {
                if (type.isAssignableFrom(Supplier.class)) {
                    return (T) new SupplierSelector();
                }

                T field=super.createField(type, fieldType);
                if (field instanceof TextField) {
                    ((TextField) field).setNullRepresentation("");
                }
                return field;
            }
        });

        formLayout.addComponent(fieldGroup.buildAndBind("Nombre: ", "name"));
        formLayout.addComponent(fieldGroup.buildAndBind("Costo: ","price"));
        formLayout.addComponent(fieldGroup.buildAndBind("Cantidad: ","quantity"));
        formLayout.addComponent(fieldGroup.buildAndBind("Proveedor: ","supplier"));
        formLayout.addComponent(fieldGroup.buildAndBind("Disponible: ","available"));

        TextArea notes=new TextArea("Descripción");
        notes.setColumns(15);
        notes.setNullRepresentation("");
        formLayout.addComponent(notes);
        fieldGroup.bind(notes,"notes");

        Button saveButton = new Button("Save");
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    fieldGroup.commit();
                    SupplierSelector supplierSelector= (SupplierSelector) fieldGroup.getField("supplier");

                    Product product = ((BeanItem<Product>) fieldGroup.getItemDataSource()).getBean();
                    product.setReservedQuantity(1);
                    product.setSupplier(supplierSelector.getValue());
                    ProductService productService=((JEE6VaadinServlet) VaadinServlet.getCurrent()).getProductService();
                    Office office = ((MyVaadinUI) UI.getCurrent()).getEmployee().getOffice();

                    if(product.getId()==null){
                        product.setOffice(office);
                        productService.saveProduct(product);
                    }
                    else{
                        product.setOffice(office);
                        productService.updateProduct(product);
                    }
                    close();
                    fieldGroup.discard();

                }catch (Exception e) {
                    Notification.show("Problema al guardar el producto: "
                                    + e.getMessage(),
                            Notification.Type.ERROR_MESSAGE);
                }
            }
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                fieldGroup.discard();
                close();
            }
        });
        HorizontalLayout buttonsLayout=new HorizontalLayout();
        buttonsLayout.setSpacing(true);
        buttonsLayout.addComponent(saveButton);
        buttonsLayout.addComponent(cancelButton);
        formLayout.addComponent(buttonsLayout);

        formLayout.setSpacing(true);
        formLayout.setSizeUndefined();

        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        mainLayout.setSizeUndefined();
        setContent(mainLayout);
    }

    @Override
    public void close() {
        super.close();
        productContainer.refresh();
    }
}