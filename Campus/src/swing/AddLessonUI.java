/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package swing;

import dao.DisciplineDAO;
import dao.EducationDAO;
import dao.LessonDAO;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import metier.Discipline;
import metier.Education;
import metier.Lesson;
import metier.School;

/**
 *
 * @author Madeleine
 */
public class AddLessonUI extends javax.swing.JFrame {

    private Education   myEdu = null;
    private School      mySchool = null;
    /**
     * Creates new form AddLessonUI
     */
    public AddLessonUI() {
        initComponents();
    }
    
    public AddLessonUI(Education pEdu) {
        initComponents();
        this.myEdu = pEdu;
    }
    
    public AddLessonUI(School pSchool) {
        initComponents();
        this.mySchool = pSchool;
        this.initComboBox();
    }
    
    /**
     * ==============
     * Init JComboBox
     * ==============
     */
    private void initComboBox() {
        Integer hourValue[] = new Integer[24];
        Integer minValue[] = new Integer[12];
        
        EducationDAO eduDAO = new EducationDAO();
        List    listEdu = eduDAO.selectAllBySchoolId(this.mySchool.getId()); //getting all education from a school
        DefaultComboBoxModel dcbmEdu = new DefaultComboBoxModel();
        
        // Education Value from School
        // ---------------------------
        for (Object o : listEdu) {
            Education anEdu = (Education) o;
            dcbmEdu.addElement(anEdu);
        }
        
        // Hour in Month Value
        // -------------------
        for (int i = 0; i < hourValue.length; i++) {
            hourValue[i] = i+1;
        }
        
        // Min in Year Value
        // -----------------
        int j = 0;
        for (int i = 0; i < minValue.length; i++) {
            minValue[i] = j;
            j += 5;
        }
        
        DefaultComboBoxModel dcbmBHour = new DefaultComboBoxModel(hourValue);
        DefaultComboBoxModel dcbmBMin = new DefaultComboBoxModel(minValue);
        DefaultComboBoxModel dcbmEHour = new DefaultComboBoxModel(hourValue);
        DefaultComboBoxModel dcbmEMin = new DefaultComboBoxModel(minValue);
        this.combob_bHour.setModel(dcbmBHour);
        this.combob_bMin.setModel(dcbmBMin);
        this.combob_eHour.setModel(dcbmEHour);
        this.combob_eMin.setModel(dcbmEMin);
        this.combob_edu.setModel(dcbmEdu);
        this.initComboBoxDisci();
    }
    
    private void initComboBoxDisci() {
        
        DisciplineDAO disciDAO = new DisciplineDAO();
        List    listDisci = disciDAO.selectAllByEducationId(((Education)this.combob_edu.getSelectedItem()).getId()); //getting all education from a school
        DefaultComboBoxModel dcbmDisci = new DefaultComboBoxModel();
        
        // Discipline Value from Education
        // -------------------------------
        for (Object o : listDisci) {
            Discipline myDisci = (Discipline) o;
            dcbmDisci.addElement(myDisci);
        }
        this.combob_disci.setModel(dcbmDisci);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        combob_bMin = new javax.swing.JComboBox();
        combob_eMin = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        combob_bHour = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        lbl_winTitle = new javax.swing.JLabel();
        tf_name = new javax.swing.JTextField();
        bt_addLesson = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        combob_eHour = new javax.swing.JComboBox();
        checkb_tp = new javax.swing.JCheckBox();
        checkb_interro = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        combob_edu = new javax.swing.JComboBox();
        combob_disci = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        combob_bMin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        combob_eMin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Heure de début:");

        combob_bHour.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Matière:");

        lbl_winTitle.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        lbl_winTitle.setForeground(new java.awt.Color(0, 102, 204));
        lbl_winTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_winTitle.setText("Ajout d'un cours / TP / Interro");

        bt_addLesson.setText("Ajouter");
        bt_addLesson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addLessonActionPerformed(evt);
            }
        });

        jLabel3.setText("Heure de fin:");

        jLabel1.setText("Nom:");

        combob_eHour.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        checkb_tp.setText("TP");

        checkb_interro.setText("Interrogation");

        jLabel5.setText("Formation:");

        combob_edu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combob_edu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combob_eduActionPerformed(evt);
            }
        });

        combob_disci.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(checkb_tp))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(checkb_interro)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(combob_disci, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbl_winTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(combob_edu, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(combob_eHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(combob_eMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(tf_name)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(combob_bHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(combob_bMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(bt_addLesson, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_winTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tf_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(combob_bHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combob_bMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(combob_eHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(combob_eMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(combob_edu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(combob_disci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkb_tp)
                    .addComponent(checkb_interro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_addLesson)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_addLessonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addLessonActionPerformed
        // TODO add your handling code here:

        // getting data from form
        // ----------------------
        String          aName = this.tf_name.getText();

        Calendar        myCal = Calendar.getInstance(); // set a calendar to init sql.Date
        myCal.set(Calendar.YEAR, ((Integer)combob_bHour.getSelectedItem()).intValue());
        myCal.set(Calendar.MONTH, ((Integer)combob_bMin.getSelectedItem()).intValue());
        myCal.set(Calendar.DAY_OF_MONTH, ((Integer)combob_bHour.getSelectedItem()).intValue());
        Date            aBeginDate = new Date(myCal.getTime().getTime());

        Calendar        myCal1 = Calendar.getInstance(); // set a calendar to init sql.Date
        myCal.set(Calendar.YEAR, ((Integer)combob_eHour.getSelectedItem()).intValue());
        myCal.set(Calendar.MONTH, ((Integer)combob_eMin.getSelectedItem()).intValue());
        myCal.set(Calendar.DAY_OF_MONTH, ((Integer)combob_eHour.getSelectedItem()).intValue());
        Date            anEndDate = new Date(myCal1.getTime().getTime());
        
        boolean         isTP = this.checkb_tp.isSelected();
        boolean         isTest = this.checkb_interro.isSelected();

        Discipline aDisci = (Discipline)this.combob_disci.getSelectedItem();

        System.out.println("name: " + aName);
        System.out.println("beginDate: " + aBeginDate);
        System.out.println("endDate: " + anEndDate);
        System.out.println("discipline:" + aDisci);
        System.out.println("isTP: " + isTP);
        System.out.println("isTest:" + isTest);

        // setting lesson
        // --------------
        Lesson myLesson = new Lesson(aName, isTP, isTest, aBeginDate, anEndDate, "Disponible", null, aDisci, null);
        System.out.println(myLesson);

        // saving lesson in DB
        // -------------------
        LessonDAO lessonDAO = new LessonDAO();
        lessonDAO.insert(myLesson);

        this.dispose();
    }//GEN-LAST:event_bt_addLessonActionPerformed

    private void combob_eduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combob_eduActionPerformed
        // TODO add your handling code here:
        this.initComboBoxDisci();
    }//GEN-LAST:event_combob_eduActionPerformed

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
            java.util.logging.Logger.getLogger(AddLessonUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddLessonUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddLessonUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddLessonUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddLessonUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_addLesson;
    private javax.swing.JCheckBox checkb_interro;
    private javax.swing.JCheckBox checkb_tp;
    private javax.swing.JComboBox combob_bHour;
    private javax.swing.JComboBox combob_bMin;
    private javax.swing.JComboBox combob_disci;
    private javax.swing.JComboBox combob_eHour;
    private javax.swing.JComboBox combob_eMin;
    private javax.swing.JComboBox combob_edu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lbl_winTitle;
    private javax.swing.JTextField tf_name;
    // End of variables declaration//GEN-END:variables
}