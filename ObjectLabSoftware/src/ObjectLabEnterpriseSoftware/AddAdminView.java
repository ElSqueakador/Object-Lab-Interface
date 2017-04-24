package ObjectLabEnterpriseSoftware;

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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author IEUser
 */
public class AddAdminView extends javax.swing.JFrame {
    JButton submit;
    JButton cancel;
    JTextField newID;
    
    public AddAdminView(){
        setPreferredSize(new Dimension (700,500));
        setTitle("Add Admin");
        setResizable(false);
        initComponents();
        setVisible(true);
    }
    
    public void initComponents(){
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JLabel labelID = new JLabel();
        labelID.setText("New Admin ID");
        labelID.setVisible(true);
        labelID.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        labelID.setBounds(100, 150, 200, 30);
        getContentPane().add(labelID);
        
        
        submit = new JButton();
        submit.setText("Submit");
        submit.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        submit.setBounds(400,300,200,30);
        submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
                            if(newID.getText() == null){
                                JOptionPane.showMessageDialog(null, "Enter an ID", "Invalid ID", JOptionPane.ERROR_MESSAGE);
                            }
                            else{
				addToDatabase();
                                dispose();
                            }
			}
	});
        getContentPane().add(submit);
        
        cancel = new JButton();
        cancel.setText("Cancel");
        cancel.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        cancel.setBounds(400,100,200,30);
        cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
                            dispose();
			}
	});
        getContentPane().add(cancel);
        
        newID = new JTextField();
        newID.setBounds(100,250,200,30);
        newID.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        getContentPane().add(newID);
        
        setLocationRelativeTo(null);
        pack();
    }
    private void addToDatabase(){
        SQLMethods dbconn = new SQLMethods();
        dbconn.addAdmin(newID.getText());    
    }
}
