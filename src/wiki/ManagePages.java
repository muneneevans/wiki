/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiki;

import java.awt.Desktop;
import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author overmars
 */
public class ManagePages extends javax.swing.JFrame {
    
    private DBManager Db = new DBManager();
    private user currentuser = new user();
    ArrayList<page> model = new ArrayList<page>();
    DefaultListModel<String> stringmodel = new DefaultListModel<String>();
    DefaultListModel<String> filestringmodel = new DefaultListModel<String>();
    DefaultListModel<String> userstringmodel = new DefaultListModel<String>();
    public ManagePages() {
        initComponents();
        SetListSource();
    }
    public void SetUser(user u)
    {
        currentuser = u ; 
        System.out.println(u.role_id);     
        SetButtons();
    }
    public void SetButtons()
    {
        EditPageButton.setEnabled(false);
        switch(currentuser.role_id)
        {
            case 1:
                //is admin, can do anything                
                EditPageButton.setEnabled(true);
                break;
            case 2:
                //is teacher. cam read and edit pages
                EditPageButton.setEnabled(true);
                break;
            case 3:
                //student reader, can only view pages                                
                break;
            case 4:
                //student writer, can view and edit page
                EditPageButton.setEnabled(true);
                break;
        }
    }
    private void SetListSource()
    {
        try 
        {   
            
            model = Db.GetPages();            
            for(page p : model)
            {
                stringmodel.addElement(p.title);
            }
            PagesListView.setModel(stringmodel);
        }
        catch(Exception e)                
        {
            System.out.println(e.getMessage());
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        PagesListView = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        FileListView = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        UserListView = new javax.swing.JList<>();
        EditPageButton = new javax.swing.JButton();
        FilesLabel = new javax.swing.JLabel();
        UsersLabel = new javax.swing.JLabel();
        PagesLabel = new javax.swing.JLabel();
        BackButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PagesListView.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "There are no pages available now", " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        PagesListView.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                PagesListViewValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(PagesListView);

        FileListView.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "there are no files to show in this page" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        FileListView.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                FileListViewValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(FileListView);

        UserListView.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "there are no users in this page" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(UserListView);

        EditPageButton.setText("Edit Page");
        EditPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditPageButtonActionPerformed(evt);
            }
        });

        FilesLabel.setText("Files in page");

        UsersLabel.setText("Authors of files");

        PagesLabel.setText("Pages");

        BackButton.setText("Back");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PagesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FilesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(UsersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(BackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FilesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UsersLabel)
                    .addComponent(PagesLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EditPageButton)
                    .addComponent(BackButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PagesListViewValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_PagesListViewValueChanged
        // TODO add your handling code here:
        
        //get the selected page
        int id = PagesListView.getSelectedIndex();
        page p = model.get(id );
        
        //get page files
        FileListView.removeAll();
        filestringmodel = new DefaultListModel<String>();
        for (file f : Db.GetPageFiles(p))
        {
            filestringmodel.addElement(f.file_name);
        }
        FileListView.setModel(filestringmodel);
        
        //show contributers for the page
        UserListView.removeAll();
        userstringmodel = new DefaultListModel<String>();
        for(user u : Db.GetPageUsers(p))
        {
            userstringmodel.addElement(u.fname + " " + u.lname); 
        }
        UserListView.setModel(userstringmodel);
        
        
        
        //get all files from the user
    }//GEN-LAST:event_PagesListViewValueChanged

    private void FileListViewValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_FileListViewValueChanged
        // TODO add your handling code here:
        File document = new File("files\\" +FileListView.getSelectedValue()+".html");
        System.out.println(document.getPath());
        try 
        {
            Desktop.getDesktop().open(new File (document.getPath().toString())) ;
        } 
        catch (Exception ex) 
        {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_FileListViewValueChanged

    private void EditPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditPageButtonActionPerformed
        // TODO add your handling code here:
        //get page and go edit it
        EditPage Ep = new EditPage();
        
        // get the selected pagge
        int id = PagesListView.getSelectedIndex();
        page p = model.get(id );
        
        Ep.SetValues(p, currentuser);
        Ep.show();
        this.dispose();
    }//GEN-LAST:event_EditPageButtonActionPerformed

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        // TODO add your handling code here:
            Menu mn = new Menu();
            mn.SetUser(currentuser);
            mn.show();
            this.dispose();
    }//GEN-LAST:event_BackButtonActionPerformed

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
            java.util.logging.Logger.getLogger(ManagePages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagePages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagePages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagePages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagePages().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JButton EditPageButton;
    private javax.swing.JList<String> FileListView;
    private javax.swing.JLabel FilesLabel;
    private javax.swing.JLabel PagesLabel;
    private javax.swing.JList<String> PagesListView;
    private javax.swing.JList<String> UserListView;
    private javax.swing.JLabel UsersLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
    
    
}
