package com.bo.acredito.ui.customfields;

/**
 * Created by asejas on 2/6/14.
 */

import com.bo.acredito.domain.Occupation;
import com.bo.acredito.util.Constants;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Property;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomField;


/**
 * A custom field that allows selection of an address.
 */
public class OccupationSelector extends CustomField<Occupation> {
    private ComboBox occupation = new ComboBox();

    private JPAContainer<Occupation> container;

    public OccupationSelector() {
        container = JPAContainerFactory.make(Occupation.class,
                Constants.PersistenceUnit);

        occupation.setContainerDataSource(container);
        occupation.setItemCaptionPropertyId("name");

        occupation.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(
                    com.vaadin.data.Property.ValueChangeEvent event) {
                /*
                 * Modify the actual value of the custom field.
                 */
                if (occupation.getValue() == null) {
                    setValue(null, false);
                } else {
                    Occupation entity = container
                            .getItem(occupation.getValue()).getEntity();
                    setValue(entity, false);
                }
            }
        });
    }

    @Override
    protected Component initContent() {
        CssLayout cssLayout = new CssLayout();
        cssLayout.addComponent(occupation);
        return cssLayout;
    }
    @Override
    public void setPropertyDataSource(Property newDataSource) {
        super.setPropertyDataSource(newDataSource);
        setOccupation((Occupation) newDataSource.getValue());
    }

    @Override
    public void setValue(Occupation newValue) throws ReadOnlyException,
            Converter.ConversionException {
        setOccupation(newValue);
        super.setValue(newValue);
    }

    private void setOccupation(Occupation value) {
        occupation.setValue(value != null ? value.getId() : null);
    }
    @Override
    public Class<? extends Occupation> getType() {
        return Occupation.class;
    }
}