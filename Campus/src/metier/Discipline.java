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
    private List<Lesson>    lessons;
    private List<Float>     marks;
    private Calendar        beginDate;
    private Calendar        endDate;
    
    /**
     * Constructeur
     * @param id
     * @param name
     * @param lessons
     * @param marks
     * @param beginDate
     * @param endDate 
     */
    public Discipline(int id, String name, List<Lesson> lessons, List<Float> marks, Calendar beginDate, Calendar endDate) {
        this.id = id;
        this.name = name;
        this.lessons = lessons;
        this.marks = marks;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }
    /**
     * Constructeur sans id
     * @param name
     * @param lessons
     * @param marks
     * @param beginDate
     * @param endDate 
     */
    public Discipline(String name, List<Lesson> lessons, List<Float> marks, Calendar beginDate, Calendar endDate) {
        this.name = name;
        this.lessons = lessons;
        this.marks = marks;
        this.beginDate = beginDate;
        this.endDate = endDate;
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

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Float> getMarks() {
        return marks;
    }

    public void setMarks(List<Float> marks) {
        this.marks = marks;
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
    
    @Override
    public String toString() {
        return "Discipline{" + "name=" + name + ", lessons=" + lessons + ", marks=" + marks + ", beginDate=" + beginDate + ", endDate=" + endDate + '}';
    }
    
    
    
    
}
