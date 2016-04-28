/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiki;

import java.io.PrintWriter;
import java.util.UUID;

/**
 *
 * @author overmars
 */
public class EditPage extends javax.swing.JFrame {

    DBManager Db = new DBManager();
    page selectedpage = new page();
    user currentuser = new user();
    public EditPage() {
        initComponents();
        
        page p = new page();
        p.page_id = 8 ;
        
        user u = new user();
        u.user_id =6 ;
        
        SetValues(p,u);
    }
    
    //function to pass the page values
    public void SetValues(page p , user u )
    {
        selectedpage = p ;
        currentuser = u ;
    }
    private void WriteToFile(String uuid , String title , String content) 
    {
        try{
            
        PrintWriter writer = new PrintWriter("files/" +uuid +".html" , "UTF-8");
        
        writer.println("<head><title>");
        writer.println(title);
        writer.println("</title></head>");
        
        writer.println("<h2>");
        writer.println(title);
        writer.println("</h2>");
        
        writer.println("<div>");
        writer.println(content);        
        writer.println("</div>");
        writer.close();
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ContentLabel = new javax.swing.JLabel();
        SaveButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ContentTextBox = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ContentLabel.setText("New Content");

        SaveButton.setText("Save");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(ContentTextBox);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ContentLabel)
                    .addComponent(SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ContentLabel)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(SaveButton)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        // TODO add your handling code here:       
        file f = new file();
        page_file pf = new page_file();
        //create a new file
        //generate a random long string for file name
        f.file_name =  UUID.randomUUID().toString();
        f.file_id = Db.InsertFile(f);
        //create the physical file
        WriteToFile(f.file_name , selectedpage.title , ContentTextBox.getText());
        
        //insert page file
        pf.page_id = selectedpage.page_id ;
        pf.file_id = f.file_id  ; 
        pf.user_id = currentuser.user_id;
        Db.InsertPageFile(pf);
    }//GEN-LAST:event_SaveButtonActionPerformed

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
            java.util.logging.Logger.getLogger(EditPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ContentLabel;
    private javax.swing.JTextPane ContentTextBox;
    private javax.swing.JButton SaveButton;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
