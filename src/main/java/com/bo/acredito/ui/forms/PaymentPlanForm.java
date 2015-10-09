package com.bo.acredito.ui.forms;

import com.bo.acredito.domain.PaymentPlan;
import com.bo.acredito.service.PaymentPlanService;
import com.bo.acredito.web.JEE6VaadinServlet;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

/**
 * @author aralco
 */
public class PaymentPlanForm extends Window {
    private JPAContainer<PaymentPlan> paymentPlanJPAContainer;

    public PaymentPlanForm(String caption, String paymentPlanId, JPAContainer<PaymentPlan> paymentPlanJPAContainer) {
        super(caption);
        this.paymentPlanJPAContainer = paymentPlanJPAContainer;
        setModal(true);

        FormLayout formLayout = new FormLayout();

        HorizontalLayout mainLayout=new HorizontalLayout();
        mainLayout.addComponent(formLayout);

        PaymentPlan paymentPlan = new PaymentPlan();
        if(paymentPlanId!=null){
            paymentPlan = paymentPlanJPAContainer.getItem(paymentPlanId).getEntity();
        }

        final FieldGroup fieldGroup = new FieldGroup(new BeanItem<PaymentPlan>(paymentPlan)) {
            /*
             * Override configureField to add a bean validator to each field.
             */
            @Override
            protected void configureField(Field<?> field) {
                super.configureField(field);
                // Add Bean validators if there are annotations
                // Note that this requires a bean validation implementation to
                // be available.
                String propertyName=getPropertyId(field).toString();
                BeanValidator validator = new BeanValidator(PaymentPlan.class,
                        propertyName);
                field.addValidator(validator);
                if (field.getLocale() != null) {
                    validator.setLocale(field.getLocale());
                }
            }
        };

                /*
         * This is an example of a field factory that constructs a complex
         * field.
         */
        fieldGroup.setFieldFactory(new DefaultFieldGroupFieldFactory() {
            @Override
            public <T extends Field> T createField(Class<?> type,
                                                   Class<T> fieldType) {
                T field=super.createField(type, fieldType);
                if (field instanceof TextField) {
                    ((TextField) field).setNullRepresentation("");
                }
                return field;
            }
        });

        formLayout.addComponent(fieldGroup.buildAndBind("Nombre: ", "name"));
        formLayout.addComponent(fieldGroup.buildAndBind("Cuota inicial: ","defaultAmount"));
        formLayout.addComponent(fieldGroup.buildAndBind("Número de Cuotas: ","quotesNumber"));
        formLayout.addComponent(fieldGroup.buildAndBind("Activo: ","active"));

        TextArea description=new TextArea("Descripción");
        description.setColumns(15);
        description.setNullRepresentation("");
        formLayout.addComponent(description);
        fieldGroup.bind(description, "description");

        Button saveButton = new Button("Guardar");
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    fieldGroup.commit();
                    PaymentPlan paymentPlan = ((BeanItem<PaymentPlan>) fieldGroup.getItemDataSource()).getBean();
                    PaymentPlanService paymentPlanService = ((JEE6VaadinServlet) VaadinServlet.getCurrent()).getPaymentPlanService();

                    if(paymentPlan.getId()==null){
                        paymentPlanService.save(paymentPlan);
                    }
                    else{
                        paymentPlanService.update(paymentPlan);
                    }
                    close();
                    fieldGroup.discard();

                }catch (Exception e) {
                    System.out.println("***********EXCEPTION!!!!!!!!!!!!!!! ");
                    e.printStackTrace();
                    Notification.show("Problema al guardar el plan de pagos: "
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

        formLayout.setSpacing(true);
        formLayout.setSizeUndefined();

        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        mainLayout.setSizeUndefined();
        setContent(mainLayout);

    }

    @Override
    public void close() {
        super.close();
        paymentPlanJPAContainer.refresh();
    }

}
