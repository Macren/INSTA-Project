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
  `nb_max_student` INT NOT NULL ,
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
-- Données Table `campus_bdd`.`role`
-- -----------------------------------------------------
INSERT INTO `campus_bdd`.`role` (`label`) VALUES ('Administrator');
INSERT INTO `campus_bdd`.`role` (`label`) VALUES ('Teacher');
INSERT INTO `campus_bdd`.`role` (`label`) VALUES ('Student');

-- -----------------------------------------------------
-- Données Table `campus_bdd`.`school`
-- -----------------------------------------------------
INSERT INTO `campus_bdd`.`school` (`name`) VALUES ('Insta');
INSERT INTO `campus_bdd`.`school` (`name`) VALUES ('Ecole2');

-- -----------------------------------------------------
-- Données Table `campus_bdd`.`user`
-- -----------------------------------------------------
INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_school`) VALUES ('campus_admin', '53466ef439339fdeaf15372b85288d6c ', 'campus_admin', '2014-03-24 00:00:00.000000', 'campus_admin', 'campus_admin', '0000000000', '1', '1');
INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_school`) VALUES ('campus_teacher', '61e99028e915e6caa4f1ddafacf9b3d7 ', 'campus_teacher', '2014-03-24 00:00:00.000000', 'campus_teacher', 'campus_teacher', '0000000000', '2', '1');
INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_school`) VALUES ('campus_student', 'a0016406b5f15f7f3f1e1300154c6d10 ', 'campus_student', '2014-03-24 00:00:00.000000', 'campus_student', 'campus_student', '0000000000', '3', '1');

INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_school`) VALUES ('AMAR.Philippe', '9003d1df22eb4d3820015070385194c8', 'student@campus.com', '1987-03-24 00:00:00.000000', 'AMAR', 'Philippe', '0606060606', '3', '1');
INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_school`) VALUES ('BAEV.Rosen', '9003d1df22eb4d3820015070385194c8', 'student@campus.com', '1988-06-12 00:00:00.000000', 'BAEV', 'Rosen', '0606060606', '3', '1');
INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_school`) VALUES ('BASTIEN.Jacqueline', '9003d1df22eb4d3820015070385194c8', 'student@campus.com', '1988-11-27 00:00:00.000000', 'BASTIEN', 'Jacqueline', '0606060606', '3', '1');
INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_school`) VALUES ('BERGQUIST.Asa', '9003d1df22eb4d3820015070385194c8', 'student@campus.com', '1988-02-12 00:00:00.000000', 'BERGQUIST', 'Asa', '0606060606', '3', '1');
INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_school`) VALUES ('BERTHOLLET.Nicolas', '9003d1df22eb4d3820015070385194c8', 'student@campus.com', '1988-07-05 00:00:00.000000', 'BERTHOLLET', 'Nicolas', '0606060606', '3', '1');
INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_school`) VALUES ('BEZERRA.MariaRita', '9003d1df22eb4d3820015070385194c8', 'student@campus.com', '1987-10-08 00:00:00.000000', 'BEZERRA', 'Maria Rita', '0606060606', '3', '1');
INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_school`) VALUES ('BONNAUD.Laure', '9003d1df22eb4d3820015070385194c8', 'student@campus.com', '1987-10-12 00:00:00.000000', 'BONNAUD', 'Laure', '0606060606', '3', '1');
INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_school`) VALUES ('BORISOVA.Inés', '9003d1df22eb4d3820015070385194c8', 'student@campus.com', '1989-06-09 00:00:00.000000', 'BORISOVA', 'Inés', '0606060606', '3', '1');
INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_school`) VALUES ('BRECK.Karine', '9003d1df22eb4d3820015070385194c8', 'student@campus.com', '1989-04-02 00:00:00.000000', 'BRECK', 'Karine', '0606060606', '3', '1');
INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_school`) VALUES ('BRICE.Anne', '9003d1df22eb4d3820015070385194c8', 'student@campus.com', '1988-01-04 00:00:00.000000', 'BRICE', 'Anne', '0606060606', '3', '1');
INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_school`) VALUES ('CARICATO.Paolo', '9003d1df22eb4d3820015070385194c8', 'student@campus.com', '1988-01-19 00:00:00.000000', 'CARICATO', 'Paolo', '0606060606', '3', '1');
INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_school`) VALUES ('DEMONT.Pierre', '9003d1df22eb4d3820015070385194c8', 'student@campus.com', '1988-04-18 00:00:00.000000', 'DEMONT', 'Pierre', '0606060606', '3', '1');
INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_school`) VALUES ('FAMOSE.Catherine', '9003d1df22eb4d3820015070385194c8', 'student@campus.com', '1986-04-15 00:00:00.000000', 'FAMOSE', 'Catherine', '0606060606', '3', '1');
INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_school`) VALUES ('FRUGERE.Stephanie', '9003d1df22eb4d3820015070385194c8', 'student@campus.com', '1988-06-21 00:00:00.000000', 'FRUGERE', 'Stephanie', '0606060606', '3', '1');
INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_school`) VALUES ('FULLICK.Joanna', '9003d1df22eb4d3820015070385194c8', 'student@campus.com', '1988-08-16 00:00:00.000000', 'FULLICK', 'Joanna', '0606060606', '3', '1');
INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_school`) VALUES ('HERAU.Vincent', '9003d1df22eb4d3820015070385194c8', 'student@campus.com', '1988-08-16 00:00:00.000000', 'HERAU', 'Vincent', '0606060606', '3', '1');
INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_school`) VALUES ('KNOWLES.Sarah', '9003d1df22eb4d3820015070385194c8', 'student@campus.com', '1989-01-26 00:00:00.000000', 'KNOWLES', 'Sarah', '0606060606', '3', '1');
INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_school`) VALUES ('LAMBERT.Michel', '9003d1df22eb4d3820015070385194c8', 'student@campus.com', '1987-02-10 00:00:00.000000', 'LAMBERT', 'Michel', '0606060606', '3', '1');
INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_school`) VALUES ('SAIDOU.Cheick', '9003d1df22eb4d3820015070385194c8', 'student@campus.com', '1987-03-02 00:00:00.000000', 'SAIDOU', 'Cheick', '0606060606', '3', '1');
INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_school`) VALUES ('PUJOL.Jacques', '9003d1df22eb4d3820015070385194c8', 'student@campus.com', '1988-03-03 00:00:00.000000', 'PUJOL', 'Jacques', '0606060606', '3', '1');
INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_school`) VALUES ('TOUZET.Denis', '9003d1df22eb4d3820015070385194c8', 'student@campus.com', '1988-04-17 00:00:00.000000', 'TOUZET', 'Denis', '0606060606', '3', '1');
INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_school`) VALUES ('VANA.Jan', '9003d1df22eb4d3820015070385194c8', 'student@campus.com', '1989-05-21 00:00:00.000000', 'VANA', 'Jan', '0606060606', '3', '1');
INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_school`) VALUES ('ZALCMANE.Luis', '9003d1df22eb4d3820015070385194c8', 'student@campus.com', '1989-05-21 00:00:00.000000', 'ZALCMANE', 'Luis', '0606060606', '3', '1');


-- -----------------------------------------------------
-- Données Table `campus_bdd`.`lesson_status`
-- -----------------------------------------------------
INSERT INTO `campus_bdd`.`lesson_status` (`label`) VALUES ('Disponible');
INSERT INTO `campus_bdd`.`lesson_status` (`label`) VALUES ('Annulé');
INSERT INTO `campus_bdd`.`lesson_status` (`label`) VALUES ('Complet');
INSERT INTO `campus_bdd`.`lesson_status` (`label`) VALUES ('Terminé');

-- -----------------------------------------------------
-- Données Table `campus_bdd`.`discipline_status`
-- -----------------------------------------------------
INSERT INTO `campus_bdd`.`discipline_status` (`label`) VALUES ('Disponible');
INSERT INTO `campus_bdd`.`discipline_status` (`label`) VALUES ('Complet');

-- -----------------------------------------------------
-- Données Table `campus_bdd`.`education`
-- -----------------------------------------------------
INSERT INTO `campus_bdd`.`education` (`name`, `nb_hours`, `promo`, `id_school`) VALUES ('Analyste Informaticien', '200', '2013', '1');
INSERT INTO `campus_bdd`.`education` (`name`, `nb_hours`, `promo`, `id_school`) VALUES ('Analyste Informaticien', '200', '2014', '1');
INSERT INTO `campus_bdd`.`education` (`name`, `nb_hours`, `promo`, `id_school`) VALUES ('Architecte Logiciel', '250', '2013', '1');
INSERT INTO `campus_bdd`.`education` (`name`, `nb_hours`, `promo`, `id_school`) VALUES ('Architecte Logiciel', '250', '2014', '1');

-- -----------------------------------------------------
-- Données Table `campus_bdd`.`discipline`
-- -----------------------------------------------------
INSERT INTO `campus_bdd`.`discipline` (`name`, `begin_date`, `end_date`, `id_education`, `id_discipline_status`) VALUES ('Java Bases', '2013-11-01 09:00:00.000000', '2013-11-30 17:30:00.000000', '1', '1');
INSERT INTO `campus_bdd`.`discipline` (`name`, `begin_date`, `end_date`, `id_education`, `id_discipline_status`) VALUES ('Java SE', '2014-03-01 09:00:00.000000', '2014-03-30 17:30:00.000000', '1', '1');
INSERT INTO `campus_bdd`.`discipline` (`name`, `begin_date`, `end_date`, `id_education`, `id_discipline_status`) VALUES ('Symfony2', '2014-06-01 09:00:00.000000', '2014-06-30 17:30:00.000000', '1', '1');
INSERT INTO `campus_bdd`.`discipline` (`name`, `begin_date`, `end_date`, `id_education`, `id_discipline_status`) VALUES ('Android', '2014-10-01 09:00:00.000000', '2014-10-30 17:30:00.000000', '2', '1');
INSERT INTO `campus_bdd`.`discipline` (`name`, `begin_date`, `end_date`, `id_education`, `id_discipline_status`) VALUES ('Cours Gestion Projet', '2015-02-01 09:00:00.000000', '2015-03-01 17:30:00.000000', '2', '1');
INSERT INTO `campus_bdd`.`discipline` (`name`, `begin_date`, `end_date`, `id_education`, `id_discipline_status`) VALUES ('Java EE', '2015-05-01 09:00:00.000000', '2015-05-31 17:30:00.000000', '2', '1');

-- -----------------------------------------------------
-- Données Table `campus_bdd`.`lesson`
-- -----------------------------------------------------
INSERT INTO `campus_bdd`.`lesson` (`name`, `is_tp`, `is_test`, `begin_date`, `end_date`, `id_discipline`, `id_user_teacher`, `id_lesson_status`) VALUES ('Premier cours Java', '0', '0', '2013-11-01 09:00:00.000000', '2013-11-01 17:30:00.000000', '1', '2', '4');
INSERT INTO `campus_bdd`.`lesson` (`name`, `is_tp`, `is_test`, `begin_date`, `end_date`, `id_discipline`, `id_user_teacher`, `id_lesson_status`) VALUES ('Premier cours Java2', '0', '0', '2013-11-02 09:00:00.000000', '2013-11-02 17:30:00.000000', '1', '2', '4');
INSERT INTO `campus_bdd`.`lesson` (`name`, `is_tp`, `is_test`, `begin_date`, `end_date`, `id_discipline`, `id_user_teacher`, `id_lesson_status`) VALUES ('Premier cours Java3', '0', '0', '2013-11-03 09:00:00.000000', '2013-11-03 17:30:00.000000', '1', '2', '4');
INSERT INTO `campus_bdd`.`lesson` (`name`, `is_tp`, `is_test`, `begin_date`, `end_date`, `id_discipline`, `id_user_teacher`, `id_lesson_status`) VALUES ('Premier cours Java4', '1', '0', '2013-11-04 09:00:00.000000', '2013-11-04 17:30:00.000000', '1', '2', '4');
INSERT INTO `campus_bdd`.`lesson` (`name`, `is_tp`, `is_test`, `begin_date`, `end_date`, `id_discipline`, `id_user_teacher`, `id_lesson_status`) VALUES ('Premier cours Java5', '0', '0', '2013-11-05 09:00:00.000000', '2013-11-05 17:30:00.000000', '1', '2', '4');
INSERT INTO `campus_bdd`.`lesson` (`name`, `is_tp`, `is_test`, `begin_date`, `end_date`, `id_discipline`, `id_user_teacher`, `id_lesson_status`) VALUES ('Premier cours Java6', '0', '1', '2013-11-06 09:00:00.000000', '2013-11-06 17:30:00.000000', '1', '2', '4');

INSERT INTO `campus_bdd`.`lesson` (`name`, `is_tp`, `is_test`, `begin_date`, `end_date`, `id_discipline`, `id_user_teacher`, `id_lesson_status`) VALUES ('Cours Hibernate 1', '0', '0', '2014-04-07 09:00:00.000000', '2013-11-07 17:30:00.000000', '1', '2', '1');
INSERT INTO `campus_bdd`.`lesson` (`name`, `is_tp`, `is_test`, `begin_date`, `end_date`, `id_discipline`, `id_user_teacher`, `id_lesson_status`) VALUES ('Cours Hibernate 2', '0', '0', '2013-11-08 09:00:00.000000', '2013-11-08 17:30:00.000000', '1', '2', '1');
INSERT INTO `campus_bdd`.`lesson` (`name`, `is_tp`, `is_test`, `begin_date`, `end_date`, `id_discipline`, `id_user_teacher`, `id_lesson_status`) VALUES ('Cours Hibernate 3', '0', '0', '2013-11-09 09:00:00.000000', '2013-11-09 17:30:00.000000', '1', '2', '1');
INSERT INTO `campus_bdd`.`lesson` (`name`, `is_tp`, `is_test`, `begin_date`, `end_date`, `id_discipline`, `id_user_teacher`, `id_lesson_status`) VALUES ('Cours Hibernate 4', '1', '0', '2014-04-10 09:00:00.000000', '2013-11-10 17:30:00.000000', '1', '2', '1');
INSERT INTO `campus_bdd`.`lesson` (`name`, `is_tp`, `is_test`, `begin_date`, `end_date`, `id_discipline`, `id_user_teacher`, `id_lesson_status`) VALUES ('Cours Hibernate 5', '0', '0', '2013-11-11 09:00:00.000000', '2013-11-11 17:30:00.000000', '1', '2', '1');
INSERT INTO `campus_bdd`.`lesson` (`name`, `is_tp`, `is_test`, `begin_date`, `end_date`, `id_discipline`, `id_user_teacher`, `id_lesson_status`) VALUES ('Cours Hibernate 6', '0', '1', '2013-11-12 09:00:00.000000', '2013-11-12 17:30:00.000000', '1', '2', '1');


