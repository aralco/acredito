package com.bo.acredito.ui.lists;

import com.bo.acredito.domain.Person;
import com.bo.acredito.ui.forms.BoundPersonForm;
import com.bo.acredito.ui.forms.PersonForm;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.JPAContainerItem;
import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * Created by asejas on 1/29/14.
 */
public class PersonList extends Table{
    private String[] tablePropertyIds={"firstName","lastName", "country", "city", "birthday"};
    private String[] tableHeaders={"Nombre","Apellido", "País", "Ciudad", "Fecha de nacimiento"};

    public PersonList() {
        JPAContainer container = JPAContainerFactory.make(Person.class, "acreditoPU");
        new Table(null, container);
        setContainerDataSource(container);
        setVisibleColumns(tablePropertyIds);
        setSelectable(true);
        setImmediate(true);
        setSizeFull();
        setColumnHeaders(tableHeaders);
        addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                Object itemId = event.getProperty().getValue();
                BoundPersonForm personForm=new BoundPersonForm(getItem(itemId));

                Window window = new Window();
                window.setModal(true);
                VerticalLayout verticalLayout = new VerticalLayout();
                verticalLayout.addComponent(personForm);
                window.setContent(verticalLayout);
                getUI().addWindow(window);
            }
        });
    }
}
