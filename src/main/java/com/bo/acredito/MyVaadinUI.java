package com.bo.acredito;

//import com.bo.acredito.domain.Person;
//import com.bo.acredito.ui.components.PersonUI;
import com.bo.acredito.ui.components.SalesUI;
import com.bo.acredito.web.JEE6VaadinServlet;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.persistence.PersistenceContext;
import javax.servlet.annotation.WebServlet;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI
{
    public static final String PERSISTENCE_UNIT = "acreditoPU";
    public static final String CUSTOMERS = "Clientes";
    public static final String PRODUCTS = "Productos";
    public static final String SALES = "Ventas";
    public static final String CHARGES = "Cobranzas";
    public static final String EXPENSES = "Egresos";
    public static final String SUPPORT = "Soporte";
    public static final String ADMIN = "Administración";

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class)
    @PersistenceContext(name="persistence/em",unitName="acreditoPU")
    public static class Servlet extends JEE6VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout contentLayout = new VerticalLayout();
        contentLayout.setSizeFull();
        //contentLayout.setMargin(true);
        setContent(contentLayout);

        //Tabs Container
        TabSheet tabSheetMain = new TabSheet();
        tabSheetMain.setImmediate(true);
        tabSheetMain.setWidth("100%");
        tabSheetMain.setHeight("100%");

        //Customers
        //JPAContainer<com.bo.acredito.domain.Person> container = JPAContainerFactory.make(Person.class, PERSISTENCE_UNIT);
        //PersonUI personUI=new PersonUI(new BeanItem<Person>(new Person()));
        //tabSheetMain.addTab(personUI, CUSTOMERS, null);
        tabSheetMain.addTab(null, CUSTOMERS, null);

        //Products
        Label productosLabel = new Label("Productos List below");
        tabSheetMain.addTab(productosLabel, PRODUCTS, null);


        //Sales
        SalesUI salesUI = new SalesUI();
        tabSheetMain.addTab(salesUI, SALES, null);

        //Charges
        Label chargesLabel = new Label(CHARGES+"... Not Yet Implemented");
        tabSheetMain.addTab(chargesLabel, CHARGES, null);

        //Expenses
        Label expensesLabel = new Label(EXPENSES+"... Not Yet Implemented");
        tabSheetMain.addTab(expensesLabel, EXPENSES, null);

        //Support
        Label supportLabel = new Label(SUPPORT+"... Not Yet Implemented");
        tabSheetMain.addTab(supportLabel, SUPPORT, null);

        //Admin
        Label adminLabel = new Label(ADMIN+"... Not Yet Implemented");
        tabSheetMain.addTab(adminLabel, ADMIN, null);

        contentLayout.addComponent(tabSheetMain);

    }

}
