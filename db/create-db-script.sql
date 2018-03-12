-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bucketlistDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bucketlistDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bucketlistDB` DEFAULT CHARACTER SET latin1 ;
USE `bucketlistDB` ;

-- -----------------------------------------------------
-- Table `bucketlistDB`.`categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bucketlistDB`.`categories` (
  `category_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`category_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bucketlistDB`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bucketlistDB`.`users` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `pass` CHAR(128) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bucketlistDB`.`bucket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bucketlistDB`.`bucket` (
  `bucket_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `category_fk` INT(11) NULL DEFAULT NULL,
  `public` VARCHAR(1) NOT NULL,
  `user_fk` INT(11) NOT NULL,
  `bucketpass` CHAR(128) NULL DEFAULT NULL,
  PRIMARY KEY (`bucket_id`),
  INDEX `category_id_idx` (`category_fk` ASC),
  INDEX `user_fk_idx` (`user_fk` ASC),
  CONSTRAINT `category_fk`
    FOREIGN KEY (`category_fk`)
    REFERENCES `bucketlistDB`.`categories` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_fk`
    FOREIGN KEY (`user_fk`)
    REFERENCES `bucketlistDB`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bucketlistDB`.`listitems`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bucketlistDB`.`listitems` (
  `item_id` INT(11) NOT NULL AUTO_INCREMENT,
  `bucket_fk` INT(11) NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `desc` VARCHAR(1000) NULL DEFAULT NULL,
  `done` VARCHAR(1) NOT NULL,
  `secret_mark` VARCHAR(1) NOT NULL,
  `secret_author` VARCHAR(255) NULL DEFAULT NULL,
  `tags` VARCHAR(255) NULL DEFAULT NULL,
  `icon` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  INDEX `bucket_fk_idx` (`bucket_fk` ASC),
  CONSTRAINT `bucket_fk`
    FOREIGN KEY (`bucket_fk`)
    REFERENCES `bucketlistDB`.`bucket` (`bucket_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
