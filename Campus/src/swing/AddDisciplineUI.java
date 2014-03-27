/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package swing;

import dao.DisciplineDAO;
import dao.EducationDAO;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import metier.Discipline;
import metier.Education;
import metier.School;

/**
 *
 * @author Madeleine
 */
public class AddDisciplineUI extends javax.swing.JFrame {

    private School  mySchool = null;
    /**
     * Creates new form AddDisciplineUI
     */
    public AddDisciplineUI() {
        initComponents();
    }
    
    public AddDisciplineUI(School pSchool) {
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
        Integer daysValue[] = new Integer[31];
        Integer monthValue[] = new Integer[12];
        Integer yearValue[] = new Integer[10];
        
        EducationDAO eduDAO = new EducationDAO();
        List    listEdu = eduDAO.selectAllBySchoolId(this.mySchool.getId()); //getting all education from a school
        DefaultComboBoxModel dcbmEdu = new DefaultComboBoxModel();
        
        // Education Value from School
        // ---------------------------
        for (Object o : listEdu) {
            Education myEdu = (Education) o;
            dcbmEdu.addElement(myEdu);
        }
        
        // Day in Month Value
        // ------------------
        for (int i = 0; i < daysValue.length; i++) {
            daysValue[i] = i+1;
        }
        
        // Month in Year Value
        // -------------------
        for (int i = 0; i < monthValue.length; i++) {
            monthValue[i] = i+1;
        }
        
        // Year Value (10 max)
        // -------------------
        for (int i = 0; i < yearValue.length; i++) {
            yearValue[i] = i+2014;
        }
        
        DefaultComboBoxModel dcbmDay = new DefaultComboBoxModel(daysValue);
        DefaultComboBoxModel dcbmMonth = new DefaultComboBoxModel(monthValue);
        DefaultComboBoxModel dcbmYear = new DefaultComboBoxModel(yearValue);
        DefaultComboBoxModel dcbmDay1 = new DefaultComboBoxModel(daysValue);
        DefaultComboBoxModel dcbmMonth1 = new DefaultComboBoxModel(monthValue);
        DefaultComboBoxModel dcbmYear1 = new DefaultComboBoxModel(yearValue);
        this.combob_day.setModel(dcbmDay);
        this.combob_month.setModel(dcbmMonth);
        this.combob_year.setModel(dcbmYear);
        this.combob_day1.setModel(dcbmDay1);
        this.combob_month1.setModel(dcbmMonth1);
        this.combob_year1.setModel(dcbmYear1);
        this.combob_edu.setModel(dcbmEdu);
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
        bt_addEdu = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lbl_winTitle = new javax.swing.JLabel();
        tf_name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        combob_day = new javax.swing.JComboBox();
        combob_month = new javax.swing.JComboBox();
        combob_year = new javax.swing.JComboBox();
        combob_day1 = new javax.swing.JComboBox();
        combob_month1 = new javax.swing.JComboBox();
        combob_year1 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        combob_edu = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nom:");

        bt_addEdu.setText("Ajouter");
        bt_addEdu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addEduActionPerformed(evt);
            }
        });

        jLabel3.setText("Date de fin:");

        lbl_winTitle.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        lbl_winTitle.setForeground(new java.awt.Color(0, 102, 204));
        lbl_winTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_winTitle.setText("Ajout d'une matière");

        jLabel2.setText("Date de début:");

        combob_day.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        combob_month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        combob_year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        combob_day1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        combob_month1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        combob_year1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Formation:");

        combob_edu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_winTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_addEdu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(combob_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(combob_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(combob_year, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(combob_day1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(combob_month1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(combob_year1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(combob_edu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_name))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_winTitle)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tf_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(combob_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combob_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combob_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(combob_day1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(combob_month1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(combob_year1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(combob_edu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(bt_addEdu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_addEduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addEduActionPerformed
        // TODO add your handling code here:

        // getting data from form
        // ----------------------
        String          aName = this.tf_name.getText();
        
        Calendar        myCal = Calendar.getInstance(); // set a calendar to init sql.Date
        myCal.set(Calendar.YEAR, ((Integer)combob_year.getSelectedItem()).intValue());
        myCal.set(Calendar.MONTH, ((Integer)combob_month.getSelectedItem()).intValue());
        myCal.set(Calendar.DAY_OF_MONTH, ((Integer)combob_day.getSelectedItem()).intValue());
        Date            aBeginDate = new Date(myCal.getTime().getTime());
        
        Calendar        myCal1 = Calendar.getInstance(); // set a calendar to init sql.Date
        myCal.set(Calendar.YEAR, ((Integer)combob_year1.getSelectedItem()).intValue());
        myCal.set(Calendar.MONTH, ((Integer)combob_month1.getSelectedItem()).intValue());
        myCal.set(Calendar.DAY_OF_MONTH, ((Integer)combob_day1.getSelectedItem()).intValue());
        Date            anEndDate = new Date(myCal1.getTime().getTime());
        
        Education anEdu = (Education)this.combob_edu.getSelectedItem();

        System.out.println("name: " + aName);
        System.out.println("beginDate: " + aBeginDate);
        System.out.println("endDate: " + anEndDate);
        System.out.println("school:" + this.mySchool);

        // setting discipline
        // ------------------
        Discipline myDisci = new Discipline(aName, aBeginDate, anEndDate, anEdu, "Disponible");
        System.out.println(myDisci);
        
        // saving discipline in DB
        // -----------------------
        DisciplineDAO disciDAO = new DisciplineDAO();
        disciDAO.insert(myDisci);

        this.dispose();
    }//GEN-LAST:event_bt_addEduActionPerformed

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
            java.util.logging.Logger.getLogger(AddDisciplineUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddDisciplineUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddDisciplineUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddDisciplineUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddDisciplineUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_addEdu;
    private javax.swing.JComboBox combob_day;
    private javax.swing.JComboBox combob_day1;
    private javax.swing.JComboBox combob_edu;
    private javax.swing.JComboBox combob_month;
    private javax.swing.JComboBox combob_month1;
    private javax.swing.JComboBox combob_year;
    private javax.swing.JComboBox combob_year1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lbl_winTitle;
    private javax.swing.JTextField tf_name;
    // End of variables declaration//GEN-END:variables
}
