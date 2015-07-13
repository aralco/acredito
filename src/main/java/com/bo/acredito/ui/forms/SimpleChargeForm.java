package com.bo.acredito.ui.forms;

import com.bo.acredito.MyVaadinUI;
import com.bo.acredito.domain.*;
import com.bo.acredito.service.CustomerService;
import com.bo.acredito.service.SaleService;
import com.bo.acredito.ui.customfields.AddressSelector2;
import com.bo.acredito.web.JEE6VaadinServlet;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

/**
 * This class implements the simple charge form (for a CASH sale)
 */
public class SimpleChargeForm extends Window {
    private JPAContainer<Sale> saleContainer;
    public SimpleChargeForm(String caption, Long saleId, JPAContainer<Sale> parentContainer) {
        super(caption);
        final Office office = ((MyVaadinUI) UI.getCurrent()).getEmployee().getOffice();
        Employee employee = ((MyVaadinUI) UI.getCurrent()).getEmployee();
        saleContainer =parentContainer;
        setModal(true);

        FormLayout formLayoutLeft1 = new FormLayout();
        FormLayout formLayoutRight1 = new FormLayout();

        HorizontalLayout topLayout=new HorizontalLayout();
        HorizontalLayout centerLayout=new HorizontalLayout();
        VerticalLayout mainLayout=new VerticalLayout();
        topLayout.addComponent(formLayoutLeft1);
        topLayout.addComponent(formLayoutRight1);

        Sale sale = saleContainer.getItem(saleId).getEntity();

        BeanItem<Sale> saleBeanItem=new BeanItem<Sale>(sale);
        saleBeanItem.addNestedProperty("customer");


        final FieldGroup fieldGroup = new FieldGroup(saleBeanItem);

        /*
         * This is an example of a field factory that constructs a complex
         * field
         */
        fieldGroup.setFieldFactory(new DefaultFieldGroupFieldFactory() {
            @Override
            public <T extends Field> T createField(Class<?> type,
                                                   Class<T> fieldType) {
                T field = super.createField(type, fieldType);
                if (field instanceof TextField) {
                    ((TextField) field).setNullRepresentation("");
                }
                return field;
            }
        });
        fieldGroup.setReadOnly(true);
        formLayoutLeft1.addComponent(fieldGroup.buildAndBind("Código: ", "code"));
        formLayoutLeft1.addComponent(fieldGroup.buildAndBind("Fecha: ", "date"));
        TextField customerTF=new TextField("Cliente");
        customerTF.setValue(sale.getCustomer().toString());
        customerTF.setReadOnly(true);
        formLayoutLeft1.addComponent(customerTF);

        TextField customerIdTF=new TextField("Identificación del cliente");
        customerIdTF.setValue(sale.getCustomer().getIdNumber() + " (" + sale.getCustomer().getIdType().toString() + ")");
        customerIdTF.setReadOnly(true);
        formLayoutLeft1.addComponent(customerIdTF);

        TextField saleTypeTF=new TextField("Tipo de venta");
        saleTypeTF.setValue(sale.getSaleType().toString());
        saleTypeTF.setReadOnly(true);
        formLayoutRight1.addComponent(saleTypeTF);

        TextField officeTF=new TextField("Oficina");
        officeTF.setValue(office.getName());
        officeTF.setReadOnly(true);
        formLayoutRight1.addComponent(officeTF);

        TextField employeeTF=new TextField("Usuario");
        employeeTF.setValue(employee.toString());
        employeeTF.setReadOnly(true);
        formLayoutRight1.addComponent(employeeTF);
        //SaleProductTable
        SaleService saleService=((JEE6VaadinServlet) VaadinServlet.getCurrent()).getSaleService();
        BeanItemContainer<SaleProduct> saleProductsContainer=new BeanItemContainer<SaleProduct>(
                SaleProduct.class,
                saleService.loadSaleProducts(sale.getId()));
        Table table1 = new Table("Products");
        table1.setContainerDataSource(saleProductsContainer);
        centerLayout.addComponent(table1);

        Button saveButton = new Button("Save");
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    fieldGroup.commit();

                    AddressSelector2 addressSelector2= (AddressSelector2) fieldGroup.getField("address");

                    Customer customer = ((BeanItem<Customer>) fieldGroup.getItemDataSource()).getBean();

                    customer.setAddress(addressSelector2.getValue());
                    CustomerService customerService=((JEE6VaadinServlet) VaadinServlet.getCurrent()).getCustomerService();

                    if(customer.getId()==null){
                        customer.setOffice(office);
                        customerService.saveCustomer(customer);
                    }
                    else{
                        customer.setOffice(office);
                        customerService.updateCustomer(customer);
                    }
                    close();
                    fieldGroup.discard();

                }catch (Exception e) {
                    Notification.show("Problema al guardar el cliente: "
                                    + e.getMessage(),
                            Notification.Type.ERROR_MESSAGE);
                }
            }
        });
        Button cancelButton = new Button("Cancel");
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
        formLayoutRight1.addComponent(buttonsLayout);

        formLayoutLeft1.setSpacing(true);
        formLayoutLeft1.setSizeUndefined();
        formLayoutRight1.setSpacing(true);
        formLayoutRight1.setSizeUndefined();

        topLayout.setMargin(true);
        topLayout.setSpacing(true);
        topLayout.setSizeUndefined();

        mainLayout.addComponent(topLayout);
        mainLayout.addComponent(centerLayout);
        setContent(mainLayout);
    }

    @Override
    public void close() {
        super.close();
        saleContainer.refresh();
    }
}
