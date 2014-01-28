package bo.com.acredito.ui.forms;

import bo.com.acredito.domain.Person;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.Reindeer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by asejas on 1/27/14.
 */
public class PersonForm extends Form {
        final String[] formPropertyIds={"firstName", "lastName"};

        public PersonForm(String caption, BeanItem<Person> personBeanItem) {

            setCaption(caption);
            setItemDataSource(personBeanItem, Arrays.asList(formPropertyIds));
            setFormFieldFactory(new FieldFactory());

            // Add Commit and Discard controls to the form.
            Button commit = new Button("Save", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    System.out.println("************************Toy aquiiiiiiiiii SUBMIT");
                    commit();
                    // now we get a reference to the wine object

                    Person person = ((BeanItem<Person>) getItemDataSource()).getBean();
                    JPAContainer<Person> container = JPAContainerFactory.make(Person.class, "acreditoPU");
                    container.addEntity(person);
                }
            });
            commit.setStyleName(Reindeer.BUTTON_DEFAULT);

            Button discard = new Button("Reset", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    System.out.println("***************Toy aquiiiiiiiiii DISCARD");
                    discard();
                }
            });

            HorizontalLayout footer = new HorizontalLayout();
            footer.addComponent(commit);
            footer.addComponent(discard);
            setFooter(footer);

        }
}
