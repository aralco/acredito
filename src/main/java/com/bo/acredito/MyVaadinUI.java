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
import com.vaadin.ui.Button.ClickEvent;

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

        JPAContainer<com.bo.acredito.domain.Person> container = JPAContainerFactory.make(Person.class, PERSISTENCE_UNIT);
        PersonUI personUI=new PersonUI(new BeanItem<Person>(new Person()));
        setContent(personUI);
    }

}
