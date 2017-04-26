package ObjectLabEnterpriseSoftware;

import static ObjectLabEnterpriseSoftware.newJobsMgr.getSelectedRowNum;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author IEUser
 */
public class AddAdminView extends javax.swing.JFrame {
    JButton submit;
    JButton cancel;
    JTextField newID;
    JTextField removeID;
    private JTable adminTable;
    private DefaultTableModel adminModel;
    
    public AddAdminView(){
        setIconImage(Toolkit.getDefaultToolkit().getImage(DeviceMgr.class.getResource("/ObjectLabEnterpriseSoftware/images/icon.ico")));
	setPreferredSize(new Dimension(550, 370));
        setTitle("Admin Manager");
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);
        initComponents();
        setVisible(true);
    }
    
    public void initComponents(){
        JLabel titleLabel = new JLabel("Admin Manager");
        titleLabel.setBounds(189, 11, 300, 40);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setVisible(true);
        getContentPane().setLayout(null);
        getContentPane().add(titleLabel);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JLabel labelID = new JLabel();
        JLabel labelRemoveID = new JLabel();
        labelID.setText("New Admin ID:");
        labelRemoveID.setText("Remove Admin ID:");
        labelID.setVisible(true);
        labelRemoveID.setVisible(true);
        labelID.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        labelRemoveID.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        labelID.setBounds(100, 100, 200, 30);
        labelRemoveID.setBounds(100, 160, 200, 30);
        getContentPane().add(labelID);
        getContentPane().add(labelRemoveID);
        
        
        
        submit = new JButton();
        submit.setText("Submit");
        submit.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        submit.setBounds(385,310,150,30);
        submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
                            if(newID.getText() == null && removeID.getText() == null){
                                JOptionPane.showMessageDialog(null, "Enter an ID", "Invalid ID", JOptionPane.ERROR_MESSAGE);
                            }
                            else if(newID.getText() == null){
				removeFromDatabase();
                                dispose();
                            }
                            else if(removeID.getText() == null){
                                addToDatabase();
                                dispose();
                            }
                            else{
                                addToDatabase();
                                removeFromDatabase();
                                dispose();
                            }
			}
	});
        getContentPane().add(submit);
        
        cancel = new JButton();
        cancel.setText("Cancel");
        cancel.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        cancel.setBounds(10,310,150,30);
        cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
                            dispose();
			}
	});
        getContentPane().add(cancel);
        
        newID = new JTextField();
        newID.setBounds(240,100,200,30);
        newID.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        getContentPane().add(newID);
        
        removeID = new JTextField();
        removeID.setBounds(240, 160,200,30);
        removeID.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        getContentPane().add(removeID);
        
        /*JScrollPane adminPane = new JScrollPane();
        adminPane.setBounds(330, 70, 200, 200);
        getContentPane().add(adminPane);*/
        //Hoping to add a admin table of current admins
        
        pack();
        setLocationRelativeTo(null);
    }
    private void addToDatabase(){
        SQLMethods dbconn = new SQLMethods();
        dbconn.addAdmin(newID.getText());    
    }
    private void removeFromDatabase(){
        SQLMethods dbconn = new SQLMethods();
        dbconn.removeAdmin(removeID.getText());
    }
}