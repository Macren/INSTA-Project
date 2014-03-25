/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package swing;

import dao.AdministratorDAO;
import java.awt.Dimension;
import javax.swing.JInternalFrame;
import metier.Administrator;
import metier.Student;
import metier.Teacher;

/**
 *
 * @author Madeleine
 */
public class GeneralMDI extends javax.swing.JFrame {

    private Student         myStudent = null;
    private Teacher         myTeacher = null;
    private Administrator   myAdmin = null;
    /**
     * Creates new form GeneralMDI
     */
    public GeneralMDI() {
        initComponents();
        this.menuBar.setVisible(false);
        this.centerJIF(this.jif_authent);
        this.lbl_error.setText("");
    }

    /**
     * Center a JInternalFrame in the GeneralMDI
     * -----------------------------------------
     * @param jif 
     */
    public void centerJIF(JInternalFrame jif) {
        Dimension desktopSize = this.desktopPane.getSize();
        Dimension jInternalFrameSize = jif.getSize();
        int width = (desktopSize.width - jInternalFrameSize.width) / 2;
        int height = (desktopSize.height - jInternalFrameSize.height) / 2;
        jif.setLocation(width, height);
        jif.setVisible(true);
    }
    
    public String getMD5Str(String md5) {
        try {
             java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
             byte[] array = md.digest(md5.getBytes());
             StringBuffer sb = new StringBuffer();
             for (int i = 0; i < array.length; ++i) {
               sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
             return sb.toString();
         } catch (java.security.NoSuchAlgorithmException e) {
         }
         return null;
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
        jif_authent = new javax.swing.JInternalFrame();
        lbl_login = new javax.swing.JLabel();
        lbl_pwd = new javax.swing.JLabel();
        tf_login = new javax.swing.JTextField();
        bt_connect = new javax.swing.JButton();
        checkb_remember = new javax.swing.JCheckBox();
        tf_pwd = new javax.swing.JPasswordField();
        lbl_error = new javax.swing.JLabel();
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

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jif_authent.setTitle("Authentification");
        jif_authent.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jif_authent.setVisible(true);

        lbl_login.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        lbl_login.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_login.setText("Login:");

        lbl_pwd.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        lbl_pwd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_pwd.setText("Mot de passe:");

        tf_login.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        tf_login.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        bt_connect.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        bt_connect.setText("Ouvrir session");
        bt_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_connectActionPerformed(evt);
            }
        });

        checkb_remember.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        checkb_remember.setText("Se souvenir de moi");

        lbl_error.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        lbl_error.setForeground(new java.awt.Color(255, 0, 0));
        lbl_error.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_error.setText("jLabel1");

        javax.swing.GroupLayout jif_authentLayout = new javax.swing.GroupLayout(jif_authent.getContentPane());
        jif_authent.getContentPane().setLayout(jif_authentLayout);
        jif_authentLayout.setHorizontalGroup(
            jif_authentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jif_authentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jif_authentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_connect, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tf_login)
                    .addComponent(lbl_login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_pwd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tf_pwd)
                    .addGroup(jif_authentLayout.createSequentialGroup()
                        .addComponent(checkb_remember)
                        .addGap(0, 54, Short.MAX_VALUE))
                    .addComponent(lbl_error, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jif_authentLayout.setVerticalGroup(
            jif_authentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jif_authentLayout.createSequentialGroup()
                .addContainerGap()
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

        desktopPane.add(jif_authent);
        jif_authent.setBounds(10, 10, 217, 234);

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
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void bt_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_connectActionPerformed
        // TODO add your handling code here:
        String aLogin = tf_login.getText();
        String  cleanPwd = new String(this.tf_pwd.getPassword());
        String  cryptPwd = this.getMD5Str(cleanPwd);
        cleanPwd = null;
        
        AdministratorDAO adminDAO = new AdministratorDAO();
        Administrator myAdminTest = adminDAO.selectByLoginPwd(aLogin, cryptPwd);
        if (myAdminTest != null) {
            System.out.println(myAdminTest);
            this.jif_authent.dispose();
            this.menuBar.setVisible(true);
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
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton bt_connect;
    private javax.swing.JCheckBox checkb_remember;
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JInternalFrame jif_authent;
    private javax.swing.JLabel lbl_error;
    private javax.swing.JLabel lbl_login;
    private javax.swing.JLabel lbl_pwd;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JTextField tf_login;
    private javax.swing.JPasswordField tf_pwd;
    // End of variables declaration//GEN-END:variables

}
