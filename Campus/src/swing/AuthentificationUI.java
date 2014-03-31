/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package swing;

import dao.AdministratorDAO;
import dao.StudentDAO;
import dao.TeacherDAO;
import metier.AbstractUser;
import utils.PasswordUtils;
import utils.UserType;

/**
 *
 * @author Madeleine
 */
public class AuthentificationUI extends javax.swing.JFrame {

    /**
     * Creates new form AuthentificationUI
     */
    public AuthentificationUI() {
        initComponents();
        setLocationRelativeTo(null);
        this.setTitle("Campus");
        this.lbl_error.setText("");
        this.lbl_error.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tf_pwd = new javax.swing.JPasswordField();
        tf_login = new javax.swing.JTextField();
        lbl_pwd = new javax.swing.JLabel();
        lbl_login = new javax.swing.JLabel();
        checkb_remember = new javax.swing.JCheckBox();
        bt_connect = new javax.swing.JButton();
        lbl_error = new javax.swing.JLabel();
        combob_userType = new javax.swing.JComboBox();
        lbl_winTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 153));
        setResizable(false);

        tf_pwd.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tf_login.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        tf_login.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lbl_pwd.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        lbl_pwd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_pwd.setText("Mot de passe:");

        lbl_login.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        lbl_login.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_login.setText("Login:");

        checkb_remember.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        checkb_remember.setText("Se souvenir de moi");

        bt_connect.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        bt_connect.setText("Ouvrir session");
        bt_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_connectActionPerformed(evt);
            }
        });

        lbl_error.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        lbl_error.setForeground(new java.awt.Color(255, 0, 0));
        lbl_error.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_error.setText("jLabel1");

        combob_userType.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        combob_userType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Eleve", "Professeur", "Administration" }));
        combob_userType.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lbl_winTitle.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        lbl_winTitle.setForeground(new java.awt.Color(0, 102, 153));
        lbl_winTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_winTitle.setText("Authentification");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_connect, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tf_login)
                    .addComponent(lbl_login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_pwd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tf_pwd)
                    .addComponent(lbl_error, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(combob_userType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(checkb_remember)
                        .addGap(0, 98, Short.MAX_VALUE))
                    .addComponent(lbl_winTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_winTitle)
                .addGap(18, 18, 18)
                .addComponent(combob_userType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl_login)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl_pwd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_pwd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(checkb_remember)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_error)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_connect)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    /**
     * =====================
     * User Authentification 
     * =====================
     * @param evt 
     */
    private void bt_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_connectActionPerformed
        // TODO add your handling code here:
        
        AbstractUser    aUser = null;
        GeneralMDI      mdi = null;
        UserType        userType = null;
        
        // getting data from form
        // ----------------------
        String          aLogin = tf_login.getText();
        String          cleanPwd = new String(this.tf_pwd.getPassword());
        String          cryptPwd = PasswordUtils.getMD5Str(cleanPwd); //crypt password using MD5
        String          strType = this.combob_userType.getSelectedItem().toString();
        
        System.out.println("type d'utilisateur: " + strType);
        System.out.println("login: " + aLogin);
        System.out.println("clean pass: " + cleanPwd);
        System.out.println("crypt pass: " + cryptPwd);

        // getting user from database
        // --------------------------
        if (strType.compareTo("Eleve") == 0) { // a student
            StudentDAO studentDAO = new StudentDAO();
            aUser = studentDAO.selectByLoginPwd(aLogin, cryptPwd);
            userType = UserType.STUDENT;
        }
        else if (strType.compareTo("Professeur") == 0) { // a teacher
            TeacherDAO teacherDAO = new TeacherDAO();
            aUser = teacherDAO.selectByLoginPwd(aLogin, cryptPwd);
            userType = UserType.TEACHER;
        }
        else if (strType.compareTo("Administration") == 0) { // an admin user
            AdministratorDAO adminDAO = new AdministratorDAO();
            aUser = adminDAO.selectByLoginPwd(aLogin, cryptPwd);
            userType = UserType.ADMIN;
        }
        
        // lauching MDI
        // ------------
        if (aUser != null) { // if user exist in database
            mdi = new GeneralMDI(userType, aUser);
            mdi.setLocationRelativeTo(null);
            mdi.setVisible(true);
            System.out.println(aUser);
            this.dispose();
        }
        else {
            System.out.println("noooooooo");
            this.lbl_error.setText("noooooooooo");
        }
    }//GEN-LAST:event_bt_connectActionPerformed

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
            java.util.logging.Logger.getLogger(AuthentificationUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AuthentificationUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AuthentificationUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AuthentificationUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AuthentificationUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_connect;
    private javax.swing.JCheckBox checkb_remember;
    private javax.swing.JComboBox combob_userType;
    private javax.swing.JLabel lbl_error;
    private javax.swing.JLabel lbl_login;
    private javax.swing.JLabel lbl_pwd;
    private javax.swing.JLabel lbl_winTitle;
    private javax.swing.JTextField tf_login;
    private javax.swing.JPasswordField tf_pwd;
    // End of variables declaration//GEN-END:variables
}
