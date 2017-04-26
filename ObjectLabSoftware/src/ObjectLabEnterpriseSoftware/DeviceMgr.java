package ObjectLabEnterpriseSoftware;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JSplitPane;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.AbstractListModel;
import java.awt.Toolkit;


/*
 * 
 * Device Manager
 * @author=M. Alex Boyd
 * 
 * TO DOS
 * 1. Add error text for empty textfields when adding device
 */
public class DeviceMgr extends JFrame {


    //Current count of labels and fields
	ArrayList<String> currentDevices = UtilController.getListOfCurrentDevices();
	ListModel removeTable;
    private int count = 0;
    private boolean trackingSelected = true;
    private String id;


	private DefaultListModel allClassListModel;
	private DefaultListModel currentClassListModel;
	private DefaultListModel removeClassListModel = new DefaultListModel();
	private DefaultListModel currentDeviceListModel = new DefaultListModel();
    newSettingsMenu settings;
	private static FileManager inst = null;
	public DeviceMgr(String userID) {
                id = userID;
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DeviceMgr.class.getResource("/ObjectLabEnterpriseSoftware/images/icon.ico")));
		setPreferredSize(new Dimension(550, 370));
		setResizable(false);
		setFont(new Font("Segoe UI", Font.PLAIN, 15));
		setTitle("Device Manager");
		getContentPane().setBackground(Color.WHITE);
		initWindow();
		updateView();

		   addWindowListener(
	                new WindowAdapter()
	                {
	                    @Override
	                    public void windowClosing(WindowEvent we)
	                    {
	                    	//settings = new newSettingsMenu(id);
	                    	//settings.setVisible(true);
	                    	dispose();
	                    }
	                 }
	                );
		   
		   for(int i = 0; i < currentDevices.size(); i++)
		   {
		   currentDeviceListModel.addElement(currentDevices.get(i));
		   }
		   
		   
	} // end of constructor
	
	private void initWindow()
	{
		

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JLabel titleLabel = new JLabel("Device Manager");
		titleLabel.setBounds(177, 11, 300, 40);
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titleLabel.setVisible(true);
		getContentPane().setLayout(null);
		getContentPane().add(titleLabel);
		
		tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBounds(10, 55, 516, 287);
		tabbedPane.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(tabbedPane);
		tabbedPane.setVisible(true);
		
		addDevicePanel = new JPanel();
		tabbedPane.addTab("Add Device", null, addDevicePanel, null);
		addDevicePanel.setBorder(null);
		addDevicePanel.setLayout(null);
		
		deviceNameLabel = new JLabel("Device Name:");
		deviceNameLabel.setBounds(0, 5, 170, 30);
		deviceNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		addDevicePanel.add(deviceNameLabel);
		

		deviceNameInput = new JTextField();
		deviceNameInput.setBounds(190, 12, 100, 20);
		deviceNameLabel.setLabelFor(deviceNameInput);
		addDevicePanel.add(deviceNameInput);
		deviceNameInput.setColumns(10);
		
		fileExtLabel = new JLabel("Accepted File Extension:");
		fileExtLabel.setBounds(0, 35, 170, 30);
		fileExtLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		addDevicePanel.add(fileExtLabel);
		
		fileExtInput = new JTextField();
		fileExtInput.setBounds(190, 42, 100, 20);
		fileExtInput.setColumns(10);
		addDevicePanel.add(fileExtInput);
		
		JTextArea txtDirections = new JTextArea();
		txtDirections.setBackground(SystemColor.menu);
		txtDirections.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtDirections.setWrapStyleWord(true);
		txtDirections.setText("Enter types of data this device requires: \n If it's a numerical value, enter field name and unit of measurement \n ex) Volume (g) \n If it's a non-numerical, just enter field name \n ex) Color");
		txtDirections.setBounds(0, 73, 401, 91);
		addDevicePanel.add(txtDirections);
		
		fieldL1Label = new JLabel("Field name 2:");
		fieldL1Label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fieldL1Label.setBounds(10, 185, 150, 20);
		fieldL1Label.setVisible(false);
		addDevicePanel.add(fieldL1Label);
		
		fieldL0 = new JTextField();
		fieldL0.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fieldL0.setColumns(10);
		fieldL1Label.setLabelFor(fieldL0);
		fieldL0.setBounds(100, 160, 100, 20);
		addDevicePanel.add(fieldL0);
		
		numberValCheck0 = new JCheckBox("Numerical value");
		numberValCheck0.setFont(new Font("Tahoma", Font.PLAIN, 14));
		numberValCheck0.setBounds(230, 160, 150, 20);
		addDevicePanel.add(numberValCheck0);
		
		addFieldButton = new JButton("Add Field");
		addFieldButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addFieldButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addFieldButtonActionPerformed(arg0);
			}
		});
		addFieldButton.setBounds(0, 260, 100, 20);
		addDevicePanel.add(addFieldButton);
		
		saveButton = new JButton("Add Device");
                saveButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		saveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				addDevice();
			}
		});
		saveButton.setBounds(295, 260, 120, 20);
		addDevicePanel.add(saveButton);
		
		JPanel removeClassPanel = new JPanel();
		tabbedPane.addTab("Remove Device", null, removeClassPanel, null);
		removeClassPanel.setLayout(null);
		
		removePrinterButton = new JButton("Remove");
                removePrinterButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		removePrinterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				  removeClassButtonActionPerformed(arg0);
			}
		});
		removePrinterButton.setBounds(160, 240, 100, 25);
		removeClassPanel.add(removePrinterButton);
		
		lblAvailableClasses = new JLabel();
		lblAvailableClasses.setText("Available Devices");
		lblAvailableClasses.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblAvailableClasses.setBounds(15, 20, 150, 20);
		removeClassPanel.add(lblAvailableClasses);
		
		spAvaiableClassesRC = new JScrollPane();
		spAvaiableClassesRC.setBounds(15, 44, 139, 189);
		removeClassPanel.add(spAvaiableClassesRC);
		
		currentClassListRC = new JList();
		currentClassListRC.setModel(currentDeviceListModel);
		spAvaiableClassesRC.setViewportView(currentClassListRC);
		
		addArrowRC = new JButton();
		addArrowRC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addArrowRCActionPerformed(evt);
			}
		});
		addArrowRC.setText("--->");
		addArrowRC.setPreferredSize(new Dimension(60, 23));
		addArrowRC.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		addArrowRC.setBounds(164, 99, 90, 23);
		removeClassPanel.add(addArrowRC);
		
		removeArrowRC = new JButton();
		removeArrowRC.setText("<---");
		removeArrowRC.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		removeArrowRC.setBounds(164, 129, 90, 23);
		removeArrowRC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				removeArrowRCActionPerformed(evt);
			}
		});
		removeClassPanel.add(removeArrowRC);
		
		JScrollPane spToBeRemovedRC = new JScrollPane();
		spToBeRemovedRC.setBounds(264, 44, 139, 190);
		removeClassPanel.add(spToBeRemovedRC);
		
		removeClassList = new JList();
		spToBeRemovedRC.setViewportView(removeClassList);
		
		JLabel lblClassesToRemove = new JLabel();
		lblClassesToRemove.setText("Devices to Remove");
		lblClassesToRemove.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblClassesToRemove.setBounds(264, 20, 150, 20);
		removeClassPanel.add(lblClassesToRemove);
		
		backButton = new JLabel("");
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//newSettingsMenu ns = new newSettingsMenu(id);
				//ns.setVisible(true);
                                //No longer want new window called
				dispose();
			}
		});
		backButton.setIcon(new ImageIcon(DeviceMgr.class.getResource("/ObjectLabEnterpriseSoftware/images/back_arrow_button.png")));
		backButton.setBounds(21, 11, 45, 37);
		getContentPane().add(backButton);
        
        fieldL0Label = new JLabel("Field name 1:");
        fieldL0Label.setFont(new Font("Tahoma", Font.PLAIN, 14));
        fieldL0Label.setBounds(10, 160, 150, 20);
        addDevicePanel.add(fieldL0Label);
        
        field1 = new JTextField();
        field1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        field1.setColumns(10);
        field1.setBounds(100, 185, 100, 20);
        field1.setVisible(false);
        addDevicePanel.add(field1);
        
        numberValCheck1 = new JCheckBox("Numerical value");
        numberValCheck1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        numberValCheck1.setBounds(230, 185, 150, 20);
        numberValCheck1.setVisible(false);
        addDevicePanel.add(numberValCheck1);
        
        fieldL2Label = new JLabel("Field name 3:");
        fieldL2Label.setFont(new Font("Tahoma", Font.PLAIN, 14));
        fieldL2Label.setBounds(10, 210, 150, 20);
        fieldL2Label.setVisible(false);
        addDevicePanel.add(fieldL2Label);
        
        field2 = new JTextField();
        field2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        field2.setColumns(10);
        field2.setBounds(100, 210, 100, 20);
        field2.setVisible(false);
        addDevicePanel.add(field2);
        
        numberValCheck2 = new JCheckBox("Numerical value");
        numberValCheck2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        numberValCheck2.setBounds(230, 210, 150, 20);
        numberValCheck2.setVisible(false);
        addDevicePanel.add(numberValCheck2);
        
        fieldL3Label = new JLabel("Field name 4:");
        fieldL3Label.setFont(new Font("Tahoma", Font.PLAIN, 14));
        fieldL3Label.setBounds(10, 235, 150, 20);
        fieldL3Label.setVisible(false);
        addDevicePanel.add(fieldL3Label);
        
        field3 = new JTextField();
        field3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        field3.setColumns(10);
        field3.setBounds(100, 235, 100, 20);
        field3.setVisible(false);
        addDevicePanel.add(field3);
        
        numberValCheck3 = new JCheckBox("Numerical value");
        numberValCheck3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        numberValCheck3.setBounds(230, 235, 150, 20);
        numberValCheck3.setVisible(false);
        addDevicePanel.add(numberValCheck3);
        
        removeFieldButton = new JButton("Remove Field");
        removeFieldButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		removeFields();
        	}
        	
        });
        removeFieldButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        removeFieldButton.setBounds(120, 260, 150, 20);
        addDevicePanel.add(removeFieldButton);
        pack();
        
        setLocationRelativeTo(null);
	}


	protected void addDevice() { // this is quick and dirty and ugly, validation should be seperated into seperate methods ~Alex
		
		String deviceName = null;
		String fileExt = null;
		ArrayList<String> fields = new ArrayList<String>();
		int[] boxes = new int[5];
		if(!deviceNameInput.getText().isEmpty())
		{
			deviceName = deviceNameInput.getText();
		}
		else
		{
			System.out.println("Device name empty! ADD ERROR TXT");
		}
		
		if(!fileExtInput.getText().isEmpty())
		{
			fileExt = fileExtInput.getText();
		}
		else
		{
			System.out.println("File extension field empty! ADD ERROR TXT");
		}
		
		
		if(count >= 0) // count keeps track of visible fields (see removeFieldButton)
		{
			if(!fieldL0.getText().isEmpty())
			{
				fields.add(fieldL0.getText());
				if(numberValCheck0.isSelected())
				{
					boxes[0] = 1;// 1 = selected numerical value
				}
			}
			else // field 0 will always be visible because remove button doesn't allow for it to be removed. (Need at least 1 field).
			{
				System.out.println("Field 1 Empty!");
			}


			if(!field1.getText().isEmpty())
			{
				fields.add(field1.getText());
				if(numberValCheck1.isSelected())
				{
					boxes[1] = 1;// 1 = selected numerical value
				}
			}
			else if( field1.isVisible() )
			{
				System.out.println("Field 2 Empty!");
			}
			
			if(!field2.getText().isEmpty())
			{
				fields.add(field2.getText());
				if(numberValCheck2.isSelected())
				{
					boxes[2] = 1;// 1 = selected numerical value
				}
			}
			else if( field2.isVisible() )
			{
				System.out.println("Field 3 Empty!");
			}			
			
			if(!field3.getText().isEmpty())
			{
				fields.add(field3.getText());
				if(numberValCheck3.isSelected())
				{
					boxes[3] = 1;// 1 = selected numerical value
				}
			}
			else if( field3.isVisible() )
			{
				System.out.println("Field 4 Empty!");
			}
			
			if(!field4.getText().isEmpty())
			{
				fields.add(field4.getText());
				if(numberValCheck4.isSelected())
				{
					boxes[4] = 1;// 1 = selected numerical value
				}
			}
			else if( field4.isVisible() )
			{
				System.out.println("Field 5 Empty!");
			}
			
		}
		
		if(!deviceName.equals(null) && !fileExt.equals(null)) 
		{
			device = new Device(deviceName,
					new ArrayList(Arrays.asList(fileExt.split(" "))), trackingSelected);

			for(int i = 0; i < fields.size(); i++)
			{
				if(boxes[i] == 1) // numerical value field
				{
					device.addField(fields.get(i), new Double("0"));
				}
				else
				{
					device.addField(fields.get(i), "");
				}
			}

			if (currentDevices.contains(device.getDeviceName()))
			{
				JOptionPane.showMessageDialog(this, "Could not save! Device '" + device.getDeviceName() + "' already exists!");
			} else if (UtilController.addDevice(device) == true)
			{
				JOptionPane.showMessageDialog(this, "Device '" + device.getDeviceName() + "' was saved and added to the printer list!");
				repaint();
			} else
			{
				JOptionPane.showMessageDialog(this, "There was an error while saving the printer.");
			}
		}


	} // end of addDevice()

		


	private void updateView()
	{
            if(allClassListModel != null)
                allClassListModel.clear();
            
            if(currentClassListModel != null)
                currentClassListModel.clear();
            
            allClassListModel = new javax.swing.DefaultListModel(); /* false */
            currentClassListModel = UtilController.returnCurrentClasses();
            removeClassList.setModel(removeClassListModel);
            currentClassListRC.setModel(currentDeviceListModel);
            
	}
    
    private void addArrowRCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addArrowActionPerformed
		if (removeClassListModel.contains(currentClassListRC.getSelectedValue()))
		{
			JOptionPane.showMessageDialog(null, "Class already in current class list",
					"Add Error", JOptionPane.ERROR_MESSAGE);
		} 
		else if(currentClassListRC.getSelectedValue() == null)
		{
			JOptionPane.showMessageDialog(null, "No class selected!",
					"Add Error", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			removeClassListModel.addElement(currentClassListRC.getSelectedValue());
			currentDeviceListModel.removeElement(currentClassListRC.getSelectedValue());
			
			removeClassList.setModel(removeClassListModel);
			
		}
    }//GEN-LAST:event_addArrowActionPerformed

    
    
    
    private void removeArrowRCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeArrowActionPerformed
		int i;
		for (i = 0; i < removeClassListModel.getSize(); i++)
		{
			if (removeClassListModel.elementAt(i).equals(removeClassList.getSelectedValue()))
			{
				currentDeviceListModel.addElement(removeClassListModel.elementAt(i));
				removeClassListModel.removeElementAt(i);
			}
		}
    }//GEN-LAST:event_removeArrowActionPerformed

    
    
    private void removeClassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeClassButtonActionPerformed

            if(JOptionPane.showConfirmDialog(null, "Continue? If you delete this device you will no longer be able "
                    + "to select this device when building jobs.\nFiles associated with this device will not be deleted "
                    + "but they will no longer be associated with this device. \n Click 'YES' to CONFIRM DELETION this is permanent!","Warning",JOptionPane.YES_OPTION)==JOptionPane.YES_OPTION){
            	for (int i = 0; i < removeClassListModel.getSize(); i++)
        		{
        				String selected = (String) removeClassListModel.elementAt(i);
        				final String id = selected;
        				Thread runner = new Thread() {

        					public void run()
        					{
        						UtilController.removeDevice(id);
        					}
        				};
        				
        				runner.run();
        				
        				
        		}     
            	removeClassListModel.clear();
            	updateView();
            }
        else
            JOptionPane.showMessageDialog(null, "Please select a class to remove.");
    }//GEN-LAST:event_removeClassButtonActionPerformed
    
    
    private void addFieldButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFieldButtonActionPerformed
        switch(count)
        {
        	case 0:
        		fieldL1Label.setVisible(true);
        		field1.setVisible(true);
        		numberValCheck1.setVisible(true);
        		count++;
        		break;
        	case 1:
        		fieldL2Label.setVisible(true);
        		field2.setVisible(true);
        		numberValCheck2.setVisible(true);
        		count++;
        		break;
        	case 2:
        		fieldL3Label.setVisible(true);
        		field3.setVisible(true);
        		numberValCheck3.setVisible(true);
        		count++;
        		break;
        	default:
        		JOptionPane op = new JOptionPane("The maximum number of fields has been reached. Sorry, please use 4 fields or less.", JOptionPane.INFORMATION_MESSAGE);
                JDialog dialog = op.createDialog("Device Manager: Maximum Fields!");
                dialog.setAlwaysOnTop(true);
                dialog.setModal(true);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
                count = 3;
                break;
        		
        }
    }
    
    private void removeFields()
    {
    	 switch(count)
         {
         	case 1:
         		fieldL1Label.setVisible(false);
         		field1.setVisible(false);
         		numberValCheck1.setVisible(false);
         		count--;
         		break;
         	case 2:
         		fieldL2Label.setVisible(false);
         		field2.setVisible(false);
         		numberValCheck2.setVisible(false);
         		count--;
         		break;
         	case 3:
         		fieldL3Label.setVisible(false);
         		field3.setVisible(false);
         		numberValCheck3.setVisible(false);
         		count--;
         		break;
         	default:
         		JOptionPane op = new JOptionPane("The Minimum number of fields has been reached. Sorry, please use 1 field or more.", JOptionPane.INFORMATION_MESSAGE);
                 JDialog dialog = op.createDialog("Device Manager: Minimum Fields!");
                 dialog.setAlwaysOnTop(true);
                 dialog.setModal(true);
                 dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                 dialog.setVisible(true);
                 count = 0;
                 break;
         		
         }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    JTextField tfield;
    Device device;
    JLabel tlabel;
    JCheckBox cbox;
    private JList removeClassList;
    private JButton addArrowRC;
    private JButton removeArrowRC;
    private JPanel addDevicePanel;
	private JTextField deviceNameInput;
	private JTextField fileExtInput;
	private JList currentClassListRC;
	private JScrollPane spAvaiableClassesRC ;
	private JLabel lblAvailableClasses;
	private JButton removePrinterButton;
	private JList classList ;
	private JLabel backButton;
	private JLabel deviceNameLabel;
	private JLabel fileExtLabel;
	private JLabel fieldL1Label;
	private JTextField fieldL0;
	private JCheckBox numberValCheck0;
	private JButton addFieldButton;
	private JButton saveButton;
	private JTabbedPane tabbedPane;
	private JLabel fieldL0Label;
	private JTextField field1;
	private JCheckBox numberValCheck1;
	private JLabel fieldL2Label;
	private JTextField field2;
	private JCheckBox numberValCheck2;
	private JLabel fieldL3Label;
	private JTextField field3;
	private JCheckBox numberValCheck3;
	private JLabel fieldL4Label;
	private JTextField field4;
	private JCheckBox numberValCheck4;
	private JButton removeFieldButton;
}// end of class