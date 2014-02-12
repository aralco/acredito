package com.bo.acredito.ui.customfields;

import com.bo.acredito.domain.Address;
import com.bo.acredito.domain.State;
import com.bo.acredito.util.Constants;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.*;

/**
 * Created by asejas on 2/12/14.
 */
public class AddressSelector2 extends CustomField<Address> {
    private FieldGroup fieldGroup;

    private VerticalLayout verticalLayout=new VerticalLayout();

    final JPAContainer<Address> addressContainer = JPAContainerFactory.make(Address.class,
            Constants.PERSISTENCE_UNIT);
    final JPAContainer<State> stateContainer = JPAContainerFactory.make(State.class,
            Constants.PERSISTENCE_UNIT);
    public AddressSelector2() {

    }

    @Override
    protected Component initContent() {
        return verticalLayout;
    }
    @Override
    public void setPropertyDataSource(Property newDataSource) {
        super.setPropertyDataSource(newDataSource);
        setAddress((Address) newDataSource.getValue());
    }

    @Override
    public void setValue(Address newValue) throws Property.ReadOnlyException,
            Converter.ConversionException {
        setAddress(newValue);
        super.setValue(newValue);
    }

    private void setAddress(Address value) {
        fieldGroup = new FieldGroup(new BeanItem<Address>(value)) {
            @Override
            protected void configureField(Field<?> field) {
                super.configureField(field);
                String propertyName=getPropertyId(field).toString();
                BeanValidator validator = new BeanValidator(Address.class,
                        propertyName);
                field.addValidator(validator);
                if (field.getLocale() != null) {
                    validator.setLocale(field.getLocale());
                }
            }
        };

        fieldGroup.setFieldFactory(new DefaultFieldGroupFieldFactory() {
            @Override
            public <T extends Field> T createField(Class<?> type,
                                                   Class<T> fieldType) {
                if (type.isAssignableFrom(State.class)) {
                    return (T)new StateSelector();
                }

                T field=super.createField(type, fieldType);
                if (field instanceof TextField) {
                    ((TextField) field).setNullRepresentation("");
                }
                return field;
            }
        });

        verticalLayout.addComponent(fieldGroup.buildAndBind("Departamento: ", "state"));
        verticalLayout.addComponent(fieldGroup.buildAndBind("Provincia: ","province"));
        verticalLayout.addComponent(fieldGroup.buildAndBind("Dirección: ","address1"));
        verticalLayout.addComponent(fieldGroup.buildAndBind("Direccion2: ","address2"));
        verticalLayout.addComponent(fieldGroup.buildAndBind("Teléfono: ","phone"));
        verticalLayout.addComponent(fieldGroup.buildAndBind("Celular: ","mobile"));
        verticalLayout.addComponent(fieldGroup.buildAndBind("Teléfono de trabajo: ","workPhone"));

    }
    @Override
    public Class<? extends Address> getType() {
        return Address.class;
    }

    @Override
    public Address getValue() {
        Address address=null;
        try {
            fieldGroup.commit();
            address = ((BeanItem<Address>) fieldGroup.getItemDataSource()).getBean();
        } catch (FieldGroup.CommitException e) {
            e.printStackTrace();
        }
        if(address==null){
            address=super.getValue();
        }
        return address;
    }
}
