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
-- Table `acredito`.`EMPLOYEE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acredito`.`EMPLOYEE` ;

CREATE TABLE IF NOT EXISTS `acredito`.`EMPLOYEE` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `active` TINYINT(1) NOT NULL,
  `birthday` DATE NOT NULL,
  `code` BIGINT(20) NOT NULL,
  `firstName` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `idNumber` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `idType` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `lastName` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `password` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `photo` LONGBLOB NULL DEFAULT NULL,
  `username` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `version` BIGINT(20) NOT NULL,
  `addressId` BIGINT(20) NOT NULL,
  `officeId` BIGINT(20) NOT NULL,
  `occupationId` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_EMPLOYEE_addressId` (`addressId` ASC),
  INDEX `FK_EMPLOYEE_officeId` (`officeId` ASC),
  INDEX `fk_EMPLOYEE_OCCUPATION1_idx` (`occupationId` ASC),
  CONSTRAINT `FK_EMPLOYEE_officeId`
    FOREIGN KEY (`officeId`)
    REFERENCES `acredito`.`OFFICE` (`id`),
  CONSTRAINT `FK_EMPLOYEE_addressId`
    FOREIGN KEY (`addressId`)
    REFERENCES `acredito`.`ADDRESS` (`id`),
  CONSTRAINT `fk_EMPLOYEE_OCCUPATION1`
    FOREIGN KEY (`occupationId`)
    REFERENCES `acredito`.`OCCUPATION` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
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
  `idType` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
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
-- Table `acredito`.`PAYMENT_PLAN`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acredito`.`PAYMENT_PLAN` ;

CREATE TABLE IF NOT EXISTS `acredito`.`PAYMENT_PLAN` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NOT NULL,
  `defaultAmount` INT NOT NULL,
  `quotesNumber` INT NOT NULL,
  `active` TINYINT(1) NOT NULL,
  `description` LONGTEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acredito`.`SALE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acredito`.`SALE` ;

CREATE TABLE IF NOT EXISTS `acredito`.`SALE` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `version` BIGINT(20) NOT NULL,
  `code` BIGINT(20) NOT NULL,
  `date` DATETIME NOT NULL,
  `saleType` ENUM('CREDIT','CASH','ADVANCE') CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `saleStatus` ENUM('NOT_PAID','CANCELLED','PAID','ADVANCE_PAID','PARTIALLY_PAID','COMPLETED') NOT NULL,
  `partialAmount` DOUBLE NOT NULL,
  `discount` DOUBLE NOT NULL DEFAULT 0,
  `totalAmount` DOUBLE NOT NULL,
  `advanceAmount` DOUBLE NOT NULL,
  `residualPayment` DOUBLE NOT NULL,
  `initialPayment` DOUBLE NOT NULL,
  `paymentQuotes` INT(11) NOT NULL,
  `notes` LONGTEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `delivered` TINYINT(1) NOT NULL,
  `contactId1` BIGINT(20) NULL,
  `contactId2` BIGINT(20) NULL,
  `contactId3` BIGINT(20) NULL,
  `paymentPlanId` BIGINT(20) NULL,
  `customerId` BIGINT(20) NOT NULL,
  `employeeId` BIGINT(20) NOT NULL,
  `officeId` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_SALE_officeId` (`officeId` ASC),
  INDEX `FK_SALE_contactId2` (`contactId2` ASC),
  INDEX `FK_SALE_employeeId` (`employeeId` ASC),
  INDEX `FK_SALE_contactId1` (`contactId1` ASC),
  INDEX `FK_SALE_contactId3` (`contactId3` ASC),
  INDEX `FK_SALE_customerId` (`customerId` ASC),
  INDEX `fk_SALE_PAYMENT_PLAN1_idx` (`paymentPlanId` ASC),
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
  CONSTRAINT `fk_SALE_PAYMENT_PLAN1`
    FOREIGN KEY (`paymentPlanId`)
    REFERENCES `acredito`.`PAYMENT_PLAN` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `acredito`.`PAYMENT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acredito`.`PAYMENT` ;

CREATE TABLE IF NOT EXISTS `acredito`.`PAYMENT` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `code` BIGINT(20) NOT NULL,
  `date` DATETIME NOT NULL,
  `chargedAmount` DOUBLE NOT NULL,
  `disbursedAmount` DOUBLE NOT NULL,
  `notes` LONGTEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `employee_id` BIGINT(20) NOT NULL,
  `sale_id` BIGINT(20) NOT NULL,
  `customer_id` BIGINT(20) NOT NULL,
  `office_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_CHARGE_EMPLOYEE1_idx` (`employee_id` ASC),
  INDEX `fk_CHARGE_SALE1_idx` (`sale_id` ASC),
  INDEX `fk_CHARGE_CUSTOMER1_idx` (`customer_id` ASC),
  INDEX `fk_PAYMENT_OFFICE1_idx` (`office_id` ASC),
  CONSTRAINT `fk_CHARGE_EMPLOYEE1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `acredito`.`EMPLOYEE` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CHARGE_SALE1`
    FOREIGN KEY (`sale_id`)
    REFERENCES `acredito`.`SALE` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CHARGE_CUSTOMER1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `acredito`.`CUSTOMER` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PAYMENT_OFFICE1`
    FOREIGN KEY (`office_id`)
    REFERENCES `acredito`.`OFFICE` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `acredito`.`PAYMENT_QUOTE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acredito`.`PAYMENT_QUOTE` ;

CREATE TABLE IF NOT EXISTS `acredito`.`PAYMENT_QUOTE` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `amountDue` DOUBLE NOT NULL,
  `dueDate` DATE NOT NULL,
  `paymentNumber` INT(11) NOT NULL,
  `mora` DOUBLE NOT NULL DEFAULT 0,
  `paymentDate` DATE NULL,
  `paymentCode` BIGINT(20) NULL,
  `sale_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_PAYMENT_QUOTE_SALE1_idx` (`sale_id` ASC),
  CONSTRAINT `fk_PAYMENT_QUOTE_SALE1`
    FOREIGN KEY (`sale_id`)
    REFERENCES `acredito`.`SALE` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
  `description` LONGTEXT NULL,
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
  `available` TINYINT(1) NOT NULL,
  `code` BIGINT(20) NOT NULL,
  `name` VARCHAR(250) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `notes` LONGTEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `photo` LONGBLOB NULL DEFAULT NULL,
  `price` DOUBLE NOT NULL,
  `quantity` INT NOT NULL,
  `reservedQuantity` INT NOT NULL DEFAULT 0,
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
-- Table `acredito`.`SALE_PRODUCT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acredito`.`SALE_PRODUCT` ;

CREATE TABLE IF NOT EXISTS `acredito`.`SALE_PRODUCT` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `quantity` INT NOT NULL,
  `unitPrice` DOUBLE NOT NULL,
  `partialAmount` DOUBLE NOT NULL,
  `saleId` BIGINT(20) NOT NULL,
  `productId` BIGINT(20) NOT NULL,
  `officeId` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_SALE_has_PRODUCT_PRODUCT1_idx` (`productId` ASC),
  INDEX `fk_SALE_has_PRODUCT_SALE1_idx` (`saleId` ASC),
  INDEX `fk_SALE_PRODUCT_OFFICE1_idx` (`officeId` ASC),
  CONSTRAINT `fk_SALE_has_PRODUCT_SALE1`
    FOREIGN KEY (`saleId`)
    REFERENCES `acredito`.`SALE` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SALE_has_PRODUCT_PRODUCT1`
    FOREIGN KEY (`productId`)
    REFERENCES `acredito`.`PRODUCT` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SALE_PRODUCT_OFFICE1`
    FOREIGN KEY (`officeId`)
    REFERENCES `acredito`.`OFFICE` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `acredito`.`ROLE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acredito`.`ROLE` ;

CREATE TABLE IF NOT EXISTS `acredito`.`ROLE` (
  `id` BIGINT(20) NOT NULL,
  `name` VARCHAR(250) NOT NULL,
  `description` LONGTEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acredito`.`EMPLOYEE_ROLE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acredito`.`EMPLOYEE_ROLE` ;

CREATE TABLE IF NOT EXISTS `acredito`.`EMPLOYEE_ROLE` (
  `employeeId` BIGINT(20) NOT NULL,
  `roleId` BIGINT(20) NOT NULL,
  PRIMARY KEY (`employeeId`, `roleId`),
  INDEX `fk_EMPLOYEE_has_ROLE_ROLE1_idx` (`roleId` ASC),
  INDEX `fk_EMPLOYEE_has_ROLE_EMPLOYEE1_idx` (`employeeId` ASC),
  CONSTRAINT `fk_EMPLOYEE_has_ROLE_EMPLOYEE1`
    FOREIGN KEY (`employeeId`)
    REFERENCES `acredito`.`EMPLOYEE` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_EMPLOYEE_has_ROLE_ROLE1`
    FOREIGN KEY (`roleId`)
    REFERENCES `acredito`.`ROLE` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `acredito`.`PERMISSION`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acredito`.`PERMISSION` ;

CREATE TABLE IF NOT EXISTS `acredito`.`PERMISSION` (
  `id` BIGINT(20) NOT NULL,
  `name` VARCHAR(250) NOT NULL,
  `description` LONGTEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acredito`.`ROLE_PERMISSION`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acredito`.`ROLE_PERMISSION` ;

CREATE TABLE IF NOT EXISTS `acredito`.`ROLE_PERMISSION` (
  `ROLE_id` BIGINT(20) NOT NULL,
  `PERMISSION_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`ROLE_id`, `PERMISSION_id`),
  INDEX `fk_ROLE_has_PERMISSION_PERMISSION1_idx` (`PERMISSION_id` ASC),
  INDEX `fk_ROLE_has_PERMISSION_ROLE1_idx` (`ROLE_id` ASC),
  CONSTRAINT `fk_ROLE_has_PERMISSION_ROLE1`
    FOREIGN KEY (`ROLE_id`)
    REFERENCES `acredito`.`ROLE` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ROLE_has_PERMISSION_PERMISSION1`
    FOREIGN KEY (`PERMISSION_id`)
    REFERENCES `acredito`.`PERMISSION` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `acredito`.`TESTENTITY` (
  `id` VARCHAR(200) NOT NULL,
  `code` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
)ENGINE = InnoDB;

#CREATE TABLE IF NOT EXISTS `acredito`.`SEQUENCE` (
#  `SEQ_NAME` VARCHAR(30) NOT NULL,
#  `SEQ_COUNT` BIGINT(20) NOT NULL,
#  PRIMARY KEY (`SEQ_NAME`)
#)ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
