package metier;

import java.io.Serializable;
import java.util.Calendar;
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
    private Calendar        beginHour;
    private Calendar        endHour;
    private LessonStatus    status;
    private Teacher         teacher;
    private List<Student>   students;
    
    /**
     * Constructeur
     * @param id
     * @param name
     * @param tp
     * @param test
     * @param beginHour
     * @param endHour
     * @param status
     * @param teacher
     * @param students 
     */
    public Lesson(int id, String name, boolean tp, boolean test, Calendar beginHour, Calendar endHour, LessonStatus status, Teacher teacher, List<Student> students) {
        this.id = id;
        this.name = name;
        this.tp = tp;
        this.test = test;
        this.beginHour = beginHour;
        this.endHour = endHour;
        this.status = status;
        this.teacher = teacher;
        this.students = students;
    }
    /**
     * Constructeur sans id
     * @param name
     * @param tp
     * @param test
     * @param beginHour
     * @param endHour
     * @param status
     * @param teacher
     * @param students 
     */
    public Lesson(String name, boolean tp, boolean test, Calendar beginHour, Calendar endHour, LessonStatus status, Teacher teacher, List<Student> students) {
        this.name = name;
        this.tp = tp;
        this.test = test;
        this.beginHour = beginHour;
        this.endHour = endHour;
        this.status = status;
        this.teacher = teacher;
        this.students = students;
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

    public Calendar getBeginHour() {
        return beginHour;
    }

    public void setBeginHour(Calendar beginHour) {
        this.beginHour = beginHour;
    }

    public Calendar getEndHour() {
        return endHour;
    }

    public void setEndHour(Calendar endHour) {
        this.endHour = endHour;
    }

    public LessonStatus getStatus() {
        return status;
    }

    public void setStatus(LessonStatus status) {
        this.status = status;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Lesson{" + "name=" + name + ", tp=" + tp + ", test=" + test + ", beginHour=" + beginHour + ", endHour=" + endHour + ", status=" + status + ", teacher=" + teacher + ", students=" + students + '}';
    }
    
    
}
