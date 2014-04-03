/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package swing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import metier.AbstractUser;
import metier.Administrator;
import metier.Contact;
import metier.Student;
import metier.Teacher;
import service.AdministratorService;
import service.ContactService;
import service.StudentService;
import service.TeacherService;

/**
 *
 * @author biron
 */
public class ListContacts extends javax.swing.JInternalFrame {
  
  private AbstractUser utilisateurConnecte;
  
  private DefaultMutableTreeNode racine;

  /**
   * Creates new form ListContacts
   */
  public ListContacts() {
    initComponents();
    
    this.initListContacts();
  }
  /**
   * Creates new form ListContacts
   */
  public ListContacts(AbstractUser pUser) {
    initComponents();
    
    if(pUser.getClass().isInstance(Administrator.class)){
      this.utilisateurConnecte = new Administrator(pUser);
    }
    if(pUser.getClass().isInstance(Teacher.class)){
      this.utilisateurConnecte = new Teacher(pUser);
    }
    if(pUser.getClass().isInstance(Student.class)){
      this.utilisateurConnecte = new Student(pUser);
    }
    
    this.initListContacts();
  }
  
  
  private void initListContacts() {
    
    ContactService contactService = new ContactService();
    List<Contact> listContacts = new ArrayList();
    listContacts = contactService.selectAllByOneUserId(1);
//    listContacts = contactService.selectAllByOneUserId(this.utilisateurConnecte.getId());
    
    // On cherche à récupérer une liste d'utilisateurs
    List<AbstractUser> listUsers = null;
    
    StudentService studentService             = new StudentService();
    TeacherService teacherService             = new TeacherService();
    AdministratorService administratorService = new AdministratorService();
    
    for (Contact contactCourant : listContacts) {
      AbstractUser u;
      
      if(studentService.selectById(contactCourant.getUtilisateur2().getId()) != null){
        u = studentService.selectById(contactCourant.getUtilisateur2().getId());
        listUsers.add(u);
      }
      if(teacherService.selectById(contactCourant.getUtilisateur2().getId()) != null){
        u = teacherService.selectById(contactCourant.getUtilisateur2().getId());
        listUsers.add((AbstractUser)u);
      }
      if(administratorService.selectById(contactCourant.getUtilisateur2().getId()) != null){
        u = administratorService.selectById(contactCourant.getUtilisateur2().getId());
        listUsers.add(u);
      }
    }
    
    this.racine = new DefaultMutableTreeNode("Contacts");
    TreeModel model = new DefaultTreeModel(racine);
    
    for (AbstractUser currentUser : listUsers) {
      DefaultMutableTreeNode noeud = new DefaultMutableTreeNode(currentUser);
      this.racine.add(noeud);
    }
    
  }
  

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    jButton1 = new javax.swing.JButton();
    jScrollPane2 = new javax.swing.JScrollPane();
    jt_list_contacts = new javax.swing.JTree();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

    jLabel1.setText("Liste de vos contacts");

    jButton1.setText("jButton1");

    jScrollPane2.setViewportView(jt_list_contacts);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(38, 38, 38)
            .addComponent(jButton1))
          .addComponent(jLabel1))
        .addContainerGap(134, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel1)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton1)
            .addGap(140, 140, 140))
          .addGroup(layout.createSequentialGroup()
            .addGap(18, 18, 18)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
            .addContainerGap())))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(ListContacts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(ListContacts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(ListContacts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(ListContacts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
        //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new ListContacts().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButton1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JTree jt_list_contacts;
  // End of variables declaration//GEN-END:variables
}
