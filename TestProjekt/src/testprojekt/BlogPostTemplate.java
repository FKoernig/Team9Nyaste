/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojekt;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import oru.inf.InfDB;
import oru.inf.InfException;

/**
 *
 * @author isakj
 */
public class BlogPostTemplate extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    
    private InfDB idb;
    private Validator validator;
    private String id;
    private String userID;
    
    
    public BlogPostTemplate() {
        initComponents();
        validator = new Validator();
        idb = TestProjekt.getDB();
        btnSave.setVisible(false);
        btnTabortInlagg.setVisible(false);
        lblTitle.setEditable(false);
        lblTitle.setBorder(null);
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setTitle(String title) {
        lblTitle.setText(title);
    }
    
    public void setText(String text) {
        txtText.setText(text);
    }
    
    public void setAuthor(String author) {
        lblAuthor.setText(author);
    }
    
    public void setDate(String date) {
        lblDate.setText(date);
    }
    
    public void setCategory(String name) {
        lblCategory.setText(name);
    }
    
    public javax.swing.JTextArea getTextArea() {
        return txtText;
    }
    
    public javax.swing.JButton getEditButton() {
        return btnEdit;
    }
    
    public javax.swing.JTextField getTitleField() {
        return lblTitle;
    }
    
    public void setUserID(String id) {
        userID = id;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblWrittenBy = new javax.swing.JLabel();
        lblAuthor = new javax.swing.JLabel();
        txtText = new javax.swing.JTextArea();
        lblDate = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        lblTitle = new javax.swing.JTextField();
        lblCategory = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnTabortInlagg = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        lblWrittenBy.setText("Skrivet av ");

        lblAuthor.setText("jLabel3");

        txtText.setEditable(false);
        txtText.setColumns(20);
        txtText.setLineWrap(true);
        txtText.setRows(5);
        txtText.setText("Text");
        txtText.setWrapStyleWord(true);

        lblDate.setFont(new java.awt.Font("DialogInput", 2, 12)); // NOI18N
        lblDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDate.setText("yyyy-MM-dd");

        btnEdit.setText("�ndra");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnSave.setText("Spara");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblTitleActionPerformed(evt);
            }
        });

        lblCategory.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lblCategory.setText("#kategori");

        jButton1.setText("Kommentarer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnTabortInlagg.setText("Ta bort");
        btnTabortInlagg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTabortInlaggActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblWrittenBy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                        .addGap(385, 385, 385))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDate)
                        .addGap(18, 18, 18)
                        .addComponent(lblCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTabortInlagg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitle)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtText)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtText, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWrittenBy)
                    .addComponent(lblAuthor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDate)
                    .addComponent(lblCategory)
                    .addComponent(btnSave)
                    .addComponent(btnEdit)
                    .addComponent(jButton1)
                    .addComponent(btnTabortInlagg))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        txtText.setEditable(true);
        lblTitle.setEditable(true);
        btnTabortInlagg.setVisible(true);
        txtText.requestFocus();
        txtText.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblTitle.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnEdit.setVisible(false);
        btnSave.setVisible(true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        String text = txtText.getText().trim();
        String title = lblTitle.getText();
        if (validator.validateNewPost(title, text, "Jobb")) {
            System.out.println(text.length());
            String query = "update Inlagg set text = '" + text + "', Rubrik = '" + title + "' WHERE INLAGGSID = " + id;
            try {
                idb.update(query);
            } catch(InfException ie) {
                System.out.println(ie.getMessage());
            }
            JOptionPane.showMessageDialog(null, "Inl�gget har �ndrats");
            btnSave.setVisible(false);
            btnEdit.setVisible(true);
            lblTitle.setEditable(false);
            txtText.setEditable(false);
            lblTitle.setBorder(null);
            txtText.setBorder(null);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void lblTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblTitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblTitleActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new Comments(id, userID).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnTabortInlaggActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabortInlaggActionPerformed
        // TODO add your handling code here:
        
         try{ 
        idb.delete("DELETE FROM inlagg WHERE inlaggsid = " + id);
        btnTabortInlagg.setVisible(false);
        btnSave.setVisible(false);
        btnEdit.setVisible(false);
       
    }
         catch(InfException e)
       {
       JOptionPane.showMessageDialog(null, "Ett fel uppstod.");
                System.out.println("Internt felmeddelande:" + e.getMessage());
       }
         
        
    }//GEN-LAST:event_btnTabortInlaggActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnTabortInlagg;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel lblAuthor;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblDate;
    private javax.swing.JTextField lblTitle;
    private javax.swing.JLabel lblWrittenBy;
    private javax.swing.JTextArea txtText;
    // End of variables declaration//GEN-END:variables
}