/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metier;

import java.io.Serializable;

/**
 *
 * @author biron
 */
public class Mark implements Serializable {
  
  /**
   * Variables
   */
  private int         id;
  private float       value;
  private float       valueMax;
  private Student     student;
  private Teacher     teacher;
  private Discipline  discipline;
  private String      comment;
  
  /**
   * Constructeur
   * @param id
   * @param value
   * @param valueMax
   * @param student
   * @param teacher
   * @param discipline
   * @param comment
   */
  public Mark(int id, float value, float valueMax, Student student, Teacher teacher, Discipline discipline, String comment) {
    this.id         = id;
    this.value      = value;
    this.valueMax   = valueMax;
    this.student    = student;
    this.teacher    = teacher;
    this.discipline = discipline;
    this.comment    = comment;
  }
  
  /**structeur
   * @param value
   * @param valueMax
   * @param student
   * @param teacher
   * @param discipline
   * @param comment
   */
  public Mark(float value, float valueMax, Student student, Teacher teacher, Discipline discipline, String comment) {
    this.value      = value;
    this.valueMax   = valueMax;
    this.student    = student;
    this.teacher    = teacher;
    this.discipline = discipline;
    this.comment    = comment;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public float getValue() {
    return value;
  }

  public void setValue(float value) {
    this.value = value;
  }

  public float getValueMax() {
    return valueMax;
  }

  public void setValueMax(float valueMax) {
    this.valueMax = valueMax;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
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

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
  
  @Override
  public String toString() {
    return value + "/" + valueMax;
  }
  
  
  
}
