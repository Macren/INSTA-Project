/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metier;

import java.util.List;

/**
 *
 * @author Madeleine
 */
public class Teacher extends AbstractUser implements IDocUse {
    
    /**
     * =========
     * Attribute
     * =========
     */
    private List<Lesson>    lessons;

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
     * @param schoolID
     * @param educationID 
     * @param lessons 
     */
    public Teacher(int id, String login, String passwd, String mail, String birthDate, String firstName, String lastName, int phone, int schoolID, int educationID, List<Lesson> lessons) {
        super(id, login, passwd, mail, birthDate, firstName, lastName, phone, schoolID, educationID);
        this.lessons = lessons;
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
     * @param lessons 
     */
    public Teacher(String login, String passwd, String mail, String birthDate, String firstName, String lastName, int phone, int schoolID, int educationID, List<Lesson> lessons) {
        super(login, passwd, mail, birthDate, firstName, lastName, phone, schoolID, educationID);
        this.lessons = lessons;
    }
    
    
    /**
     * =======
     * Methods
     * =======
     */
    
    /**
     * boolean updateLesson()
     * ----------------------
     * The teacher update a lesson
     * 
     * @param lesson to update
     * @return true if succeed
     */
    public  boolean     updateLesson(Lesson lesson) {
        return true;
    }

    
    /**
     * ==========================
     * Override Interface Methods
     * ==========================
     */
    
    /**
     * boolean uploadDoc()
     * -------------------
     * See IDocUse documentation
     * 
     * @param path
     * @return 
     */
    @Override
    public boolean uploadDoc(String path) {
        
        //TO-DO
        return true;
    }

    
    /**
     * boolean downloadDoc()
     * ---------------------
     * See IDocUse documentation
     * 
     * @param path
     * @return
     */
    @Override
    public String downloadDoc(String path) {
        
        //TO-DO
        return "";    
    }
}
