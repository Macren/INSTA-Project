package metier;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Thierry
 */
public class Discipline implements Serializable {
    
    /**
     * Variables
     */
    private int             id;
    private String          name;
    private Calendar        beginDate;
    private Calendar        endDate;
    private Education       education;
    
    /**
     * Constructeur
     * @param id
     * @param name
     * @param beginDate
     * @param endDate 
     */
    public Discipline(int id, String name, Calendar beginDate, Calendar endDate, Education education) {
        this.id = id;
        this.name = name;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.education = education;
    }
    /**
     * Constructeur sans id
     * @param name
     * @param lessons
     * @param marks
     * @param beginDate
     * @param endDate 
     */
    public Discipline(String name, Calendar beginDate, Calendar endDate, Education education) {
        this.name = name;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.education = education;
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

    public Calendar getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Calendar beginDate) {
        this.beginDate = beginDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

  public Education getEducation() {
    return education;
  }

  public void setEducation(Education education) {
    this.education = education;
  }
    
    
    
    @Override
    public String toString() {
        return "Discipline{" + "name=" + name + ", beginDate=" + beginDate + ", endDate=" + endDate + '}';
    }
    
    
    
    
}
