use acredito;

#COMPANY
insert into COMPANY(id, description, name)
values(1, 'Main', 'Acredito');

#OFFICE
insert into OFFICE(id, address, description, name, phone, companyId)
values(1, 'Esteban Arze', 'Acredito Agencia Principal', 'Acredito 1', '44453535', 1);

#ADDRESS for EMPLOYEE - id=1
insert into ADDRESS(id, address1, address2, stateId, mobile, phone, workPhone, province, officeId)
values(1, 'Av. Villazon 102', 'km 3 1/2 Quintanilla', 382, '70748821', '4321274', '4324233', 'Cercado', 1);

#ADDRESS for SUPPLIER - id=2
insert into ADDRESS(id, address1, address2, stateId, mobile, phone, workPhone, province, officeId)
values(2, 'Av. Blanco Galindo 876', 'km 6 frente Hospital CNS', 382, '77482821', '4728374', '4324233', 'Cercado', 1);

#OCCUPATION
insert into OCCUPATION(id, name) values(1,'Carpintero');
insert into OCCUPATION(id, name) values(2,'Comerciante');
insert into OCCUPATION(id, name) values(3,'Abogado');
insert into OCCUPATION(id, name) values(4,'Contador');

#CUSTOMER
insert into CUSTOMER(id ,birthday , code, firstName, idNumber, idType, lastName, notes, salutation, version, addressId, occupationId, officeId)
values(1, '1980-02-02','2','Pepe','4323','ID','Grillo','hola','DR.',1,1,1,1);

#EMPLOYEE
INSERT INTO EMPLOYEE(id, active, birthday, code, firstName, idNumber, idType, lastName, password, username, version, addressId, officeId, occupationId)
VALUES (1, '1', '1979-12-12', '1', 'Miguel Angel', '4321234 CBA', 'ID', 'Mendez Perez', 'secret', 'mmendez', 1, 1, 1, 2);

#SUPPLIER
INSERT INTO SUPPLIER(id, code, companyName, firstName, lastName, nit, version, addressId, officeId)
VALUES (1, '1', 'Panchito Import Export SRL', 'Francisco J.', 'Salvador Muriel', '502312011', 1, 1, 1);

#Sequence
#INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT)
#VALUES ('TestEntity', 1);

