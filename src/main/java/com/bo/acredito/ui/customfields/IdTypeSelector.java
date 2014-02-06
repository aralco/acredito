package com.bo.acredito.ui.customfields;


import com.bo.acredito.domain.IdTypeEnum;
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
    }

    @Override
    protected Component initContent() {
        CssLayout cssLayout = new CssLayout();
        cssLayout.addComponent(idType);
        return cssLayout;
    }

    @Override
    public Class<? extends IdTypeEnum> getType() {
        return IdTypeEnum.class;
    }
}