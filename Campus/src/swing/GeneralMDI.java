/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package swing;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import metier.AbstractUser;
import metier.Administrator;
import metier.Student;
import metier.Teacher;
import utils.UserType;

/**
 *
 * @author Madeleine
 */
public class GeneralMDI extends javax.swing.JFrame {

    
    /**
     * ===============
     * User Attributes
     * ===============
     */
    private Student         myStudent = null;
    private Teacher         myTeacher = null;
    private Administrator   myAdmin = null;
    
    
    
    /**
     * ============
     * Constructors
     * ============
     */
    
    /**
     * Creates new form GeneralMDI
     */
    public GeneralMDI() {
        initComponents();
    }
    
    public GeneralMDI(UserType userType, AbstractUser user) {
        initComponents();
        this.jLabel1.setText("Victoryyyyyy");
        switch (userType) {
            case STUDENT:
                myStudent = new Student(user);
                this.initMenuForStudent();
                break;
                
            case TEACHER:
                myTeacher = new Teacher(user);
                break;
                
            case ADMIN:
                myAdmin = new Administrator(user);
                this.initMenuForAdmin();
                break;
        }
        if (myStudent != null)
            System.out.println(myStudent);
        if (myTeacher != null)
            System.out.println(myTeacher);
        if (myAdmin != null)
            System.out.println(myAdmin);
    }
    
    /**
     * =================
     * Init UI Functions
     * =================
     */
    
    public  void        initMenuForAdmin() {
        
        // Menu Bar : Fichier
        // ------------------
        JMenuItem       importItem = new JMenuItem("Importer...");
        JMenuItem       exportItem = new JMenuItem("Exporter...");
        this.fileMenuBar.add(importItem);
        this.fileMenuBar.add(exportItem);
        this.fileMenuBar.add(this.disconnectMenuItem);
        
        // Menu Bar : Outils
        // -----------------
        JMenu           addItem = new JMenu("Ajouter...");
        JMenuItem       addUserItem = new JMenuItem("Utilisateur");
        JMenuItem       addLessonItem = new JMenuItem("Cours");
        JMenuItem       addDisciplineItem = new JMenuItem("Matière");
        JMenuItem       addEducationItem = new JMenuItem("Formation");
        
        addUserItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserMenuItemActionPerformed(evt);
            }
        });
        
        addEducationItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEduMenuItemActionPerformed(evt);
            }
        });
        
        addDisciplineItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDisciMenuItemActionPerformed(evt);
            }
        });
        
        addLessonItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addLessonMenuItemActionPerformed(evt);
            }
        });
        
        addItem.add(addUserItem);
        addItem.add(addLessonItem);
        addItem.add(addDisciplineItem);
        addItem.add(addEducationItem);
        JMenuItem       listStudentItem = new JMenuItem("Liste des élèves");
        JMenuItem       listTeacherItem = new JMenuItem("Liste des profs");
        JMenuItem       listAdminItem = new JMenuItem("Liste des admin");
        this.toolsMenuBar.add(addItem);
        this.toolsMenuBar.add(listStudentItem);
        this.toolsMenuBar.add(listTeacherItem);
        this.toolsMenuBar.add(listAdminItem);
    }
    
    public  void        initMenuForStudent() {
        
        // Menu Bar : Fichier
        // ------------------
        JMenuItem       modifyProfileItem = new JMenuItem("Modifier profil");
        this.fileMenuBar.add(modifyProfileItem);
        this.fileMenuBar.add(this.disconnectMenuItem);
        
        // Menu Bar : Outils
        // -----------------
        JMenuItem       planningItem = new JMenuItem("Planning...");
        JMenuItem       listLessonItem = new JMenuItem("Liste cours");
        JMenuItem       infoEducItem = new JMenuItem("Infos formation");
        JMenuItem       contactManagerItem = new JMenuItem("Carnet d'adresse");
        this.toolsMenuBar.add(planningItem);
        this.toolsMenuBar.add(listLessonItem);
        this.toolsMenuBar.add(infoEducItem);
        this.toolsMenuBar.add(contactManagerItem);
    }
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        desktopPane = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenuBar = new javax.swing.JMenu();
        disconnectMenuItem = new javax.swing.JMenuItem();
        toolsMenuBar = new javax.swing.JMenu();
        helpMenuBar = new javax.swing.JMenu();

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");
        desktopPane.add(jLabel1);
        jLabel1.setBounds(50, 44, 590, 340);

        fileMenuBar.setMnemonic('h');
        fileMenuBar.setText("Fichier");

        disconnectMenuItem.setText("Se déconnecter");
        disconnectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnectMenuItemActionPerformed(evt);
            }
        });
        fileMenuBar.add(disconnectMenuItem);

        menuBar.add(fileMenuBar);

        toolsMenuBar.setText("Outils");
        menuBar.add(toolsMenuBar);

        helpMenuBar.setText("Aide");
        menuBar.add(helpMenuBar);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void disconnectMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnectMenuItemActionPerformed
        // TODO add your handling code here:
        AuthentificationUI authentUI = new AuthentificationUI();
        authentUI.setVisible(true);
        if (this.myAdmin != null)
            myAdmin = null;
        if (this.myTeacher != null)
            myTeacher = null;
        if (this.myStudent != null)
            myStudent = null;
        this.dispose();
    }//GEN-LAST:event_disconnectMenuItemActionPerformed

    private void addUserMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
        AddUserUI addUserUI = new AddUserUI(this.myAdmin.getSchool());
        addUserUI.setVisible(true);
    }                                                   

    private void addEduMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
        AddEducationUI addEduUI = new AddEducationUI(this.myAdmin.getSchool());
        addEduUI.setVisible(true);
    }                                                

    private void addDisciMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
        AddDisciplineUI addDisciUI = new AddDisciplineUI(this.myAdmin.getSchool());
        addDisciUI.setVisible(true);
    }                                          

    private void addLessonMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
        AddLessonUI addLessonUI = new AddLessonUI(this.myAdmin.getSchool());
        addLessonUI.setVisible(true);
    }  
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
            java.util.logging.Logger.getLogger(GeneralMDI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GeneralMDI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GeneralMDI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GeneralMDI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GeneralMDI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem disconnectMenuItem;
    private javax.swing.JMenu fileMenuBar;
    private javax.swing.JMenu helpMenuBar;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu toolsMenuBar;
    // End of variables declaration//GEN-END:variables

}
