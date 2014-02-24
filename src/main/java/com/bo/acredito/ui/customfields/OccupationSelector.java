package com.bo.acredito.ui.customfields;

/**
 * This class implements the OccupationSelector
 * Created by asejas on 2/6/14.
 */

import com.bo.acredito.domain.Occupation;
import com.bo.acredito.service.CustomerService;
import com.bo.acredito.util.Constants;
import com.bo.acredito.web.JEE6VaadinServlet;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Property;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;


public class OccupationSelector extends CustomField<Occupation> {
    private ComboBox occupation = new ComboBox();

    private JPAContainer<Occupation> container;

    public OccupationSelector() {
        container = JPAContainerFactory.make(Occupation.class,
                Constants.PERSISTENCE_UNIT);

        occupation.setContainerDataSource(container);
        occupation.setItemCaptionPropertyId("name");
        occupation.setImmediate(true);
        occupation.setNewItemsAllowed(true);
        occupation.setNewItemHandler(new AbstractSelect.NewItemHandler(){
            @Override
            public void addNewItem(String newItemCaption) {
                Occupation newOccupation=new Occupation();
                newOccupation.setName(newItemCaption);
                CustomerService customerService=((JEE6VaadinServlet) VaadinServlet.getCurrent()).getCustomerService();
                customerService.saveOccupation(newOccupation);
                container.refresh();
                occupation.setValue(newOccupation.getId());
            }
        });
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