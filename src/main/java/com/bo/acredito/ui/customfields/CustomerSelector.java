package com.bo.acredito.ui.customfields;

import com.bo.acredito.domain.Customer;
import com.bo.acredito.util.Constants;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Property;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.*;

/**
 * @author aralco
 */
public class CustomerSelector extends CustomField<Customer> {
    private ComboBox customer = new ComboBox();
    private JPAContainer<Customer> customerJPAContainer;


    public CustomerSelector() {
        customerJPAContainer = JPAContainerFactory.make(Customer.class, Constants.PERSISTENCE_UNIT);
        customer.setContainerDataSource(customerJPAContainer);
        customer.setItemCaptionMode(AbstractSelect.ItemCaptionMode.ITEM);
        customer.setFilteringMode(FilteringMode.CONTAINS);
        customer.setImmediate(true);
        customer.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if(customer.getValue()==null)   {
                    setValue(null, false);
                } else {
                    Customer entity = customerJPAContainer.getItem(customer.getValue()).getEntity();
                    setValue(entity, false);
                }
            }
        });
    }

    @Override
    protected Component initContent() {
        CssLayout cssLayout = new CssLayout();
        cssLayout.addComponent(customer);
        return cssLayout;
    }

    @Override
    public void setPropertyDataSource(Property newDataSource) {
        super.setPropertyDataSource(newDataSource);
        setCustomer((Customer) newDataSource.getValue());
    }

    @Override
    public void setValue(Customer newFieldValue) throws ReadOnlyException, Converter.ConversionException {
        setCustomer(newFieldValue);
        super.setValue(newFieldValue);
    }

    @Override
    public Class<? extends Customer> getType() {
        return Customer.class;
    }

    public void setCustomer(Customer customer) {
        this.customer.setValue(customer!=null?customer.getId():null);
    }


}
