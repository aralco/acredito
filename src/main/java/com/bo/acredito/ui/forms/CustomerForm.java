package com.bo.acredito.ui.forms;

/**
 * Created by asejas on 2/6/14.
 */
import com.bo.acredito.domain.Customer;
import com.bo.acredito.domain.IdTypeEnum;
import com.bo.acredito.domain.Occupation;
import com.bo.acredito.ui.customfields.IdTypeSelector;
import com.bo.acredito.ui.customfields.OccupationSelector;
import com.bo.acredito.util.Constants;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

public class CustomerForm extends CustomComponent {

    public CustomerForm() {
        JPAContainer<Customer> customerContainer = JPAContainerFactory.make(Customer.class,
                Constants.PersistenceUnit);

        FormLayout formLayout = new FormLayout();

        //final FieldGroup fieldGroup = new FieldGroup(customerContainer.getItem(customerContainer.getItem(new Long(401)))) {
        final FieldGroup fieldGroup = new FieldGroup(new BeanItem<Customer>(new Customer())) {
            /*
             * Override configureField to add a bean validator to each field.
             */
            @Override
            protected void configureField(Field<?> field) {
                super.configureField(field);
                // Add Bean validators if there are annotations
                // Note that this requires a bean validation implementation to
                // be available.
                BeanValidator validator = new BeanValidator(Customer.class,
                        getPropertyId(field).toString());
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
                if (type.isAssignableFrom(IdTypeEnum.class)) {
                    return (T) new IdTypeSelector();
                }
                T field=super.createField(type, fieldType);
                if (field instanceof TextField) {
                       ((TextField) field).setNullRepresentation("");
       	        }
                return field;
            }
        });

        formLayout.addComponent(fieldGroup.buildAndBind("Nombres: ","firstName"));
        formLayout.addComponent(fieldGroup.buildAndBind("Apellidos: ","lastName"));
        formLayout.addComponent(fieldGroup.buildAndBind("Saludo: ","salutation"));
        formLayout.addComponent(fieldGroup.buildAndBind("Tipo de identificación: ","idType"));
        formLayout.addComponent(fieldGroup.buildAndBind("Número de identificación: ","idNumber"));
        formLayout.addComponent(fieldGroup.buildAndBind("Fecha de nacimiento: ","birthday"));

        formLayout.addComponent(fieldGroup.buildAndBind("Dirección: ","address"));
        formLayout.addComponent(fieldGroup.buildAndBind("Persona de contacto1: ","contact1"));
        formLayout.addComponent(fieldGroup.buildAndBind("Persona de contacto2: ","contact2"));
        formLayout.addComponent(fieldGroup.buildAndBind("Persona de contacto3: ","contact3"));
        formLayout.addComponent(fieldGroup.buildAndBind("Ciudad: ","city"));
        formLayout.addComponent(fieldGroup.buildAndBind("Ocupación: ","occupation"));
        formLayout.addComponent(fieldGroup.buildAndBind("Observaciones: ","notes"));

        Button saveButton = new Button("Save");
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    fieldGroup.commit();
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
            }
        });

        formLayout.addComponent(saveButton);
        formLayout.addComponent(cancelButton);

        formLayout.setMargin(true);
        formLayout.setSpacing(true);
        setCompositionRoot(formLayout);
    }
}