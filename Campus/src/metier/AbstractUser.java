/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metier;

import java.io.Serializable;
import java.sql.Date;
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
    
    protected   int         id;
    protected   String      login;
    protected   String      passwd;
    protected   String      mail;
    protected   Date        birthDate;
    protected   String      firstName;
    protected   String      lastName;
    protected   int         phone;
    protected   School      school;
    protected   Education   education;
    

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
     * @param school 
     * @param education 
     */
    public AbstractUser(int id, String login, String passwd, String mail, Date birthDate, String firstName, String lastName, int phone, School school, Education education) {
        this.id         = id;
        this.login      = login;
        this.passwd     = passwd;
        this.mail       = mail;
        this.birthDate  = birthDate;
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.phone      = phone;
        this.school     = school;
        this.education  = education;
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
     * @param school 
     * @param education 
     */
    public AbstractUser(String login, String passwd, String mail, Date birthDate, String firstName, String lastName, int phone, School school, Education education) {
        this.login      = login;
        this.passwd     = passwd;
        this.mail       = mail;
        this.birthDate  = birthDate;
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.phone      = phone;
        this.school     = school;
        this.education  = education;
    }
    
    /**
     * ================
     * Copy Constructor
     * ================
     * @param other user
     */
    public AbstractUser(AbstractUser other) {
        if (other != null) {
            this.id         = other.id;
            this.login      = other.login;
            this.passwd     = other.passwd;
            this.mail       = other.mail;
            this.birthDate  = other.birthDate;
            this.firstName  = other.firstName;
            this.lastName   = other.lastName;
            this.phone      = other.phone;
            this.school     = other.school;
            this.education  = other.education;
        }
    }

    /**
     * ================================
     * Constructor for authentification
     * ================================
     * @param login
     * @param passwd 
     */
    public AbstractUser(String login, String passwd) {
        this.login  = login;
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return login;
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

    public Date getBirthDate() {
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

    public School getSchool() {
        return school;
    }

    public Education getEducation() {
        return education;
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

    public void setBirthDate(Date birthDate) {
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

    public void setSchool(School school) {
        this.school = school;
    }

    public void setEducation(Education education) {
        this.education = education;
    }


}
