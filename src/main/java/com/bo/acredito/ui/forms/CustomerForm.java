package com.bo.acredito.ui.forms;

/**
 * This is the CustomerForm
 * Created by asejas on 2/6/14.
 */

import com.bo.acredito.MyVaadinUI;
import com.bo.acredito.domain.*;
import com.bo.acredito.service.CustomerService;
import com.bo.acredito.ui.customfields.AddressSelector2;
import com.bo.acredito.ui.customfields.IdTypeSelector;
import com.bo.acredito.ui.customfields.OccupationSelector;
import com.bo.acredito.ui.customfields.StateSelector;
import com.bo.acredito.web.JEE6VaadinServlet;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import java.util.Date;

public class CustomerForm extends Window {
    private JPAContainer<Customer> customerContainer;
    public CustomerForm(String caption, Long customerId, JPAContainer<Customer> parentContainer) {
        super(caption);
        customerContainer =parentContainer;
        setModal(true);

        //final JPAContainer<Customer> customerContainer = JPAContainerFactory.make(Customer.class,Constants.PERSISTENCE_UNIT);

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
//                else if (type.isAssignableFrom(Contact.class)) {
//                    return (T) new ContactSelector();
//                }
                else if (type.isAssignableFrom(Address.class)) {
                    return (T) new AddressSelector2();
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
        formLayoutLeft.addComponent(fieldGroup.buildAndBind("Ocupación: ","occupation"));
//        formLayoutLeft.addComponent(fieldGroup.buildAndBind("Persona de contacto1: ","contact1"));
//        formLayoutLeft.addComponent(fieldGroup.buildAndBind("Persona de contacto2: ","contact2"));
//        formLayoutLeft.addComponent(fieldGroup.buildAndBind("Persona de contacto3: ","contact3"));

        formLayoutRight.addComponent(fieldGroup.buildAndBind("Dirección: ","address"));
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
                    Office office = ((MyVaadinUI) UI.getCurrent()).getEmployee().getOffice();
                    AddressSelector2 addressSelector2= (AddressSelector2) fieldGroup.getField("address");

                    Customer customer = ((BeanItem<Customer>) fieldGroup.getItemDataSource()).getBean();
                    Address address=addressSelector2.getValue();
                    if(address.getOffice()==null){
                        address.setOffice(office);
                    }
                    customer.setAddress(address);
                    CustomerService customerService=((JEE6VaadinServlet) VaadinServlet.getCurrent()).getCustomerService();

                    if(customer.getId()==null){
                        customer.setOffice(office);
                        customerService.saveCustomer(customer);
                    }
                    else{
                        customer.setOffice(office);
                        customerService.updateCustomer(customer);
                    }
                    close();
                    fieldGroup.discard();

                }catch (Exception e) {
                    Notification.show("Problema al guardar el cliente: "
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

    @Override
    public void close() {
        super.close();
        customerContainer.refresh();
    }
}