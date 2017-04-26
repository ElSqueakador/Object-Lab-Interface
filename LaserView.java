/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectLabEnterpriseSoftware;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author IEUser
 */
public class LaserView extends javax.swing.JFrame {
    private static final String NAME_OF_PAGE = "Reports";
    private static final MainView home = new MainView();
	
    private UtilController controller;

	// --nav bar views ~Alex
	private BuildView buildView;
	private newJobsMgr jobsMgr;
	private ReportsView reportsView;
        private AddAdminView addAdminView;
        private LaserView laserView;
        private BalanceView balanceView;
	private newSettingsMenu adminSettingsView;
        private String idString;
        private LaserTimeView laserTimeView;
        
        private JButton navBtn_jobsMgr;
	private JButton navBtn_build;
	private JButton navBtn_reports;
        private JButton navBtn_laser;
        private JButton navBtn_admin;
        private JButton navBtn_balance;
	private JButton navBtn_settings;
        private JButton navBtn_logout;
        
        private JButton submit;
        private JComboBox material;
        private JTextField thickness;
        private JTextField studentID;
        
        public LaserView(String adminID){
                idString = adminID;
		setIconImage(Toolkit.getDefaultToolkit().getImage(newJobsMgr.class.getResource("/ObjectLabEnterpriseSoftware/images/icon.ico")));
		setTitle("Administration Panel");
		setPreferredSize(new Dimension(995,660));
		setAlwaysOnTop(false);
		getContentPane().setBackground(Color.WHITE);
		initWindow();
		setLocationRelativeTo(null);
                setResizable(false);
                setVisible(true);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
        
        public LaserView(){
                idString = "";
		setIconImage(Toolkit.getDefaultToolkit().getImage(newJobsMgr.class.getResource("/ObjectLabEnterpriseSoftware/images/icon.ico")));
		setTitle("Administration Panel");
		setPreferredSize(new Dimension(995,660));
		setAlwaysOnTop(true);
		getContentPane().setBackground(Color.WHITE);
		initWindow();
		setLocationRelativeTo(null);
                setResizable(true);
                setVisible(true);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
        
        private void initWindow(){
                submit = new JButton();
                submit.setText("Submit");
                submit.setBounds(343, 459, 100, 30);
                submit.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                getContentPane().add(submit);
                submit.setVisible(true);
                submit.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                         submitMouseClicked(evt);
                    }
                });
                
                material = new JComboBox();
                material.setBounds(343, 359, 100, 30);
                material.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Plastic", "Metal", "Wood", "Other" }));
                material.addComponentListener(new java.awt.event.ComponentAdapter() {
                    public void componentHidden(java.awt.event.ComponentEvent evt) {
                         materialComponentHidden(evt);
                    }
                });
                material.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                getContentPane().add(material);
                material.setVisible(true);
                
                JLabel matLabel = new JLabel("Enter Material");
                matLabel.setBounds(143, 359, 100, 30);
                matLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                matLabel.setVisible(true);
                getContentPane().add(matLabel);            
                
                thickness = new JTextField();
                thickness.setBounds(343, 259, 100, 30);
                thickness.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                getContentPane().add(thickness);
                thickness.setVisible(true);
                
                JLabel thickLabel = new JLabel("Enter Thickness");
                thickLabel.setBounds(143, 259, 100, 30);
                thickLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                thickLabel.setVisible(true);
                getContentPane().add(thickLabel);
                  
                studentID = new JTextField();
                studentID.setBounds(343, 159, 100, 30);
                studentID.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                getContentPane().add(studentID);
                studentID.setVisible(true);
                
                JLabel idLabel = new JLabel("Enter Student ID");
                idLabel.setBounds(143, 159, 100, 30);
                idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                idLabel.setVisible(true);
                getContentPane().add(idLabel);
                
                
            	JMenuBar jMenuBar1 = new JMenuBar();
		setJMenuBar(jMenuBar1);

		jMenuBar1.setPreferredSize(new Dimension(995, 40));
		setJMenuBar(jMenuBar1);

		navBtn_jobsMgr = new JButton("Jobs Manager");
		navBtn_jobsMgr.setIcon(new ImageIcon(newJobsMgr.class.getResource("/ObjectLabEnterpriseSoftware/images/view_file_icon.png")));
		navBtn_jobsMgr.setPreferredSize(new Dimension(166,40));


		jMenuBar1.add(Box.createRigidArea(new Dimension(0, 0)));
		jMenuBar1.add(navBtn_jobsMgr);

		navBtn_build = new JButton("Enter Build");
		navBtn_build.setIcon(new ImageIcon(newJobsMgr.class.getResource("/ObjectLabEnterpriseSoftware/images/hammer_icon.png")));

		navBtn_build.setPreferredSize(new Dimension(166,40));

		jMenuBar1.add(navBtn_build);

		navBtn_reports = new JButton("Reports");
		navBtn_reports.setIcon(new ImageIcon(newJobsMgr.class.getResource("/ObjectLabEnterpriseSoftware/images/reports_icon.png")));
		navBtn_reports.setPreferredSize(new Dimension(166,40));

		jMenuBar1.add(navBtn_reports);
                
 		navBtn_laser = new JButton("Laser Cutter");
		navBtn_laser.setIcon(new ImageIcon(newJobsMgr.class.getResource("/ObjectLabEnterpriseSoftware/images/scissors-icon-31-2.png")));
		navBtn_laser.setPreferredSize(new Dimension(166,40));

		jMenuBar1.add(navBtn_laser);                
                                
                
                navBtn_balance = new JButton("Balance");
		navBtn_balance.setIcon(new ImageIcon(newJobsMgr.class.getResource("/ObjectLabEnterpriseSoftware/images/stats_icon.png")));
		navBtn_balance.setPreferredSize(new Dimension(166,40));

		jMenuBar1.add(navBtn_balance);
		navBtn_settings = new JButton("Settings");
		navBtn_settings.setIcon(new ImageIcon(newJobsMgr.class.getResource("/ObjectLabEnterpriseSoftware/images/cog_icon.png")));
		navBtn_settings.setPreferredSize(new Dimension(166,40));

		jMenuBar1.add(navBtn_settings);

                getContentPane().setLayout(null);

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

                navBtn_balance.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                        navBtn_balanceActionPerformed(evt);
                        }
                });

                navBtn_settings.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                navBtn_settingsActionPerformed(evt);
                        }
                }); 
                        
                navBtn_laser.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				navBtn_laserActionPerformed(evt);
			}
		});

                
                pack();
        }
        
        	private void navBtn_jobsMgrActionPerformed(java.awt.event.ActionEvent evt)
	{
		jobsMgr = new newJobsMgr(idString);
		jobsMgr.setVisible(true);
		dispose();

	}

	private void navBtn_buildActionPerformed(java.awt.event.ActionEvent evt)
	{
		buildView = new BuildView(idString);
		buildView.startMakeBuildProcess();
		dispose();

	}

	private void navBtn_reportsActionPerformed(java.awt.event.ActionEvent evt)
	{
		reportsView = new ReportsView(idString);
		reportsView.ReportsPage();
		dispose();

	}
        
        private void navBtn_laserActionPerformed(java.awt.event.ActionEvent evt)
	{
                laserView = new LaserView(idString);
                laserView.setVisible(true);
                dispose();
	}
        
        private void navBtn_adminActionPerformed(java.awt.event.ActionEvent evt)
	{
                addAdminView = new AddAdminView();
                addAdminView.setVisible(true);
                dispose();
	}
        
        private void navBtn_balanceActionPerformed(java.awt.event.ActionEvent evt)
	{
		balanceView= new BalanceView();
		balanceView.setVisible(true);
		dispose();

	}

	private void navBtn_settingsActionPerformed(java.awt.event.ActionEvent evt)
	{
		adminSettingsView = new newSettingsMenu(idString);
		adminSettingsView.setVisible(true);
		dispose();

	}

	private void navBtn_logoutActionPerformed(java.awt.event.ActionEvent evt)
	{
		MainView mv = new MainView();
		mv.setVisible(true);
		dispose();
	}
        
        private void submitMouseClicked(MouseEvent evt) {
            try{
                double thick = Double.parseDouble(thickness.getText());
                String mat = (String) material.getSelectedItem();
                String id = studentID.getText();
                laserTimeView = new LaserTimeView(id, mat, thick, 0,0,0);
                laserTimeView.setVisible(true);
                thickness.setText("");
                studentID.setText("");
            }
            catch(NumberFormatException E){
                JOptionPane.showMessageDialog(null, "Enter a valid number for thickness", "Invalid Thickness", JOptionPane.ERROR_MESSAGE);
            }
                
        }
        private void materialComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_materialComponentHidden
        // TODO add your handling code here:
        }//GEN-LAST:event_materialComponentHidden
}
