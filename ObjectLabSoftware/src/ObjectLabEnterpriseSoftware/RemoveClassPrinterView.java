package ObjectLabEnterpriseSoftware;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

public class RemoveClassPrinterView extends javax.swing.JFrame {
	public RemoveClassPrinterView() {
	}
    
    private static final String NAME_OF_PAGE = "Remove Class/Device";
    private static SQLMethods sql = new SQLMethods();
    AdminSettingsView settings;
    public void removeClassPrinterStart() {
        initComponents();
        settings = new AdminSettingsView();
        setLocationRelativeTo(null);
        setVisible(true);
        addWindowListener
        (
            new WindowAdapter() 
            {
                @Override
                public void windowClosing(WindowEvent we) 
                {
                    /* If they close the program then close out the window properly */
                    settings.AdminSettingsViewStart();
                    dispose();
                }
            }
        );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cancelButton = new javax.swing.JButton();
        classComboBox = new javax.swing.JComboBox();
        titleLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        classLabel = new javax.swing.JLabel();
        printerLabel = new javax.swing.JLabel();
        printerComboBox = new javax.swing.JComboBox();
        removeClassButton = new javax.swing.JButton();
        removePrinterButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle(UtilController.getPageName(NAME_OF_PAGE));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ObjectLabEnterpriseSoftware/images/back_arrow_button.png"))); // NOI18N
        cancelButton.setToolTipText("Back");
        cancelButton.setBorderPainted(false);
        cancelButton.setContentAreaFilled(false);
        cancelButton.setFocusPainted(false);
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        getContentPane().add(cancelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 70, -1));

        classComboBox.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        classComboBox.setModel(new javax.swing.DefaultComboBoxModel(UtilController.returnAvailableClasses()));
        classComboBox.setSelectedItem(null);
        getContentPane().add(classComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 200, -1));

        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        titleLabel.setText("Remove Class/Device");
        getContentPane().add(titleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 290, 20));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 390, 10));

        classLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        classLabel.setText("Class:");
        getContentPane().add(classLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 50, -1));

        printerLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        printerLabel.setText("Device:");
        getContentPane().add(printerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 50, -1));

        printerComboBox.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        printerComboBox.setModel(new javax.swing.DefaultComboBoxModel(UtilController.arrayListToStringArray(UtilController.getListOfCurrentDevices())));
        printerComboBox.setSelectedItem(null);
        getContentPane().add(printerComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 200, -1));

        removeClassButton.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        removeClassButton.setText("Remove");
        removeClassButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeClassButtonActionPerformed(evt);
            }
        });
        getContentPane().add(removeClassButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, -1, -1));

        removePrinterButton.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        removePrinterButton.setText("Remove");
        removePrinterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removePrinterButtonActionPerformed(evt);
            }
        });
        getContentPane().add(removePrinterButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ObjectLabEnterpriseSoftware/images/white_bg.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 160));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        settings.AdminSettingsViewStart();
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void removeClassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeClassButtonActionPerformed
        if(classComboBox.getSelectedItem()!=null){
            if(JOptionPane.showConfirmDialog(null, "Continue? If you delete this class you will no longer be able "
                    + "to select this class when building jobs.\nFiles associated with this class will not be deleted "
                    + "but they will no longer be associated with this class. Click 'YES' to CONFIRM DELETION this is permanent!","Warning",JOptionPane.YES_OPTION)==JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(null, classComboBox.getSelectedItem() + " was deleted from the class list.");
                //delete class here
                //sql.deleteFromClass((String)classComboBox.getSelectedItem());
                String selected = (String)classComboBox.getSelectedItem();
                int id = Integer.parseInt(selected.split(" ")[0]);
                //System.out.print(id);
                UtilController.removeClass(id);
            }
        }else
            JOptionPane.showMessageDialog(null, "Please select a class to remove.");
    }//GEN-LAST:event_removeClassButtonActionPerformed

    private void removePrinterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removePrinterButtonActionPerformed
        if(printerComboBox.getSelectedItem()!=null){
            if(JOptionPane.showConfirmDialog(null, "Continue? If you delete this printer you will no longer be able "
                    + "to select this printer when building jobs.\nFiles associated with this printer will not be deleted "
                    + "but they will no longer be associated with this printer. Click 'YES' to CONFIRM DELETION this is permanent!","Warning",JOptionPane.YES_OPTION)==JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(null, printerComboBox.getSelectedItem() + " was deleted from the printer list.");
                //delete printer here    
                String printerName = (String)printerComboBox.getSelectedItem();
                //int id = Integer.parseInt(selected.split(" ")[0]);
                //System.out.print(id);
                UtilController.removeDevice(printerName);
            }
        }else
            JOptionPane.showMessageDialog(null, "Please select a printer to remove.");
    }//GEN-LAST:event_removePrinterButtonActionPerformed

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
            java.util.logging.Logger.getLogger(RemoveClassPrinterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RemoveClassPrinterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RemoveClassPrinterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RemoveClassPrinterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RemoveClassPrinterView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox classComboBox;
    private javax.swing.JLabel classLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox printerComboBox;
    private javax.swing.JLabel printerLabel;
    private javax.swing.JButton removeClassButton;
    private javax.swing.JButton removePrinterButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
