package com.bo.acredito.ui.components;

import com.bo.acredito.domain.Person;
import com.bo.acredito.ui.forms.PersonForm;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by asejas on 1/27/14.
 */
public class PersonUI extends CustomComponent {
    private final String persistenceUnit="acreditoPU";

    public PersonUI(BeanItem<Person> personBeanItem) {

       // Example form
       final PersonForm form = new PersonForm("Contact Information", personBeanItem);


        // Layout the example
        VerticalLayout root = new VerticalLayout();
        root.setMargin(true);
        root.setSpacing(true);
        root.addComponent(form);
        setCompositionRoot(root);

    }

}
