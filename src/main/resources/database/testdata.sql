use acredito;

--company
insert into COMPANY(id, description, name) values(1, "Main", "Acredito");

--office
insert into OFFICE(id, address, description, name, phone, companyId) values(1, "Esteban Arze", "Acredito Agencia Principal", "Acredito 1", "44453535", 1);

--Customer
insert into Customer(id ,birthday , code, firstName, idNumber, idType, lastName, notes, salutation, version, addressId, contactId1, contactId2, contactId3, occupationId)
values(15, '1980-02-02',2,'Pepe','4323','ID','Grillo','hola','DR.',1,2,1,2,3,1);

--Occupations
insert into Occupation(id, name) values(1,"Carpintero");
insert into Occupation(id, name) values(2,"Comerciante");
insert into Occupation(id, name) values(3,"Abogado");
insert into Occupation(id, name) values(4,"Contador");

--Address for Employee - id=1
insert into Address(address1, address2, stateId, mobile, phone, workPhone, province, officeId) values("Av. Villazon 102", "km 3 1/2 Quintanilla", 382, "70748821", "4321274", "4324233", "Cercado", '1');

--Address for supplier - id=2
insert into Address(address1, address2, stateId, mobile, phone, workPhone, province, officeId) values("Av. Blanco Galindo 876", "km 6 frente Hospital CNS", 382, "77482821", "4728374", "4324233", "Cercado", '1');


--Employee
INSERT INTO Employee(id, active, birthday, code, firstName, idNumber, idType, lastName, password, username, version, addressId, officeId)
VALUES (1, b'1', '1979-12-12', '1', 'Miguel Angel', '4321234 CBA', 'ID', 'Mendez Perez', 'secret', 'mmendez', 1, 1, 1);

--Supplier
INSERT INTO Supplier(id, code, companyName, firstName, lastName, nit, version, addressId, officeId)
VALUES (1, '1', 'Panchito Import Export SRL', 'Francisco J.', 'Salvador Muriel', '502312011', 1, 2, 1);

