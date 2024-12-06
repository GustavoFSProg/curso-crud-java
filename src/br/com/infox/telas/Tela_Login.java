package br.com.infox.telas;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */


import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import javax.swing.JOptionPane;

/**
 *
 * @author oem
 */
public class Tela_Login extends javax.swing.JFrame {
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form Tela_Login
     */
    public Tela_Login() {
        initComponents();
       
        conexao = ModuloConexao.conector();
        
        if(conexao != null){
        Label1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/upload_pequeno.png")));
//          Label1.setText("CONECTADO"); 
    }else{
//           Label1.setText("DESCONETADO");
            Label1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/remove_icon_pequeno.png")));
        }
        
        System.out.println("CONEXAO"); 
        System.out.println(conexao);
        
         
         
      
            
             
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Label1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        usuarioLabel = new javax.swing.JTextField();
        senhaLabel = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ordem de Serviço 1.0");
        setResizable(false);

        Label1.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        Label1.setForeground(new java.awt.Color(122, 122, 179));
        Label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel1.setFont(new java.awt.Font("TeXGyreHeros", 1, 14)); // NOI18N
        jLabel1.setText("SENHA");

        jLabel2.setFont(new java.awt.Font("TeXGyreHeros", 1, 14)); // NOI18N
        jLabel2.setText("USUÁRIO");

        usuarioLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioLabelActionPerformed(evt);
            }
        });

        senhaLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                senhaLabelActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Noto Sans CJK HK", 1, 13)); // NOI18N
        jButton1.setText("Login");
        jButton1.setPreferredSize(new java.awt.Dimension(50, 24));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Label1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(8, 8, 8)))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(usuarioLabel)
                        .addComponent(senhaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(usuarioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(senhaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Label1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );

        setSize(new java.awt.Dimension(437, 241));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void usuarioLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usuarioLabelActionPerformed

    private void senhaLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_senhaLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_senhaLabelActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
  String sql ="select * from clientes where login=? and senha=?";
            
            try{
                pst = conexao.prepareStatement(sql);
                pst.setString(1, usuarioLabel.getText());    
                pst.setString(2, senhaLabel.getText());
                
                rs = pst.executeQuery();
                
                if(rs.next()){
                    JOptionPane.showMessageDialog(null, "Login com Sucesso!");
                }else{
                     JOptionPane.showMessageDialog(null, "ERRO de login!");
                    
                }

            
                
            }catch(Exception e){
                   
                  System.out.println("ERROR");
                
            }
                // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Tela_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tela_Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Label1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField senhaLabel;
    private javax.swing.JTextField usuarioLabel;
    // End of variables declaration//GEN-END:variables
}
