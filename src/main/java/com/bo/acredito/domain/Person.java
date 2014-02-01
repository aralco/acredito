//package com.bo.acredito.domain;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import java.util.Date;
//
//@Entity
//public class Person {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    private String firstName;
//    private String lastName;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    @Override
//    public String toString() {
//        return firstName + " " + lastName;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (obj instanceof Person) {
//            Person p = (Person) obj;
//            if (this == p) {
//                return true;
//            }
//            if (this.id == null || p.id == null) {
//                return false;
//            }
//            return this.id.equals(p.id);
//        }
//
//        return super.equals(obj);
//    }
//
//    @Override
//    public int hashCode() {
//        return id == null ? 0 : id.hashCode();
//    }
//
//}
//


package com.bo.acredito.domain;
import javax.persistence.*;
import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;

/**
* Created by aralco on 2/1/14.
*/
@Entity
public class Person {
    private long id;
    private String firstName;
    private String lastName;
    private String salutation;
    private String idType;
    private String idNumber;
    private String country;
    private String city;
    private Date birthday;
    private byte[] photo;
    private String notes;
    private Collection<Contact> contactsById;
    private Collection<Customer> customersById;
    private Collection<Employee> employeesById;
    private Occupation occupationByOccupationId;
    private Address addressByAddressId;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "salutation")
    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    @Basic
    @Column(name = "idType")
    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    @Basic
    @Column(name = "idNumber")
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Basic
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "photo")
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Basic
    @Column(name = "notes")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        if (birthday != null ? !birthday.equals(person.birthday) : person.birthday != null) return false;
        if (city != null ? !city.equals(person.city) : person.city != null) return false;
        if (country != null ? !country.equals(person.country) : person.country != null) return false;
        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) return false;
        if (idNumber != null ? !idNumber.equals(person.idNumber) : person.idNumber != null) return false;
        if (idType != null ? !idType.equals(person.idType) : person.idType != null) return false;
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null) return false;
        if (notes != null ? !notes.equals(person.notes) : person.notes != null) return false;
        if (!Arrays.equals(photo, person.photo)) return false;
        if (salutation != null ? !salutation.equals(person.salutation) : person.salutation != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (salutation != null ? salutation.hashCode() : 0);
        result = 31 * result + (idType != null ? idType.hashCode() : 0);
        result = 31 * result + (idNumber != null ? idNumber.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (photo != null ? Arrays.hashCode(photo) : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "personByPersonId")
    public Collection<Contact> getContactsById() {
        return contactsById;
    }

    public void setContactsById(Collection<Contact> contactsById) {
        this.contactsById = contactsById;
    }

    @OneToMany(mappedBy = "personByPersonId")
    public Collection<Customer> getCustomersById() {
        return customersById;
    }

    public void setCustomersById(Collection<Customer> customersById) {
        this.customersById = customersById;
    }

    @OneToMany(mappedBy = "personByPersonId")
    public Collection<Employee> getEmployeesById() {
        return employeesById;
    }

    public void setEmployeesById(Collection<Employee> employeesById) {
        this.employeesById = employeesById;
    }

    @ManyToOne
    @JoinColumn(name = "occupationId", referencedColumnName = "id", nullable = false)
    public Occupation getOccupationByOccupationId() {
        return occupationByOccupationId;
    }

    public void setOccupationByOccupationId(Occupation occupationByOccupationId) {
        this.occupationByOccupationId = occupationByOccupationId;
    }

    @ManyToOne
    @JoinColumn(name = "addressId", referencedColumnName = "id", nullable = false)
    public Address getAddressByAddressId() {
        return addressByAddressId;
    }

    public void setAddressByAddressId(Address addressByAddressId) {
        this.addressByAddressId = addressByAddressId;
    }
}
