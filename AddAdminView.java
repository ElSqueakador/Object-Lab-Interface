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
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author IEUser
 */
public class AddAdminView extends javax.swing.JFrame {
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
        private SQLMethods dbconn;
        
        private JButton navBtn_jobsMgr;
	private JButton navBtn_build;
	private JButton navBtn_reports;
        private JButton navBtn_laser;
        private JButton navBtn_admin;
        private JButton navBtn_balance;
	private JButton navBtn_settings;
        private JButton navBtn_logout;
        
        private JButton submit;
        private JTextField newAdminID;
        
        public AddAdminView(String adminID){
                idString = adminID;
		setIconImage(Toolkit.getDefaultToolkit().getImage(newJobsMgr.class.getResource("/ObjectLabEnterpriseSoftware/images/icon.ico")));
		setTitle("Administration Panel");
		setPreferredSize(new Dimension(995,660));
		setAlwaysOnTop(true);
		getContentPane().setBackground(Color.WHITE);
		initWindow();
		setLocationRelativeTo(null);
                setResizable(false);
                setVisible(true);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
        
        public AddAdminView(){
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
                newAdminID = new JTextField();
                newAdminID.setBounds(343, 259, 100, 30);
                getContentPane().add(newAdminID);
                newAdminID.setVisible(true);
                newAdminID.setFont(new Font("Segoe UI", Font.PLAIN, 12));                
                
                JLabel adminLabel = new JLabel("New Admin's ID");
                adminLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                adminLabel.setBounds(343, 159, 100, 30);
                getContentPane().add(adminLabel);
                adminLabel.setVisible(true);
                
            	JMenuBar jMenuBar1 = new JMenuBar();
		//setJMenuBar(jMenuBar1);

		jMenuBar1.setPreferredSize(new Dimension(995, 40));
		setJMenuBar(jMenuBar1);
                
                JLabel titleLabel = new JLabel("Add Admin");
		titleLabel.setBounds(420, 11, 159, 41);
		titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		getContentPane().add(titleLabel);

		navBtn_jobsMgr = new JButton("Jobs Manager");
		navBtn_jobsMgr.setIcon(new ImageIcon(newJobsMgr.class.getResource("/ObjectLabEnterpriseSoftware/images/view_file_icon.png")));
		navBtn_jobsMgr.setPreferredSize(new Dimension(141,40));


		//jMenuBar1.add(Box.createRigidArea(new Dimension(0, 0)));
		jMenuBar1.add(navBtn_jobsMgr);

		navBtn_build = new JButton("Enter Build");
		navBtn_build.setIcon(new ImageIcon(newJobsMgr.class.getResource("/ObjectLabEnterpriseSoftware/images/hammer_icon.png")));

		navBtn_build.setPreferredSize(new Dimension(141,40));

		jMenuBar1.add(navBtn_build);

		navBtn_reports = new JButton("Reports");
		navBtn_reports.setIcon(new ImageIcon(newJobsMgr.class.getResource("/ObjectLabEnterpriseSoftware/images/reports_icon.png")));
		navBtn_reports.setPreferredSize(new Dimension(141,40));

		jMenuBar1.add(navBtn_reports);
                
 		navBtn_laser = new JButton("Laser Cutter");
		navBtn_laser.setIcon(new ImageIcon(newJobsMgr.class.getResource("/ObjectLabEnterpriseSoftware/images/reports_icon.png")));
		navBtn_laser.setPreferredSize(new Dimension((141),40));

		jMenuBar1.add(navBtn_laser);                
                
//		navBtn_admin = new JButton("Add Admin");
//		navBtn_admin.setIcon(new ImageIcon(newJobsMgr.class.getResource("/ObjectLabEnterpriseSoftware/images/reports_icon.png")));
//		navBtn_admin.setPreferredSize(new Dimension(141,40));
//
//		jMenuBar1.add(navBtn_admin);                
                
                navBtn_balance = new JButton("Balance");
		navBtn_balance.setIcon(new ImageIcon(newJobsMgr.class.getResource("/ObjectLabEnterpriseSoftware/images/stats_icon.png")));
		navBtn_balance.setPreferredSize(new Dimension(141,40));

		jMenuBar1.add(navBtn_balance);
		navBtn_settings = new JButton("Settings");
		navBtn_settings.setIcon(new ImageIcon(newJobsMgr.class.getResource("/ObjectLabEnterpriseSoftware/images/cog_icon.png")));
		navBtn_settings.setPreferredSize(new Dimension(141,40));

		jMenuBar1.add(navBtn_settings);
                navBtn_logout = new JButton("Logout");
                navBtn_logout.setIcon(new ImageIcon(newJobsMgr.class.getResource("/ObjectLabEnterpriseSoftware/images/back_arrow_button.png")));
                navBtn_logout.setPreferredSize(new Dimension(141, 40));
                
                jMenuBar1.add(navBtn_logout);
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
                
                navBtn_laser.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				navBtn_laserActionPerformed(evt);
			}
		});
                
                navBtn_admin.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				navBtn_adminActionPerformed(evt);
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
                
                navBtn_logout.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                navBtn_logoutActionPerformed(evt);
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
                laserView = new LaserView();
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
                dbconn = new SQLMethods();
                String newID = newAdminID.getText();
                dbconn.addAdmin(newID);
                newAdminID.setText("");
        }
    
}
