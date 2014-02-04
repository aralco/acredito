package com.bo.acredito.service.person;

import com.bo.acredito.domain.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by asejas on 2/2/14.
 */
@Stateless
public class PersonService {

    @PersistenceContext(unitName = "acreditoPU")
    private EntityManager em;

    public void savePerson(Person person){
        Address address=new Address();
        address.setAddress("Dirección 1");
        address.setAddress2("Dirección 2");
        address.setCity("Cochabamba");
        address.setCountry(CountryEnum.Bolivia);
        address.setMobile("72712345");
        address.setPhone("42312345");
        address.setProvince("Quillacollo");
        address.setWorkPhone("4123333");
        em.persist(address);

        person.setAddress(address);

        Occupation occupation=em.find(Occupation.class, new Long(1));
        if(occupation==null){
            occupation=new Occupation();
            occupation.setName("Cuentista del tío");
            em.persist(occupation);
        }
        person.setOccupation(occupation);

        Customer customer=new Customer();
        em.persist(customer);
        person.setCustomer(customer);

        System.out.println("Aquí estoy.... firstName: "+person.getFirstName()+ " addressId: "+address.getId()+" occupationId: "+occupation.getId());
        em.persist(person);


    }
    public void upatePerson(Person person){
        em.merge(person);
    }

}
