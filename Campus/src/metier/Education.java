package metier;

import java.io.Serializable;

/**
 *
 * @author Thierry
 */
public class Education implements Serializable {
    
    /**
     * Attributs
     */
    private int     id;
    private String  name;
    private int     nbHours;
    private int     promo;
    private School  school;
    
    /**
     * Contructeur
     * @param id
     * @param name
     * @param nbHours
     * @param promo
     * @param school
     */
    public Education(int id, String name, int nbHours, int promo, School school) {
        this.id       = id;
        this.name     = name;
        this.nbHours  = nbHours;
        this.promo    = promo;
        this.school   = school;
    }
    /**
     * Constructeur sans id
     * @param name
     * @param nbHours
     * @param promo
     * @param school
     */
    public Education(String name, int nbHours, int promo, School school) {
        this.name     = name;
        this.nbHours  = nbHours;
        this.promo    = promo;
        this.school   = school;
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

    public int getNbHours() {
        return nbHours;
    }

    public void setNbHours(int nbHours) {
        this.nbHours = nbHours;
    }
    
    public int getPromo() {
        return promo;
    }

    public void setPromo(int promo) {
        this.promo = promo;
    }

    public School getSchool() {
      return school;
    }

    public void setSchool(School school) {
      this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " - Promo " + promo;
    }
    
    
    
    
}
