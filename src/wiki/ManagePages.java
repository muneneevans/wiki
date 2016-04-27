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
    ArrayList<page> model = new ArrayList<page>();
    DefaultListModel<String> stringmodel = new DefaultListModel<String>();
    DefaultListModel<String> filestringmodel = new DefaultListModel<String>();
    DefaultListModel<String> userstringmodel = new DefaultListModel<String>();
    public ManagePages() {
        initComponents();
        SetListSource();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PagesListView.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "item1", "item2", "item3" };
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
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
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
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(UserListView);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3))
                .addContainerGap(116, Short.MAX_VALUE))
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
    private javax.swing.JList<String> FileListView;
    private javax.swing.JList<String> PagesListView;
    private javax.swing.JList<String> UserListView;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
    
    
}
