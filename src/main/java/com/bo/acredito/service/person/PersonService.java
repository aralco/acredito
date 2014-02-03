package com.bo.acredito.service.person;

import com.bo.acredito.domain.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by asejas on 2/2/14.
 */
@Stateless
public class PersonService {

    @PersistenceContext
    private EntityManager em;

    public void savePerson(Person person){
        System.out.println("*********************EntityManager!!!!"+em);
        System.out.println("person.id"+person.getId()+"?? ");
        System.out.println("Existe?: "+em.contains(person));
    }

}
