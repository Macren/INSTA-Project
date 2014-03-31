/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package swing;

import dao.AdministratorDAO;
import dao.DisciplineDAO;
import dao.EducationDAO;
import dao.LessonDAO;
import dao.StudentDAO;
import dao.TeacherDAO;
import java.sql.Date;
import java.sql.Time;
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
import utils.PasswordUtils;
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
        this.jif_addLesson.setVisible(false);
        this.jif_addUser.setVisible(false);
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

    
    private void initComboBoxForAddLessonUI() {
        Integer hourValue[] = new Integer[24];
        Integer minValue[] = new Integer[12];
        
        EducationDAO eduDAO = new EducationDAO();
        List    listEdu = eduDAO.selectAllBySchoolId(this.myAdmin.getSchool().getId()); //getting all education from a school
        DefaultComboBoxModel dcbmEdu = new DefaultComboBoxModel();
        
        TeacherDAO  teacherDAO = new TeacherDAO();
        List        listTeacher = teacherDAO.selectAllBySchoolId(this.myAdmin.getSchool().getId()); //getting all education from a school
        DefaultComboBoxModel dcbmTeach = new DefaultComboBoxModel();
        
        // Education Value from School
        // ---------------------------
        for (Object o : listEdu) {
            Education anEdu = (Education) o;
            dcbmEdu.addElement(anEdu);
        }
        
        // Education Value from School
        // ---------------------------
        for (Object o : listTeacher) {
            Teacher aTeach = (Teacher) o;
            dcbmTeach.addElement(aTeach);
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
        this.combob_addLesson_bHour.setModel(dcbmBHour);
        this.combob_addLesson_bMin.setModel(dcbmBMin);
        this.combob_eHour.setModel(dcbmEHour);
        this.combob_addLesson_eMin.setModel(dcbmEMin);
        this.combob_addLesson_edu.setModel(dcbmEdu);
        this.combob_addLesson_teacher.setModel(dcbmTeach);
        this.initComboBoxDisciForAddLessonUI();
    }
    
    private void initComboBoxDisciForAddLessonUI() {
        
        DisciplineDAO disciDAO = new DisciplineDAO();
        List    listDisci = disciDAO.selectAllByEducationId(((Education)this.combob_addLesson_edu.getSelectedItem()).getId()); //getting all education from a school
        DefaultComboBoxModel dcbmDisci = new DefaultComboBoxModel();
        
        // Discipline Value from Education
        // -------------------------------
        for (Object o : listDisci) {
            Discipline myDisci = (Discipline) o;
            dcbmDisci.addElement(myDisci);
        }
        this.combob_addLesson_disci.setModel(dcbmDisci);
    }
    
    private void initComboBoxForAddUserUI() {
        Integer daysValue[] = new Integer[31];
        Integer monthValue[] = new Integer[12];
        Integer yearValue[] = new Integer[100];
        String  typeValue[] = new String[3];
        
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
        
        // Year Value (50 max)
        // -------------------
        for (int i = 0; i < yearValue.length; i++) {
            yearValue[i] = i+1914;
        }
        
        // User Type Value
        // ---------------
        for (int i = 0; i < typeValue.length; i++) {
            switch(i) {
                case 0:
                    typeValue[i] = "Eleve";
                    break;
                    
                case 1:
                    typeValue[i] = "Professeur";
                    break;
                    
                case 2:
                    typeValue[i] = "Admin";
                    break;
            }
        }
        
        DefaultComboBoxModel dcbmDay = new DefaultComboBoxModel(daysValue);
        DefaultComboBoxModel dcbmMonth = new DefaultComboBoxModel(monthValue);
        DefaultComboBoxModel dcbmYear = new DefaultComboBoxModel(yearValue);
        DefaultComboBoxModel dcbmType = new DefaultComboBoxModel(typeValue);
        this.combob_addUser_day.setModel(dcbmDay);
        this.combob_addUser_month.setModel(dcbmMonth);
        this.combob_addUser_year.setModel(dcbmYear);
        this.combob_addUser_type.setModel(dcbmType);
        this.combob_addUser_education.setModel(dcbmEdu);
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
        jif_addLesson = new javax.swing.JInternalFrame();
        spin_addLesson_nbMax = new javax.swing.JSpinner();
        combob_addLesson_bHour = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        combob_addLesson_eMin = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        combob_addLesson_teacher = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        combob_addLesson_disci = new javax.swing.JComboBox();
        combob_addLesson_edu = new javax.swing.JComboBox();
        tf_addLesson_name = new javax.swing.JTextField();
        bt_addLesson = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        lbl_addLesson_winTitle = new javax.swing.JLabel();
        combob_eHour = new javax.swing.JComboBox();
        checkb_addLesson_tp = new javax.swing.JCheckBox();
        checkb_addLesson_test = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        combob_addLesson_bMin = new javax.swing.JComboBox();
        jif_addUser = new javax.swing.JInternalFrame();
        combob_addUser_year = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        combob_addUser_day = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();
        combob_addUser_month = new javax.swing.JComboBox();
        bt_addUser_add = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        lbl_addUser_winTitle = new javax.swing.JLabel();
        tf_addUser_login = new javax.swing.JTextField();
        combob_addUser_type = new javax.swing.JComboBox();
        tf_addUser_passwd = new javax.swing.JPasswordField();
        jLabel24 = new javax.swing.JLabel();
        tf_addUser_lastName = new javax.swing.JTextField();
        combob_addUser_education = new javax.swing.JComboBox();
        tf_addUser_mail = new javax.swing.JTextField();
        tf_addUser_firstName = new javax.swing.JTextField();
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
        combob_education.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combob_educationActionPerformed(evt);
            }
        });

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

        jif_addLesson.setVisible(true);

        combob_addLesson_bHour.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("Nombre max:");

        jLabel10.setText("Heure de début:");

        jLabel11.setText("minute");

        combob_addLesson_eMin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setText("heure");

        combob_addLesson_teacher.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel13.setText("Professeur:");

        combob_addLesson_disci.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        combob_addLesson_edu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combob_addLesson_edu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combob_addLesson_eduActionPerformed(evt);
            }
        });

        bt_addLesson.setText("Ajouter");
        bt_addLesson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addLessonActionPerformed(evt);
            }
        });

        jLabel14.setText("Matière:");

        lbl_addLesson_winTitle.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        lbl_addLesson_winTitle.setForeground(new java.awt.Color(0, 102, 204));
        lbl_addLesson_winTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_addLesson_winTitle.setText("Ajout d'un cours / TP / Interro");

        combob_eHour.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        checkb_addLesson_tp.setText("TP");

        checkb_addLesson_test.setText("Interrogation");

        jLabel15.setText("Heure de fin:");

        jLabel16.setText("Formation:");

        jLabel17.setText("Nom:");

        combob_addLesson_bMin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jif_addLessonLayout = new javax.swing.GroupLayout(jif_addLesson.getContentPane());
        jif_addLesson.getContentPane().setLayout(jif_addLessonLayout);
        jif_addLessonLayout.setHorizontalGroup(
            jif_addLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jif_addLessonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jif_addLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jif_addLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbl_addLesson_winTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jif_addLessonLayout.createSequentialGroup()
                            .addComponent(checkb_addLesson_tp)
                            .addGap(46, 46, 46)
                            .addComponent(checkb_addLesson_test))
                        .addComponent(bt_addLesson, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jif_addLessonLayout.createSequentialGroup()
                            .addComponent(jLabel17)
                            .addGap(58, 58, 58)
                            .addGroup(jif_addLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jif_addLessonLayout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(jLabel12)
                                    .addGap(33, 33, 33)
                                    .addComponent(jLabel11))
                                .addComponent(tf_addLesson_name, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jif_addLessonLayout.createSequentialGroup()
                        .addGroup(jif_addLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13))
                        .addGap(27, 27, 27)
                        .addGroup(jif_addLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(combob_addLesson_teacher, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(combob_addLesson_disci, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jif_addLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jif_addLessonLayout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addGap(18, 18, 18)
                            .addComponent(spin_addLesson_nbMax))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jif_addLessonLayout.createSequentialGroup()
                            .addGroup(jif_addLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel15)
                                .addComponent(jLabel10)
                                .addComponent(jLabel16))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jif_addLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(combob_addLesson_edu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jif_addLessonLayout.createSequentialGroup()
                                    .addComponent(combob_eHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(combob_addLesson_eMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jif_addLessonLayout.createSequentialGroup()
                                    .addComponent(combob_addLesson_bHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(combob_addLesson_bMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jif_addLessonLayout.setVerticalGroup(
            jif_addLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jif_addLessonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_addLesson_winTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jif_addLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(tf_addLesson_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jif_addLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(spin_addLesson_nbMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jif_addLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jif_addLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(combob_addLesson_bHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combob_addLesson_bMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jif_addLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(jif_addLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(combob_eHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(combob_addLesson_eMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jif_addLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(combob_addLesson_edu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jif_addLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(combob_addLesson_disci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jif_addLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(combob_addLesson_teacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jif_addLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkb_addLesson_tp)
                    .addComponent(checkb_addLesson_test))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_addLesson)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        desktopPane.add(jif_addLesson);
        jif_addLesson.setBounds(0, 0, 264, 353);

        jif_addUser.setVisible(true);

        combob_addUser_year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel18.setText("Prénom:");

        jLabel19.setText("Mot de Passe:");

        jLabel20.setText("E-mail:");

        jLabel21.setText("Date de Naissance:");

        combob_addUser_day.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel22.setText("Login:");

        combob_addUser_month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        bt_addUser_add.setText("Ajouter");
        bt_addUser_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addUser_addActionPerformed(evt);
            }
        });

        jLabel23.setText("Formation:");

        lbl_addUser_winTitle.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        lbl_addUser_winTitle.setForeground(new java.awt.Color(0, 102, 204));
        lbl_addUser_winTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_addUser_winTitle.setText("Ajout d'utilisateur");

        combob_addUser_type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel24.setText("Nom:");

        combob_addUser_education.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jif_addUserLayout = new javax.swing.GroupLayout(jif_addUser.getContentPane());
        jif_addUser.getContentPane().setLayout(jif_addUserLayout);
        jif_addUserLayout.setHorizontalGroup(
            jif_addUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jif_addUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jif_addUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_addUser_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jif_addUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lbl_addUser_winTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jif_addUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jif_addUserLayout.createSequentialGroup()
                                .addGroup(jif_addUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(combob_addUser_type, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jif_addUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jif_addUserLayout.createSequentialGroup()
                                        .addComponent(combob_addUser_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(combob_addUser_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(combob_addUser_year, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tf_addUser_firstName)
                                    .addComponent(tf_addUser_mail)
                                    .addComponent(tf_addUser_login)
                                    .addComponent(tf_addUser_passwd)
                                    .addComponent(combob_addUser_education, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_addUser_lastName)))
                            .addComponent(jLabel23))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jif_addUserLayout.setVerticalGroup(
            jif_addUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jif_addUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_addUser_winTitle)
                .addGap(18, 18, 18)
                .addComponent(combob_addUser_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jif_addUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(tf_addUser_lastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jif_addUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(tf_addUser_firstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jif_addUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(tf_addUser_mail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jif_addUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(combob_addUser_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combob_addUser_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combob_addUser_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jif_addUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(tf_addUser_login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jif_addUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(tf_addUser_passwd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jif_addUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(combob_addUser_education, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_addUser_add)
                .addContainerGap())
        );

        desktopPane.add(jif_addUser);
        jif_addUser.setBounds(0, 0, 334, 407);

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

        this.jif_addEdu.setVisible(false);
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
        ((DefaultTreeModel)this.myTree.getModel()).reload();

        this.jif_addDisci.setVisible(false);
    }//GEN-LAST:event_bt_addDisci_addActionPerformed

    private void combob_educationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combob_educationActionPerformed
        // TODO add your handling code here:
        this.initTree();
    }//GEN-LAST:event_combob_educationActionPerformed

    private void combob_addLesson_eduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combob_addLesson_eduActionPerformed
        // TODO add your handling code here:
        this.initComboBoxDisciForAddLessonUI();
    }//GEN-LAST:event_combob_addLesson_eduActionPerformed

    private void bt_addLessonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addLessonActionPerformed
        // TODO add your handling code here:

        // getting data from form
        // ----------------------
        String          aName = this.tf_addLesson_name.getText();

        Calendar        myCal = Calendar.getInstance(); // set a calendar to init sql.Date
        myCal.set(Calendar.HOUR, ((Integer)combob_addLesson_bHour.getSelectedItem()).intValue());
        myCal.set(Calendar.MINUTE, ((Integer)combob_addLesson_bMin.getSelectedItem()).intValue());
        Date            aBeginDate = new Date(myCal.getTime().getTime());

        Calendar        myCal1 = Calendar.getInstance(); // set a calendar to init sql.Date
        myCal1.set(Calendar.HOUR, ((Integer)combob_eHour.getSelectedItem()).intValue());
        myCal1.set(Calendar.MINUTE, ((Integer)combob_addLesson_eMin.getSelectedItem()).intValue());
        Date            anEndDate = new Date(myCal1.getTime().getTime());

        boolean         isTP = this.checkb_addLesson_tp.isSelected();
        boolean         isTest = this.checkb_addLesson_test.isSelected();
        int             nbMax = (int)this.spin_addLesson_nbMax.getValue();

        Discipline  aDisci = (Discipline)this.combob_addLesson_disci.getSelectedItem();
        Teacher     aTeacher = (Teacher)this.combob_addLesson_teacher.getSelectedItem();

        System.out.println("name: " + aName);
        System.out.println("beginDate: " + new Time(aBeginDate.getTime()));
        System.out.println("endDate: " + new Time(anEndDate.getTime()));
        System.out.println("discipline:" + aDisci);
        System.out.println("isTP: " + isTP);
        System.out.println("isTest:" + isTest);

        // setting lesson
        // --------------
        Lesson myLesson = new Lesson(aName, isTP, isTest, nbMax, aBeginDate, anEndDate, "Disponible", aTeacher, aDisci);
        System.out.println(myLesson);

        // saving lesson in DB
        // -------------------
        LessonDAO lessonDAO = new LessonDAO();
        lessonDAO.insert(myLesson);

        this.initTree();
        ((DefaultTreeModel)this.myTree.getModel()).reload();
        this.jif_addLesson.setVisible(false);
    }//GEN-LAST:event_bt_addLessonActionPerformed

    private void bt_addUser_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addUser_addActionPerformed
        // TODO add your handling code here:

        // getting data from form
        // ----------------------
        String          aLogin = this.tf_addUser_login.getText();
        String          cleanPwd = new String(this.tf_addUser_passwd.getPassword());
        String          cryptPwd = PasswordUtils.getMD5Str(cleanPwd); //crypt password using MD5
        String          strType = this.combob_addUser_type.getSelectedItem().toString();
        String          aFirstName = this.tf_addUser_firstName.getText();
        String          aLastName = this.tf_addUser_lastName.getText();
        String          aMail = this.tf_addUser_mail.getText();
        Calendar        myCal = Calendar.getInstance(); // set a calendar to init sql.Date
        myCal.set(Calendar.YEAR, ((Integer)combob_addUser_year.getSelectedItem()).intValue());
        myCal.set(Calendar.MONTH, ((Integer)combob_addUser_month.getSelectedItem()).intValue());
        myCal.set(Calendar.DAY_OF_MONTH, ((Integer)combob_addUser_day.getSelectedItem()).intValue());
        Date            aBirthDate = new Date(myCal.getTime().getTime());
        Education       anEdu = (Education)this.combob_addUser_education.getSelectedItem();
        AbstractUser    aUser = null;
        UserType        anUserType = null;

        System.out.println("type d'utilisateur: " + strType);
        System.out.println("login: " + aLogin);
        System.out.println("clean pass: " + cleanPwd);
        System.out.println("crypt pass: " + cryptPwd);
        System.out.println("mail: " + aMail);
        System.out.println("first name: " + aFirstName);
        System.out.println("last name: " + aLastName);
        System.out.println("date de naissance:" + aBirthDate);
        System.out.println("formation:" + anEdu);
        System.out.println("école:" + this.myAdmin.getSchool());

        // setting user type
        // -----------------
        if (strType.compareTo("Eleve") == 0) { // a student
            anUserType = UserType.STUDENT;
            aUser = new Student(aLogin, cryptPwd, aMail, aBirthDate, aFirstName, aLastName, 0, this.myAdmin.getSchool(), anEdu);
            System.out.println(aUser);
            StudentDAO studentDAO = new StudentDAO();
            studentDAO.insert((Student)aUser);
        }
        else if (strType.compareTo("Professeur") == 0) { // a teacher
            anUserType = UserType.TEACHER;
            aUser = new Teacher(aLogin, cryptPwd, aMail, aBirthDate, aFirstName, aLastName, 0, this.myAdmin.getSchool(), null);
            System.out.println(aUser);
            TeacherDAO teacherDAO = new TeacherDAO();
            teacherDAO.insert((Teacher)aUser);
        }
        else if (strType.compareTo("Administration") == 0) { // an admin user
            anUserType = UserType.ADMIN;
            aUser = new Administrator(aLogin, cryptPwd, aMail, aBirthDate, aFirstName, aLastName, 0, this.myAdmin.getSchool(), null);
            System.out.println(aUser);
            AdministratorDAO adminDAO = new AdministratorDAO();
            adminDAO.insert((Administrator)aUser);
        }

        this.jif_addUser.setVisible(false);
    }//GEN-LAST:event_bt_addUser_addActionPerformed

    private void addUserMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
        this.initComboBoxForAddUserUI();
        UIUtils.maxJIF(this.jif_addUser, this.desktopPane);
    }                                                   

    private void addEduMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
        UIUtils.maxJIF(this.jif_addEdu, this.desktopPane);
        UIUtils.centerPanel(this.panel_addEdu, this.desktopPane);
    }                                                

    private void addDisciMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
        this.initComboBoxForAddDisciUI();
        UIUtils.maxJIF(this.jif_addDisci, this.desktopPane);
    }                                          

    private void addLessonMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
        this.initComboBoxForAddLessonUI();
        UIUtils.maxJIF(this.jif_addLesson, this.desktopPane);
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
    private javax.swing.JButton bt_addLesson;
    private javax.swing.JButton bt_addUser_add;
    private javax.swing.JCheckBox checkb_addLesson_test;
    private javax.swing.JCheckBox checkb_addLesson_tp;
    private javax.swing.JComboBox combob_addDisci_Year;
    private javax.swing.JComboBox combob_addDisci_bDay;
    private javax.swing.JComboBox combob_addDisci_bMonth;
    private javax.swing.JComboBox combob_addDisci_eDay;
    private javax.swing.JComboBox combob_addDisci_eMonth;
    private javax.swing.JComboBox combob_addDisci_eYear;
    private javax.swing.JComboBox combob_addDisci_edu;
    private javax.swing.JComboBox combob_addLesson_bHour;
    private javax.swing.JComboBox combob_addLesson_bMin;
    private javax.swing.JComboBox combob_addLesson_disci;
    private javax.swing.JComboBox combob_addLesson_eMin;
    private javax.swing.JComboBox combob_addLesson_edu;
    private javax.swing.JComboBox combob_addLesson_teacher;
    private javax.swing.JComboBox combob_addUser_day;
    private javax.swing.JComboBox combob_addUser_education;
    private javax.swing.JComboBox combob_addUser_month;
    private javax.swing.JComboBox combob_addUser_type;
    private javax.swing.JComboBox combob_addUser_year;
    private javax.swing.JComboBox combob_eHour;
    private javax.swing.JComboBox combob_education;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem disconnectMenuItem;
    private javax.swing.JMenu fileMenuBar;
    private javax.swing.JMenu helpMenuBar;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JInternalFrame jif_addDisci;
    private javax.swing.JInternalFrame jif_addEdu;
    private javax.swing.JInternalFrame jif_addLesson;
    private javax.swing.JInternalFrame jif_addUser;
    private javax.swing.JInternalFrame jif_home;
    private javax.swing.JLabel lbl_addDisci_winTitle;
    private javax.swing.JLabel lbl_addEdu_winTitle;
    private javax.swing.JLabel lbl_addLesson_winTitle;
    private javax.swing.JLabel lbl_addUser_winTitle;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTree myTree;
    private javax.swing.JPanel panel_addEdu;
    private javax.swing.JSpinner spin_addEdu_nbHour;
    private javax.swing.JSpinner spin_addEdu_promo;
    private javax.swing.JSpinner spin_addLesson_nbMax;
    private javax.swing.JTextField tf_addDisci_name;
    private javax.swing.JTextField tf_addEdu_name;
    private javax.swing.JTextField tf_addLesson_name;
    private javax.swing.JTextField tf_addUser_firstName;
    private javax.swing.JTextField tf_addUser_lastName;
    private javax.swing.JTextField tf_addUser_login;
    private javax.swing.JTextField tf_addUser_mail;
    private javax.swing.JPasswordField tf_addUser_passwd;
    private javax.swing.JMenu toolsMenuBar;
    // End of variables declaration//GEN-END:variables

}
