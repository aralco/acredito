SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `acredito` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `acredito` ;

-- -----------------------------------------------------
-- Table `acredito`.`Product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acredito`.`Product` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` BIGINT NOT NULL,
  `name` VARCHAR(250) NOT NULL,
  `available` TINYINT(1) NOT NULL,
  `notes` TEXT NOT NULL,
  `photo` BLOB NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acredito`.`Price`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acredito`.`Price` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `price` DECIMAL(5,2) NOT NULL,
  `startDate` DATETIME NOT NULL,
  `endDate` DATETIME NULL,
  `productId` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Price_Product1_idx` (`productId` ASC),
  CONSTRAINT `fk_Price_Product1`
  FOREIGN KEY (`productId`)
  REFERENCES `acredito`.`Product` (`id`)
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
-- Table `acredito`.`Address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acredito`.`Address` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(250) NOT NULL,
  `address2` VARCHAR(250) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `province` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `mobile` VARCHAR(45) NOT NULL,
  `workPhone` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acredito`.`Person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acredito`.`Person` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(100) NOT NULL,
  `lastName` VARCHAR(100) NOT NULL,
  `salutation` VARCHAR(45) NOT NULL,
  `idType` VARCHAR(45) NOT NULL,
  `idNumber` VARCHAR(45) NOT NULL,
  `country` VARCHAR(100) NOT NULL,
  `city` VARCHAR(100) NOT NULL,
  `birthday` DATE NOT NULL,
  `photo` BLOB NULL,
  `notes` TEXT NOT NULL,
  `occupationId` BIGINT NOT NULL,
  `addressId` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Person_Occupation1_idx` (`occupationId` ASC),
  INDEX `fk_Person_Address1_idx` (`addressId` ASC),
  CONSTRAINT `fk_Person_Occupation1`
  FOREIGN KEY (`occupationId`)
  REFERENCES `acredito`.`Occupation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Person_Address1`
  FOREIGN KEY (`addressId`)
  REFERENCES `acredito`.`Address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acredito`.`Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acredito`.`Customer` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` BIGINT NOT NULL,
  `personId` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Customer_Person1_idx` (`personId` ASC),
  CONSTRAINT `fk_Customer_Person1`
  FOREIGN KEY (`personId`)
  REFERENCES `acredito`.`Person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acredito`.`Employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acredito`.`Employee` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` BIGINT NOT NULL,
  `active` TINYINT(1) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `personId` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Employee_Person1_idx` (`personId` ASC),
  CONSTRAINT `fk_Employee_Person1`
  FOREIGN KEY (`personId`)
  REFERENCES `acredito`.`Person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acredito`.`Sale`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acredito`.`Sale` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` BIGINT NOT NULL,
  `date` DATETIME NOT NULL,
  `subTotal` DECIMAL(5,2) NOT NULL,
  `discountedAmount` DECIMAL(5,2) NOT NULL,
  `total` DECIMAL(5,2) NOT NULL,
  `saleType` VARCHAR(50) NOT NULL,
  `initialPayment` DECIMAL(5,2) NOT NULL,
  `readidualPayment` DECIMAL(5,2) NOT NULL,
  `notes` TEXT NOT NULL,
  `customerId` BIGINT NOT NULL,
  `employeeId` BIGINT NOT NULL,
  `productId` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Sale_Customer1_idx` (`customerId` ASC),
  INDEX `fk_Sale_Employee1_idx` (`employeeId` ASC),
  INDEX `fk_Sale_Product1_idx` (`productId` ASC),
  CONSTRAINT `fk_Sale_Customer1`
  FOREIGN KEY (`customerId`)
  REFERENCES `acredito`.`Customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Sale_Employee1`
  FOREIGN KEY (`employeeId`)
  REFERENCES `acredito`.`Employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Sale_Product1`
  FOREIGN KEY (`productId`)
  REFERENCES `acredito`.`Product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acredito`.`Payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acredito`.`Payment` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
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


-- -----------------------------------------------------
-- Table `acredito`.`Contact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acredito`.`Contact` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `personId` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Contact_Person1_idx` (`personId` ASC),
  CONSTRAINT `fk_Contact_Person1`
  FOREIGN KEY (`personId`)
  REFERENCES `acredito`.`Person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
