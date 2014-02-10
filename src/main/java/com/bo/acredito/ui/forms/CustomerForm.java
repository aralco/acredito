package com.bo.acredito.ui.forms;

/**
 * This is the CustomerForm
 * Created by asejas on 2/6/14.
 */

import com.bo.acredito.domain.*;
import com.bo.acredito.service.CustomerService;
import com.bo.acredito.ui.customfields.*;
import com.bo.acredito.util.Constants;
import com.bo.acredito.web.JEE6VaadinServlet;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import java.util.Date;

public class CustomerForm extends Window {

    public CustomerForm(String caption, Long customerId) {
        super(caption);
        setModal(true);

        final JPAContainer<Customer> customerContainer = JPAContainerFactory.make(Customer.class,
                Constants.PERSISTENCE_UNIT);

        FormLayout formLayoutLeft = new FormLayout();
        FormLayout formLayoutRight = new FormLayout();

        HorizontalLayout mainLayout=new HorizontalLayout();
        mainLayout.addComponent(formLayoutLeft);
        mainLayout.addComponent(formLayoutRight);

        Customer customer=new Customer();
        if(customerId!=null){
            customer = customerContainer.getItem(customerId).getEntity();
        }

        final FieldGroup fieldGroup = new FieldGroup(new BeanItem<Customer>(customer)) {
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
                BeanValidator validator = new BeanValidator(Customer.class,
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
                if (type.isAssignableFrom(Occupation.class)) {
                    return (T) new OccupationSelector();
                }
                else if (type.isAssignableFrom(IdTypeEnum.class)) {
                    return (T) new IdTypeSelector();
                }
                else if (type.isAssignableFrom(Contact.class)) {
                    return (T) new ContactSelector();
                }
                else if (type.isAssignableFrom(Address.class)) {
                    return (T) new AddressSelector();
                }
                else if (type.isAssignableFrom(State.class)) {
                    return (T) new StateSelector();
                }
                else if (type.isAssignableFrom(State.class)) {
                    return (T) new StateSelector();
                }
                else if (type.isAssignableFrom(Date.class)) {
                    PopupDateField popupDateField=new PopupDateField();
                    popupDateField.setDateFormat("yyyy-MM-dd");
                    return (T)popupDateField;
                }
                T field=super.createField(type, fieldType);
                if (field instanceof TextField) {
                       ((TextField) field).setNullRepresentation("");
       	        }
                return field;
            }
        });
        formLayoutLeft.addComponent(fieldGroup.buildAndBind("Saludo: ","salutation"));
        formLayoutLeft.addComponent(fieldGroup.buildAndBind("Nombres: ","firstName"));
        formLayoutLeft.addComponent(fieldGroup.buildAndBind("Apellidos: ","lastName"));
        formLayoutLeft.addComponent(fieldGroup.buildAndBind("Tipo de identificación: ","idType"));
        formLayoutLeft.addComponent(fieldGroup.buildAndBind("Número de identificación: ","idNumber"));
        formLayoutLeft.addComponent(fieldGroup.buildAndBind("Fecha de nacimiento: ","birthday"));
        formLayoutLeft.addComponent(fieldGroup.buildAndBind("Lugar de nacimiento: ","state"));
        formLayoutLeft.addComponent(fieldGroup.buildAndBind("Ocupación: ","occupation"));
        formLayoutLeft.addComponent(fieldGroup.buildAndBind("Persona de contacto1: ","contact1"));
        formLayoutLeft.addComponent(fieldGroup.buildAndBind("Persona de contacto2: ","contact2"));
        formLayoutLeft.addComponent(fieldGroup.buildAndBind("Persona de contacto3: ","contact3"));

        formLayoutRight.addComponent(fieldGroup.buildAndBind("Dirección: ","address"));
        //formLayoutRight.addComponent(fieldGroup.buildAndBind("Observaciones: ","notes"));
        TextArea notes=new TextArea("Observaciones");
        notes.setColumns(15);
        notes.setNullRepresentation("");
        formLayoutRight.addComponent(notes);
        fieldGroup.bind(notes,"notes");

        Button saveButton = new Button("Save");
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    fieldGroup.commit();
                    Customer customer = ((BeanItem<Customer>) fieldGroup.getItemDataSource()).getBean();

                    CustomerService customerService=((JEE6VaadinServlet) VaadinServlet.getCurrent()).getCustomerService();
                    if(customer.getId()==null){
                        customerService.saveCustomer(customer);
                    }
                    else{
                        customerService.updateCustomer(customer);
                    }
                    close();
                    fieldGroup.discard();
                } catch (FieldGroup.CommitException e) {
                    Notification.show("Problema al guardar el cliente: "
                            + e.getCause().getMessage(),
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
        buttonsLayout.addComponent(saveButton);
        buttonsLayout.addComponent(cancelButton);
        formLayoutRight.addComponent(buttonsLayout);

        formLayoutLeft.setSpacing(true);
        formLayoutLeft.setSizeUndefined();
        formLayoutRight.setSpacing(true);
        formLayoutRight.setSizeUndefined();

        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        mainLayout.setSizeUndefined();
        setContent(mainLayout);
    }
}