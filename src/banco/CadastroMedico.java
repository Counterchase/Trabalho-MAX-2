/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import banco.Model.Admin;
import banco.Model.Medico;
import banco.Model.Clinica;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Platform.exit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import sun.swing.table.DefaultTableCellHeaderRenderer;

public class CadastroMedico extends javax.swing.JFrame {
    
    private Connection conn;
    private List<Medico> medicos;
    private List<Clinica> lclinica;
    private List<Admin> ladmin;
    
    public void alinharTbMedicos(JTable tb) {
        MedicoTableModel modelo = new MedicoTableModel();
        
        DefaultTableCellRenderer dtcr = new DefaultTableCellHeaderRenderer();
        
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int i = 0; i < 4; i++) {
            tb.getColumnModel().getColumn(i).setCellRenderer(dtcr);
        }
    }
    
    public List<Medico> listarTbMedico() throws SQLException {
        medicos = new ArrayList<>();
        conn = Banco.conecta();
        if (conn == null || conn.isClosed()) {
            System.out.println("erro ao conectar ao banco de dados");
            System.exit(-1);
        }
        String sql = "SELECT idmedico, nome, cpf, crm, datanascimento, idclinica, idadmin FROM Medico";
        System.out.println("sql: " + sql);

        //atravez desse objeto usamos comandos sql
        Statement stmt = conn.createStatement();

        //select
        ResultSet res = stmt.executeQuery(sql);
       
        
        while (res.next()) {
            
            Medico m = new Medico();;
            m.setIdmedico(res.getInt("idmedico"));
            m.setNome(res.getString("nome"));
            m.setCpf(res.getString("cpf").toString());
            m.setCrm(res.getString("crm"));
            
            Calendar c = Calendar.getInstance();
            c.setTime(res.getDate("datanascimento"));     
            m.setDatanascimento(c);
            
            
            m.setIdclinica(res.getInt("idclinica"));
            m.setIdadmin(res.getInt("idadmin"));
            medicos.add(m);
        }
        stmt.close();
        conn.close();
        return medicos;
        
    }
    
    public List<Admin> listarAdmins() throws SQLException {
        
        List<Admin> Admins = new ArrayList<>();
        
        conn = Banco.conecta();
        if (conn == null || conn.isClosed()) {
            System.out.println("erro ao conectar ao banco de dados");
            System.exit(-1);
        }
        
        String sql = "SELECT idadmin, nome,login, senha FROM admin";
        
        System.out.println("sql: " + sql);
        Statement stmt = conn.createStatement();

        //retorna um conjunto de dados , sempre q for fazer insert usar o executeUpdate
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println("Id:  " + res.getInt("idadmin"));
            System.out.println("Nome:  " + res.getString("nome"));
            System.out.println("Login:  " + res.getString("login"));
            System.out.println("Senha:  " + res.getString("senha"));
            
            Admin b = new Admin();
            b.setIdadmin(res.getInt("idadmin"));
            b.setNome(res.getString("nome"));
            b.setLogin(res.getString("login"));
            b.setSenha(res.getString("senha"));
            
            Admins.add(b);
            
        }
        return Admins;
        
    }
    
    public List<Clinica> listarClinicas() throws SQLException {
        
        List<Clinica> Clinicas = new ArrayList<>();
        conn = Banco.conecta();
        if (conn == null || conn.isClosed()) {
            System.out.println("erro ao conectar ao banco de dados");
            System.exit(-1);
        }
        
        String sql = "SELECT idclinica, nome,cnpj, cidadeclinica FROM Clinica";
        
        System.out.println("sql: " + sql);
        Statement stmt = conn.createStatement();

        //retorna um conjunto de dados , sempre q for fazer insert usar o executeUpdate
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {
            
            Clinica a = new Clinica();
            a.setIdclinica(res.getInt("idclinica"));
            a.setNome(res.getString("nome"));
            a.setCnpj(res.getString("cnpj"));
            a.setCidadeclinica(res.getString("cidadeclinica"));
            
            Clinicas.add(a);
            
        }
        return Clinicas;
        
    }
    
    public CadastroMedico() {
        initComponents();
        setLocationRelativeTo(null);
        
        medicos = new ArrayList<>();
        lclinica = new ArrayList<>();
        ladmin = new ArrayList<>();
        
        try {
            medicos = listarTbMedico();
            lclinica = listarClinicas();
            ladmin = listarAdmins();
            
        } catch (SQLException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        MedicoTableModel modelo = new MedicoTableModel();
        modelo.setListamedicos(medicos);
        
        tbMedico.setModel(modelo);
        
        for (int i = 0; i < lclinica.size(); i++) {
            combClinica.addItem(lclinica.get(i));
            
        }
        
        for (int i = 0; i < ladmin.size(); i++) {
            combAdmin.addItem(ladmin.get(i));
            
        }
        
        medicos = new ArrayList<>();
        
        conn = Banco.conecta();
        
        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("erro ao conectar ao banco de dados");
                System.exit(-1);
            }

            //encerrou a conexão
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(CadastroClinica.class.getName()).log(Level.SEVERE, null, ex);
        }

        //txtDataNascimento.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
        txtDataNascimento.setFormats("dd/MM/yyyy");
        txtDataNascimento.setDate(Calendar.getInstance().getTime());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        txtDataNascimento = new org.jdesktop.swingx.JXDatePicker();
        jLabel3 = new javax.swing.JLabel();
        txtCrm = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCpf = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        combClinica = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMedico = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        combAdmin = new javax.swing.JComboBox<>();
        btnExcluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Nome:");

        jLabel2.setText("Data de Nascimento:");

        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        jButton1.setText("Cadastrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtDataNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataNascimentoActionPerformed(evt);
            }
        });

        jLabel3.setText("CRM:");

        jLabel4.setText("CPF:");

        jLabel5.setText("Clinica:");

        combClinica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combClinicaActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Cadastro de Médicos");

        tbMedico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbMedico);

        jLabel7.setText("Admin:");

        combAdmin.setToolTipText("");

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4))
                                .addGap(104, 104, 104)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNome)
                                    .addComponent(txtCrm)
                                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel3)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btnExcluir)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnEditar))
                                        .addComponent(combClinica, 0, 200, Short.MAX_VALUE)
                                        .addComponent(combAdmin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 6, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(37, 37, 37))))
            .addGroup(layout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtCrm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(combClinica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(combAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnExcluir)
                            .addComponent(btnEditar))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed

    }//GEN-LAST:event_txtNomeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        conn = Banco.conecta();
        String sql = "";
        
        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("erro ao conectar ao banco de dados");
                System.exit(-1);
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date aux = txtDataNascimento.getDate();
            
            String nome = "'" + txtNome.getText() + "'";
            String crm = "'" + txtCrm.getText() + "'";
            String cpf = "'" + txtCpf.getText() + "'";
            
            String dataNasc = "'" + sdf.format(aux) + "'";

            
            Statement stmt = conn.createStatement();
            
            sql = "INSERT INTO Medico (nome,crm, cpf, datanascimento, idclinica, idadmin ) VALUES ("
                    + "" + nome + "," + crm + "," + cpf + "," + dataNasc + "," + ((Clinica) combClinica.getSelectedItem()).getIdclinica() + ","
                    + " " + ((Admin) combAdmin.getSelectedItem()).getIdadmin() + ")";
            System.out.println("sql: " + sql);

            //atravez desse objeto usamos comandos sql
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            //select
            //stmt.executeQuery(sql);
            //retorna um conjunto de dados , sempre q for fazer insert usar o executeUpdate : inset, update, delete

            //encerrou a conexão
            stmt.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Médico(a) cadastrado!");
            String Mostrar = " ";
            txtNome.setText(Mostrar);
            txtCpf.setText(Mostrar);
            txtCrm.setText(Mostrar);
            
            medicos = listarTbMedico();
            
            MedicoTableModel modelo = new MedicoTableModel();
            modelo.setListamedicos(medicos);
            
            tbMedico.setModel(modelo);

            //   alinharTbMedicos(tbMedico);
        } catch (SQLException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void combClinicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combClinicaActionPerformed
        

    }//GEN-LAST:event_combClinicaActionPerformed

    private void txtDataNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataNascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataNascimentoActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        
        conn = Banco.conecta();
        
        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("erro ao conectar ao banco de dados");
                System.exit(-1);
                
            }
            
            
            int k = tbMedico.getSelectedRow();
            // System.out.println(k);
            int i = ((MedicoTableModel) tbMedico.getModel()).getListamedicos().get(k).getIdmedico();
            
            Statement stmt = conn.createStatement();
            String sql = "Delete from medico where idmedico = " + i + " ";
            stmt.executeUpdate(sql);
            
            stmt.close();
            conn.close();
            
            JOptionPane.showMessageDialog(null, "Médico(a) Apagado!");
            
            String Mostrar = " ";
            txtNome.setText(Mostrar);
            txtCpf.setText(Mostrar);
            txtCrm.setText(Mostrar);
            
            medicos = listarTbMedico();
            MedicoTableModel modelo = new MedicoTableModel();
            modelo.setListamedicos(medicos);
            tbMedico.setModel(modelo);
            
        } catch (SQLException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
         conn = Banco.conecta();
        
        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("erro ao conectar ao banco de dados");
                System.exit(-1);
                
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date aux = txtDataNascimento.getDate();
            
            String nome = "'" + txtNome.getText() + "'";
            String crm = "'" + txtCrm.getText() + "'";
            String cpf = "'" + txtCpf.getText() + "'";
            String dataNasc = "'" + sdf.format(aux) + "'";
            
            int k = tbMedico.getSelectedRow();
            // System.out.println(k);
            int i = ((MedicoTableModel) tbMedico.getModel()).getListamedicos().get(k).getIdmedico();
            
            Statement stmt = conn.createStatement();
            String sql = "UPDATE medico SET nome = "+nome+",crm = "+ crm +",cpf = "+ cpf +",datanascimento = "+ dataNasc +"WHERE idmedico = " + i +"";
            stmt.executeUpdate(sql);
            
            stmt.close();
            conn.close();
            
            JOptionPane.showMessageDialog(null, "Médico(a) Editado!");
            
            String Mostrar = " ";
            txtNome.setText(Mostrar);
            txtCpf.setText(Mostrar);
            txtCrm.setText(Mostrar);
            
            medicos = listarTbMedico();
            MedicoTableModel modelo = new MedicoTableModel();
            modelo.setListamedicos(medicos);
            tbMedico.setModel(modelo);
            
        } catch (SQLException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
          
       
    }//GEN-LAST:event_btnEditarActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroMedico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JComboBox<Admin> combAdmin;
    private javax.swing.JComboBox<Clinica> combClinica;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbMedico;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtCrm;
    private org.jdesktop.swingx.JXDatePicker txtDataNascimento;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
