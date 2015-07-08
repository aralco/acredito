package com.bo.acredito.ui.customfields;

import com.bo.acredito.domain.Supplier;
import com.bo.acredito.util.Constants;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Property;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.*;

/**
 * This is a selector for supplier (updating supplier from product)
 * Created by asejas on 29/3/15.
 */
public class SupplierSelector extends CustomField<Supplier> {
    private ComboBox supplier = new ComboBox();
    private JPAContainer<Supplier> container;

    public SupplierSelector() {
        container = JPAContainerFactory.make(Supplier.class,
                Constants.PERSISTENCE_UNIT);
        supplier.setContainerDataSource(container);
        supplier.setItemCaptionMode(AbstractSelect.ItemCaptionMode.ITEM);
        supplier.setFilteringMode(FilteringMode.CONTAINS);
        supplier.setImmediate(true);
        supplier.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(
                    com.vaadin.data.Property.ValueChangeEvent event) {
                /*
                 * Modify the actual value of the custom field.
                 */
                if (supplier.getValue() == null) {
                    setValue(null, false);
                } else {
                    Supplier entity = container
                            .getItem(supplier.getValue()).getEntity();
                    setValue(entity, false);
                }
            }
        });
    }

    @Override
    protected Component initContent() {
        CssLayout cssLayout = new CssLayout();
        cssLayout.addComponent(supplier);
        return cssLayout;
    }
    @Override
    public void setPropertyDataSource(Property newDataSource) {
        super.setPropertyDataSource(newDataSource);
        setSupplier((Supplier) newDataSource.getValue());
    }

    @Override
    public void setValue(Supplier newValue) throws ReadOnlyException,
            Converter.ConversionException {
        setSupplier(newValue);
        super.setValue(newValue);
    }

    private void setSupplier(Supplier value) {
        supplier.setValue(value != null ? value.getId() : null);
    }
    @Override
    public Class<? extends Supplier> getType() {
        return Supplier.class;
    }
}
