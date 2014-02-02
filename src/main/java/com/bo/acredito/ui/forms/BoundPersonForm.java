package com.bo.acredito.ui.forms;

import com.bo.acredito.domain.CountryEnum;
import com.bo.acredito.domain.IdTypeEnum;
import com.vaadin.ui.*;

import java.util.Arrays;

/**
 * Created by asejas on 2/1/14.
 */
public class BoundPersonForm extends FormLayout {

    private TextField firstName = new TextField("Nombres: ");
    private TextField lastName = new TextField("Apellidos: ");
    private TextField salutation= new TextField("Saludo: ");
    private ComboBox idType=new ComboBox("Tipo de identificación: ", Arrays.asList(IdTypeEnum.values()));
    private TextField idNumber=new TextField("Identificación: ");
    private ComboBox country=new ComboBox("País: ", Arrays.asList(CountryEnum.values()));
    private TextField city=new TextField("Ciudad: ");
    private DateField birthday=new DateField("Fecha de nacimiento: ");
    private TextArea notes=new TextArea("Observaciones: ");

    public BoundPersonForm() {
        setSizeUndefined();
        setMargin(true);
        addComponent(firstName);
        addComponent(lastName);
        addComponent(salutation);
        addComponent(idType);
        addComponent(idNumber);
        addComponent(country);
        addComponent(city);
        addComponent(birthday);
        addComponent(notes);
    }
}
