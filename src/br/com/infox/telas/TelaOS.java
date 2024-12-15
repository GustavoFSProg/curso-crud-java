/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.telas;
import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author oem
 */
public class TelaOS extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    private String tipo;
    /**
     * Creates new form TelaOS
     */
    public TelaOS() {
        initComponents();
        
        conexao = ModuloConexao.conector();
    }
    
    
      public void pesquisar_clientes(){
          String sql = "select  id as Id, name as Nome, fone as Fone  from clientes where  name like ?";
          try{
               pst=conexao.prepareStatement(sql);
               
               pst.setString(1, PesquisarCli.getText() + "%");
               
                     rs= pst.executeQuery();
                     
                     TabelaClientes.setModel(DbUtils.resultSetToTableModel(rs));
                     
          
              }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
          
           
      }
      
       private void setar_campos(){
           int setar = TabelaClientes.getSelectedRow( );
           
           ID.setText(TabelaClientes.getModel().getValueAt(setar, 0).toString());
       }
       
       
    private void emitir_os(){
        String sql = "insert into  os (tipo, situacao , equipamento , defeito, tecnico, valor, idCli, servico)  values(?,?,?,?,?,?,?,?)";
        try{
            pst=conexao.prepareStatement(sql);
            
//        String datas =    ComboBoxSituacao.getSelectedItem().toString();
//            pst.setString(1,  IdField.getText());          
//        teste.setText(datas);
            pst.setString(1, tipo); 
            pst.setString(2, ComboBoxSituacao.getSelectedItem().toString());   
            pst.setString(3, Equipamento.getText());       
            pst.setString(4, Defeito.getText());        
            pst.setString(5, Tecnico.getText());       
            pst.setString(6, Valor.getText());        
            pst.setString(7, ID.getText());          
            pst.setString(8, Servico.getText());     


            
            if(ID.getText().isEmpty() ||  Equipamento.getText().isEmpty() || Defeito.getText().isEmpty())                  
                     {
                JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios!");
                
                
            }else{
            
             int adicionado =     pst.executeUpdate();
            
                          

            if(adicionado > 0){
                
               JOptionPane.showMessageDialog(null,"OS Emitida com sucesso!");
                limpar_campos();
            }
            }
             }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    
    }
    
    
       private void limpar_campos(){
                 Equipamento.setText("");       
           Defeito.setText("");        
            Tecnico.setText("");       
            Valor.setText("");        
            ID.setText("");          
             Servico.setText("");    
             PesquisarCli.setText("");
             IdCliente.setText("");     
             
                 ((DefaultTableModel) TabelaClientes.getModel()).setRowCount(0);
                 ComboBoxSituacao.setSelectedItem(" ");
       }
       
       
       
       private void pesquisar_os(){
           String  num_os = JOptionPane.showInputDialog("Número da OS");
           String sql = "select  id,  date_format(data_os,  '%d/%m/%Y  -  %H:%i') , tipo, situacao, equipamento, defeito, tecnico, valor, idCli, servico   from os where  id= " + num_os;
           
               try{
               pst=conexao.prepareStatement(sql);
                     rs= pst.executeQuery();
                     
                     if(rs.next()){                    
          
                         NumeroOS.setText(rs.getString(1));         
                         DataOS.setText(rs.getString(2));     
                        ComboBoxSituacao.setSelectedItem(rs.getString(4));       
                  
                         Equipamento.setText(rs.getString(5));
                         Defeito.setText(rs.getString(6));        
                         Tecnico.setText(rs.getString(7));  
                         Valor.setText(rs.getString(8));       
                         IdCliente.setText(rs.getString(9));       
                         Servico.setText(rs.getString(10));      
                         

                         
                         String   radioButton = rs.getString(3);
                         
                 
                         if(radioButton.equals("Ordem de Serviço")){
                             
                                  RadioButtonOs.setSelected(true);    
                                 

                                  tipo="Ordem de Serviço";
                                  
                         }else{                            
                         

                               RadioButtonOrcam.setSelected(true);
                                  tipo="Orçamento";
                         }
                     }else{
                         JOptionPane.showMessageDialog(null, "OS Invalida!! ");
                     
                     }
                     
                     TabelaClientes.setModel(DbUtils.resultSetToTableModel(rs));
                     
                     UpdateButton.setEnabled(true);
                     deleteButton.setEnabled(true);
                     Imprimir.setEnabled(true);
                     AddButton.setEnabled(false);
          
              }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
          
           
       }
       
       
       private void update(){
           
             String sql = "update  os  set   tipo=?, situacao =?, equipamento=? , defeito=? , tecnico=?, valor=?,  servico=?  where  id=?";
             
          
             try{
                 
            pst= conexao.prepareStatement(sql);
            pst.setString(1, tipo); 
            pst.setString(2, ComboBoxSituacao.getSelectedItem().toString());   
            pst.setString(3, Equipamento.getText());       
            pst.setString(4, Defeito.getText());        
            pst.setString(5, Tecnico.getText());       
            pst.setString(6, Valor.getText());        
                
            pst.setString(7, Servico.getText());                         
            pst.setString(8, NumeroOS.getText());      
 
             

      if(Equipamento.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios!");
                
                
            }else{
            
             int adicionado =     pst.executeUpdate();
            
                          

            if(adicionado > 0){
                
               JOptionPane.showMessageDialog(null,"OS  Atualizado com sucesso!");
//              
               
               limpar_campos();
                 
                  AddButton.setEnabled(true);
                     UpdateButton.setEnabled(false);
                     deleteButton.setEnabled(false);
                     Imprimir.setEnabled(false);
              
            }
            }
             }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    
           
       }
       
       
       private void deletar(){
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover essa OS?", "Atenção", JOptionPane.YES_NO_OPTION);
        
      if(confirma ==JOptionPane.YES_OPTION)  {
          String sql = "delete from os  where id=?";
          
          try{
              pst=conexao.prepareStatement(sql);
              pst.setString(1, NumeroOS.getText());
              
               int apagado =   pst.executeUpdate();
               
               if(apagado > 0){
                   
              
                JOptionPane.showMessageDialog(null,"Ordem de Serviço  deletada  com sucesso!");
              
               limpar_campos();
               
               AddButton.setEnabled(true);
                
                               }

              
                }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
      }
       }
       
       private void imprimir_os(){
            int confirma = JOptionPane.showConfirmDialog(null, "Confirma a Impressão da OS?", "Atenção", JOptionPane.YES_NO_OPTION);
        
        if(confirma  == JOptionPane.YES_OPTION){
            try{
                
                
                HashMap filtro = new HashMap();
                
                filtro.put("ID", Integer.parseInt(NumeroOS.getText()));
                JasperPrint print =   JasperFillManager.fillReport("/home/oem/reports/OSReport.jasper", filtro, conexao);
                
                
                JasperViewer.viewReport(print, false);
              
            }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            }
        }
       }
       
       
 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        NumeroOSLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        NumeroOS = new javax.swing.JTextField();
        DataOS = new javax.swing.JTextField();
        RadioButtonOrcam = new javax.swing.JRadioButton();
        RadioButtonOs = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        ComboBoxSituacao = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        PesquisarCli = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ID = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelaClientes = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Defeito = new javax.swing.JTextField();
        Equipamento = new javax.swing.JTextField();
        Tecnico = new javax.swing.JTextField();
        Servico = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Valor = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        ReadButton = new javax.swing.JButton();
        UpdateButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        AddButton = new javax.swing.JButton();
        Imprimir = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        teste = new javax.swing.JLabel();
        IdCliente = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Ordem de Serviço");
        setPreferredSize(new java.awt.Dimension(640, 533));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        NumeroOSLabel.setText("Número OS");

        jLabel1.setText("Data");

        NumeroOS.setEditable(false);
        NumeroOS.setEnabled(false);

        DataOS.setEditable(false);
        DataOS.setEnabled(false);

        buttonGroup1.add(RadioButtonOrcam);
        RadioButtonOrcam.setText("Orçamento");
        RadioButtonOrcam.setActionCommand("Orcamento");
        RadioButtonOrcam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButtonOrcamActionPerformed(evt);
            }
        });

        buttonGroup1.add(RadioButtonOs);
        RadioButtonOs.setText("Ordem de serviço");
        RadioButtonOs.setActionCommand("OS");
        RadioButtonOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButtonOsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NumeroOSLabel)
                    .addComponent(NumeroOS, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RadioButtonOrcam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(RadioButtonOs)
                        .addGap(34, 34, 34))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(DataOS, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NumeroOSLabel)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NumeroOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DataOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RadioButtonOrcam)
                    .addComponent(RadioButtonOs))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        RadioButtonOs.getAccessibleContext().setAccessibleName("OS");

        jLabel2.setText("Situação");

        ComboBoxSituacao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Na Bancada", "Entrega OK", "Orçamento REPROVADO", "Aguardando Aprovação", "Aguardando Peças", "Abandonado pelo Cliente", "Retornou" }));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        PesquisarCli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PesquisarCliKeyReleased(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/lupa.png"))); // NOI18N

        jLabel4.setText("* ID");

        ID.setEnabled(false);

        TabelaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Nome", "Fone"
            }
        ));
        TabelaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelaClientes);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ID))
                            .addComponent(PesquisarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 44, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(PesquisarCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel5.setText("*  Equipamento");

        jLabel6.setText("* Defeito");

        jLabel7.setText("Serviço");

        jLabel8.setText("Técnico");

        jLabel9.setText("Valor");

        Valor.setText("0");

        jPanel3.setAlignmentX(20);
        jPanel3.setAlignmentY(20);

        ReadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/read.png"))); // NOI18N
        ReadButton.setToolTipText("Ler Dados");
        ReadButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ReadButton.setPreferredSize(new java.awt.Dimension(70, 70));
        ReadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReadButtonActionPerformed(evt);
            }
        });

        UpdateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/update.png"))); // NOI18N
        UpdateButton.setToolTipText("Editar");
        UpdateButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        UpdateButton.setEnabled(false);
        UpdateButton.setPreferredSize(new java.awt.Dimension(70, 70));
        UpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateButtonActionPerformed(evt);
            }
        });

        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/delete.png"))); // NOI18N
        deleteButton.setToolTipText("Deletar");
        deleteButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteButton.setEnabled(false);
        deleteButton.setPreferredSize(new java.awt.Dimension(70, 70));
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        AddButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/add.png"))); // NOI18N
        AddButton.setToolTipText("Adicionar");
        AddButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AddButton.setPreferredSize(new java.awt.Dimension(70, 70));
        AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddButtonActionPerformed(evt);
            }
        });

        Imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/printer.png"))); // NOI18N
        Imprimir.setToolTipText("Imprimir");
        Imprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Imprimir.setEnabled(false);
        Imprimir.setPreferredSize(new java.awt.Dimension(70, 70));
        Imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImprimirActionPerformed(evt);
            }
        });

        jButton1.setText("Limpar");
        jButton1.setPreferredSize(new java.awt.Dimension(70, 70));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(ReadButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(UpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ReadButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        teste.setText("Id Cliente");

        IdCliente.setEnabled(false);

        jButton2.setText("Ver Oss");
        jButton2.setPreferredSize(new java.awt.Dimension(68, 38));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(Servico, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Defeito, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Equipamento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(teste)
                                .addComponent(jLabel2))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(ComboBoxSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(IdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Tecnico)
                            .addComponent(Valor, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(layout.createSequentialGroup()
                .addGap(192, 192, 192)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Valor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Tecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(teste)
                            .addComponent(IdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(ComboBoxSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Equipamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Defeito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Servico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setBounds(0, 0, 640, 559);
    }// </editor-fold>//GEN-END:initComponents

    private void ReadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReadButtonActionPerformed
      pesquisar_os();
    }//GEN-LAST:event_ReadButtonActionPerformed

    private void UpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateButtonActionPerformed
    update();//        update();        // TODO add your handling code here:
    }//GEN-LAST:event_UpdateButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
       deletar() ;
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonActionPerformed
        emitir_os();
    }//GEN-LAST:event_AddButtonActionPerformed

    private void ImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImprimirActionPerformed
       imprimir_os();        // TODO add your handling code here:
    }//GEN-LAST:event_ImprimirActionPerformed

    private void PesquisarCliKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PesquisarCliKeyReleased
   pesquisar_clientes();        // TODO add your handling code here:
    }//GEN-LAST:event_PesquisarCliKeyReleased

    private void TabelaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaClientesMouseClicked
       setar_campos();        // TODO add your handling code here:
    }//GEN-LAST:event_TabelaClientesMouseClicked

    private void RadioButtonOrcamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButtonOrcamActionPerformed
         tipo = "Orçamento!";        // TODO add your handling code here:
    }//GEN-LAST:event_RadioButtonOrcamActionPerformed

    private void RadioButtonOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButtonOsActionPerformed
       tipo="Ordem de Serviço";        // TODO add your handling code here:
    }//GEN-LAST:event_RadioButtonOsActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
       RadioButtonOrcam.setSelected(true);
       tipo = "Orçamento";
               
// TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         limpar_campos();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
   BuscarOs  buscar= new BuscarOs();
          buscar.setVisible(true);  
//        Desktop.add(telaVerOS);// TODO add your handling code here:        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButton;
    private javax.swing.JComboBox ComboBoxSituacao;
    private javax.swing.JTextField DataOS;
    private javax.swing.JTextField Defeito;
    private javax.swing.JTextField Equipamento;
    private javax.swing.JTextField ID;
    private javax.swing.JTextField IdCliente;
    private javax.swing.JButton Imprimir;
    private javax.swing.JTextField NumeroOS;
    private javax.swing.JLabel NumeroOSLabel;
    private javax.swing.JTextField PesquisarCli;
    private javax.swing.JRadioButton RadioButtonOrcam;
    private javax.swing.JRadioButton RadioButtonOs;
    private javax.swing.JButton ReadButton;
    private javax.swing.JTextField Servico;
    private javax.swing.JTable TabelaClientes;
    private javax.swing.JTextField Tecnico;
    private javax.swing.JButton UpdateButton;
    private javax.swing.JTextField Valor;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel teste;
    // End of variables declaration//GEN-END:variables
}
