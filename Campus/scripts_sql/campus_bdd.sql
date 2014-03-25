CREATE DATABASE IF NOT EXISTS `campus_bdd`;

USE `campus_bdd` ;

-- -----------------------------------------------------
-- Table `campus_bdd`.`school`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `campus_bdd`.`school` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `campus_bdd`.`education`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `campus_bdd`.`education` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `nb_hours` INT NULL ,
  `promo` INT NOT NULL ,
  `id_school` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_education_school_idx` (`id_school` ASC) ,
  CONSTRAINT `fk_education_school`
    FOREIGN KEY (`id_school` )
    REFERENCES `campus_bdd`.`school` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `campus_bdd`.`discipline_status`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `campus_bdd`.`discipline_status` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `label` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `campus_bdd`.`discipline`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `campus_bdd`.`discipline` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `begin_date` DATETIME NOT NULL ,
  `end_date` DATETIME NOT NULL ,
  `id_education` INT NOT NULL ,
  `id_discipline_status` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_discipline_education_idx` (`id_education` ASC) ,
  INDEX `fk_discipline_status_idx` (`id_discipline_status` ASC) ,
  CONSTRAINT `fk_discipline_education`
    FOREIGN KEY (`id_education` )
    REFERENCES `campus_bdd`.`education` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_discipline_status`
    FOREIGN KEY (`id_discipline_status` )
    REFERENCES `campus_bdd`.`discipline_status` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `campus_bdd`.`role`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `campus_bdd`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `label` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `campus_bdd`.`user`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `campus_bdd`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `login` VARCHAR(45) NOT NULL ,
  `pwd` VARCHAR(45) NOT NULL ,
  `mail` VARCHAR(45) NOT NULL ,
  `birth_date` DATETIME NOT NULL ,
  `first_name` VARCHAR(45) NOT NULL ,
  `last_name` VARCHAR(45) NOT NULL ,
  `phone` INT NOT NULL ,
  `id_role` INT NOT NULL ,
  `id_school` INT NOT NULL ,
  `id_education` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_user_role_idx` (`id_role` ASC) ,
  INDEX `fk_user_school_idx` (`id_school` ASC) ,
  INDEX `fk_user_education_idx` (`id_education` ASC) ,
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`id_role` )
    REFERENCES `campus_bdd`.`role` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_school`
    FOREIGN KEY (`id_school` )
    REFERENCES `campus_bdd`.`school` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_education`
    FOREIGN KEY (`id_education` )
    REFERENCES `campus_bdd`.`education` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `campus_bdd`.`lesson_status`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `campus_bdd`.`lesson_status` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `label` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `campus_bdd`.`lesson`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `campus_bdd`.`lesson` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `is_tp` TINYINT(1) NULL ,
  `is_test` TINYINT(1) NULL ,
  `begin_date` DATETIME NOT NULL ,
  `end_date` DATETIME NOT NULL ,
  `id_discipline` INT NOT NULL ,
  `id_user_teacher` INT NOT NULL ,
  `id_lesson_status` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_lesson_discipline_idx` (`id_discipline` ASC) ,
  INDEX `fk_lesson_user_teacher_idx` (`id_user_teacher` ASC) ,
  INDEX `fk_lesson_status_lesson_idx` (`id_lesson_status` ASC) ,
  CONSTRAINT `fk_lesson_discipline`
    FOREIGN KEY (`id_discipline` )
    REFERENCES `campus_bdd`.`discipline` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lesson_user_teacher`
    FOREIGN KEY (`id_user_teacher` )
    REFERENCES `campus_bdd`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lesson_status_lesson`
    FOREIGN KEY (`id_lesson_status` )
    REFERENCES `campus_bdd`.`lesson_status` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = big5
COLLATE = big5_chinese_ci;


-- -----------------------------------------------------
-- Table `campus_bdd`.`participation_lesson`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `campus_bdd`.`participation_lesson` (
  `id_user_student` INT NOT NULL ,
  `id_lesson` INT NOT NULL ,
  `participation_date` DATETIME NOT NULL ,
  PRIMARY KEY (`id_user_student`, `id_lesson`) ,
  INDEX `fk_participation_user_student_idx` (`id_user_student` ASC) ,
  INDEX `fk_participation_lesson_idx` (`id_lesson` ASC) ,
  CONSTRAINT `fk_participation_user_student`
    FOREIGN KEY (`id_user_student` )
    REFERENCES `campus_bdd`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_participation_lesson`
    FOREIGN KEY (`id_lesson` )
    REFERENCES `campus_bdd`.`lesson` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `campus_bdd`.`mark`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `campus_bdd`.`mark` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `value` FLOAT NULL ,
  `value_max` FLOAT NULL ,
  `id_user_student` INT NOT NULL ,
  `id_user_teacher` INT NULL ,
  `id_discipline` INT NOT NULL ,
  `comment` VARCHAR(255) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_mark_user_student_idx` (`id_user_student` ASC) ,
  INDEX `fk_mark_discipline_idx` (`id_discipline` ASC) ,
  INDEX `fk_mark_user_teacher_idx` (`id_user_teacher` ASC) ,
  CONSTRAINT `fk_mark_user_student`
    FOREIGN KEY (`id_user_student` )
    REFERENCES `campus_bdd`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mark_discipline`
    FOREIGN KEY (`id_discipline` )
    REFERENCES `campus_bdd`.`discipline` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mark_user_teacher`
    FOREIGN KEY (`id_user_teacher` )
    REFERENCES `campus_bdd`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `campus_bdd`.`group`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `campus_bdd`.`group` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `id_user_creator` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_group_user_creator_idx` (`id_user_creator` ASC) ,
  CONSTRAINT `fk_group_user_creator`
    FOREIGN KEY (`id_user_creator` )
    REFERENCES `campus_bdd`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `campus_bdd`.`inscription_discipline`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `campus_bdd`.`inscription_discipline` (
  `id_user_student` INT NOT NULL ,
  `id_discipline` INT NOT NULL ,
  `inscription_date` DATETIME NOT NULL ,
  `admin_validation` TINYINT(1) NULL ,
  PRIMARY KEY (`id_user_student`, `id_discipline`) ,
  INDEX `fk_inscription_user_student_idx` (`id_user_student` ASC) ,
  INDEX `fk_inscription_discipline_idx` (`id_discipline` ASC) ,
  CONSTRAINT `fk_inscription_user_student`
    FOREIGN KEY (`id_user_student` )
    REFERENCES `campus_bdd`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inscription_discipline`
    FOREIGN KEY (`id_discipline` )
    REFERENCES `campus_bdd`.`discipline` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `campus_bdd`.`inscription_lesson`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `campus_bdd`.`inscription_lesson` (
  `id_user_student` INT NOT NULL ,
  `id_lesson` INT NOT NULL ,
  `inscription_date` DATETIME NOT NULL ,
  `desinscription_date` DATETIME NULL ,
  `admin_validation` VARCHAR(45) NULL ,
  PRIMARY KEY (`id_user_student`, `id_lesson`) ,
  INDEX `fk_inscription_lesson_user_student_idx` (`id_user_student` ASC) ,
  INDEX `fk_inscription_lesson_lesson_idx` (`id_lesson` ASC) ,
  CONSTRAINT `fk_inscription_lesson_user_student`
    FOREIGN KEY (`id_user_student` )
    REFERENCES `campus_bdd`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inscription_lesson_lesson`
    FOREIGN KEY (`id_lesson` )
    REFERENCES `campus_bdd`.`lesson` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `campus_bdd`.`group_user`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `campus_bdd`.`group_user` (
  `id_user` INT NOT NULL ,
  `id_group` INT NOT NULL ,
  PRIMARY KEY (`id_user`, `id_group`) ,
  INDEX `fk_group_user_user_idx` (`id_user` ASC) ,
  INDEX `fk_group_user_group_idx` (`id_group` ASC) ,
  CONSTRAINT `fk_group_user_user`
    FOREIGN KEY (`id_user` )
    REFERENCES `campus_bdd`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_group_user_group`
    FOREIGN KEY (`id_group` )
    REFERENCES `campus_bdd`.`group` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Donnée Table `campus_bdd`.`group_user`
-- -----------------------------------------------------
INSERT INTO `campus_bdd`.`role` (`label`) VALUES ('Administrator');
INSERT INTO `campus_bdd`.`role` (`label`) VALUES ('Teacher');
INSERT INTO `campus_bdd`.`role` (`label`) VALUES ('Student');

-- -----------------------------------------------------
-- Donnée Table `campus_bdd`.`group_user`
-- -----------------------------------------------------
INSERT INTO `campus_bdd`.`school` (`name`) VALUES ('School_guest');

-- -----------------------------------------------------
-- Donnée Table `campus_bdd`.`user`
-- -----------------------------------------------------
INSERT INTO `campus_bdd`.`user` (`id`, `login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_school`) VALUES (NULL, 'campus_admin', '53466ef439339fdeaf15372b85288d6c ', 'campus_admin', '2014-03-24 00:00:00.000000', 'campus_admin', OLD_PASSWORD('campus_admin'), '0000000000', '1', '1');

-- -----------------------------------------------------
-- Donnée Table `campus_bdd`.`lesson_status`
-- -----------------------------------------------------
INSERT INTO `campus_bdd`.`lesson_status` (`label`) VALUES ('AVAILABLE');
INSERT INTO `campus_bdd`.`lesson_status` (`label`) VALUES ('CANCELED');
INSERT INTO `campus_bdd`.`lesson_status` (`label`) VALUES ('FULL');
INSERT INTO `campus_bdd`.`lesson_status` (`label`) VALUES ('ENDED');

-- -----------------------------------------------------
-- Donnée Table `campus_bdd`.`discipline_status`
-- -----------------------------------------------------
INSERT INTO `campus_bdd`.`discipline_status` (`label`) VALUES ('AVAILABLE');
INSERT INTO `campus_bdd`.`discipline_status` (`label`) VALUES ('FULL');
