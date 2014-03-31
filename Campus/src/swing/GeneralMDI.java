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
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import metier.AbstractUser;
import metier.Administrator;
import metier.Discipline;
import metier.Education;
import metier.Lesson;
import metier.Student;
import metier.Teacher;
import utils.UIUtils;
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
        initTree();
    }
    
    public GeneralMDI(UserType userType, AbstractUser user) {
        initComponents();
        this.jif_addEdu.setVisible(false);
        this.jif_addDisci.setVisible(false);
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
                this.initComboBox();
                this.initTree();
                break;
        }
        UIUtils.maxJIF(jif_home, desktopPane);
        if (myStudent != null)
            System.out.println(myStudent);
        if (myTeacher != null)
            System.out.println(myTeacher);
        if (myAdmin != null)
            System.out.println(myAdmin);
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Init UI Functions">  
    /**
     * =================
     * Init UI Functions
     * =================
     */
    
    /**
     * ====================
     * initMenuForStudent()
     * ====================
     * 
     * init menuBar when user is an Admin
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
    
    /**
     * ====================
     * initMenuForStudent()
     * ====================
     * 
     * init menuBar when user is a Student
     */
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
     * ==========
     * initTree()
     * ==========
     * 
     * init JTree with all discipline in a promo education
     * 
     */
    public void     initTree() {
       
        // getting list of all discipline in a promo education
        // ---------------------------------------------------
        DisciplineDAO disciplineDAO = new DisciplineDAO();
        List<Discipline> listDiscipline = disciplineDAO.selectAllByEducationIdAndEducationPromo((Education)this.combob_education.getSelectedItem());
    
        DefaultMutableTreeNode racine = new DefaultMutableTreeNode("Matières");
        
        // for each discipline
        // -------------------
        for (Discipline discipline : listDiscipline) {
            DefaultMutableTreeNode disciplineNode = new DefaultMutableTreeNode(discipline); // save the current discipline

            // getting all lessons/TPs/Tests for this discipline
            // -------------------------------------------------
            LessonDAO       lessonDAO =     new LessonDAO();
            List<Lesson>    listLesson =    lessonDAO.selectAllLessonsByDisciplineId(discipline.getId());
            List<Lesson>    listTP =        lessonDAO.selectAllTpsByDisciplineId(discipline.getId());
            List<Lesson>    listTest =      lessonDAO.selectAllTestsByDisciplineId(discipline.getId());
            
            // insert in a lesson node each lesson
            // -----------------------------------
            DefaultMutableTreeNode lessonTitleNode = new DefaultMutableTreeNode("Cours");
            disciplineNode.add(lessonTitleNode);
            for (Lesson lesson : listLesson){
                DefaultMutableTreeNode  lessonNode =   new DefaultMutableTreeNode(lesson);
                lessonTitleNode.add(lessonNode);
            }
            
            // insert in a tp node each tp
            // ---------------------------
            DefaultMutableTreeNode tpTitleNode = new DefaultMutableTreeNode("TP");
            disciplineNode.add(tpTitleNode);
            for (Lesson tp : listTP){
                DefaultMutableTreeNode  tpNode =   new DefaultMutableTreeNode(tp);
                tpTitleNode.add(tpNode);
            }
            
            // insert in a test node each test
            // -------------------------------
            DefaultMutableTreeNode testTitleNode = new DefaultMutableTreeNode("Test");
            disciplineNode.add(testTitleNode);
            for (Lesson test : listTest){
                DefaultMutableTreeNode  testNode =   new DefaultMutableTreeNode(test);
                testTitleNode.add(testNode);
            }
            
            // insert nodes in discipline node then root
            // -----------------------------------------
            disciplineNode.add(lessonTitleNode);
            disciplineNode.add(tpTitleNode);
            disciplineNode.add(testTitleNode);
            racine.add(disciplineNode);
        }
        TreeModel model = new DefaultTreeModel(racine);

        this.myTree.setModel(model);
        
    }
    
    private void initComboBox() {
        
        EducationDAO eduDAO = new EducationDAO();
        List    listEdu = eduDAO.selectAllBySchoolId(this.myAdmin.getSchool().getId()); //getting all education from a school
        DefaultComboBoxModel dcbmEdu = new DefaultComboBoxModel();
        
        // Education Value from School
        // ---------------------------
        for (Object o : listEdu) {
            Education myEdu = (Education) o;
            dcbmEdu.addElement(myEdu);
        }
        this.combob_education.setModel(dcbmEdu);
    }
    
    
    private void initComboBoxForAddDisciUI() {
        Integer daysValue[] = new Integer[31];
        Integer monthValue[] = new Integer[12];
        Integer yearValue[] = new Integer[10];
        
        EducationDAO eduDAO = new EducationDAO();
        List    listEdu = eduDAO.selectAllBySchoolId(this.myAdmin.getSchool().getId()); //getting all education from a school
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
        this.combob_addDisci_bDay.setModel(dcbmDay);
        this.combob_addDisci_bMonth.setModel(dcbmMonth);
        this.combob_addDisci_Year.setModel(dcbmYear);
        this.combob_addDisci_eDay.setModel(dcbmDay1);
        this.combob_addDisci_eMonth.setModel(dcbmMonth1);
        this.combob_addDisci_eYear.setModel(dcbmYear1);
        this.combob_addDisci_edu.setModel(dcbmEdu);
    }

    // </editor-fold>
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        desktopPane = new javax.swing.JDesktopPane();
        jif_addEdu = new javax.swing.JInternalFrame();
        panel_addEdu = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_addEdu_winTitle = new javax.swing.JLabel();
        bt_addEdu = new javax.swing.JButton();
        tf_addEdu_name = new javax.swing.JTextField();
        spin_addEdu_nbHour = new javax.swing.JSpinner();
        spin_addEdu_promo = new javax.swing.JSpinner();
        jif_home = new javax.swing.JInternalFrame();
        combob_education = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        myTree = new javax.swing.JTree();
        jif_addDisci = new javax.swing.JInternalFrame();
        lbl_addDisci_winTitle = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        combob_addDisci_edu = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        combob_addDisci_eYear = new javax.swing.JComboBox();
        combob_addDisci_eMonth = new javax.swing.JComboBox();
        bt_addDisci_add = new javax.swing.JButton();
        combob_addDisci_eDay = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        combob_addDisci_bDay = new javax.swing.JComboBox();
        combob_addDisci_bMonth = new javax.swing.JComboBox();
        combob_addDisci_Year = new javax.swing.JComboBox();
        tf_addDisci_name = new javax.swing.JTextField();
        menuBar = new javax.swing.JMenuBar();
        fileMenuBar = new javax.swing.JMenu();
        disconnectMenuItem = new javax.swing.JMenuItem();
        toolsMenuBar = new javax.swing.JMenu();
        helpMenuBar = new javax.swing.JMenu();

        jCheckBox1.setText("jCheckBox1");

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jif_addEdu.setVisible(true);

        jLabel4.setText("Nombre d'heure:");

        jLabel3.setText("Nom:");

        jLabel5.setText("Année Promo:");

        lbl_addEdu_winTitle.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        lbl_addEdu_winTitle.setForeground(new java.awt.Color(0, 102, 204));
        lbl_addEdu_winTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_addEdu_winTitle.setText("Ajout d'une formation");

        bt_addEdu.setText("Ajouter");
        bt_addEdu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addEduActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_addEduLayout = new javax.swing.GroupLayout(panel_addEdu);
        panel_addEdu.setLayout(panel_addEduLayout);
        panel_addEduLayout.setHorizontalGroup(
            panel_addEduLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_addEduLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_addEduLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_addEdu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_addEdu_winTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel_addEduLayout.createSequentialGroup()
                        .addGroup(panel_addEduLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_addEduLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tf_addEdu_name)
                            .addComponent(spin_addEdu_nbHour)
                            .addComponent(spin_addEdu_promo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_addEduLayout.setVerticalGroup(
            panel_addEduLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_addEduLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_addEdu_winTitle)
                .addGap(18, 18, 18)
                .addGroup(panel_addEduLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tf_addEdu_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_addEduLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(spin_addEdu_nbHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(panel_addEduLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(spin_addEdu_promo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(bt_addEdu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jif_addEduLayout = new javax.swing.GroupLayout(jif_addEdu.getContentPane());
        jif_addEdu.getContentPane().setLayout(jif_addEduLayout);
        jif_addEduLayout.setHorizontalGroup(
            jif_addEduLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jif_addEduLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_addEdu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jif_addEduLayout.setVerticalGroup(
            jif_addEduLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jif_addEduLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_addEdu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        desktopPane.add(jif_addEdu);
        jif_addEdu.setBounds(490, 0, 280, 250);
        desktopPane.setLayer(jif_addEdu, javax.swing.JLayeredPane.MODAL_LAYER);

        jif_home.setVisible(true);

        combob_education.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jScrollPane1.setViewportView(myTree);

        javax.swing.GroupLayout jif_homeLayout = new javax.swing.GroupLayout(jif_home.getContentPane());
        jif_home.getContentPane().setLayout(jif_homeLayout);
        jif_homeLayout.setHorizontalGroup(
            jif_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jif_homeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jif_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(combob_education, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE))
                .addContainerGap(463, Short.MAX_VALUE))
        );
        jif_homeLayout.setVerticalGroup(
            jif_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jif_homeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(combob_education, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                .addContainerGap())
        );

        desktopPane.add(jif_home);
        jif_home.setBounds(0, 0, 780, 580);

        jif_addDisci.setVisible(true);

        lbl_addDisci_winTitle.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        lbl_addDisci_winTitle.setForeground(new java.awt.Color(0, 102, 204));
        lbl_addDisci_winTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_addDisci_winTitle.setText("Ajout d'une matière");

        jLabel6.setText("Date de fin:");

        combob_addDisci_edu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Formation:");

        combob_addDisci_eYear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        combob_addDisci_eMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        bt_addDisci_add.setText("Ajouter");
        bt_addDisci_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addDisci_addActionPerformed(evt);
            }
        });

        combob_addDisci_eDay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Nom:");

        jLabel8.setText("Date de début:");

        combob_addDisci_bDay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        combob_addDisci_bMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        combob_addDisci_Year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jif_addDisciLayout = new javax.swing.GroupLayout(jif_addDisci.getContentPane());
        jif_addDisci.getContentPane().setLayout(jif_addDisciLayout);
        jif_addDisciLayout.setHorizontalGroup(
            jif_addDisciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jif_addDisciLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jif_addDisciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_addDisci_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jif_addDisciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lbl_addDisci_winTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jif_addDisciLayout.createSequentialGroup()
                            .addGroup(jif_addDisciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel8)
                                .addComponent(jLabel2)
                                .addComponent(jLabel7))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jif_addDisciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jif_addDisciLayout.createSequentialGroup()
                                    .addComponent(combob_addDisci_bDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(combob_addDisci_bMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(combob_addDisci_Year, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jif_addDisciLayout.createSequentialGroup()
                                    .addComponent(combob_addDisci_eDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(combob_addDisci_eMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(combob_addDisci_eYear, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(combob_addDisci_edu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tf_addDisci_name)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jif_addDisciLayout.setVerticalGroup(
            jif_addDisciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jif_addDisciLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_addDisci_winTitle)
                .addGap(18, 18, 18)
                .addGroup(jif_addDisciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tf_addDisci_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jif_addDisciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(combob_addDisci_bDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combob_addDisci_bMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combob_addDisci_Year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jif_addDisciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jif_addDisciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(combob_addDisci_eDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(combob_addDisci_eMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(combob_addDisci_eYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jif_addDisciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(combob_addDisci_edu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(bt_addDisci_add)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        desktopPane.add(jif_addDisci);
        jif_addDisci.setBounds(0, 0, 317, 278);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                .addContainerGap())
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

    private void bt_addEduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addEduActionPerformed
        // TODO add your handling code here:

        // getting data from form
        // ----------------------
        String  aName   = this.tf_addEdu_name.getText();
        int     aNbHour = ((Integer)spin_addEdu_nbHour.getValue()).intValue();
        int     aPromo  = ((Integer)spin_addEdu_promo.getValue()).intValue();

        System.out.println("name: " + aName);
        System.out.println("nbHour: " + aNbHour);
        System.out.println("promo: " + aPromo);
        System.out.println("school:" + myAdmin.getSchool());

        // setting & saving education
        // --------------------------
        Education myEdu = new Education(aName, aNbHour, aPromo, myAdmin.getSchool());
        System.out.println(myEdu);
        EducationDAO eduDAO = new EducationDAO();
        eduDAO.insert(myEdu);
        
        this.initComboBox();
        
        this.combob_education.revalidate();
        this.combob_education.repaint();

        this.jif_addEdu.dispose();
    }//GEN-LAST:event_bt_addEduActionPerformed

    private void bt_addDisci_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addDisci_addActionPerformed
        // TODO add your handling code here:

        // getting data from form
        // ----------------------
        String          aName = this.tf_addDisci_name.getText();

        Calendar        myCal = Calendar.getInstance(); // set a calendar to init sql.Date
        myCal.set(Calendar.YEAR, ((Integer)combob_addDisci_Year.getSelectedItem()).intValue());
        myCal.set(Calendar.MONTH, ((Integer)combob_addDisci_bMonth.getSelectedItem()).intValue());
        myCal.set(Calendar.DAY_OF_MONTH, ((Integer)combob_addDisci_bDay.getSelectedItem()).intValue());
        Date            aBeginDate = new Date(myCal.getTime().getTime());

        Calendar        myCal1 = Calendar.getInstance(); // set a calendar to init sql.Date
        myCal.set(Calendar.YEAR, ((Integer)combob_addDisci_eYear.getSelectedItem()).intValue());
        myCal.set(Calendar.MONTH, ((Integer)combob_addDisci_eMonth.getSelectedItem()).intValue());
        myCal.set(Calendar.DAY_OF_MONTH, ((Integer)combob_addDisci_eDay.getSelectedItem()).intValue());
        Date            anEndDate = new Date(myCal1.getTime().getTime());

        Education anEdu = (Education)this.combob_addDisci_edu.getSelectedItem();

        System.out.println("name: " + aName);
        System.out.println("beginDate: " + aBeginDate);
        System.out.println("endDate: " + anEndDate);
        System.out.println("school:" + this.myAdmin.getSchool());

        // setting discipline
        // ------------------
        Discipline myDisci = new Discipline(aName, aBeginDate, anEndDate, anEdu, "Disponible");
        System.out.println(myDisci);

        // saving discipline in DB
        // -----------------------
        DisciplineDAO disciDAO = new DisciplineDAO();
        disciDAO.insert(myDisci);
        
        this.initTree();
        
        this.myTree.revalidate();
        this.myTree.repaint();

        this.jif_addDisci.dispose();

        this.jif_addDisci.dispose();
    }//GEN-LAST:event_bt_addDisci_addActionPerformed

    private void addUserMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
        AddUserUI addUserUI = new AddUserUI(this.myAdmin.getSchool());
        addUserUI.setVisible(true);
    }                                                   

    private void addEduMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
        UIUtils.maxJIF(this.jif_addEdu, this.desktopPane);
        UIUtils.centerPanel(this.panel_addEdu, this.desktopPane);
//        AddEducationUI addEduUI = new AddEducationUI(this.myAdmin.getSchool());
//        addEduUI.setVisible(true);
    }                                                

    private void addDisciMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
        this.initComboBoxForAddDisciUI();
        UIUtils.maxJIF(this.jif_addDisci, this.desktopPane);
//        UIUtils.centerPanel(this.panel_addEdu, this.desktopPane);
//        AddDisciplineUI addDisciUI = new AddDisciplineUI(this.myAdmin.getSchool());
//        addDisciUI.setVisible(true);
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
    private javax.swing.JButton bt_addDisci_add;
    private javax.swing.JButton bt_addEdu;
    private javax.swing.JComboBox combob_addDisci_Year;
    private javax.swing.JComboBox combob_addDisci_bDay;
    private javax.swing.JComboBox combob_addDisci_bMonth;
    private javax.swing.JComboBox combob_addDisci_eDay;
    private javax.swing.JComboBox combob_addDisci_eMonth;
    private javax.swing.JComboBox combob_addDisci_eYear;
    private javax.swing.JComboBox combob_addDisci_edu;
    private javax.swing.JComboBox combob_education;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem disconnectMenuItem;
    private javax.swing.JMenu fileMenuBar;
    private javax.swing.JMenu helpMenuBar;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JInternalFrame jif_addDisci;
    private javax.swing.JInternalFrame jif_addEdu;
    private javax.swing.JInternalFrame jif_home;
    private javax.swing.JLabel lbl_addDisci_winTitle;
    private javax.swing.JLabel lbl_addEdu_winTitle;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTree myTree;
    private javax.swing.JPanel panel_addEdu;
    private javax.swing.JSpinner spin_addEdu_nbHour;
    private javax.swing.JSpinner spin_addEdu_promo;
    private javax.swing.JTextField tf_addDisci_name;
    private javax.swing.JTextField tf_addEdu_name;
    private javax.swing.JMenu toolsMenuBar;
    // End of variables declaration//GEN-END:variables

}
