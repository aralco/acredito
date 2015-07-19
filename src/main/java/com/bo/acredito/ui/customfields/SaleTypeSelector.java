package com.bo.acredito.ui.customfields;

import com.bo.acredito.domain.SaleType;
import com.vaadin.data.Property;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomField;

import java.util.Arrays;

/**
 * @author aralco
 */
public class SaleTypeSelector extends CustomField<SaleType> {
    private ComboBox saleType =new ComboBox(null, Arrays.asList(SaleType.values()));

    public SaleTypeSelector() {
        saleType.setImmediate(true);
        saleType.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(
                Property.ValueChangeEvent event) {
                if (saleType.getValue() == null) {
                    setValue(null, false);
                } else {
                    setValue((SaleType) saleType.getValue(), false);
                }
            }
        });
    }

    @Override
    protected Component initContent() {
        CssLayout cssLayout = new CssLayout();
        cssLayout.addComponent(saleType);
        return cssLayout;
    }

    @Override
    public void setPropertyDataSource(Property newDataSource) {
        super.setPropertyDataSource(newDataSource);
        setSaleType((SaleType) newDataSource.getValue());
    }

    @Override
    public void setValue(SaleType newValue) throws Property.ReadOnlyException,
            Converter.ConversionException {
        setSaleType(newValue);
        super.setValue(newValue);
    }

    private void setSaleType(SaleType value) {
        saleType.setValue(value != null ? value : null);
    }
    @Override
    public Class<? extends SaleType> getType() {
        return SaleType.class;
    }
}
