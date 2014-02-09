package com.bo.acredito.ui.customfields;

import com.bo.acredito.domain.Contact;
import com.vaadin.data.Property;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

/**
 * This class implements the ContactSelector
 * Created by asejas on 2/8/14.
 */
public class ContactSelector extends CustomField<Contact>

    {
        private TextField name=new TextField("Nombre");
        private TextField phone=new TextField("Telefono");
        private TextField id=new TextField();

        public ContactSelector() {
            name.setNullRepresentation("");
            phone.setNullRepresentation("");
            phone.setColumns(10);
            id.setVisible(false);

            name.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(
                        com.vaadin.data.Property.ValueChangeEvent event) {
                    selectorValueChange();
                }
            });
            phone.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(
                        com.vaadin.data.Property.ValueChangeEvent event) {
                    selectorValueChange();
                }
            });
    }

        @Override
        protected Component initContent() {
        HorizontalLayout content = new HorizontalLayout();
        content.setSpacing(true);
        content.addComponent(name);
        content.addComponent(phone);
        content.addComponent(id);
        return content;
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

        id.setValue(value != null ? value.getId().toString() : null);
        name.setValue(value != null ? value.getName() : null);
        phone.setValue(value != null ? value.getPhone() : null);
    }
    @Override
    public Class<? extends Contact> getType() {
        return Contact.class;
    }
    public void selectorValueChange() {
        Contact entity= new Contact();
        entity.setName(name.getValue());
        entity.setPhone(phone.getValue());
        if(id.getValue()!=null){
            entity.setId(new Long(id.getValue()));
        }
        setValue(entity, false);
    }

}
