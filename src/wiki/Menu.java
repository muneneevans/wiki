/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiki;

/**
 *
 * @author overmars
 */
public class Menu extends javax.swing.JFrame {

    user currentuser = new user();
    public Menu() {
        initComponents();
        
    }
    public void SetUser(user u)
    {
        currentuser = u ; 
        System.out.println(u.role_id);
        SetButtons();
    }
    
    
    public void SetButtons()
    {
        //disable all buttons
        NewPageButton.setEnabled(false);
        ManagePagesButton.setEnabled(false);
        ManageUsersButton.setEnabled(false);
        NewUserButton.setEnabled(false);
        switch(currentuser.role_id)
        {
            case 1:
                //is admin, can do anything                
                NewPageButton.setEnabled(true);
                ManagePagesButton.setEnabled(true);
                ManageUsersButton.setEnabled(true);
                NewUserButton.setEnabled(true);
                break;
            case 2:
                //is teacher. cam read and edit pages
                NewPageButton.setEnabled(true);
                ManagePagesButton.setEnabled(true);                
                break;
            case 3:
                //student reader, can only view pages                
                ManagePagesButton.setEnabled(true);                
                break;
            case 4:
                //student writer, can view and edit page
                NewPageButton.setEnabled(true);
                ManagePagesButton.setEnabled(true);                
                break;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NewPageButton = new javax.swing.JButton();
        ManagePagesButton = new javax.swing.JButton();
        NewUserButton = new javax.swing.JButton();
        ManageUsersButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        NewPageButton.setText("New Page");
        NewPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewPageButtonActionPerformed(evt);
            }
        });

        ManagePagesButton.setText("View Pages");
        ManagePagesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ManagePagesButtonActionPerformed(evt);
            }
        });

        NewUserButton.setText("New User");
        NewUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewUserButtonActionPerformed(evt);
            }
        });

        ManageUsersButton.setText("Manage Users");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(ManagePagesButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(NewUserButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ManageUsersButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(NewPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(310, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(ManagePagesButton)
                .addGap(32, 32, 32)
                .addComponent(NewPageButton)
                .addGap(34, 34, 34)
                .addComponent(NewUserButton)
                .addGap(33, 33, 33)
                .addComponent(ManageUsersButton)
                .addContainerGap(117, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NewPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewPageButtonActionPerformed
        // TODO add your handling code here:
        NewPage Np = new NewPage();
        Np.SetUser(currentuser);
        Np.show();
        this.dispose();
    }//GEN-LAST:event_NewPageButtonActionPerformed

    private void ManagePagesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ManagePagesButtonActionPerformed
        // TODO add your handling code here:
        ManagePages Mp = new ManagePages();
        Mp.SetUser(currentuser);
        Mp.show();
        this.dispose();
    }//GEN-LAST:event_ManagePagesButtonActionPerformed

    private void NewUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewUserButtonActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_NewUserButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ManagePagesButton;
    private javax.swing.JButton ManageUsersButton;
    private javax.swing.JButton NewPageButton;
    private javax.swing.JButton NewUserButton;
    // End of variables declaration//GEN-END:variables
}
