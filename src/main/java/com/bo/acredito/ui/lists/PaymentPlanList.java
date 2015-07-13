package com.bo.acredito.ui.lists;

import com.bo.acredito.domain.PaymentPlan;
import com.bo.acredito.ui.forms.PaymentPlanForm;
import com.bo.acredito.ui.util.ListTableUtil;
import com.bo.acredito.util.Constants;
import com.bo.acredito.util.RefreshableTabComponent;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.JPAContainerItem;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Button;
import org.tepi.filtertable.paged.PagedFilterTable;

/**
 * @author aralco
 */
public class PaymentPlanList extends RefreshableTabComponent {

    PagedFilterTable<IndexedContainer> filterTable;
    private String[] tablePropertyIds={"name", "defaultAmount", "quotesNumber", "active"};
    private String[] tableHeaders={"Nombre", "Cuota inicial", "Número de Cuotas", "Activo"};
    private Button addButton;
    private JPAContainer jpaContainer;


    public PaymentPlanList() {
        paintComponent();
    }

    @Override
    public void paintComponent() {
        jpaContainer = JPAContainerFactory.make(PaymentPlan.class, Constants.PERSISTENCE_UNIT);
        filterTable= ListTableUtil.buildPagedTable(tablePropertyIds, tableHeaders, filterTable, jpaContainer);
        filterTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                PaymentPlan selectedPaymentPlan = ((JPAContainerItem<PaymentPlan>) event.getItem()).getEntity();
                PaymentPlanForm productForm = new PaymentPlanForm("Editar plan de pagos", selectedPaymentPlan.getId(), jpaContainer);
                getUI().addWindow(productForm);
            }
        });

        addButton = new Button("Registrar nuevo plan de pagos");
        addButton.setDescription("Crea un nuevo plan de pagos");
        addButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                PaymentPlanForm paymentPlanForm = new PaymentPlanForm("Nuevo plan de pagos", null, jpaContainer);
                getUI().addWindow(paymentPlanForm);
            }
        });

        final AbstractOrderedLayout mainLayout=ListTableUtil.createListLayout("Lista de plan de pagos", addButton, filterTable);
        setCompositionRoot(mainLayout);

    }

}
