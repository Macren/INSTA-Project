/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package swing;

import java.util.List;
import javax.swing.DefaultListModel;
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
    
    private DefaultListModel        dcmContact = new DefaultListModel();
    private ContactService          contactService = new ContactService();
    private Student                 myStudent = null;
    private Teacher                 myTeacher = null;
    private Administrator           myAdmin = null;
    private StudentService          studentService = new StudentService();
    private TeacherService          teacherService = new TeacherService();
    private AdministratorService    adminService = new AdministratorService();

    /**
     * Ctor
     */
    public ContactMDI() {
      
      initComponents();
      initList();
    }
    /**
     * Creates new form ContactMDI
     */
    public ContactMDI(AbstractUser user) {
      
      if(user.getClass().isInstance(Administrator.class)){
        this.myAdmin = (Administrator) user;
      }
      if(user.getClass().isInstance(Teacher.class)){
        this.myTeacher = (Teacher) user;
      }
      if(user.getClass().isInstance(Student.class)){
        this.myStudent = (Student) user;
      }
      
      initComponents();
      this.initList();
    }
    
    private void initList(){
        this.dcmContact.clear();
        
        List<Contact> listContacts = null;
        
        if (this.myAdmin != null) {
          listContacts = this.contactService.selectAllByOneUserId(this.myAdmin.getId());
        }
        if (this.myTeacher != null) {
          listContacts = this.contactService.selectAllByOneUserId(this.myTeacher.getId());
        }
        if (this.myStudent != null) {
          listContacts = this.contactService.selectAllByOneUserId(this.myStudent.getId());
        }
        
        if(listContacts != null){
          for (Contact c : listContacts ){
              Contact contact = c;
              this.dcmContact.addElement(contact.getUtilisateur2().getLogin());
          }
        }
        
        this.contactList.setModel(dcmContact);
    }
    
     private void refreshAddUserUI() {

        this.tf_addContact_login.setText("");
        
        
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
        jScrollPane1 = new javax.swing.JScrollPane();
        contactList = new javax.swing.JList();
        bt_addContact = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jif_addContact = new javax.swing.JInternalFrame();
        jLabel1 = new javax.swing.JLabel();
        lb_addContact_login = new javax.swing.JLabel();
        tf_addContact_login = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jif_home.setVisible(true);

        contactList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(contactList);

        bt_addContact.setText("+");
        bt_addContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addContactActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jif_homeLayout = new javax.swing.GroupLayout(jif_home.getContentPane());
        jif_home.getContentPane().setLayout(jif_homeLayout);
        jif_homeLayout.setHorizontalGroup(
            jif_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jif_homeLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jif_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_addContact)
                    .addGroup(jif_homeLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jif_homeLayout.setVerticalGroup(
            jif_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jif_homeLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jif_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_addContact)
                .addContainerGap())
        );

        desktopPane.add(jif_home);
        jif_home.setBounds(0, 0, 830, 490);

        jif_addContact.setVisible(true);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Contact");

        lb_addContact_login.setText("Login:");

        jButton2.setText("Ajouter");

        javax.swing.GroupLayout jif_addContactLayout = new javax.swing.GroupLayout(jif_addContact.getContentPane());
        jif_addContact.getContentPane().setLayout(jif_addContactLayout);
        jif_addContactLayout.setHorizontalGroup(
            jif_addContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jif_addContactLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jif_addContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jif_addContactLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel1))
                    .addGroup(jif_addContactLayout.createSequentialGroup()
                        .addComponent(lb_addContact_login)
                        .addGap(18, 18, 18)
                        .addComponent(tf_addContact_login, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(69, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jif_addContactLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        jif_addContactLayout.setVerticalGroup(
            jif_addContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jif_addContactLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addGroup(jif_addContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_addContact_login)
                    .addComponent(tf_addContact_login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72)
                .addComponent(jButton2)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        desktopPane.add(jif_addContact);
        jif_addContact.setBounds(660, 30, 310, 410);

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("Contents");
        helpMenu.add(contentMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void bt_addContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addContactActionPerformed
        // TODO add your handling code here:
      
      // Ici appeler la fenetre contenant la liste de tous les Students et Teachers
      // setting display
//        UIUtils.centerJIF(this.jif_addDisci, this.desktopPane);
//        this.jif_home.setVisible(false);
      
      
      
        int idUser = 0;
       // aLogin = this.tf_addContact_login.getText();
        
        
        
        if (this.myAdmin != null) {
            idUser = this.myAdmin.getId();
            Administrator a = this.adminService.selectById(idUser);
        }
        if (this.myStudent != null) {
            idUser = this.myStudent.getId();
            Student s = this.studentService.selectById(idUser);
        }
        if (this.myTeacher != null) {
            idUser = this.myTeacher.getId();
            Teacher t = this.teacherService.selectById(idUser);
        }
        
       // Contact aContact = new Contact(a, );
         
    }//GEN-LAST:event_bt_addContactActionPerformed

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
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton bt_addContact;
    private javax.swing.JList contactList;
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JInternalFrame jif_addContact;
    private javax.swing.JInternalFrame jif_home;
    private javax.swing.JLabel lb_addContact_login;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JTextField tf_addContact_login;
    // End of variables declaration//GEN-END:variables

}
