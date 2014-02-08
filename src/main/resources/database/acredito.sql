SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `acredito` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `acredito` ;

-- -----------------------------------------------------
-- Table `acredito`.`Country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acredito`.`Country` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(2) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `callCode` INT NOT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acredito`.`State`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acredito`.`State` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `countryId` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_State_Country1_idx` (`countryId` ASC),
  CONSTRAINT `fk_State_Country1`
  FOREIGN KEY (`countryId`)
  REFERENCES `acredito`.`Country` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acredito`.`City`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acredito`.`City` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `stateId` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_City_State1_idx` (`stateId` ASC),
  CONSTRAINT `fk_City_State1`
  FOREIGN KEY (`stateId`)
  REFERENCES `acredito`.`State` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acredito`.`Address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acredito`.`Address` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `version` BIGINT NOT NULL,
  `address1` VARCHAR(250) NOT NULL,
  `address2` VARCHAR(250) NOT NULL,
  `province` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `mobile` VARCHAR(45) NOT NULL,
  `workPhone` VARCHAR(45) NOT NULL,
  `cityId` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Address_City1_idx` (`cityId` ASC),
  CONSTRAINT `fk_Address_City1`
  FOREIGN KEY (`cityId`)
  REFERENCES `acredito`.`City` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acredito`.`Supplier`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acredito`.`Supplier` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `version` BIGINT NOT NULL,
  `code` BIGINT NOT NULL,
  `firstName` VARCHAR(100) NOT NULL,
  `lastName` VARCHAR(100) NOT NULL,
  `companyName` VARCHAR(100) NULL,
  `nit` VARCHAR(45) NULL,
  `addressId` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Supplier_Address1_idx` (`addressId` ASC),
  CONSTRAINT `fk_Supplier_Address1`
  FOREIGN KEY (`addressId`)
  REFERENCES `acredito`.`Address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acredito`.`Product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acredito`.`Product` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `version` BIGINT NOT NULL,
  `code` BIGINT NOT NULL,
  `name` VARCHAR(250) NOT NULL,
  `price` DECIMAL(5,2) NOT NULL,
  `available` TINYINT(1) NOT NULL,
  `notes` TEXT NOT NULL,
  `photo` BLOB NULL,
  `supplierId` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Product_Supplier1_idx` (`supplierId` ASC),
  CONSTRAINT `fk_Product_Supplier1`
  FOREIGN KEY (`supplierId`)
  REFERENCES `acredito`.`Supplier` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acredito`.`Employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acredito`.`Employee` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `version` BIGINT NOT NULL,
  `code` BIGINT NOT NULL,
  `active` TINYINT(1) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `firstName` VARCHAR(100) NOT NULL,
  `lastName` VARCHAR(100) NOT NULL,
  `idType` ENUM('ID','PASSPORT','MILITAR') NOT NULL,
  `idNumber` VARCHAR(45) NOT NULL,
  `birthday` DATE NOT NULL,
  `photo` BLOB NOT NULL,
  `addressId` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Employee_Address1_idx` (`addressId` ASC),
  CONSTRAINT `fk_Employee_Address1`
  FOREIGN KEY (`addressId`)
  REFERENCES `acredito`.`Address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acredito`.`Occupation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acredito`.`Occupation` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acredito`.`Contact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acredito`.`Contact` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `version` BIGINT NOT NULL,
  `name` VARCHAR(250) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acredito`.`Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acredito`.`Customer` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `version` BIGINT NOT NULL,
  `code` BIGINT NOT NULL,
  `firstName` VARCHAR(100) NOT NULL,
  `lastName` VARCHAR(100) NOT NULL,
  `salutation` VARCHAR(45) NOT NULL,
  `idType` ENUM('ID','PASSPORT','MILITAR') NOT NULL,
  `idNumber` VARCHAR(45) NOT NULL,
  `birthday` DATE NOT NULL,
  `photo` BLOB NULL,
  `notes` TEXT NOT NULL,
  `occupationId` BIGINT NOT NULL,
  `addressId` BIGINT NOT NULL,
  `contactId1` BIGINT NOT NULL,
  `contactId2` BIGINT NOT NULL,
  `contactId3` BIGINT NOT NULL,
  `cityId` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Person_Occupation1_idx` (`occupationId` ASC),
  INDEX `fk_Person_Address1_idx` (`addressId` ASC),
  INDEX `fk_Customer_Contact1_idx` (`contactId1` ASC),
  INDEX `fk_Customer_Contact2_idx` (`contactId2` ASC),
  INDEX `fk_Customer_Contact3_idx` (`contactId3` ASC),
  INDEX `fk_Customer_City1_idx` (`cityId` ASC),
  CONSTRAINT `fk_Person_Occupation1`
  FOREIGN KEY (`occupationId`)
  REFERENCES `acredito`.`Occupation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Person_Address1`
  FOREIGN KEY (`addressId`)
  REFERENCES `acredito`.`Address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Customer_Contact1`
  FOREIGN KEY (`contactId1`)
  REFERENCES `acredito`.`Contact` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Customer_Contact2`
  FOREIGN KEY (`contactId2`)
  REFERENCES `acredito`.`Contact` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Customer_Contact3`
  FOREIGN KEY (`contactId3`)
  REFERENCES `acredito`.`Contact` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Customer_City1`
  FOREIGN KEY (`cityId`)
  REFERENCES `acredito`.`City` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acredito`.`Sale`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acredito`.`Sale` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `version` BIGINT NOT NULL,
  `code` BIGINT NOT NULL,
  `date` DATETIME NOT NULL,
  `productPrice` DECIMAL(5,2) NOT NULL,
  `discountedAmount` DECIMAL(5,2) NOT NULL,
  `total` DECIMAL(5,2) NOT NULL,
  `saleType` ENUM('CASH','CREDIT') NOT NULL,
  `initialPayment` DECIMAL(5,2) NOT NULL,
  `residualPayment` DECIMAL(5,2) NOT NULL,
  `paymentQuotes` INT NOT NULL,
  `notes` TEXT NOT NULL,
  `employeeId` BIGINT NOT NULL,
  `productId` BIGINT NOT NULL,
  `customerId` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Sale_Employee1_idx` (`employeeId` ASC),
  INDEX `fk_Sale_Product1_idx` (`productId` ASC),
  INDEX `fk_Sale_Customer1_idx` (`customerId` ASC),
  CONSTRAINT `fk_Sale_Employee1`
  FOREIGN KEY (`employeeId`)
  REFERENCES `acredito`.`Employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Sale_Product1`
  FOREIGN KEY (`productId`)
  REFERENCES `acredito`.`Product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Sale_Customer1`
  FOREIGN KEY (`customerId`)
  REFERENCES `acredito`.`Customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acredito`.`Payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acredito`.`Payment` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `version` BIGINT NOT NULL,
  `paymentNumber` INT NOT NULL,
  `dueDate` DATETIME NOT NULL,
  `amountDue` DECIMAL(5,2) NOT NULL,
  `saleId` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Payment_Sale1_idx` (`saleId` ASC),
  CONSTRAINT `fk_Payment_Sale1`
  FOREIGN KEY (`saleId`)
  REFERENCES `acredito`.`Sale` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acredito`.`Charge`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acredito`.`Charge` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `version` BIGINT NOT NULL,
  `code` BIGINT NOT NULL,
  `date` DATETIME NOT NULL,
  `chargeAmount` DECIMAL(5,2) NOT NULL,
  `defaultingAmount` DECIMAL(5,2) NOT NULL,
  `totalAmount` DECIMAL(5,2) NOT NULL,
  `notes` TEXT NOT NULL,
  `saleId` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Charge_Sale1_idx` (`saleId` ASC),
  CONSTRAINT `fk_Charge_Sale1`
  FOREIGN KEY (`saleId`)
  REFERENCES `acredito`.`Sale` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
