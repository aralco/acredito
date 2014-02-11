insert into Contact(id, name, phone) values(1, "Don Tiburcio", "43126634");
insert into Contact(id, name, phone) values(2, "Don Trifón", "4312999");
insert into Contact(id, name, phone) values(3, "Don Tolomesio", "43122122");

insert into address(id, address1, address2, stateId, mobile, phone, workPhone, province, version)
values(1, "Av. Heroinas esq Oquendo Nro 876", "Calle Bolivar Nro. 123", 382, "73456237", "4728374", "4324233", "Cercado",1);
#Occupations
insert into Occupation(id, name) values(1,"Carpintero");
insert into Occupation(id, name) values(2,"Comerciante");
insert into Occupation(id, name) values(3,"Abogado");
insert into Occupation(id, name) values(4,"Contador");

#Address for Employee - id=1
insert into address(address1, address2, stateId, mobile, phone, workPhone, province)
values("Av. Villazon 102", "km 3 1/2 Quintanilla", 382, "70748821", "4321274", "4324233", "Cercado");
#Address for supplier - id=2
insert into address(address1, address2, stateId, mobile, phone, workPhone, province)
values("Av. Blanco Galindo 876", "km 6 frente Hospital CNS", 382, "77482821", "4728374", "4324233", "Cercado");
#Employee
INSERT INTO `acredito`.`employee` (`id`, `active`, `birthday`, `code`, `firstName`, `idNumber`, `idType`, `lastName`, `password`, `username`, `version`, `addressId`)
VALUES (NULL, b'1', '1979-12-12', '1', 'Miguel Angel', '4321234 CBA', 'ID', 'Mendez Perez', 'secret', 'mmendez', '1', '1');
#Supplier
INSERT INTO `acredito`.`supplier` (`id`, `code`, `companyName`, `firstName`, `lastName`, `nit`, `version`, `addressId`)
VALUES (NULL, '1', 'Panchito Import Export SRL', 'Francisco J.', 'Salvador Muriel', '502312011', '1', '2');
#Products
INSERT INTO `acredito`.`Product` (`id`, `available`, `code`, `name`, `notes`, `photo`, `price`, `version`,`supplierId`)
VALUES (NULL, b'1', '1', 'MCVGA021 - Televisor Sony LED', 'skdfksdnjkfnsjkdnfjksndjkfnskjafdafsdf', NULL, '1000', '1','1'),
 (NULL, b'0', '2', 'MD0931 - Celular IPhone 5', 'sdfnjnajdfnsnfkjsdf', NULL, '750', '1','1');

