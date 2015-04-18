package com.bo.acredito.ui.forms;

/**
 * This is the SupplierForm
 * Created by asejas on 17/4/15.
 */

import com.bo.acredito.MyVaadinUI;
import com.bo.acredito.domain.*;
import com.bo.acredito.service.CustomerService;
import com.bo.acredito.service.ProductService;
import com.bo.acredito.service.SupplierService;
import com.bo.acredito.ui.customfields.*;
import com.bo.acredito.web.JEE6VaadinServlet;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import org.eclipse.persistence.exceptions.OptimisticLockException;

import java.util.Date;

public class SupplierForm extends Window {
    private JPAContainer<Supplier> supplierContainer;
    public SupplierForm(String caption, Long productId, JPAContainer<Supplier> parentContainer) {
        super(caption);
        supplierContainer =parentContainer;
        setModal(true);

        FormLayout formLayout = new FormLayout();

        HorizontalLayout mainLayout=new HorizontalLayout();
        mainLayout.addComponent(formLayout);

        Supplier supplier=new Supplier();
        if(productId!=null){
            supplier = supplierContainer.getItem(productId).getEntity();
        }

        final FieldGroup fieldGroup = new FieldGroup(new BeanItem<Supplier>(supplier)) {
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
                BeanValidator validator = new BeanValidator(Supplier.class,
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
                if (type.isAssignableFrom(Address.class)) {
                    return (T) new AddressSelector2();
                }

                T field=super.createField(type, fieldType);
                if (field instanceof TextField) {
                    ((TextField) field).setNullRepresentation("");
                }
                return field;
            }
        });

        formLayout.addComponent(fieldGroup.buildAndBind("Nombre: ", "firstName"));
        formLayout.addComponent(fieldGroup.buildAndBind("Apellido: ","lastName"));
        formLayout.addComponent(fieldGroup.buildAndBind("Nombre de la compañía: ","companyName"));
        formLayout.addComponent(fieldGroup.buildAndBind("NIT: ","nit"));
        formLayout.addComponent(fieldGroup.buildAndBind("Dirección: ","address"));

        Button saveButton = new Button("Save");
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    fieldGroup.commit();
                    Supplier supplier = ((BeanItem<Supplier>) fieldGroup.getItemDataSource()).getBean();
                    Office office = ((MyVaadinUI) UI.getCurrent()).getEmployee().getOffice();
                    AddressSelector2 addressSelector2= (AddressSelector2) fieldGroup.getField("address");
                    supplier.setAddress(addressSelector2.getValue());

                    SupplierService supplierService=((JEE6VaadinServlet) VaadinServlet.getCurrent()).getSupplierService();

                    if(supplier.getId()==null){
                        supplier.setOffice(office);
                        supplierService.saveSupplier(supplier);
                    }
                    else{
                        supplier.setOffice(office);
                        supplierService.updateSupplier(supplier);
                    }
                    close();
                    fieldGroup.discard();

                }catch (Exception e) {
                    System.out.println("***********EXCEPTION!!!!!!!!!!!!!!! ");
                    e.printStackTrace();
                    Notification.show("Problema al guardar el proveedor: "
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
        supplierContainer.refresh();
    }
}