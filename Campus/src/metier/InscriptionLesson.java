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
public class InscriptionLesson implements Serializable {
  
  private Student student;
  private Lesson lesson;

  /**
   * Seul constructeur
   * 
   * @param student
   * @param lesson
   */
  public InscriptionLesson(Student student, Lesson lesson) {
    this.student = student;
    this.lesson = lesson;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Lesson getLesson() {
    return lesson;
  }

  public void setLesson(Lesson lesson) {
    this.lesson = lesson;
  }

}
