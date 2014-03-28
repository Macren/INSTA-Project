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
     * @param school
     * @param education 
     */
    public Administrator(int id, String login, String passwd, String mail, Date birthDate, String firstName, String lastName, int phone, School school, Education education) {
        super(id, login, passwd, mail, birthDate, firstName, lastName, phone, school, education);
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
    public Administrator(String login, String passwd, String mail, Date birthDate, String firstName, String lastName, int phone, School school, Education education) {
        super(login, passwd, mail, birthDate, firstName, lastName, phone, school, education);
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
    
    /**
     * Methods
     */
    
    
    
    /**
     * createStudent()
     * ---------------
     * Create a new student in school database
     * 
     * @param myStudent 
     */
    public  void    createStudent(Student myStudent) {
        
    }
    
    /**
     * updateStudent()
     * ---------------
     * Update a student information in school database
     * 
     * @param myStudent 
     */
    public  void    updateStudent(Student myStudent) {
        
    }
    
    /**
     * deleteStudent()
     * ---------------
     * Delete a student from school database
     * 
     * @param myStudent 
     */
    public  void    deleteStudent(Student myStudent) {
        
    }
    
    /**
     * createTeacher()
     * ---------------
     * Create a new teacher in school database
     * 
     * @param myTeacher 
     */
    public  void    createTeacher(Teacher myTeacher) {
        
    }
    
    /**
     * updateTeacher()
     * ---------------
     * Update a teacher information in school database
     * 
     * @param myTeacher
     */
    public  void    updateTeacher(Teacher myTeacher) {
        
    }
    
    /**
     * deleteTeacher()
     * ---------------
     * Delete a teacher from school database
     * 
     * @param myTeacher 
     */
    public  void    deleteTeacher(Teacher myTeacher) {
        
    }
    
    /**
     * createEducation()
     * -----------------
     * Create a new education in school database
     * 
     * @param myEducation
     */
    public  void    createEducation(Education myEducation) {
        
    }
    
    /**
     * updateEducation()
     * -----------------
     * Update an education information in school database
     * 
     * @param myEducation
     */
    public  void    updateEducation(Education myEducation) {
        
    }
    
    /**
     * deleteEducation()
     * -----------------
     * Delete an education from school database
     * 
     * @param myEducation 
     */
    public  void    deleteEducation(Education myEducation) {
        
    }
    
    /**
     * createDiscipline()
     * ------------------
     * Create a new discipline in school database
     * 
     * @param myDiscipline
     */
    public  void    createDiscipline(Discipline myDiscipline) {
        
    }
    
    
    /**
     * updateDiscipline()
     * ------------------
     * Update a discipline information in school database
     * 
     * @param myDiscipline
     */
    public  void    updateDiscipline(Discipline myDiscipline) {
        
    }
    
    
    /**
     * deleteDiscipline()
     * ------------------
     * Delete a discipline from school database
     * 
     * @param myDiscipline
     */
    public  void    deleteDiscipline(Discipline myDiscipline) {
        
    }
    
    /**
     * createLesson()
     * --------------
     * Create a new lesson in school database
     * 
     * @param myLesson
     */
    public  void    createLesson(Lesson myLesson) {
        
    }
    
    
    /**
     * updateLesson()
     * --------------
     * Update a lesson information in school database
     * 
     * @param myLesson
     */
    public  void    updateLesson(Lesson myLesson) {
        
    }
    
    
    /**
     * deleteLesson()
     * --------------
     * Delete a lesson from school database
     * 
     * @param myLesson
     */
    public  void    deleteLesson(Lesson myLesson) {
        
    }
}
