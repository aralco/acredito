package com.bo.acredito;

import javax.servlet.annotation.WebServlet;

import com.bo.acredito.domain.Person;
import com.bo.acredito.ui.components.PersonUI;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI
{
    public static final String PERSISTENCE_UNIT = "acreditoPU";

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        TabSheet tabSheetMain = new TabSheet();
        tabSheetMain.setImmediate(true);
        tabSheetMain.setWidth("100%");
        tabSheetMain.setHeight("100%");

        //Clientes
        Label clientesLabel = new Label("Clientes List below");
        JPAContainer<com.bo.acredito.domain.Person> container = JPAContainerFactory.make(Person.class, PERSISTENCE_UNIT);
        PersonUI personUI=new PersonUI(new BeanItem<Person>(new Person()));


        //Productos
        Label productosLabel = new Label("Productos List below");

        //Ventas
        Form salesForm = new Form();
        salesForm.setCaption("Form Caption");
        salesForm.setDescription("This is a description of the Form that is " +
                "displayed in the upper part of the form. You normally " +
                "enter some descriptive text about the form and its " +
                "use here.");
        // Add a field directly to the layout. This field will
        // not be bound to the data source Item of the form.
        salesForm.getLayout().addComponent(new TextField("A Field"));

        // Add a field and bind it to an named item property.
        salesForm.addField("another", new TextField("Another Field"));

        Button button = new Button("Click Me");
        button.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                layout.addComponent(new Label("Thank you for clicking"));
            }
        });
        salesForm.getFooter().addComponent(button);

        //Cobranzas
        Label cobranzasLabel = new Label("Cobranzas List below");
        //Egresos
        Label egresosLabel = new Label("Egresos List below");
        //Soporte
        Label soporteLabel = new Label("Soporte List below");
        //Administración
        Label administracionLabel = new Label("Administración List below");


        tabSheetMain.addTab(personUI, "Clientes", null);
        tabSheetMain.addTab(productosLabel, "Productos", null);
        tabSheetMain.addTab(salesForm, "Ventas", null);
        tabSheetMain.addTab(cobranzasLabel, "Cobranzas", null);
        tabSheetMain.addTab(egresosLabel, "Egresos", null);
        tabSheetMain.addTab(soporteLabel, "Soporte", null);
        tabSheetMain.addTab(administracionLabel, "Administración", null);

        layout.addComponent(tabSheetMain);

    }

}
