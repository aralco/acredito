package com.bo.acredito.ui.customfields;

import com.bo.acredito.domain.Country;
import com.bo.acredito.domain.State;
import com.bo.acredito.util.Constants;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Property;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.VerticalLayout;

/**
 * This is a selector for state (updating state from country)
 */
public class StateSelector extends CustomField<State> {
    private ComboBox country = new ComboBox("País");
    private ComboBox state = new ComboBox("Ciudad");

    private JPAContainer<Country> countryContainer;
    private JPAContainer<State> stateContainer;
    private boolean countryListenerEnabled=true;

    public StateSelector() {
        countryContainer = JPAContainerFactory.make(Country.class,
                Constants.PersistenceUnit);
        stateContainer = JPAContainerFactory.make(State.class,
                Constants.PersistenceUnit);
        state.setEnabled(false);
        country.setContainerDataSource(countryContainer);
        country.setItemCaptionPropertyId("name");
        country.setImmediate(true);
        country.setTextInputAllowed(false);
        state.setContainerDataSource(stateContainer);
        state.setItemCaptionPropertyId("name");
        state.setTextInputAllowed(false);

        country.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(
                    com.vaadin.data.Property.ValueChangeEvent event) {
                if(countryListenerEnabled){
                    if (country.getValue() == null) {
                        setValue(null);
                        state.setEnabled(false);
                    } else {
                        Country countryEntity = countryContainer
                                .getItem(country.getValue()).getEntity();

                        stateContainer.removeAllContainerFilters();
                        stateContainer.addContainerFilter(new Compare.Equal("country", countryEntity));
                        stateContainer.applyFilters();
                        state.setValue(null);
                        state.setEnabled(true);
                    }
                }
            }
        });
        state.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(
                    com.vaadin.data.Property.ValueChangeEvent event) {
                if (state.getValue() == null) {
                    setValue(null, false);
                } else {
                    State entity = stateContainer
                            .getItem(state.getValue()).getEntity();
                    if(country.getValue()==null){
                        countryListenerEnabled=false;
                        country.setValue(entity.getCountry().getId());
                        countryListenerEnabled=true;
                    }
                    setValue(entity, false);
                    setEnabled(true);
                }
            }
        });
    }

    @Override
    protected Component initContent() {
        VerticalLayout verticalLayout=new VerticalLayout();
        verticalLayout.addComponent(country);
        verticalLayout.addComponent(state);
        return verticalLayout;
    }
    @Override
    public void setPropertyDataSource(Property newDataSource) {
        super.setPropertyDataSource(newDataSource);
        setState((State) newDataSource.getValue());
    }

    @Override
    public void setValue(State newValue) throws ReadOnlyException,
            Converter.ConversionException {
        setState(newValue);
        super.setValue(newValue);
    }

    private void setState(State value) {
        state.setValue(value != null ? value.getId() : null);
        if(state.getValue()!=null){
            state.setEnabled(true);
        }
    }
    @Override
    public Class<? extends State> getType() {
        return State.class;
    }

}
