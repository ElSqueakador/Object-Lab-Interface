package ObjectLabEnterpriseSoftware;

import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class TomSoftMain extends javax.swing.JFrame {

    NewStudent newStudentSys;
    PendingJobsView pendingSys;
    StudentSubmission studentSys;
    boolean show;
    String PASS = "ForwardMotion";

    public TomSoftMain() {
        initComponents();
        this.setResizable(false);
        setPrintersVisible(false);
        pendingSys = new PendingJobsView();
        studentSys = new StudentSubmission();
        newStudentSys = new NewStudent();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        newStudentButton = new javax.swing.JButton();
        errorIdLabel = new javax.swing.JLabel();
        studentIdString = new javax.swing.JTextField();
        studentButton = new javax.swing.JButton();
        zcorpButton = new javax.swing.JButton();
        solidscapeButton = new javax.swing.JButton();
        AdminButton = new javax.swing.JButton();
        openProjectsButton = new javax.swing.JButton();
        objetButton = new javax.swing.JButton();
        settingsButton = new javax.swing.JButton();
        ReportsButton = new javax.swing.JButton();
        newStudent = new javax.swing.JLabel();

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Art 101-001\nArt 201-002\nArt 401-004\nArt 501-005\nArt 601-006\nArt 701-007\nArt 801-009");
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TomSoft");
        setMinimumSize(new java.awt.Dimension(545, 350));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        newStudentButton.setText("New Student");
        newStudentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newStudentButtonActionPerformed(evt);
            }
        });
        getContentPane().add(newStudentButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, 110, 30));

        errorIdLabel.setBackground(new java.awt.Color(0, 0, 0));
        errorIdLabel.setForeground(new java.awt.Color(255, 0, 0));
        getContentPane().add(errorIdLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 130, 20));

        studentIdString.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentIdStringActionPerformed(evt);
            }
        });
        getContentPane().add(studentIdString, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 130, 30));

        studentButton.setText("Student");
        studentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentButtonActionPerformed(evt);
            }
        });
        getContentPane().add(studentButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, 110, 30));

        zcorpButton.setText("ZCorp");
        zcorpButton.setToolTipText("");
        zcorpButton.setPreferredSize(new java.awt.Dimension(417, 417));
        zcorpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zcorpButtonActionPerformed(evt);
            }
        });
        getContentPane().add(zcorpButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, 130, 30));

        solidscapeButton.setText("Solidscape");
        solidscapeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solidscapeButtonActionPerformed(evt);
            }
        });
        getContentPane().add(solidscapeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, 130, 30));

        AdminButton.setText("Administrator");
        AdminButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminButtonActionPerformed(evt);
            }
        });
        getContentPane().add(AdminButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 130, 30));

        openProjectsButton.setText("Projects");
        openProjectsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openProjectsButtonActionPerformed(evt);
            }
        });
        getContentPane().add(openProjectsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, 130, 30));

        objetButton.setText("Objet");
        objetButton.setPreferredSize(new java.awt.Dimension(417, 417));
        objetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                objetButtonActionPerformed(evt);
            }
        });
        getContentPane().add(objetButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, 130, 30));

        settingsButton.setText("Settings");
        settingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsButtonActionPerformed(evt);
            }
        });
        getContentPane().add(settingsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, 130, 30));

        ReportsButton.setText("Reports");
        ReportsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportsButtonActionPerformed(evt);
            }
        });
        getContentPane().add(ReportsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 260, 130, 30));

        newStudent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ObjectLabEnterpriseSoftware/backgroundrender.png"))); // NOI18N
        getContentPane().add(newStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 350));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void solidscapeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solidscapeButtonActionPerformed
        // TODO add your handling code here:
        UtilController.retrievePrinterSettings("solidscape");
        PrinterBuild Build = new PrinterBuild();
        Build.startMakeBuildProcess("solidscape");
        dispose();
    }//GEN-LAST:event_solidscapeButtonActionPerformed

    private void openProjectsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openProjectsButtonActionPerformed
        // TODO add your handling code here:
        pendingSys.PendingJobsStart();
        dispose();
    }//GEN-LAST:event_openProjectsButtonActionPerformed

    private void zcorpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zcorpButtonActionPerformed
        // TODO add your handling code here:
        UtilController.retrievePrinterSettings("zcorp");
        PrinterBuild Build = new PrinterBuild();
        Build.startMakeBuildProcess("zcorp");
        dispose();
    }//GEN-LAST:event_zcorpButtonActionPerformed

    private void AdminButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminButtonActionPerformed
        PasswordDialogue dialogue = new PasswordDialogue();
        dialogue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialogue.setAlwaysOnTop(true);
        dialogue.setVisible(true);
        dialogue.setLocationRelativeTo(this);
        /* 
        Currently bypasses what is typed into dialogue for ease of testing
        password.equals(PASS) 
        */
        if (true) {
            //studentSubmissionButton.setVisible(false);
            setPrintersVisible(true);
        } else {
            JOptionPane.showMessageDialog(new java.awt.Frame(), "Incorrect password! Access Denied!");
        }
    }//GEN-LAST:event_AdminButtonActionPerformed

    private void objetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_objetButtonActionPerformed
        // TODO add your handling code here:
        UtilController.retrievePrinterSettings("objet");
        PrinterBuild Build = new PrinterBuild();
        Build.startMakeBuildProcess("objet");
        dispose();
    }//GEN-LAST:event_objetButtonActionPerformed

    private void ReportsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportsButtonActionPerformed
        // TODO add your handling code here:
        Reports reports = new Reports();
        reports.ReportsPage();
        dispose();
    }//GEN-LAST:event_ReportsButtonActionPerformed

    private void studentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentButtonActionPerformed
        // TODO add your handling code here:
        setPrintersVisible(false);
        String id = studentIdString.getText();//DB team this is to store String
        if(id.length() != 7){
            errorIdLabel.setText("TU ID must be 7 digits");   
        }
        //else if not in database "invailid TU ID, enter vaild or create new student"
        else{
            errorIdLabel.setText("");
            studentSys.studentSubmissionStart();
        }
        

        //dispose();
    }//GEN-LAST:event_studentButtonActionPerformed

    private void studentIdStringActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentIdStringActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_studentIdStringActionPerformed

    private void newStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newStudentButtonActionPerformed
        // TODO add your handling code here:
        newStudentSys.NewStudentMainStart();
        dispose();
        
    }//GEN-LAST:event_newStudentButtonActionPerformed

    private void settingsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        AdminSettings ad = new AdminSettings();
        ad.setVisible(true);
        dispose();
    }

    public void setPrintersVisible(boolean isVisible) {
        settingsButton.setVisible(isVisible);
        openProjectsButton.setVisible(isVisible);
        zcorpButton.setVisible(isVisible);
        solidscapeButton.setVisible(isVisible);
        objetButton.setVisible(isVisible);
        ReportsButton.setVisible(isVisible);
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());

                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TomSoftMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TomSoftMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AdminButton;
    private javax.swing.JButton ReportsButton;
    private javax.swing.JLabel errorIdLabel;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel newStudent;
    private javax.swing.JButton newStudentButton;
    private javax.swing.JButton objetButton;
    private javax.swing.JButton openProjectsButton;
    private javax.swing.JButton settingsButton;
    private javax.swing.JButton solidscapeButton;
    private javax.swing.JButton studentButton;
    private javax.swing.JTextField studentIdString;
    private javax.swing.JButton zcorpButton;
    // End of variables declaration//GEN-END:variables
}
