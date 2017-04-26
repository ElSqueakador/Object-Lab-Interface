package ObjectLabEnterpriseSoftware;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import ObjectLabEnterpriseSoftware.AdminSettingsView;
import ObjectLabEnterpriseSoftware.BuildView;
import ObjectLabEnterpriseSoftware.ReportsView;
import ObjectLabEnterpriseSoftware.SQLMethods;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.derby.tools.sysinfo;

import javax.swing.JPanel;
import java.awt.Button;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Toolkit;
import java.util.concurrent.TimeUnit;
import java.awt.Point;

/*
 *  Jobs Manager V2
 *  @Author=M. Alex Boyd
 * 	@Date=4/22/16
 * 
 * 	To-Dos
 * 	- Add check to make sure jobs are not already approved before approving
 * 
 */

public class newJobsMgr extends JFrame {
	// --nav bar views ~Alex
	private BuildView buildView;
	private newJobsMgr jobsMgr;
	private ReportsView reportsView;	
        private AddAdminView addAdminView;
        private LaserView laserView;
        private BalanceView balanceView;
	private newSettingsMenu adminSettingsView;
        private String idString;
	//
        
        //private CommentView commentview;
	public newJobsMgr(String adminID) {
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
	}
        public newJobsMgr(){
            idString = "";
		setIconImage(Toolkit.getDefaultToolkit().getImage(newJobsMgr.class.getResource("/ObjectLabEnterpriseSoftware/images/icon.ico")));
		setTitle("Administration Panel");
		setPreferredSize(new Dimension(995,660));
		setAlwaysOnTop(false);
		getContentPane().setBackground(Color.WHITE);
		initWindow();
		setLocationRelativeTo(null);
                setResizable(false);
        }
	
	
	private void initWindow()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
                
		jobStatusCombo = new JComboBox();
		//****
		jobStatusCombo.setBounds(275, 80, 150, 20);//163 //87 //80 //77
		// *****
		//jobStatusCombo.addItem("All Jobs");
		//****
		jobStatusCombo.addItem(" ");
		jobStatusCombo.addItem("Pending");
		jobStatusCombo.addItem("Rejected");
		//
		
		getContentPane().add(jobStatusCombo);

		JLabel lblJobStatus = new JLabel("Job Status:");
		//****
		lblJobStatus.setBounds(150, 80, 78, 17);//86 //20 //10
		lblJobStatus.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		//
		lblJobStatus.setLabelFor(jobStatusCombo);
		getContentPane().add(lblJobStatus);
		
		//****
		JLabel findclass = new JLabel("Class:");
		findclass.setBounds(414, 64, 60, 14);//283 //370
		findclass.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		findclass.setLabelFor(jobStatusCombo);//devicecombo
		//getContentPane().add(findclass); -sorry
		//
		
		//****
		classCombo = new JComboBox();
		classCombo.setBounds(450, 63, 67, 20);//380//67
		classCombo.addItem(" ");
		classCombo.addItem("ART 101");
		classCombo.addItem("COSC 412");
		classCombo.addItem("THEA 102");
		classCombo.addItem("CIS 237");
		//getContentPane().add(classCombo); -sorry
		//
		
		
		//****
		JLabel findsection = new JLabel("Sect:");
		findsection.setBounds(523, 64, 30, 14);//283
		findsection.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		findsection.setLabelFor(jobStatusCombo);//devicecombo
		//getContentPane().add(findsection); -sorry
		//
		
		//****
		sectionCombo = new JComboBox();
		sectionCombo.setBounds(553, 63, 55, 20);
		sectionCombo.addItem(" ");
		sectionCombo.addItem("-");
		//getContentPane().add(sectionCombo); -sorry
		//
		

		final JComboBox deviceCombo = new JComboBox();
		//****
		deviceCombo.setBounds(700, 80, 150, 20);//342 //266 //259 //251
		//deviceCombo.addItem(" "); added a printer called " " so we no longer need ths.
		/// Adds tracked devices to comboBox dropdown window
		SQLMethods dbconn = new SQLMethods();
		ResultSet queryResult = dbconn.getTrackedDevices();
		try {
			while(queryResult.next())
			{
				deviceCombo.addItem(queryResult.getString(1));
			}
		} catch(Exception e)
		{
			System.out.println("Error: " + e);
		}
		//  

		deviceCombo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				JComboBox cb = (JComboBox) e.getSource();
				String selectedDevice = (String) cb.getSelectedItem();
				System.out.println(selectedDevice);
				updateJobWindow(selectedDevice);

				if(jobStatusCombo.getSelectedItem().toString().equalsIgnoreCase("rejected")) // hide approve/reject/review buttons and panel
				{
					toggleButtons(false);
				}
				else if(jobStatusCombo.getSelectedItem().toString().equalsIgnoreCase("pending")) // hide approve/reject/review buttons and panel
				{
					toggleButtons(true);
				}
			
			}

		});

		jobStatusCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(deviceCombo.getSelectedItem().toString() != "")
				{
					String selectedDevice = (String) deviceCombo.getSelectedItem();
					System.out.println(selectedDevice);
					updateJobWindow(selectedDevice);
					if(jobStatusCombo.getSelectedItem().toString().equalsIgnoreCase("approved")) // hide approve/reject/review buttons and panel
					{
						toggleButtons(false);
					}
					else if(jobStatusCombo.getSelectedItem().toString().equalsIgnoreCase("pending")) // hide approve/reject/review buttons and panel
					{
						toggleButtons(true);
					}
				}
			}
		});

		getContentPane().add(deviceCombo);

		JLabel deviceLabel = new JLabel("Device:");
		//****
		deviceLabel.setBounds(600, 80, 78, 20);//283 //207
		deviceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		deviceLabel.setLabelFor(deviceCombo);
		getContentPane().add(deviceLabel);

		JScrollPane jobListingsPane = new JScrollPane();
		jobListingsPane.setBounds(10, 120, 970, 150);
		getContentPane().add(jobListingsPane);
		

		jobsTable = new JTable();
               
		jobsModel = new DefaultTableModel() {
			Class[] columnTypes = new Class[] {
					Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			//****
			public boolean isCellEditable(int row, int column) {
				if (column == 0)
                                    return true;
                                return false;
                        }
                       
                       
		};
                
		//jobsModel.setColumnCount(6);
		//jobsModel.setColumnIdentifiers(new String[] {
		//		"Selected?", "File Name", "First Name", "Last Name", "Date", "Class", "Section"
		//});

		jobsTable.setModel(jobsModel);
                
                
		jobListingsPane.setViewportView(jobsTable);
                
                jobsTable.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        for(int i=0; i<jobsTable.getRowCount(); i++){
                            if(i==jobsTable.getSelectedRow()){
                                jobsTable.setValueAt(true, jobsTable.getSelectedRow(), 0);
                            }else{
                                jobsTable.setValueAt(false, i, 0);
                    }
                    }    

                }
                }); 

                
		JLabel titleLabel = new JLabel("Jobs Manager");
		titleLabel.setBounds(420, 11, 159, 41);
		titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		getContentPane().add(titleLabel);

		/*JButton logoutButton = new JButton("Logout");
		logoutButton.setBounds(448, 500, 100, 30);
                logoutButton.setFont(new java.awt.Font("Segoe UI", 0, 16));
		logoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				logout();
			}
		});
		logoutButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		logoutButton.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(logoutButton);
                */
		approveButton = new JButton("Approve");
		approveButton.setBounds(343, 459, 100, 30);
		approveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				approveJobs();
			}
		});
		approveButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		getContentPane().add(approveButton);
		//****
		approveButton.setVisible(false);


		rejectButton = new JButton("Reject");
		rejectButton.setBounds(448, 459, 100, 30);
		rejectButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rejectJobs();
			}
		});
		rejectButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		getContentPane().add(rejectButton);
		//****
		rejectButton.setVisible(false);

		reviewButton = new JButton("Review");
		reviewButton.setBounds(553, 459, 100, 30);
		reviewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				openInDefaultProgram(evt);
			}
		});
		reviewButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		getContentPane().add(reviewButton);
		//****
		reviewButton.setVisible(false);
                

		lblFillInData = new JLabel("Please enter device statistic tracking data if approving:");
		lblFillInData.setBounds(10, 100, 970, 220);
		lblFillInData.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFillInData.setVisible(false);
		getContentPane().add(lblFillInData);

		jobStatPanel = new JPanel();
		jobStatPanel.setBounds(10, 290, 970, 150);
		jobStatPanel.setVisible(false); // stat panel doesn't show up until device is selected.
		getContentPane().add(jobStatPanel);
		jobStatPanel.setLayout(null);

		deviceNameLabel = new JLabel("filler");
		deviceNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		deviceNameLabel.setBounds(50, 10, 200, 40);
		jobStatPanel.add(deviceNameLabel);

		trackingStatLabel1 = new JLabel("New label");
		trackingStatLabel1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		trackingStatLabel1.setBounds(20, 60, 200, 20);
		jobStatPanel.add(trackingStatLabel1);

		trackingStatLabel2 = new JLabel("New label");
		trackingStatLabel2.setLabelFor(trackingStatLabel2);
		trackingStatLabel2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		trackingStatLabel2.setBounds(20, 90, 200, 20);
		jobStatPanel.add(trackingStatLabel2);

		trackingStatInput1 = new JTextField();
		trackingStatLabel1.setLabelFor(trackingStatInput1);
		trackingStatInput1.setBounds(175, 60, 200, 20);
		jobStatPanel.add(trackingStatInput1);
		trackingStatInput1.setColumns(10);

		trackingStatInput2 = new JTextField();
		trackingStatInput2.setBounds(175, 90, 200, 20);
		jobStatPanel.add(trackingStatInput2);
		trackingStatInput2.setColumns(10);


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
                

		pack();
	}

	
	protected void rejectJobs() {
		ArrayList<Integer> selectedDevices = new ArrayList<Integer>();
		for(int j = 0; j < jobsTable.getRowCount(); j++)
		{
			if(jobsTable.getValueAt(j, 0).toString().equalsIgnoreCase("true")) // get all the selected rows
			{
				selectedDevices.add(j); // adds row # for all selected rows.
			}
		}
        if (selectedDevices.size() >= 0)
        {
        	String[] choices = {"Insufficient material balance", "Invalid file", "Other - See instructor"};
        	String description = (String) JOptionPane.showInputDialog(null, "Choose reason:",
        	        "Select Reason for Rejecting", JOptionPane.QUESTION_MESSAGE, null, choices, choices[2]); 
        	
			String file = (String) jobsModel.getValueAt(selectedDevices.get(0), 1);

			boolean success = UtilController.rejectStudentSubmission(file, description);

			if (success) // always rejects, commenting out email utlity for now   ~Alex
			{
				JOptionPane op = new JOptionPane("Submission rejected.", JOptionPane.INFORMATION_MESSAGE);
				JDialog dialog = op.createDialog("Job Manager: Submission Rejected");
				dialog.setAlwaysOnTop(true);
				dialog.setModal(true);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);

				jobsModel.fireTableDataChanged();
				jobStatusCombo.setSelectedIndex(1);
				jobsTable.repaint();

			} else {
				JOptionPane.showMessageDialog(new JFrame(), "Something went wrong! You shouldn't see this, ever!");
			}
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "Please select a submission file to reject!");
		}
	}

	private void toggleButtons(boolean status) // toggles the approve,reject,review buttons along with stat panel so you can't approve the already approved
	{
		// *****
		//jobStatPanel.setVisible(status);
		//lblFillInData.setVisible(status);
		approveButton.setVisible(status);
		rejectButton.setVisible(status);
		//reviewButton.setVisible(status);
	}
	
	
	
	protected void approveJobs() { // handles the approveButton

		ArrayList<Integer> selectedDevices = new ArrayList<Integer>();
		for (int j = 0; j < jobsTable.getRowCount(); j++) {
			if (jobsTable.getValueAt(j, 0).toString().equalsIgnoreCase("true")) // get all the selected rows
			{
				selectedDevices.add(j); // adds row # for all selected rows.
			}
		}
		int rowDataLocation;
		if (selectedDevices.get(0) != 0) {
			rowDataLocation = getSelectedRowNum(jobsModel, selectedDevices.get(0), 0);
		} else {
			rowDataLocation = 0;
		}

		SQLMethods dbconn = new SQLMethods();
		String first_name = (String) jobsModel.getValueAt(rowDataLocation, 2);
		String last_name = (String) jobsModel.getValueAt(rowDataLocation, 3);
		String id = dbconn.getStudentId(first_name, last_name);
		String printer = dbconn.getFilePrinter((String) jobsModel.getValueAt(rowDataLocation, 1), id);
		dbconn.closeDBConnection();

		/*
		 * Hand off the data in the selected row found in our tablemodel to this
		 * method so we can approve the correct file to be printed... -Nick
		 */
		if (printer.equalsIgnoreCase("Objet Desktop 30")) {
			double stat1 = Double.parseDouble(trackingStatInput1.getText());
			double stat2 = Double.parseDouble(trackingStatInput2.getText());

			// This variable tracks whether the student's submission would push them into a negative balance on their account.
			boolean wouldBeNegative;
			wouldBeNegative = UtilController.checkNegativeBalance(printer, id, stat1, stat2);
			
			if(wouldBeNegative == true)
			{
				String message = "Completing this job would reduce the student's balance of a material to below 0.\n\nWould you like to proceed?";
				int reply = JOptionPane.showConfirmDialog(null, message, "Negative Balance Warning", JOptionPane.YES_NO_OPTION);
		        if (reply == JOptionPane.YES_OPTION) {
		        	// YES - Proceed and reduce their balance
		        	// Updates transaction history
					UtilController.changeStudentBalance(printer, id, stat1, stat2);
					UtilController.changeStudentBalanceHistory(printer, id, stat1, stat2);
		        }
		        else {
		        	// NO - Do not proceed
		        	// Exit method
		        	return;
		        }
			}
			// No negative balance issues -> proceed as usual:
			else
			{
				// Updates transaction history
				UtilController.changeStudentBalance(printer, id, stat1, stat2);
				UtilController.changeStudentBalanceHistory(printer, id, stat1, stat2);
			}
		}
		else if (printer.equalsIgnoreCase("Z Printer 250")) {
			double stat1 = Double.parseDouble(trackingStatInput1.getText());
			double stat2 = 0;

			// This variable tracks whether the student's submission would push them into a negative balance on their account.
			boolean wouldBeNegative;
			wouldBeNegative = UtilController.checkNegativeBalance(printer, id, stat1, stat2);
			
			if(wouldBeNegative == true)
			{
				String message = "Completing this job would reduce the student's balance of a material to below 0.\n\nWould you like to proceed?";
				int reply = JOptionPane.showConfirmDialog(null, message, "Negative Balance Warning", JOptionPane.YES_NO_OPTION);
		        if (reply == JOptionPane.YES_OPTION) {
		        	// YES - Proceed and reduce their balance
		        	// Updates transaction history
					UtilController.changeStudentBalance(printer, id, stat1, stat2);
					UtilController.changeStudentBalanceHistory(printer, id, stat1, stat2);
		        }
		        else {
		        	// NO - Do not proceed
		        	// Exit method
		        	return;
		        }
			}
			// No negative balance issues -> proceed as usual:
			else
			{
				// Updates transaction history
				UtilController.changeStudentBalance(printer, id, stat1, stat2);
				UtilController.changeStudentBalanceHistory(printer, id, stat1, stat2);
			}
		}
		UtilController.approveStudentSubmission((String) jobsModel.getValueAt(rowDataLocation, 1),
				trackingStatInput1.getText(), trackingStatInput2.getText());
		JOptionPane op = new JOptionPane("Submission successfully approved.", JOptionPane.INFORMATION_MESSAGE);
		trackingStatInput1.setText("");
		trackingStatInput2.setText("");
		jobsModel.fireTableDataChanged();
		jobStatusCombo.setSelectedIndex(0); // *****
		jobsTable.repaint();

		jobsModel.fireTableDataChanged();
		jobsTable.repaint();
	}

	public static int getSelectedRowNum(DefaultTableModel dm, int selectedRow, int column)
	{
		if (selectedRow < 0)
		{
			return -1;
		}

		for (int i = 0; i < dm.getRowCount(); i++)
		{
			if (dm.getValueAt(i, column).equals(dm.getValueAt(selectedRow, column)))
			{
				return i;
			}
		}

		return -1;
	}


	protected void openInDefaultProgram(MouseEvent evt) {
		ArrayList<Integer> selectedDevices = new ArrayList<Integer>();
		for(int j = 0; j < jobsTable.getRowCount(); j++)
		{
			if(jobsTable.getValueAt(j, 0).toString().equalsIgnoreCase("true")) // get all the selected rows
			{
				selectedDevices.add(j); // adds row # for all selected rows.
			}
		}

	        if (selectedDevices.size() > -1)
	        {
	            int rowDataLocation = getSelectedRowNum(jobsModel, selectedDevices.get(0), 0);

	
	            File fileLocation = UtilController.getFilePath(
	                    (String) jobsModel.getValueAt(rowDataLocation, 2),
	                    (String) jobsModel.getValueAt(rowDataLocation, 3),
	                    (String) jobsModel.getValueAt(rowDataLocation, 1),
	                    (String) jobsModel.getValueAt(rowDataLocation, 4)
	            );//jobsModel.addRow(new Object[] {(Boolean) false, fileName, fName, lName, date, className, classSection });
	            
	            UtilController.checkFileExists(fileLocation.getPath());
	            try
	            {
	                Desktop.getDesktop().open(fileLocation);
	            } catch (IOException ex)
	            {
	                Logger.getLogger(newJobsMgr.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        } else
	        {
	            JOptionPane.showMessageDialog(new JFrame(), "Please select a file to review!");
	        }
	}

	protected void updateJobWindow(final String selectedDevice) { // used to display all jobs according to device

		showDeviceStats(selectedDevice); // used to display stat tracking relating to device.

		ArrayList rows = new ArrayList();
		jobsModel.setRowCount(0);
		jobsTable.repaint(); 
		/// Adds tracked devices to comboBox dropdown window
		Thread runner = new Thread() {
			public void run()
			{
				try {
					SQLMethods dbconn = new SQLMethods();
					System.out.println("Trying to find " + selectedDevice);
					ResultSet queryResult;
					

					if(jobStatusCombo.getSelectedItem().toString().equalsIgnoreCase("Pending"))
                    {
                    	queryResult = dbconn.searchJobsStatusPrinter("pending", selectedDevice);
                    	//****
                    	reviewButton.setVisible(true);
                    }
                    else if(jobStatusCombo.getSelectedItem().toString().equalsIgnoreCase("Rejected"))
                    {
                    	queryResult = dbconn.searchJobsStatusPrinter("rejected", selectedDevice);
                    	//****
                    	approveButton.setVisible(false);
                    	rejectButton.setVisible(false);
                    }
                    else 
                    {
                        queryResult = null;
                    }
					
					
					//**** work here
					if(classCombo.getSelectedItem().toString().equalsIgnoreCase("ART 101"))
                    {
                    	queryResult = dbconn.searchJobsStatus("ART 101");
                    	//****
                    	reviewButton.setVisible(true);
                    }
                    //else if(classCombo.getSelectedItem().toString().equalsIgnoreCase("COSC 412"))
                    //{
                    	//queryResult = dbconn.searchJobsStatusPrinter("rejected", selectedDevice);
                    	//****
                    	//approveButton.setVisible(false);
                    	//rejectButton.setVisible(false);
                    //}
					
					//
				
					while(queryResult.next())
					{
						int jobId = queryResult.getInt(1);
						String fileName = queryResult.getString(2);
						String fName = queryResult.getString(3);
						String lName = queryResult.getString(4);
						String date = queryResult.getString(5);
						String deviceName = queryResult.getString(6);
						String className = queryResult.getString(7);
						String classSection = queryResult.getString(8);
                                                String balance1 = queryResult.getString(9);
                                                String balance2 = queryResult.getString(10);
                                                String balance3 = queryResult.getString(11);
						System.out.println("Adding row...");
                                                if (selectedDevice.equalsIgnoreCase("Z Printer 250"))
                                                {
                                                    jobsModel.setColumnCount(8);
                                                    jobsModel.setColumnIdentifiers(new String[] {
                                                    		//****
                                                    		"Selected?", "File Name", "First Name", "Last Name", "Date", "Class", "Section", "Z Corp Plaster"});
                                                    jobsModel.addRow(new Object[] {(Boolean) false, fileName, fName, lName, date, className, classSection, balance1});
                                                } else if (selectedDevice.equalsIgnoreCase("Objet Desktop 30"))
                                                {
                                                    jobsModel.setColumnCount(9);
                                                    jobsModel.setColumnIdentifiers(new String[] {
                                                    		"Selected?", "File Name", "First Name", "Last Name", "Date", "Class", "Section", "Oject Build", "Oject Support"});
                                                    jobsModel.addRow(new Object[] {(Boolean) false, fileName, fName, lName, date, className, classSection, balance2, balance3});
                                                } else
                                                {
                                                    jobsModel.setColumnCount(7);
                                                    jobsModel.setColumnIdentifiers(new String[] {
                                                    		 "Selected?", "File Name", "First Name", "Last Name", "Date", "Class", "Section"});
                                                    jobsModel.addRow(new Object[] {(Boolean) false, fileName, fName, lName, date, className, classSection});
                                                }

                                        }
					jobsTable.setModel(jobsModel);
					jobsTable.repaint();
					dbconn.closeDBConnection();

					
				} catch(Exception e)
				{
					System.out.println("Error: " + e);
				}
			}

		};
		runner.run();
	}


	private void showDeviceStats(String selectedDevice) { // used to update 

		// *****
		if(jobStatusCombo.getSelectedItem().toString().equalsIgnoreCase("Pending") &&
                (selectedDevice.equals("Objet Desktop 30") || selectedDevice.equals("Z Printer 250")))
		{
			
		jobStatPanel.setVisible(true);
		lblFillInData.setVisible(true);
		deviceNameLabel.setText(selectedDevice);


		if(selectedDevice.equals("Objet Desktop 30")) { // hard coding these quick and dirttay
			trackingStatLabel1.setText("Build material (g)");
			trackingStatLabel2.setText("Support material (g)");
                        trackingStatLabel2.setVisible(true);
                        trackingStatInput2.setVisible(true);
		}

		else if(selectedDevice.equals("Z Printer 250"))
		{
			trackingStatLabel1.setText("Volume (cubic in)");
			trackingStatLabel2.setVisible(false);
                        trackingStatInput2.setVisible(false);
			// *** *** Fix needed
		}
		else if(selectedDevice.equals(" ") || selectedDevice.equals(""))
		{
			jobStatPanel.setVisible(false);
			lblFillInData.setVisible(false);
		}
		else
		{
			jobStatPanel.setVisible(false);
			lblFillInData.setVisible(false);	
		}

		}
		else
		{
			jobStatPanel.setVisible(false);
			lblFillInData.setVisible(false);
		}

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
		balanceView= new BalanceView(idString);
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



	/////// Nav Bar ~Alex /////
	// --nav bar vars ~Alex
	private JButton navBtn_jobsMgr;
	private JButton navBtn_build;
	private JButton navBtn_reports;
        private JButton navBtn_laser;
        private JButton navBtn_admin;
        private JButton navBtn_balance;
	private JButton navBtn_settings;
        private JButton navBtn_logout;

	///
	// etc vars
	// 
	private JPanel jobStatPanel;
	private JPanel jobListingsPane;
	private JTextField trackingStatInput1;
	private JTextField trackingStatInput2;
	private JLabel trackingStatLabel1;
	private JLabel trackingStatLabel2;
	private JLabel deviceNameLabel;
	private JLabel lblFillInData;
	private JTable jobsTable;
	private DefaultTableModel jobsModel;
	private JComboBox jobStatusCombo;
	private JButton approveButton;
	private JButton rejectButton;
	private JButton reviewButton;
	private JComboBox classCombo;
	private JComboBox sectionCombo;

}
