SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `acredito` ;
CREATE SCHEMA IF NOT EXISTS `acredito` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `acredito` ;

-- -----------------------------------------------------
-- Table `acredito`.`COUNTRY`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acredito`.`COUNTRY` ;

CREATE TABLE IF NOT EXISTS `acredito`.`COUNTRY` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `callCode` INT(11) NOT NULL,
  `code` VARCHAR(2) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `name` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 245
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `acredito`.`STATE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acredito`.`STATE` ;

CREATE TABLE IF NOT EXISTS `acredito`.`STATE` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `countryId` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_STATE_countryId` (`countryId` ASC),
  CONSTRAINT `FK_STATE_countryId`
    FOREIGN KEY (`countryId`)
    REFERENCES `acredito`.`COUNTRY` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3984
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `acredito`.`COMPANY`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acredito`.`COMPANY` ;

CREATE TABLE IF NOT EXISTS `acredito`.`COMPANY` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `description` LONGTEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `name` VARCHAR(250) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `acredito`.`OFFICE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acredito`.`OFFICE` ;

CREATE TABLE IF NOT EXISTS `acredito`.`OFFICE` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `address` LONGTEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `description` LONGTEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `name` VARCHAR(250) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `phone` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `companyId` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_OFFICE_companyId` (`companyId` ASC),
  CONSTRAINT `FK_OFFICE_companyId`
    FOREIGN KEY (`companyId`)
    REFERENCES `acredito`.`COMPANY` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `acredito`.`ADDRESS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acredito`.`ADDRESS` ;

CREATE TABLE IF NOT EXISTS `acredito`.`ADDRESS` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `address1` VARCHAR(250) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `address2` VARCHAR(250) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `mobile` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `phone` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `province` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `workPhone` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `officeId` BIGINT(20) NOT NULL,
  `stateId` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_ADDRESS_officeId` (`officeId` ASC),
  INDEX `FK_ADDRESS_stateId` (`stateId` ASC),
  CONSTRAINT `FK_ADDRESS_stateId`
    FOREIGN KEY (`stateId`)
    REFERENCES `acredito`.`STATE` (`id`),
  CONSTRAINT `FK_ADDRESS_officeId`
    FOREIGN KEY (`officeId`)
    REFERENCES `acredito`.`OFFICE` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `acredito`.`CHARGE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acredito`.`CHARGE` ;

CREATE TABLE IF NOT EXISTS `acredito`.`CHARGE` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `chargeAmount` DOUBLE NOT NULL,
  `code` BIGINT(20) NOT NULL,
  `date` DATETIME NOT NULL,
  `defaultingAmount` DOUBLE NOT NULL,
  `notes` LONGTEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `totalAmount` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `acredito`.`CONTACT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acredito`.`CONTACT` ;

CREATE TABLE IF NOT EXISTS `acredito`.`CONTACT` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `phone` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `officeId` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_CONTACT_officeId` (`officeId` ASC),
  CONSTRAINT `FK_CONTACT_officeId`
    FOREIGN KEY (`officeId`)
    REFERENCES `acredito`.`OFFICE` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `acredito`.`OCCUPATION`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acredito`.`OCCUPATION` ;

CREATE TABLE IF NOT EXISTS `acredito`.`OCCUPATION` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `acredito`.`CUSTOMER`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acredito`.`CUSTOMER` ;

CREATE TABLE IF NOT EXISTS `acredito`.`CUSTOMER` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `birthday` DATE NOT NULL,
  `code` BIGINT(20) NOT NULL,
  `firstName` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `idNumber` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `idType` VARCHAR(8) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `lastName` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `notes` LONGTEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `photo` LONGBLOB NULL DEFAULT NULL,
  `salutation` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `version` BIGINT(20) NOT NULL,
  `addressId` BIGINT(20) NOT NULL,
  `occupationId` BIGINT(20) NOT NULL,
  `officeId` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_CUSTOMER_addressId` (`addressId` ASC),
  INDEX `FK_CUSTOMER_occupationId` (`occupationId` ASC),
  INDEX `FK_CUSTOMER_officeId` (`officeId` ASC),
  CONSTRAINT `FK_CUSTOMER_officeId`
    FOREIGN KEY (`officeId`)
    REFERENCES `acredito`.`OFFICE` (`id`),
  CONSTRAINT `FK_CUSTOMER_addressId`
    FOREIGN KEY (`addressId`)
    REFERENCES `acredito`.`ADDRESS` (`id`),
  CONSTRAINT `FK_CUSTOMER_occupationId`
    FOREIGN KEY (`occupationId`)
    REFERENCES `acredito`.`OCCUPATION` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `acredito`.`EMPLOYEE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acredito`.`EMPLOYEE` ;

CREATE TABLE IF NOT EXISTS `acredito`.`EMPLOYEE` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `active` TINYINT(1) NOT NULL DEFAULT '0',
  `birthday` DATE NOT NULL,
  `code` BIGINT(20) NOT NULL,
  `firstName` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `idNumber` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `idType` VARCHAR(8) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `lastName` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `password` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `photo` LONGBLOB NULL DEFAULT NULL,
  `role` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `username` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `version` BIGINT(20) NOT NULL,
  `addressId` BIGINT(20) NOT NULL,
  `officeId` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_EMPLOYEE_addressId` (`addressId` ASC),
  INDEX `FK_EMPLOYEE_officeId` (`officeId` ASC),
  CONSTRAINT `FK_EMPLOYEE_officeId`
    FOREIGN KEY (`officeId`)
    REFERENCES `acredito`.`OFFICE` (`id`),
  CONSTRAINT `FK_EMPLOYEE_addressId`
    FOREIGN KEY (`addressId`)
    REFERENCES `acredito`.`ADDRESS` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `acredito`.`PAYMENT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acredito`.`PAYMENT` ;

CREATE TABLE IF NOT EXISTS `acredito`.`PAYMENT` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `amountDue` DOUBLE NOT NULL,
  `dueDate` DATE NOT NULL,
  `paymentNumber` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `acredito`.`SUPPLIER`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acredito`.`SUPPLIER` ;

CREATE TABLE IF NOT EXISTS `acredito`.`SUPPLIER` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `code` BIGINT(20) NOT NULL,
  `companyName` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `firstName` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `lastName` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `nit` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `version` BIGINT(20) NOT NULL,
  `addressId` BIGINT(20) NOT NULL,
  `officeId` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_SUPPLIER_officeId` (`officeId` ASC),
  INDEX `FK_SUPPLIER_addressId` (`addressId` ASC),
  CONSTRAINT `FK_SUPPLIER_addressId`
    FOREIGN KEY (`addressId`)
    REFERENCES `acredito`.`ADDRESS` (`id`),
  CONSTRAINT `FK_SUPPLIER_officeId`
    FOREIGN KEY (`officeId`)
    REFERENCES `acredito`.`OFFICE` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `acredito`.`PRODUCT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acredito`.`PRODUCT` ;

CREATE TABLE IF NOT EXISTS `acredito`.`PRODUCT` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `available` TINYINT(1) NOT NULL DEFAULT '0',
  `code` BIGINT(20) NOT NULL,
  `name` VARCHAR(250) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `notes` LONGTEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `photo` LONGBLOB NULL DEFAULT NULL,
  `price` DOUBLE NOT NULL,
  `version` BIGINT(20) NOT NULL,
  `officeId` BIGINT(20) NOT NULL,
  `supplierId` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_PRODUCT_supplierId` (`supplierId` ASC),
  INDEX `FK_PRODUCT_officeId` (`officeId` ASC),
  CONSTRAINT `FK_PRODUCT_officeId`
    FOREIGN KEY (`officeId`)
    REFERENCES `acredito`.`OFFICE` (`id`),
  CONSTRAINT `FK_PRODUCT_supplierId`
    FOREIGN KEY (`supplierId`)
    REFERENCES `acredito`.`SUPPLIER` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `acredito`.`SALE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acredito`.`SALE` ;

CREATE TABLE IF NOT EXISTS `acredito`.`SALE` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `code` BIGINT(20) NOT NULL,
  `date` DATETIME NOT NULL,
  `discountedAmount` DOUBLE NOT NULL,
  `initialPayment` DOUBLE NOT NULL,
  `notes` LONGTEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `paymentQuotes` INT(11) NOT NULL,
  `productPrice` DOUBLE NOT NULL,
  `residualPayment` DOUBLE NOT NULL,
  `saleType` VARCHAR(6) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `total` DOUBLE NOT NULL,
  `version` BIGINT(20) NOT NULL,
  `contactId1` BIGINT(20) NOT NULL,
  `contactId2` BIGINT(20) NOT NULL,
  `contactId3` BIGINT(20) NOT NULL,
  `customerId` BIGINT(20) NOT NULL,
  `employeeId` BIGINT(20) NOT NULL,
  `officeId` BIGINT(20) NOT NULL,
  `productId` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_SALE_productId` (`productId` ASC),
  INDEX `FK_SALE_officeId` (`officeId` ASC),
  INDEX `FK_SALE_contactId2` (`contactId2` ASC),
  INDEX `FK_SALE_employeeId` (`employeeId` ASC),
  INDEX `FK_SALE_contactId1` (`contactId1` ASC),
  INDEX `FK_SALE_contactId3` (`contactId3` ASC),
  INDEX `FK_SALE_customerId` (`customerId` ASC),
  CONSTRAINT `FK_SALE_customerId`
    FOREIGN KEY (`customerId`)
    REFERENCES `acredito`.`CUSTOMER` (`id`),
  CONSTRAINT `FK_SALE_contactId1`
    FOREIGN KEY (`contactId1`)
    REFERENCES `acredito`.`CONTACT` (`id`),
  CONSTRAINT `FK_SALE_contactId2`
    FOREIGN KEY (`contactId2`)
    REFERENCES `acredito`.`CONTACT` (`id`),
  CONSTRAINT `FK_SALE_contactId3`
    FOREIGN KEY (`contactId3`)
    REFERENCES `acredito`.`CONTACT` (`id`),
  CONSTRAINT `FK_SALE_employeeId`
    FOREIGN KEY (`employeeId`)
    REFERENCES `acredito`.`EMPLOYEE` (`id`),
  CONSTRAINT `FK_SALE_officeId`
    FOREIGN KEY (`officeId`)
    REFERENCES `acredito`.`OFFICE` (`id`),
  CONSTRAINT `FK_SALE_productId`
    FOREIGN KEY (`productId`)
    REFERENCES `acredito`.`PRODUCT` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
