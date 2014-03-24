package metier;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Thierry
 */
public class Education implements Serializable {
    
    /**
     * Attributs
     */
    private int                 id;
    private int                 nbHours;
    private String              name;
    private List<Discipline>    disciplines;
    
    /**
     * Contructeur
     * @param id
     * @param nbHours
     * @param name
     * @param disciplines 
     */
    public Education(int id, int nbHours, String name, List<Discipline> disciplines) {
        this.id = id;
        this.nbHours = nbHours;
        this.name = name;
        this.disciplines = disciplines;
    }
    /**
     * Constructeur sans id
     * @param nbHours
     * @param name
     * @param disciplines 
     */
    public Education(int nbHours, String name, List<Discipline> disciplines) {
        this.nbHours = nbHours;
        this.name = name;
        this.disciplines = disciplines;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    @Override
    public String toString() {
        return "Education{" + "nbHours=" + nbHours + ", name=" + name + ", disciplines=" + disciplines + '}';
    }
    
    
    
    
}
