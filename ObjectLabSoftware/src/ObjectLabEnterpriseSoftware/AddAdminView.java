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
    JButton submit;
    JButton cancel;
    JTextField newID;
    
    public AddAdminView(){
        setTitle("Laser Submission");
        setResizable(false);
        initComponents();
        setVisible(true);
    }
    
    public void initComponents(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        submit = new JButton();
        cancel = new JButton();
        newID = new JTextField();
        
    }
}
