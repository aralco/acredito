package com.bo.acredito.ui.customfields;

import com.bo.acredito.domain.Contact;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.*;

/**
 * This class implements the ContactSelector
 * Created by asejas on 2/8/14.
 */
public class ContactSelector extends CustomField<Contact>
{
    private FieldGroup fieldGroup;
    private HorizontalLayout horizontalLayout=new HorizontalLayout();

    public ContactSelector() {
        horizontalLayout.setSpacing(true);
    }

    @Override
    protected Component initContent() {
        return horizontalLayout;
    }
    @Override
    public void setPropertyDataSource(Property newDataSource) {
        super.setPropertyDataSource(newDataSource);
        setContact((Contact) newDataSource.getValue());
    }

    @Override
    public void setValue(Contact newValue) throws Property.ReadOnlyException,
        Converter.ConversionException {
        setContact(newValue);
        super.setValue(newValue);
    }

    private void setContact(Contact value) {
        fieldGroup = new FieldGroup(new BeanItem<Contact>(value)) {
            @Override
            protected void configureField(Field<?> field) {
                super.configureField(field);
                String propertyName=getPropertyId(field).toString();
                BeanValidator validator = new BeanValidator(Contact.class,
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
                T field=super.createField(type, fieldType);
                if (field instanceof TextField) {
                    ((TextField) field).setNullRepresentation("");
                }
                return field;
            }
        });

        horizontalLayout.addComponent(fieldGroup.buildAndBind("Nombre: ", "name"));
        horizontalLayout.addComponent(fieldGroup.buildAndBind("Teléfono: ", "phone"));

    }
    @Override
    public Class<? extends Contact> getType() {
        return Contact.class;
    }
    @Override
    public Contact getValue() {
        Contact contact=null;
        try {
            fieldGroup.commit();
            contact = ((BeanItem<Contact>) fieldGroup.getItemDataSource()).getBean();
        } catch (FieldGroup.CommitException e) {
            e.printStackTrace();
        }
        if(contact==null){
            contact=super.getValue();
        }
        return contact;
    }

}
