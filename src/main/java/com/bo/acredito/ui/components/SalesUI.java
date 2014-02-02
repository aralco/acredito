package com.bo.acredito.ui.components;

import com.bo.acredito.domain.SaleTypeEnum;
import com.vaadin.ui.*;

import java.util.Arrays;

/**
 * Created by aralco on 2/2/14.
 */
public class SalesUI extends CustomComponent {

    private ComboBox customer = new ComboBox("Cliente:");
    private ComboBox product = new ComboBox("Producto:");
    private TextField subTotal   = new TextField("Monto $us:");
    private TextField discountedAmount   = new TextField("Descuento $us:");
    private TextField total   = new TextField("Total $us:");
    private ComboBox saleType = new ComboBox("Forma de pago:", Arrays.asList(SaleTypeEnum.values()));
    private TextArea notes   = new TextArea("Observaciones");
    private final Button saveButton   = new Button("Guardar");
    private final Button cancelButton   = new Button("Cancelar");

    public SalesUI() {
        Panel salesPanel = new Panel("Registro de Venta");
        salesPanel.setSizeFull();

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();
        verticalLayout.setMargin(true);


        GridLayout gridLayout = new GridLayout(2,1);
        gridLayout.setSizeFull();

        FormLayout leftFormLayout = new FormLayout();
        leftFormLayout.addComponent(customer);
        leftFormLayout.addComponent(product);
        leftFormLayout.addComponent(subTotal);
        leftFormLayout.addComponent(discountedAmount);
        leftFormLayout.addComponent(total);
        leftFormLayout.addComponent(saleType);
        leftFormLayout.addComponent(notes);
        //leftFormLayout.setSizeFull();
        gridLayout.addComponent(leftFormLayout,0,0);

        FormLayout rightFormLayout = new FormLayout();
//        rightFormLayout.addComponent(entry3);
//        rightFormLayout.addComponent(entry4);
        //rightFormLayout.setSizeFull();
        gridLayout.addComponent(rightFormLayout,1,0);


        HorizontalLayout horizontalLayout = new HorizontalLayout();
        saveButton.addClickListener(new Button.ClickListener()  {
            public void buttonClick(Button.ClickEvent event)    {
                Notification.show("GUARDADO","Venta registrada con éxito", Notification.Type.HUMANIZED_MESSAGE);
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

        verticalLayout.addComponent(salesPanel);

        setCompositionRoot(verticalLayout);

        setSizeFull();
    }
}
