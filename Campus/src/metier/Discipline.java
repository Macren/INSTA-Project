package metier;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Thierry
 */
public class Discipline implements Serializable {
    
    /**
     * Variables
     */
    private int       id;
    private String    name;
    private Date      beginDate;
    private Date      endDate;
    private Education education;
    private String    status;
    
    /**
     * Constructeur
     * @param id
     * @param name
     * @param beginDate
     * @param endDate
     * @param education
     * @param status
     */
    public Discipline(int id, String name, Date beginDate, Date endDate, Education education, String status) {
        this.id         = id;
        this.name       = name;
        this.beginDate  = beginDate;
        this.endDate    = endDate;
        this.education  = education;
        this.status     = status;
    }
    /**
     * Constructeur sans id
     * @param name
     * @param beginDate
     * @param endDate
     * @param education
     * @param status
     */
    public Discipline(String name, Date beginDate, Date endDate, Education education, String status) {
        this.name       = name;
        this.beginDate  = beginDate;
        this.endDate    = endDate;
        this.education  = education;
        this.status     = status;
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

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

  public Education getEducation() {
    return education;
  }

  public void setEducation(Education education) {
    this.education = education;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
  
  
  
  @Override
  public String toString() {
      return this.name;
  }
  
}
