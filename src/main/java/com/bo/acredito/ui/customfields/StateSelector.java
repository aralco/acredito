package com.bo.acredito.ui.customfields;

import com.bo.acredito.domain.State;
import com.bo.acredito.util.Constants;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Property;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomField;

/**
 * This is a selector for state (updating state from country)
 */
public class StateSelector extends CustomField<State> {
    private ComboBox state = new ComboBox();
    private final Long DEFAULTCOUNTRYID=27L; //Bolivia
    private JPAContainer<State> container;

    public StateSelector() {
        container = JPAContainerFactory.make(State.class,
                Constants.PERSISTENCE_UNIT);
        container.addContainerFilter(new Compare.Equal("country.id", DEFAULTCOUNTRYID));
        state.setContainerDataSource(container);
        state.setItemCaptionPropertyId("name");
        state.setImmediate(true);
        state.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(
                    com.vaadin.data.Property.ValueChangeEvent event) {
                /*
                 * Modify the actual value of the custom field.
                 */
                if (state.getValue() == null) {
                    setValue(null, false);
                } else {
                    State entity = container
                            .getItem(state.getValue()).getEntity();
                    setValue(entity, false);
                }
            }
        });
    }

    @Override
    protected Component initContent() {
        CssLayout cssLayout = new CssLayout();
        cssLayout.addComponent(state);
        return cssLayout;
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
    }
    @Override
    public Class<? extends State> getType() {
        return State.class;
    }
}
