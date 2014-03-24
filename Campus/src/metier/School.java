/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metier;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Thierry
 */
public class School implements Serializable {
   
    /**
     * Attributs
     */
    private int             id;
    private String          name;  
    private List<Teacher>   teacher;
    private List<Education> educations;
    
    /**
     * Constructeur
     * @param id
     * @param name
     * @param teacher
     * @param educations 
     */
    public School(int id, String name, List<Teacher> teacher, List<Education> educations) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.educations = educations;
    }
    /**
     * Constructeur sans id
     * @param name
     * @param teacher
     * @param educations 
     */
    public School(String name, List<Teacher> teacher, List<Education> educations) {
        this.name = name;
        this.teacher = teacher;
        this.educations = educations;
    }
    
    /**
     * Getters setters
     * @return 
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Teacher> getTeacher() {
        return teacher;
    }

    public void setTeacher(List<Teacher> teacher) {
        this.teacher = teacher;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    @Override
    public String toString() {
        return "School{" + "name=" + name + ", teacher=" + teacher + ", educations=" + educations + '}';
    }
    
    
    
    
    
}
