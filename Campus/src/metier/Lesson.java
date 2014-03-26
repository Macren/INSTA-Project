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
    private List<Student>   students;
    
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
     * @param students 
     */
    public Lesson(int id, String name, boolean tp, boolean test, Date beginDate, Date endDate, String status, Teacher teacher, Discipline discipline, List<Student> students) {
        this.id         = id;
        this.name       = name;
        this.tp         = tp;
        this.test       = test;
        this.beginDate  = beginDate;
        this.endDate    = endDate;
        this.status     = status;
        this.teacher    = teacher;
        this.discipline = discipline;
        this.students   = students;
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
     * @param students 
     */
    public Lesson(String name, boolean tp, boolean test, Date beginDate, Date endDate, String status, Teacher teacher, Discipline discipline, List<Student> students) {
        this.name       = name;
        this.tp         = tp;
        this.test       = test;
        this.beginDate  = beginDate;
        this.endDate    = endDate;
        this.status     = status;
        this.teacher    = teacher;
        this.discipline = discipline;
        this.students   = students;
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
        return true;
    }

    public void setTp(boolean tp) {
        this.tp = tp;
    }

    public boolean isTest() {
        return true;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Lesson{" + "name=" + name + ", tp=" + tp + ", test=" + test + ", status=" + status + ", teacher=" + teacher + ", students=" + students + '}';
    }
    
    
}
