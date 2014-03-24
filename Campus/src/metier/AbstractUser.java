/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metier;

import java.io.Serializable;

/**
 *
 * @author Madeleine
 */
public abstract class AbstractUser implements Serializable {
    
    
    /**
     * ==========
     * Attributes
     * ==========
     */
    
    protected   int     id;
    protected   String  login;
    protected   String  passwd;
    protected   String  mail;
    protected   String  birthDate;
    protected   String  firstName;
    protected   String  lastName;
    protected   int     phone;
    protected   int     schoolID;
    protected   int     educationID;
    

    /**
     * ==================
     * Constructor for DB
     * ==================
     * @param id
     * @param login
     * @param passwd
     * @param mail
     * @param birthDate
     * @param firstName
     * @param lastName
     * @param phone
     * @param schoolID
     * @param educationID 
     */
    public AbstractUser(int id, String login, String passwd, String mail, String birthDate, String firstName, String lastName, int phone, int schoolID, int educationID) {
        this.id = id;
        this.login = login;
        this.passwd = passwd;
        this.mail = mail;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.schoolID = schoolID;
        this.educationID = educationID;
    }

    /**
     * =================
     * Constructor no ID
     * =================
     * @param login
     * @param passwd
     * @param mail
     * @param birthDate
     * @param firstName
     * @param lastName
     * @param phone
     * @param schoolID
     * @param educationID 
     */
    public AbstractUser(String login, String passwd, String mail, String birthDate, String firstName, String lastName, int phone, int schoolID, int educationID) {
        this.login = login;
        this.passwd = passwd;
        this.mail = mail;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.schoolID = schoolID;
        this.educationID = educationID;
    }
    
    

    /**
     * =======
     * Getters
     * =======
     */
    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getMail() {
        return mail;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getPhone() {
        return phone;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public int getEducationID() {
        return educationID;
    }

    
    
    /**
     * =======
     * Setters
     * =======
     */
    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public void setEducationID(int educationID) {
        this.educationID = educationID;
    }


}
