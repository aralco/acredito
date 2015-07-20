package com.bo.acredito.ui.forms;

import com.bo.acredito.MyVaadinUI;
import com.bo.acredito.domain.*;
import com.bo.acredito.service.SaleService;
import com.bo.acredito.ui.customfields.CustomerSelector;
import com.bo.acredito.ui.customfields.PaymentPlanSelector;
import com.bo.acredito.ui.customfields.ProductSelector;
import com.bo.acredito.ui.customfields.SaleTypeSelector;
import com.bo.acredito.web.JEE6VaadinServlet;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aralco
 */
public class SaleForm extends Window {
    private JPAContainer<Sale> saleJPAContainer;
    private final TextArea notesTextArea = new TextArea("Observaciones");
    private final ProductSelector productSelector = new ProductSelector();
    private final PaymentPlanSelector paymentPlanSelector = new PaymentPlanSelector();

    public SaleForm(String caption, Long saleId, JPAContainer<Sale> saleJPAContainer) {
        super(caption);
        this.saleJPAContainer = saleJPAContainer;
        setModal(true);
        HorizontalLayout mainLayout=new HorizontalLayout();

        FormLayout formLayout = new FormLayout();
        formLayout.setMargin(true);
        formLayout.setSpacing(true);
        formLayout.setSizeUndefined();

        final Sale sale;
        if(saleId!=null)    {
            sale = saleJPAContainer.getItem(saleId).getEntity();
        } else  {
            sale = new Sale();
            sale.setDiscount(0.0);
        }

        final FieldGroup fieldGroup = new FieldGroup();
        final BeanItem<Sale> saleBeanItem = new BeanItem<Sale>(sale);
        fieldGroup.setItemDataSource(saleBeanItem);
        fieldGroup.setFieldFactory(new DefaultFieldGroupFieldFactory() {
            @Override
            public <T extends Field> T createField(Class<?> type, Class<T> fieldType) {
                if (Customer.class.isAssignableFrom(type)) {
                    return (T) new CustomerSelector();
                } else if (SaleType.class.isAssignableFrom(type)) {
                    return (T) new SaleTypeSelector();
                } else if (PaymentPlan.class.isAssignableFrom(type)) {
                    return (T) new PaymentPlanSelector();
                }

                T field = super.createField(type, fieldType);
                if (field instanceof TextField) {
                    ((TextField) field).setNullRepresentation("");
                }
                return field;
            }
        });

        //Custom fields
        productSelector.setCaption("Productos");
        productSelector.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                //one way
//                sale.setPartialAmount(productSelector.getValue().getPrice());
//                ((TextField) fieldGroup.getField("partialAmount")).setValue(String.valueOf(sale.getPartialAmount()));

                //other way
                sale.setPartialAmount(productSelector.getValue().getPrice());
                sale.setTotalAmount(sale.getPartialAmount() - sale.getDiscount());
                fieldGroup.setItemDataSource(saleBeanItem);
                fieldGroup.getField("partialAmount").setReadOnly(true);
                fieldGroup.getField("totalAmount").setReadOnly(true);

                //another way and possible the best, but is not working
//                fieldGroup.getItemDataSource().getItemProperty("partialAmount").setValue(productSelector.getValue().getPrice());
            }
        });
        notesTextArea.setColumns(15);
        notesTextArea.setNullRepresentation("");

        formLayout.addComponent(fieldGroup.buildAndBind("Tipo de venta", "saleType"));
        formLayout.addComponent(fieldGroup.buildAndBind("Cliente", "customer"));
        formLayout.addComponent(productSelector);
        formLayout.addComponent(fieldGroup.buildAndBind("Sub Total $us", "partialAmount"));
        formLayout.addComponent(fieldGroup.buildAndBind("Descuento $us", "discount"));
        formLayout.addComponent(fieldGroup.buildAndBind("Total", "totalAmount"));
        fieldGroup.bind(notesTextArea, "notes");
        formLayout.addComponent(notesTextArea);

        fieldGroup.getField("partialAmount").setReadOnly(true);
        fieldGroup.getField("totalAmount").setReadOnly(true);

        Button saveButton = new Button("Guardar");
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    fieldGroup.commit();
                    sale.setAdvanceAmount(0.0);
                    sale.setResidualPayment(0.0);
                    sale.setInitialPayment(0.0);
                    sale.setPaymentQuotes(0);
                    Office office = ((MyVaadinUI) UI.getCurrent()).getEmployee().getOffice();
                    sale.setOffice(office);
                    Employee employee = ((MyVaadinUI) UI.getCurrent()).getEmployee();
                    sale.setEmployee(employee);
                    SaleService saleService = ((JEE6VaadinServlet) VaadinServlet.getCurrent()).getSaleService();
                    List<Product> productList = new ArrayList<Product>(0);
                    productList.add(productSelector.getValue());
                    Sale storedSale = saleService.createSale(sale);
                    saleService.createSaleProduct(storedSale, productList);
                    Notification.show("GUARDADO", "Venta registrada con éxito", Notification.Type.HUMANIZED_MESSAGE);
                    close();
                    fieldGroup.discard();

                } catch (Exception e)   {
                    Notification.show("Problema al guardar la venta: "
                                    + e.getMessage(),
                            Notification.Type.ERROR_MESSAGE);
                }
            }
        });

        Button cancelButton = new Button("Cancelar");
        cancelButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                fieldGroup.discard();
                close();
            }
        });
        HorizontalLayout buttonsLayout=new HorizontalLayout();
        buttonsLayout.setSpacing(true);
        buttonsLayout.addComponent(saveButton);
        buttonsLayout.addComponent(cancelButton);
        formLayout.addComponent(buttonsLayout);

        mainLayout.addComponent(formLayout);
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        mainLayout.setSizeUndefined();

        setContent(mainLayout);

    }

    @Override
    public void close() {
        super.close();
        saleJPAContainer.refresh();
    }

}
