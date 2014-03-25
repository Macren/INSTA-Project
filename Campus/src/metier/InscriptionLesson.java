/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metier;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author biron
 */
public class InscriptionLesson implements Serializable {
  
  private Student student;
  private Lesson lesson;
  private Date dateInscription;
  private Date dateDesinscription;
  private boolean adminValidation;

  /**
   * Seul constructeur
   * 
   * @param student
   * @param lesson
   * @param dateInscription
   * @param dateDesinscription
   * @param adminValidation 
   */
  public InscriptionLesson(Student student, Lesson lesson, Date dateInscription, Date dateDesinscription, boolean adminValidation) {
    this.student = student;
    this.lesson = lesson;
    this.dateInscription = dateInscription;
    this.dateDesinscription = dateDesinscription;
    this.adminValidation = adminValidation;
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

  public Date getDateInscription() {
    return dateInscription;
  }

  public void setDateInscription(Date dateInscription) {
    this.dateInscription = dateInscription;
  }

  public Date getDateDesinscription() {
    return dateDesinscription;
  }

  public void setDateDesinscription(Date dateDesinscription) {
    this.dateDesinscription = dateDesinscription;
  }

  public boolean isAdminValidation() {
    return adminValidation;
  }

  public void setAdminValidation(boolean adminValidation) {
    this.adminValidation = adminValidation;
  }
  
}
