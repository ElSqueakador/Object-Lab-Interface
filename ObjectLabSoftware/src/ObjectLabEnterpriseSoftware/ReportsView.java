package ObjectLabEnterpriseSoftware;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class ReportsView extends javax.swing.JFrame 
{
    private static final String NAME_OF_PAGE = "Reports";
    private static final MainView home = new MainView();
	
    private static DefaultTableModel model;
    private FileManager inst;
    private String selectedPrinter;
    private UtilController controller;
    private String[] headers = {"", "", "", ""};
    private ArrayList<String> printers;
	// --nav bar views ~Alex
	private BuildView buildView;
	private newJobsMgr jobsView;
	private ReportsView reportsView;	
	private newSettingsMenu adminSettingsView;
	//

    public ReportsView() 
    {
        this.controller = new UtilController();
        printers = UtilController.getListOfAllDevices();
        if(printers.size() > 0){
            selectedPrinter = printers.get(1);
          //* THIS IS THE PROBLEM WITH THE REPORTS NOT OPENING, THIS FEEDS THEM THE WRONG HEADERS NOW - used to be sql error. *\\\
            headers = UtilController.getReportColumnHeaders(selectedPrinter);

        }
        else{
            headers = new String[1];
        }
        addWindowListener
        (
            new WindowAdapter() 
            {
                @Override
                public void windowClosing(WindowEvent we)
                {
                    /* If they close the program then close out the window properly */
                    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                    dispose();
                    //home.resetAdminMode();
                }
            }
        );
    }

    public void ReportsPage() 
    {
    	getContentPane().setBackground(Color.WHITE);
        initComponents();
        initNavBar();
        model = (DefaultTableModel) reportsTable.getModel();
        if(selectedPrinter != null){
            for (ArrayList<Object> retval1 : UtilController.updateReportTableData(selectedPrinter)){ 
                model.addRow(retval1.toArray());
            }
        }
	setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchFilter = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        searchKey = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        ExportMasterReportButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        reportsTable = new javax.swing.JTable();
        exportBtn = new javax.swing.JButton();
        closeBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(UtilController.getPageName(NAME_OF_PAGE));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        searchFilter.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        searchFilter.setModel(new javax.swing.DefaultComboBoxModel(headers));
        searchFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFilterActionPerformed(evt);
            }
        });
       // getContentPane().add(searchFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 90, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Object Lab Search");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel2.setText("Search For:");
        //getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 60, 21));

        searchKey.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
       // getContentPane().add(searchKey, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 120, 430, -1));

        searchBtn.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        searchBtn.setText("Search");
        searchBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
       // getContentPane().add(searchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(647, 120, 50, 20));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 682, 10));

        ExportMasterReportButton.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        ExportMasterReportButton.setText("Export Master Report");
        ExportMasterReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportMasterReportButtonActionPerformed(evt);
            }
        });
        getContentPane().add(ExportMasterReportButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 380, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Reports");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(printers.toArray()));
        jComboBox1.setName("PrinterSelection"); // NOI18N
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 135, -1));

        reportsTable.setAutoCreateRowSorter(true);
        reportsTable.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        reportsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            headers
        )
    );
    jScrollPane1.setViewportView(reportsTable);

    getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 682, 203));

    exportBtn.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
    exportBtn.setText("Export Current Report");
    exportBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
    exportBtn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            exportBtnActionPerformed(evt);
        }
    });
    getContentPane().add(exportBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 140, 20));

    closeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ObjectLabEnterpriseSoftware/images/back_arrow_button.png"))); // NOI18N
    closeBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
    closeBtn.setBorderPainted(false);
    closeBtn.setContentAreaFilled(false);
    closeBtn.setFocusPainted(false);
    closeBtn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            closeBtnActionPerformed(evt);
        }
    });
    getContentPane().add(closeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, 40));

    jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
    jLabel4.setText("Device Name:");
    getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, 20));


    /*
    jMenu2.setText("Help");
    jMenuItem1.setText("User Guide");
    jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem1ActionPerformed(evt);
        }
    });
    jMenu2.add(jMenuItem1);
    jMenuBar1.add(jMenu2);
    setJMenuBar(jMenuBar1);
    */
    setPreferredSize(new Dimension(800, 550));
    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed

        dispose();
        home.resetAdminMode();
    }//GEN-LAST:event_closeBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        
        /* All of the data that is being displayed and exported is found in the completedjobs table and retrieved here
            depending on what filter is triggered
        */
        //System.out.println(searchFilter.getSelectedItem().toString());
        //System.out.println(searchKey.getText().trim().length() > 0);
        if(searchKey.getText().trim().length() > 0){
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }
            
            model = (DefaultTableModel) reportsTable.getModel();
            System.out.println(searchFilter.getSelectedItem().toString());
            for (ArrayList<Object> retval1 : UtilController.updateReportTableData(searchFilter.getSelectedItem().toString(), searchKey.getText().trim(), selectedPrinter)){ 
                model.addRow(retval1.toArray());
            }

        }

    }//GEN-LAST:event_searchBtnActionPerformed

    private void exportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportBtnActionPerformed
        
        //Exports the grid plus headers to an excel file
        controller.exportReportToFile(model, headers, (String)jComboBox1.getSelectedItem(), 'b');
        
    }//GEN-LAST:event_exportBtnActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        UtilController.openAdminHelpPage();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void searchFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchFilterActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        
        //System.out.println(evt.getItem().toString());
        selectedPrinter = evt.getItem().toString();
        //System.out.println(report);
        
        headers = UtilController.getReportColumnHeaders(selectedPrinter);
        while (model.getRowCount() > 0) {
                model.removeRow(0);
            }
            reportsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            headers));
            model = (DefaultTableModel) reportsTable.getModel();
            searchFilter.setModel(new javax.swing.DefaultComboBoxModel(headers));
            for (ArrayList<Object> retval1 : UtilController.updateReportTableData(selectedPrinter)){ 
                model.addRow(retval1.toArray());
            }
        
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void initNavBar()
    {

    	jMenuBar1.setPreferredSize(new Dimension(275, 30));
        setJMenuBar(jMenuBar1);
        
        navBtn_jobsMgr = new JButton("Jobs Manager");
        navBtn_jobsMgr.setIcon(new ImageIcon(JobsView.class.getResource("/ObjectLabEnterpriseSoftware/images/view_file_icon.png")));
        navBtn_jobsMgr.setPreferredSize(new Dimension(100,24));
        
        jMenuBar1.add(Box.createRigidArea(new Dimension(150,0)));
        jMenuBar1.add(navBtn_jobsMgr);
        
        navBtn_build = new JButton("Enter Build");
        navBtn_build.setIcon(new ImageIcon(JobsView.class.getResource("/ObjectLabEnterpriseSoftware/images/hammer_icon.png")));
        
        navBtn_build.setPreferredSize(new Dimension(100,24));
        jMenuBar1.add(navBtn_build);
        
        navBtn_reports = new JButton("Reports");
        navBtn_reports.setIcon(new ImageIcon(JobsView.class.getResource("/ObjectLabEnterpriseSoftware/images/reports_icon.png")));
        navBtn_reports.setPreferredSize(new Dimension(100,24));
        jMenuBar1.add(navBtn_reports);
        
        navBtn_settings = new JButton("Settings");
        navBtn_settings.setIcon(new ImageIcon(JobsView.class.getResource("/ObjectLabEnterpriseSoftware/images/cog_icon.png")));
        navBtn_settings.setPreferredSize(new Dimension(100,24));
        jMenuBar1.add(navBtn_settings);

        
        navBtn_jobsMgr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navBtn_jobsMgrActionPerformed(evt);
            }
        });
        
        navBtn_build.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navBtn_buildActionPerformed(evt);
            }
        });
        
        navBtn_reports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navBtn_reportsActionPerformed(evt);
            }
        });
        
        navBtn_settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	navBtn_settingsActionPerformed(evt);
            }
        }); 

    }
    
    
    private void navBtn_jobsMgrActionPerformed(java.awt.event.ActionEvent evt)
    {
    	jobsView = new newJobsMgr();
        jobsView.setVisible(true);
    	dispose();
    	
    }
    
    private void navBtn_buildActionPerformed(java.awt.event.ActionEvent evt)
    {
    	buildView = new BuildView();
        buildView.startMakeBuildProcess();
    	dispose();
    	
    }
    
    private void navBtn_reportsActionPerformed(java.awt.event.ActionEvent evt)
    {
    	reportsView = new ReportsView();
        reportsView.ReportsPage();
    	dispose();
    	
    }
    
    private void navBtn_settingsActionPerformed(java.awt.event.ActionEvent evt)
    {
    	adminSettingsView = new newSettingsMenu();
    	adminSettingsView.setVisible(true);
    	dispose();
    	
    }
    
    
    /////// Nav Bar ~Alex /////

    private void ExportMasterReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportMasterReportButtonActionPerformed
        
        UtilController.exportReportsForPrinters();
        
    }//GEN-LAST:event_ExportMasterReportButtonActionPerformed
	/////// Nav Bar ~Alex /////
	// --nav bar vars ~Alex
	private JButton navBtn_jobsMgr;
	private JButton navBtn_build;
	private JButton navBtn_reports;
	private JButton navBtn_settings;


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExportMasterReportButton;
    private static javax.swing.JButton closeBtn;
    private javax.swing.JButton exportBtn;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable reportsTable;
    private javax.swing.JButton searchBtn;
    private javax.swing.JComboBox searchFilter;
    private javax.swing.JTextField searchKey;
    
    // End of variables declaration//GEN-END:variables
}
