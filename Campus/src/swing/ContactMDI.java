/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package swing;

import java.util.ArrayList;
import java.util.List;
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
import utils.UIUtils;

/**
 *
 * @author Thierry
 */
public class ContactMDI extends javax.swing.JFrame {
    
    private ContactService          contactService  = new ContactService();
    private AbstractUser            currentUser     = null;
    private StudentService          studentService  = new StudentService();
    private TeacherService          teacherService  = new TeacherService();
    private AdministratorService    adminService    = new AdministratorService();

    /**
     * Ctor
     */
    public ContactMDI() {
      
      initComponents();
      initListContacts();
    }
    /**
     * Creates new form ContactMDI
     * @param user
     */
    public ContactMDI(AbstractUser user) {
      
      this.currentUser = user;
      
      initComponents();
      this.jif_addContact.setVisible(false);
      UIUtils.maxJIF(jif_home, this.desktopPane);
      this.initListContacts();
    }
    
     private void initListContacts() {
    
    List<Contact> listContacts = contactService.selectAllByOneUserId(this.currentUser.getId());
//    listContacts = contactService.selectAllByOneUserId(this.utilisateurConnecte.getId());
    
    // On cherche à récupérer une liste d'utilisateurs
    List<AbstractUser> listUsers = new ArrayList();
    
    DefaultMutableTreeNode racine = new DefaultMutableTreeNode("Contacts");
        
    for (Contact contactCourant : listContacts) {
      AbstractUser u;
      
      if(this.studentService.selectById(contactCourant.getUtilisateur2().getId()) != null){
        u = this.studentService.selectById(contactCourant.getUtilisateur2().getId());
        listUsers.add(u);
      }
      if(this.teacherService.selectById(contactCourant.getUtilisateur2().getId()) != null){
        u = this.teacherService.selectById(contactCourant.getUtilisateur2().getId());
        listUsers.add(u);
      }
      if(this.adminService.selectById(contactCourant.getUtilisateur2().getId()) != null){
        u = this.adminService.selectById(contactCourant.getUtilisateur2().getId());
        listUsers.add(u);
      }
    }
    
    for (AbstractUser aUser : listUsers) {
      DefaultMutableTreeNode noeud = new DefaultMutableTreeNode(aUser);
      racine.add(noeud);
    }
    
    TreeModel model = new DefaultTreeModel(racine);
    this.tree_contactList.setModel(model);
  }
     
     private void initListAddContact() {
         
        List<Student>       listStudent = this.studentService.selectAllBySchoolId(this.currentUser.getSchool().getId());
        List<Teacher>       listTeacher = this.teacherService.selectAllBySchoolId(this.currentUser.getSchool().getId());
        List<Administrator> listAdmin   = this.adminService.selectAllBySchoolId(this.currentUser.getSchool().getId());
        
        DefaultMutableTreeNode racine = new DefaultMutableTreeNode("Contacts");
        
        if (!listStudent.isEmpty()) {
            // insert in a lesson node each lesson
            // -----------------------------------
            DefaultMutableTreeNode studentNode = new DefaultMutableTreeNode("Etudiant");
            racine.add(studentNode);
            for (Student student : listStudent){
                if (student.getId() != this.currentUser.getId()) {
                    DefaultMutableTreeNode  aStudentNode =   new DefaultMutableTreeNode(student);
                    studentNode.add(aStudentNode);
                }
            }
            racine.add(studentNode);
        }
        
        if (!listTeacher.isEmpty()) {
            // insert in a lesson node each lesson
            // -----------------------------------
            DefaultMutableTreeNode teacherNode = new DefaultMutableTreeNode("Professeur");
            racine.add(teacherNode);
            for (Teacher teacher : listTeacher){
                if (teacher.getId() != this.currentUser.getId()) {
                    DefaultMutableTreeNode  aTeacherNode =   new DefaultMutableTreeNode(teacher);
                    teacherNode.add(aTeacherNode);
                }
            }
            racine.add(teacherNode);
        }
        
        if (!listAdmin.isEmpty()) {
            // insert in a lesson node each lesson
            // -----------------------------------
            DefaultMutableTreeNode adminNode = new DefaultMutableTreeNode("Admin");
            racine.add(adminNode);
            for (Administrator admin : listAdmin){
                if (admin.getId()!= this.currentUser.getId()) {
                    DefaultMutableTreeNode  anAdminNode =   new DefaultMutableTreeNode(admin);
                    adminNode.add(anAdminNode);
                }
            }
            racine.add(adminNode);
        }
        
        TreeModel model = new DefaultTreeModel(racine);

        this.tree_listAddContact.setModel(model);
     }
  
    
     private void refreshAddUserUI() {
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        jif_home = new javax.swing.JInternalFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tree_contactList = new javax.swing.JTree();
        jif_addContact = new javax.swing.JInternalFrame();
        bt_addContact_add = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tree_listAddContact = new javax.swing.JTree();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        quitMenuItem = new javax.swing.JMenuItem();
        contactMenu = new javax.swing.JMenu();
        addContactMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jif_home.setVisible(true);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jScrollPane3.setViewportView(tree_contactList);

        javax.swing.GroupLayout jif_homeLayout = new javax.swing.GroupLayout(jif_home.getContentPane());
        jif_home.getContentPane().setLayout(jif_homeLayout);
        jif_homeLayout.setHorizontalGroup(
            jif_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jif_homeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jif_homeLayout.setVerticalGroup(
            jif_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jif_homeLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jif_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        desktopPane.add(jif_home);
        jif_home.setBounds(0, 0, 670, 460);

        jif_addContact.setClosable(true);
        jif_addContact.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        jif_addContact.setVisible(true);
        jif_addContact.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jif_addContactComponentHidden(evt);
            }
        });

        bt_addContact_add.setText("Ajouter");
        bt_addContact_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addContact_addActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(tree_listAddContact);

        javax.swing.GroupLayout jif_addContactLayout = new javax.swing.GroupLayout(jif_addContact.getContentPane());
        jif_addContact.getContentPane().setLayout(jif_addContactLayout);
        jif_addContactLayout.setHorizontalGroup(
            jif_addContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jif_addContactLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jif_addContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addComponent(bt_addContact_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jif_addContactLayout.setVerticalGroup(
            jif_addContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jif_addContactLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(bt_addContact_add)
                .addContainerGap())
        );

        desktopPane.add(jif_addContact);
        jif_addContact.setBounds(660, 30, 310, 410);

        fileMenu.setMnemonic('f');
        fileMenu.setText("Fichier");

        quitMenuItem.setText("Quitter");
        fileMenu.add(quitMenuItem);

        menuBar.add(fileMenu);

        contactMenu.setText("Contact");

        addContactMenuItem.setText("Ajouter");
        addContactMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addContactMenuItemActionPerformed(evt);
            }
        });
        contactMenu.add(addContactMenuItem);

        menuBar.add(contactMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addContactMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addContactMenuItemActionPerformed
        // TODO add your handling code here:
        this.initListAddContact();
        UIUtils.centerJIF(this.jif_addContact, this.desktopPane);
        this.jif_home.setVisible(false);
    }//GEN-LAST:event_addContactMenuItemActionPerformed

    private void jif_addContactComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jif_addContactComponentHidden
        // TODO add your handling code here:
        this.jif_home.setVisible(true);
    }//GEN-LAST:event_jif_addContactComponentHidden

    private void bt_addContact_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addContact_addActionPerformed
        // TODO add your handling code here:
        System.out.println("actionPerformed");
        Contact myContact = null;
        AbstractUser friend = null;
         // getting node from jtree
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree_listAddContact.getLastSelectedPathComponent();
        // if jtree selection exist
        if ((node != null)  && (!node.isRoot())) {
            if (node.getUserObject().getClass() != String.class) {
                
                // getting AbstractUser from node
                friend = (AbstractUser)node.getUserObject();
                myContact = new Contact(this.currentUser, friend);
                this.contactService.insert(myContact);
                this.jif_addContact.setVisible(false);
                this.initListContacts();
                this.tree_contactList.revalidate();
                this.tree_contactList.repaint();
            }
        }
    }//GEN-LAST:event_bt_addContact_addActionPerformed

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
            java.util.logging.Logger.getLogger(ContactMDI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ContactMDI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ContactMDI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ContactMDI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ContactMDI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addContactMenuItem;
    private javax.swing.JButton bt_addContact_add;
    private javax.swing.JMenu contactMenu;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JInternalFrame jif_addContact;
    private javax.swing.JInternalFrame jif_home;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem quitMenuItem;
    private javax.swing.JTree tree_contactList;
    private javax.swing.JTree tree_listAddContact;
    // End of variables declaration//GEN-END:variables

}
