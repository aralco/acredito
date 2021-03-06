package com.bo.acredito.ui.customfields;

import com.bo.acredito.domain.Address;
import com.bo.acredito.domain.Country;
import com.bo.acredito.domain.State;
import com.bo.acredito.util.Constants;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Property;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.ui.*;

/**
 * Created by asejas on 2/8/14.
 */
public class AddressSelector extends CustomField<Address>

{
    private TextField address1=new TextField("Dirección1");
    private TextField address2=new TextField("Dirección2");
    private TextField province=new TextField("Provincia");
    private TextField phone=new TextField("Telefono");
    private TextField mobile=new TextField("Celular");
    private TextField workPhone=new TextField("Telefono de trabajo");
    private TextField id;
    private ComboBox country = new ComboBox("País");
    private ComboBox state = new ComboBox("Ciudad");
    private JPAContainer<Country> countryContainer;
    private JPAContainer<State> stateContainer;

    private final Long DEFAULTCOUNTRYID=27L; //Bolivia
    public AddressSelector() {
        province.setNullRepresentation("");
        address1.setNullRepresentation("");
        address2.setNullRepresentation("");
        phone.setNullRepresentation("");
        mobile.setNullRepresentation("");
        workPhone.setNullRepresentation("");
        id=new TextField();
        id.setVisible(false);

        province.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(
                    com.vaadin.data.Property.ValueChangeEvent event) {
                selectorValueChange();
            }
        });
        address1.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(
                    com.vaadin.data.Property.ValueChangeEvent event) {
                selectorValueChange();
            }
        });
        address2.addValueChangeListener(new Property.ValueChangeListener() {
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
        mobile.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(
                    com.vaadin.data.Property.ValueChangeEvent event) {
                selectorValueChange();
            }
        });
        workPhone.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(
                    com.vaadin.data.Property.ValueChangeEvent event) {
                selectorValueChange();
            }
        });
        //**************************************State related
        countryContainer = JPAContainerFactory.make(Country.class,
                Constants.PERSISTENCE_UNIT);
        countryContainer.addContainerFilter(new Compare.Equal("id", DEFAULTCOUNTRYID));

        stateContainer = JPAContainerFactory.make(State.class,
                Constants.PERSISTENCE_UNIT);
        country.setContainerDataSource(countryContainer);
        country.setItemCaptionPropertyId("name");
        country.setTextInputAllowed(false);
        country.setValue(DEFAULTCOUNTRYID);
        country.setReadOnly(true);

        stateContainer.addContainerFilter(new Compare.Equal("country.id", DEFAULTCOUNTRYID));
        state.setContainerDataSource(stateContainer);
        state.setItemCaptionPropertyId("name");
        state.setTextInputAllowed(false);
        state.setImmediate(true);
        state.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(
                    com.vaadin.data.Property.ValueChangeEvent event) {
                if(state.getValue()==null){
                    if(getValue()!=null){
                        getValue().setState(null);
                    }
                }
                else{
                    if(getValue()!=null){
                        State stateEntity = stateContainer.getItem(state.getValue()).getEntity();
                        getValue().setState(stateEntity);
                    }
                }
            }
        });

    }

    @Override
    protected Component initContent() {
        VerticalLayout content = new VerticalLayout();
        content.setSpacing(true);
        content.addComponent(country);
        content.addComponent(state);
        content.addComponent(province);
        content.addComponent(address1);
        content.addComponent(address2);
        content.addComponent(mobile);
        content.addComponent(phone);
        content.addComponent(workPhone);
        content.addComponent(id);
        return content;
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

        id.setValue(value != null ? value.getId().toString() : null);
        province.setValue(value != null ? value.getProvince() : null);
        address1.setValue(value != null ? value.getAddress1() : null);
        address2.setValue(value != null ? value.getAddress2() : null);
        phone.setValue(value != null ? value.getPhone() : null);
        mobile.setValue(value != null ? value.getMobile() : null);
        workPhone.setValue(value != null ? value.getWorkPhone() : null);
        if(value!=null){
            State entity = stateContainer.getItem(value.getState().getId()).getEntity();
            state.setValue(entity.getId());
        }
        else{
            state.setValue(null);
        }
    }
    @Override
    public Class<? extends Address> getType() {
        return Address.class;
    }

    private void selectorValueChange(){
        Address entity= new Address();
        entity.setProvince(province.getValue());
        entity.setAddress1(address1.getValue());
        entity.setAddress2(address2.getValue());
        entity.setPhone(phone.getValue());
        entity.setMobile(mobile.getValue());
        entity.setWorkPhone(workPhone.getValue());

        if(id.getValue()!=null){
            entity.setId(id.getValue());
        }
        setValue(entity, false);
    }
}
