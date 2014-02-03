package com.bo.acredito.ui.components;

import com.bo.acredito.domain.Person;
import com.bo.acredito.service.person.PersonService;
import com.bo.acredito.ui.forms.BoundPersonForm;
import com.bo.acredito.ui.forms.PersonForm;
import com.bo.acredito.ui.lists.PersonList;
import com.bo.acredito.web.JEE6VaadinServlet;
import com.vaadin.addon.jpacontainer.JPAContainerItem;
import com.vaadin.cdi.CDIUI;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

import javax.inject.Inject;

/**
 * Created by asejas on 1/27/14.
 */
public class PersonUI extends CustomComponent {
    private final String persistenceUnit="acreditoPU";

    public PersonUI(BeanItem<Person> personBeanItem) {
        final PersonList personList=new PersonList();
        final Button addButton;
        addButton = new Button("+");
        addButton.setDescription("Crear nuevo cliente");
        addButton.setStyleName(Reindeer.BUTTON_SMALL);
        addButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                BoundPersonForm personForm=new BoundPersonForm(new BeanItem<Person>(new Person()));

                Window window = new Window();
                window.setModal(true);
                VerticalLayout verticalLayout = new VerticalLayout();
                verticalLayout.addComponent(personForm);
                window.setContent(verticalLayout);
                getUI().addWindow(window);
            }
        });

        // Layout the example
        VerticalLayout root = new VerticalLayout();
        root.setMargin(true);
        root.setSpacing(true);
        root.addComponent(addButton);
        root.addComponent(personList);
        setCompositionRoot(root);

    }

}
