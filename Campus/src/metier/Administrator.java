/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metier;

import java.sql.Date;

/**
 *
 * @author Madeleine
 */
public class Administrator extends AbstractUser {

    /**
     * ==============
     * Constructor DB
     * ==============
     * @param id
     * @param login
     * @param passwd
     * @param mail
     * @param birthDate
     * @param firstName
     * @param lastName
     * @param phone
     * @param pathImgTrombi
     * @param school
     * @param education 
     */
    public Administrator(int id, String login, String passwd, String mail, Date birthDate, String firstName, String lastName, int phone, String pathImgTrombi, School school, Education education) {
        super(id, login, passwd, mail, birthDate, firstName, lastName, phone, pathImgTrombi, school, education);
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
    public Administrator(String login, String passwd, String mail, Date birthDate, String firstName, String lastName, int phone, String pathImgTrombi, School school, Education education) {
        super(login, passwd, mail, birthDate, firstName, lastName, phone, pathImgTrombi, school, education);
    }

    
    /**
     * ================
     * Copy Constructor
     * ================
     * @param other 
     */
    public Administrator(AbstractUser other) {
        super(other);
    }
    
    
    /**
     * ================================
     * Constructor for authentification
     * ================================
     * @param login
     * @param passwd 
     */
    public Administrator(String login, String passwd) {
        super(login, passwd);
    }
    
}
