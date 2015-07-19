package com.bo.acredito.ui.customfields;

import com.bo.acredito.domain.PaymentPlan;
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
public class PaymentPlanSelector extends CustomField<PaymentPlan> {
    private ComboBox paymentPlan = new ComboBox();
    private JPAContainer<PaymentPlan> paymentPlanJPAContainer;


    public PaymentPlanSelector() {
        paymentPlanJPAContainer = JPAContainerFactory.make(PaymentPlan.class, Constants.PERSISTENCE_UNIT);
        paymentPlan.setContainerDataSource(paymentPlanJPAContainer);
        paymentPlan.setItemCaptionMode(AbstractSelect.ItemCaptionMode.ITEM);
        paymentPlan.setFilteringMode(FilteringMode.CONTAINS);
        paymentPlan.setImmediate(true);
        paymentPlan.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if(paymentPlan.getValue()==null)   {
                    setValue(null, false);
                } else {
                    PaymentPlan entity = paymentPlanJPAContainer.getItem(paymentPlan.getValue()).getEntity();
                    setValue(entity, false);
                }
            }
        });
    }

    @Override
    protected Component initContent() {
        CssLayout cssLayout = new CssLayout();
        cssLayout.addComponent(paymentPlan);
        return cssLayout;
    }

    @Override
    public void setPropertyDataSource(Property newDataSource) {
        super.setPropertyDataSource(newDataSource);
        setPaymentPlan((PaymentPlan) newDataSource.getValue());
    }

    @Override
    public void setValue(PaymentPlan newFieldValue) throws ReadOnlyException, Converter.ConversionException {
        setPaymentPlan(newFieldValue);
        super.setValue(newFieldValue);
    }

    @Override
    public Class<? extends PaymentPlan> getType() {
        return PaymentPlan.class;
    }

    public void setPaymentPlan(PaymentPlan paymentPlan) {
        this.paymentPlan.setValue(paymentPlan!=null?paymentPlan.getId():null);
    }
    
}
