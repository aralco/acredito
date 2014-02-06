package com.bo.acredito.ui.customfields;

/**
 * Created by asejas on 2/6/14.
 */

import com.bo.acredito.domain.Address;
import com.bo.acredito.domain.Occupation;
import com.bo.acredito.util.Constants;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomField;

import java.awt.*;

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
    }

    @Override
    protected Component initContent() {
        CssLayout cssLayout = new CssLayout();
        cssLayout.addComponent(occupation);
        return cssLayout;
    }

    @Override
    public Class<? extends Occupation> getType() {
        return Occupation.class;
    }
}