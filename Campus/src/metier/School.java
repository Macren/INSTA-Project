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
    
    /**
     * Constructeur
     * @param id
     * @param name
     */
    public School(int id, String name) {
        this.id = id;
        this.name = name;
    }
    /**
     * Constructeur sans id
     * @param name
     */
    public School(String name) {
        this.name = name;
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
    
    @Override
    public String toString() {
        return name;
    }
    
    
    
    
    
}
