package com.bo.acredito.ui.forms;

import com.bo.acredito.domain.Customer;
import com.bo.acredito.domain.Payment;
import com.bo.acredito.domain.Product;
import com.bo.acredito.domain.SaleTypeEnum;
import com.bo.acredito.util.Constants;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;

/**
 * Created by aralco on 2/2/14.
 */
public class SalesForm extends CustomComponent {

    private ComboBox customer;
    private ComboBox product;
    private TextField subTotal   = new TextField("Monto $us:");
    private TextField discountedAmount   = new TextField("Descuento $us:");
    private TextField total   = new TextField("Total $us:");
    private ComboBox saleType = new ComboBox("Forma de pago:", Arrays.asList(SaleTypeEnum.values()));
    private TextField initialPayment = new TextField("Cuota inicial $us:");
    private TextField residualPayment = new TextField("Saldo a crédito $us:");
    private TextArea notes   = new TextArea("Observaciones: ");
    private Label totalLabel = new Label("Total a pagar: ");
    private TextField paymentQuotes = new TextField("Número de cuotas: ");
    final private Table paymentTable;
    private String [] tableHeaders = {"Número","Fecha", "Monto $us"};
    private final Button viewButton   = new Button("Ver");
    private final Button saveButton   = new Button("Guardar");
    private final Button cancelButton   = new Button("Cancelar");

    public SalesForm() {
        Panel salesPanel = new Panel("Registro de Venta");
        salesPanel.setSizeFull();

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        mainLayout.setMargin(true);

        GridLayout gridLayout = new GridLayout(2,1);
        gridLayout.setSizeFull();

        final FormLayout leftFormLayout = new FormLayout();
        //Load customers
        final JPAContainer<Customer> customers = JPAContainerFactory.make(Customer.class, "acreditoPU");
        customer = new ComboBox("Cliente:", customers);
        customer.setItemCaptionPropertyId("fullName");
        customer.setImmediate(true);
        leftFormLayout.addComponent(customer);
        //Load products
        final JPAContainer<Product> products = JPAContainerFactory.make(Product.class, "acreditoPU");
        product = new ComboBox("Producto:", products);
        product.setItemCaptionPropertyId("fullName");
        product.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                subTotal.setReadOnly(false);
                String price = products.getItem(product.getValue()).getEntity().getPrice().toString();
                subTotal.setValue(price);
                subTotal.setReadOnly(true);
                total.setReadOnly(false);
                total.setValue(price);
            }
        });
        product.setImmediate(true);
        leftFormLayout.addComponent(product);
        leftFormLayout.addComponent(subTotal);
        discountedAmount.setValue("0.00");
        discountedAmount.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(FieldEvents.TextChangeEvent textChangeEvent) {
                total.setReadOnly(false);
                if (subTotal.getValue() != null) {
                    BigDecimal st = new BigDecimal(subTotal.getValue());
                    BigDecimal da = new BigDecimal(textChangeEvent.getText());
                    total.setValue(st.subtract(da).toString());
                    total.setReadOnly(true);
                }
            }
        });
        discountedAmount.setImmediate(true);
        leftFormLayout.addComponent(discountedAmount);
        leftFormLayout.addComponent(total);
        leftFormLayout.addComponent(saleType);
        initialPayment.setVisible(false);
        leftFormLayout.addComponent(initialPayment);
        residualPayment.setVisible(false);
        leftFormLayout.addComponent(residualPayment);
        leftFormLayout.addComponent(notes);
        saleType.setNullSelectionAllowed(false);
        saleType.setValue(SaleTypeEnum.CASH);
        saleType.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                if (saleType.getValue().equals(SaleTypeEnum.CREDIT)) {
                    initialPayment.setVisible(true);
                    residualPayment.setVisible(true);
                } else {
                    initialPayment.setVisible(false);
                    residualPayment.setVisible(false);
                }
            }
        });
        saleType.setImmediate(true);
        //leftFormLayout.setSizeFull();
        gridLayout.addComponent(leftFormLayout,0,0);

        FormLayout rightFormLayout = new FormLayout();
        JPAContainer container = JPAContainerFactory.make(Payment.class, Constants.PERSISTENCE_UNIT);
        paymentTable = new Table(null, container);
        paymentTable.setVisibleColumns("paymentNumber","dueDate", "amountDue");
        paymentTable.setEditable(true);
        paymentTable.setImmediate(true);
        paymentTable.setColumnHeaders(tableHeaders);

        rightFormLayout.addComponent(totalLabel);
        HorizontalLayout rightHorizontalLayout = new HorizontalLayout(paymentQuotes,viewButton);
        rightFormLayout.addComponent(rightHorizontalLayout);
        rightFormLayout.addComponent(paymentTable);
        //rightFormLayout.setSizeFull();
        gridLayout.addComponent(rightFormLayout,1,0);


        HorizontalLayout horizontalLayout = new HorizontalLayout();
        saveButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                Notification.show("GUARDADO", "Venta registrada con éxito", Notification.Type.HUMANIZED_MESSAGE);
            }
        });
        horizontalLayout.addComponent(saveButton);
        horizontalLayout.addComponent(cancelButton);
        //horizontalLayout.setSizeFull();

//        verticalLayout.addComponent(gridLayout);
//        verticalLayout.addComponent(horizontalLayout);
//
//        salesPanel.setContent(verticalLayout);

//        setCompositionRoot(salesPanel);

        VerticalLayout formLayout = new VerticalLayout();
        formLayout.addComponent(gridLayout);
        formLayout.addComponent(horizontalLayout);
        salesPanel.setContent(formLayout);

        mainLayout.addComponent(salesPanel);

        setCompositionRoot(mainLayout);
        //setSizeFull();
    }
}
