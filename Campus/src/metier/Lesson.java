package metier;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Thierry
 */
public class Lesson implements Serializable {
   
    /**
     * Variables
     */
    private int             id;
    private String          name;
    private boolean         tp;
    private boolean         test;
    private Date            beginDate;
    private Date            endDate;
    private String          status;
    private Teacher         teacher;
    private Discipline      discipline;
    
    /**
     * Constructeur
     * @param id
     * @param name
     * @param tp
     * @param test
     * @param beginDate
     * @param endDate
     * @param status
     * @param teacher
     * @param discipline
     */
    public Lesson(int id, String name, boolean tp, boolean test, Date beginDate, Date endDate, String status, Teacher teacher, Discipline discipline) {
        this.id         = id;
        this.name       = name;
        this.tp         = tp;
        this.test       = test;
        this.beginDate  = beginDate;
        this.endDate    = endDate;
        this.status     = status;
        this.teacher    = teacher;
        this.discipline = discipline;
    }
    /**
     * Constructeur sans id
     * @param name
     * @param tp
     * @param test
     * @param beginDate
     * @param endDate
     * @param status
     * @param teacher
     * @param discipline
     */
    public Lesson(String name, boolean tp, boolean test, Date beginDate, Date endDate, String status, Teacher teacher, Discipline discipline) {
        this.name       = name;
        this.tp         = tp;
        this.test       = test;
        this.beginDate  = beginDate;
        this.endDate    = endDate;
        this.status     = status;
        this.teacher    = teacher;
        this.discipline = discipline;
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

    public boolean isTp() {
        return this.tp;
    }

    public void setTp(boolean tp) {
        this.tp = tp;
    }

    public boolean isTest() {
        return this.test;
    }

    public void setTest(boolean test) {
        this.test = test;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Discipline getDiscipline() {
      return discipline;
    }

    public void setDiscipline(Discipline discipline) {
      this.discipline = discipline;
    }


    @Override
    public String toString() {
        return name;
    }
    
    
}
