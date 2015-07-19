package com.bo.acredito.ui.customfields;

import com.bo.acredito.domain.Product;
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
public class ProductSelector extends CustomField<Product> {
    private ComboBox product = new ComboBox();
    private JPAContainer<Product> productJPAContainer;


    public ProductSelector() {
        productJPAContainer = JPAContainerFactory.make(Product.class, Constants.PERSISTENCE_UNIT);
        product.setContainerDataSource(productJPAContainer);
        product.setItemCaptionMode(AbstractSelect.ItemCaptionMode.ITEM);
        product.setFilteringMode(FilteringMode.CONTAINS);
        product.setImmediate(true);
        product.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if(product.getValue()==null)   {
                    setValue(null, false);
                } else {
                    Product entity = productJPAContainer.getItem(product.getValue()).getEntity();
                    setValue(entity, false);
                }
            }
        });
    }

    @Override
    protected Component initContent() {
        CssLayout cssLayout = new CssLayout();
        cssLayout.addComponent(product);
        return cssLayout;
    }

    @Override
    public void setPropertyDataSource(Property newDataSource) {
        super.setPropertyDataSource(newDataSource);
        setProduct((Product) newDataSource.getValue());
    }

    @Override
    public void setValue(Product newFieldValue) throws ReadOnlyException, Converter.ConversionException {
        setProduct(newFieldValue);
        super.setValue(newFieldValue);
    }

    @Override
    public Class<? extends Product> getType() {
        return Product.class;
    }

    public void setProduct(Product product) {
        this.product.setValue(product!=null?product.getId():null);
    }
}
