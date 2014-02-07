package com.bo.acredito.ui.customfields;


import com.bo.acredito.domain.IdTypeEnum;
import com.vaadin.data.Property;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomField;

import java.util.Arrays;

/**
 * Created by asejas on 2/6/14.
 */
public class IdTypeSelector extends CustomField<IdTypeEnum> {
    private ComboBox idType=new ComboBox(null, Arrays.asList(IdTypeEnum.values()));

    public IdTypeSelector() {
        idType.setImmediate(true);
        idType.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(
                    Property.ValueChangeEvent event) {
                /*
                 * Modify the actual value of the custom field.
                 */
                if (idType.getValue() == null) {
                    setValue(null, false);
                } else {
                    setValue((IdTypeEnum) idType.getValue(), false);
                }
            }
        });
    }

    @Override
    protected Component initContent() {
        CssLayout cssLayout = new CssLayout();
        cssLayout.addComponent(idType);
        return cssLayout;
    }

    @Override
    public void setPropertyDataSource(Property newDataSource) {
        super.setPropertyDataSource(newDataSource);
        setIdType((IdTypeEnum) newDataSource.getValue());
    }

    @Override
    public void setValue(IdTypeEnum newValue) throws ReadOnlyException,
            Converter.ConversionException {
        setIdType(newValue);
        super.setValue(newValue);
    }

    private void setIdType(IdTypeEnum value) {
        idType.setValue(value != null ? value : null);
    }
    @Override
    public Class<? extends IdTypeEnum> getType() {
        return IdTypeEnum.class;
    }
}