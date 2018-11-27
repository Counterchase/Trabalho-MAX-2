/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
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

/**
 *
 * @author IFMS
 */
public class CadastroAdm extends javax.swing.JFrame {

    private Connection conn;

    private List<Admin> Admins;
    private List<Clinica> Clinicas;
    private List<Medico> medicos;
    private List<Clinica> lclinica;
    private List<Admin> ladmin;

    /**
     * ALINHANOD TABELAS =============================================================================================================================*
     */
    public void alinharTbAdmins(JTable tb) {
        AdminTableModel modeloAdmin = new AdminTableModel();

        DefaultTableCellRenderer dtcr = new DefaultTableCellHeaderRenderer();

        dtcr.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < 4; i++) {
            tb.getColumnModel().getColumn(i).setCellRenderer(dtcr);
        }
    }

    public void alinharTbClinicas(JTable tb) {
        ClinicaTableModel modeloClinica = new ClinicaTableModel();

        DefaultTableCellRenderer dtcr = new DefaultTableCellHeaderRenderer();

        dtcr.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < 4; i++) {
            tb.getColumnModel().getColumn(i).setCellRenderer(dtcr);
        }
    }

    public void alinharTbMedicos(JTable tb) {
        MedicoTableModel modeloMedico = new MedicoTableModel();

        DefaultTableCellRenderer dtcr = new DefaultTableCellHeaderRenderer();

        dtcr.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < 4; i++) {
            tb.getColumnModel().getColumn(i).setCellRenderer(dtcr);
        }
    }

    /*========================================================================================================================================*/

 /*LISTANDO TABELAS ==================================================================================*/
    public List<Admin> listarTbAdmin() throws SQLException {
        Admins = new ArrayList<>();
        lclinica = new ArrayList<>();
        ladmin = new ArrayList<>();

        conn = Banco.conecta();
        if (conn == null || conn.isClosed()) {
            System.out.println("erro ao conectar ao banco de dados");
            System.exit(-1);
        }

        String sql = "SELECT idadmin, nome, login, senha, adm FROM Admin";
        System.out.println("sql: " + sql);

        //atravez desse objeto usamos comandos sql
        Statement stmt = conn.createStatement();

        //select
        ResultSet res = stmt.executeQuery(sql);

        while (res.next()) {
            System.out.print("id: " + res.getInt("idadmin"));
            System.out.print("nome: " + res.getString("nome"));
            System.out.print("login: " + res.getString("login"));
            System.out.print("senha: " + res.getString("senha"));
            System.out.print("adm: " + res.getInt("adm"));

            Admin a = new Admin();
            a.setIdadmin(res.getInt("idadmin"));
            a.setNome(res.getString("nome"));
            a.setLogin(res.getString("login"));
            a.setSenha(res.getString("senha"));
            a.setAdm(res.getInt("adm"));

            Admins.add(a);
        }
        stmt.close();
        conn.close();
        return Admins;

    }

    public List<Clinica> listarTbClinica() throws SQLException {
        Clinicas = new ArrayList<>();

        conn = Banco.conecta();
        if (conn == null || conn.isClosed()) {
            System.out.println("erro ao conectar ao banco de dados");
            System.exit(-1);
        }

        String sql = "SELECT idclinica, nome, cnpj, cidadeclinica FROM Clinica";
        System.out.println("sql: " + sql);

        //atravez desse objeto usamos comandos sql
        Statement stmt = conn.createStatement();

        //select
        ResultSet res = stmt.executeQuery(sql);

        while (res.next()) {
            System.out.print("id: " + res.getInt("idclinica"));
            System.out.print("nome: " + res.getString("nome"));
            System.out.print("cnpj: " + res.getString("cnpj"));
            System.out.print("cidade clinica: " + res.getString("cidadeclinica"));

            Clinica a = new Clinica();;
            a.setIdclinica(res.getInt("idclinica"));
            a.setNome(res.getString("nome"));
            a.setCnpj(res.getString("cnpj"));
            a.setCidadeclinica(res.getString("cidadeclinica"));

            Clinicas.add(a);
        }
        stmt.close();
        conn.close();
        return Clinicas;

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

    /*==========================================================================================================================================*/

 /*LISTAR CLINICA E ADMIN COMBO BOX =============================================================================================================*/
    public List<Admin> listarAdmins() throws SQLException {

        List<Admin> Admins = new ArrayList<>();

        conn = Banco.conecta();
        if (conn == null || conn.isClosed()) {
            System.out.println("erro ao conectar ao banco de dados");
            System.exit(-1);
        }

        String sql = "SELECT idadmin, nome,login, senha, adm FROM admin";

        System.out.println("sql: " + sql);
        Statement stmt = conn.createStatement();

        //retorna um conjunto de dados , sempre q for fazer insert usar o executeUpdate
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println("Id:  " + res.getInt("idadmin"));
            System.out.println("Nome:  " + res.getString("nome"));
            System.out.println("Login:  " + res.getString("login"));
            System.out.println("Senha:  " + res.getString("senha"));
            System.out.println("Adm:  " + res.getInt("adm"));

            Admin b = new Admin();
            b.setIdadmin(res.getInt("idadmin"));
            b.setNome(res.getString("nome"));
            b.setLogin(res.getString("login"));
            b.setSenha(res.getString("senha"));
            b.setAdm(res.getInt("adm"));

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

    /*==========================================================================================================================*/
 /*INICIALIZANDO COMPONENTESS====================================================================================================================*/
    public CadastroAdm() {
        initComponents();
        setLocationRelativeTo(null);
        medicos = new ArrayList<>();
        lclinica = new ArrayList<>();
        ladmin = new ArrayList<>();

        try {
            Admins = listarTbAdmin();
        } catch (SQLException ex) {
            Logger.getLogger(CadastroAdm.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Clinicas = listarTbClinica();
        } catch (SQLException ex) {
            Logger.getLogger(CadastroAdm.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            medicos = listarTbMedico();
        } catch (SQLException ex) {
            Logger.getLogger(CadastroAdm.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            lclinica = listarClinicas();
        } catch (SQLException ex) {
            Logger.getLogger(CadastroAdm.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ladmin = listarAdmins();
        } catch (SQLException ex) {
            Logger.getLogger(CadastroAdm.class.getName()).log(Level.SEVERE, null, ex);
        }

        AdminTableModel modeloAdmin = new AdminTableModel();
        modeloAdmin.setListaAdmins(Admins);

        tbAdmin.setModel(modeloAdmin);

        ClinicaTableModel modeloClinica = new ClinicaTableModel();
        modeloClinica.setListaClinicas(Clinicas);

        tbClinica.setModel(modeloClinica);
        alinharTbClinicas(tbClinica);

        //  alinharTbAdmins(tbAdmin);
        MedicoTableModel modeloMedico = new MedicoTableModel();
        modeloMedico.setListamedicos(medicos);

        tbMedico.setModel(modeloMedico);

        for (int i = 0; i < lclinica.size(); i++) {
            combClinicaMedico.addItem(lclinica.get(i));

        }

        for (int i = 0; i < ladmin.size(); i++) {
            combAdminMedico.addItem(ladmin.get(i));

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
        txtDataNascimentoMedico.setFormats("dd/MM/yyyy");
        txtDataNascimentoMedico.setDate(Calendar.getInstance().getTime());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        clinica = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbClinica = new javax.swing.JTable();
        btnExcluirClinica = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnEditarClinica = new javax.swing.JButton();
        txtNomeClinica = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCnpjClinica = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCidadeClinica = new javax.swing.JTextField();
        btnCadastrarClinica = new javax.swing.JButton();
        medico = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtDataNascimentoMedico = new org.jdesktop.swingx.JXDatePicker();
        combAdminMedico = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        btnExcluirMedico = new javax.swing.JButton();
        txtCrmMedico = new javax.swing.JTextField();
        btnEditarMedico = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        combClinicaMedico = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMedico = new javax.swing.JTable();
        txtNomeMedico = new javax.swing.JTextField();
        jButton1Medico = new javax.swing.JButton();
        txtCpfMedico = new javax.swing.JFormattedTextField();
        admin = new javax.swing.JPanel();
        txtNomeAdmin = new javax.swing.JTextField();
        txtLoginAdmin = new javax.swing.JTextField();
        txtSenhaAdmin = new javax.swing.JTextField();
        btnCadastrarAdmin = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbAdmin = new javax.swing.JTable();
        btnExcluirAdmin = new javax.swing.JButton();
        btnEditarAdmin = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        chkAdm = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        clinica.setBackground(new java.awt.Color(255, 255, 255));
        clinica.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Cadastro de Clínicas");

        tbClinica.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tbClinica);

        btnExcluirClinica.setText("Excluir");
        btnExcluirClinica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirClinicaActionPerformed(evt);
            }
        });

        jLabel5.setText("Nome:");

        btnEditarClinica.setText("Editar");
        btnEditarClinica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarClinicaActionPerformed(evt);
            }
        });

        txtNomeClinica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeClinicaActionPerformed(evt);
            }
        });

        jLabel6.setText("CNPJ:");

        txtCnpjClinica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCnpjClinicaActionPerformed(evt);
            }
        });

        jLabel7.setText("Cidade:");

        btnCadastrarClinica.setText("Cadastrar");
        btnCadastrarClinica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarClinicaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout clinicaLayout = new javax.swing.GroupLayout(clinica);
        clinica.setLayout(clinicaLayout);
        clinicaLayout.setHorizontalGroup(
            clinicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clinicaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(clinicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                    .addGroup(clinicaLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCidadeClinica))
                    .addComponent(btnCadastrarClinica)
                    .addComponent(jLabel4)
                    .addGroup(clinicaLayout.createSequentialGroup()
                        .addComponent(btnExcluirClinica)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 454, Short.MAX_VALUE)
                        .addComponent(btnEditarClinica))
                    .addGroup(clinicaLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomeClinica))
                    .addGroup(clinicaLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCnpjClinica)))
                .addContainerGap())
        );
        clinicaLayout.setVerticalGroup(
            clinicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clinicaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(19, 19, 19)
                .addGroup(clinicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNomeClinica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(clinicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCnpjClinica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(clinicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCidadeClinica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(clinicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluirClinica)
                    .addComponent(btnEditarClinica))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCadastrarClinica)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("<html>\n<b>Clínica</b>\n</html>", new javax.swing.ImageIcon(getClass().getResource("/banco/IMG/iconfinder_hospital-o_1608931 (1).png")), clinica, "Cadastre Clinicas\n"); // NOI18N

        medico.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setText("Admin:");

        txtDataNascimentoMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataNascimentoMedicoActionPerformed(evt);
            }
        });

        combAdminMedico.setToolTipText("");

        jLabel10.setText("CRM:");

        btnExcluirMedico.setText("Excluir");
        btnExcluirMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirMedicoActionPerformed(evt);
            }
        });

        btnEditarMedico.setText("Editar");
        btnEditarMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarMedicoActionPerformed(evt);
            }
        });

        jLabel11.setText("CPF:");

        jLabel12.setText("Clinica:");

        combClinicaMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combClinicaMedicoActionPerformed(evt);
            }
        });

        jLabel13.setText("Nome:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Cadastro de Médicos");

        jLabel15.setText("Data de Nascimento:");

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

        txtNomeMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeMedicoActionPerformed(evt);
            }
        });

        jButton1Medico.setText("Cadastrar");
        jButton1Medico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1MedicoActionPerformed(evt);
            }
        });

        txtCpfMedico.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        txtCpfMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCpfMedicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout medicoLayout = new javax.swing.GroupLayout(medico);
        medico.setLayout(medicoLayout);
        medicoLayout.setHorizontalGroup(
            medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(medicoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, medicoLayout.createSequentialGroup()
                        .addComponent(btnExcluirMedico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEditarMedico))
                    .addGroup(medicoLayout.createSequentialGroup()
                        .addGroup(medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(medicoLayout.createSequentialGroup()
                                    .addGroup(medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel12))
                                    .addGap(76, 76, 76)
                                    .addComponent(combAdminMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(combClinicaMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1Medico)
                                    .addGroup(medicoLayout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtDataNascimentoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, medicoLayout.createSequentialGroup()
                        .addGroup(medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(medicoLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(medicoLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(9, 9, 9)))
                        .addGroup(medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCrmMedico, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                            .addComponent(txtNomeMedico)))
                    .addGroup(medicoLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCpfMedico)))
                .addContainerGap())
        );
        medicoLayout.setVerticalGroup(
            medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(medicoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtNomeMedico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtCrmMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtCpfMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDataNascimentoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(combClinicaMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combAdminMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(27, 27, 27)
                .addGroup(medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluirMedico)
                    .addComponent(btnEditarMedico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1Medico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("<html><b>Médico</b>", new javax.swing.ImageIcon(getClass().getResource("/banco/IMG/iconfinder_49_3678412.png")), medico, "Cadastre um Médico\n"); // NOI18N

        admin.setBackground(new java.awt.Color(255, 255, 255));

        txtNomeAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeAdminActionPerformed(evt);
            }
        });

        txtLoginAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoginAdminActionPerformed(evt);
            }
        });

        btnCadastrarAdmin.setText("Cadastrar");
        btnCadastrarAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarAdminActionPerformed(evt);
            }
        });

        tbAdmin.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbAdmin);

        btnExcluirAdmin.setText("Excluir");
        btnExcluirAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirAdminActionPerformed(evt);
            }
        });

        btnEditarAdmin.setText("Editar");
        btnEditarAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarAdminActionPerformed(evt);
            }
        });

        jLabel1.setText("Nome:");

        jLabel2.setText("Login:");

        jLabel3.setText("Senha:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Cadastro de Admins");

        chkAdm.setText("ADMINISTRADOR?");
        chkAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAdmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout adminLayout = new javax.swing.GroupLayout(admin);
        admin.setLayout(adminLayout);
        adminLayout.setHorizontalGroup(
            adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(adminLayout.createSequentialGroup()
                        .addGroup(adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                            .addGroup(adminLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNomeAdmin))
                            .addGroup(adminLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSenhaAdmin))
                            .addGroup(adminLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtLoginAdmin))
                            .addGroup(adminLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(adminLayout.createSequentialGroup()
                                .addComponent(btnExcluirAdmin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEditarAdmin)))
                        .addContainerGap())
                    .addGroup(adminLayout.createSequentialGroup()
                        .addComponent(btnCadastrarAdmin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chkAdm)
                        .addGap(85, 85, 85))))
        );
        adminLayout.setVerticalGroup(
            adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(21, 21, 21)
                .addGroup(adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNomeAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLoginAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSenhaAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluirAdmin)
                    .addComponent(btnEditarAdmin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrarAdmin)
                    .addComponent(chkAdm))
                .addGap(47, 47, 47)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("<html><b>Admin\n", new javax.swing.ImageIcon(getClass().getResource("/banco/IMG/iconfinder_ajax-admin_3018587.png")), admin); // NOI18N

        getContentPane().add(jTabbedPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1MedicoActionPerformed
        conn = Banco.conecta();
        String sql = "";

        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("erro ao conectar ao banco de dados");
                System.exit(-1);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date aux = txtDataNascimentoMedico.getDate();

            String nome = "'" + txtNomeMedico.getText() + "'";
            String crm = "'" + txtCrmMedico.getText() + "'";
            String cpf = "'" + txtCpfMedico.getText() + "'";

            String dataNasc = "'" + sdf.format(aux) + "'";

            Statement stmt = conn.createStatement();

            sql = "INSERT INTO Medico (nome,crm, cpf, datanascimento, idclinica, idadmin ) VALUES ("
                    + "" + nome + "," + crm + "," + cpf + "," + dataNasc + "," + ((Clinica) combClinicaMedico.getSelectedItem()).getIdclinica() + ","
                    + " " + ((Admin) combAdminMedico.getSelectedItem()).getIdadmin() + ")";
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
            txtNomeMedico.setText(Mostrar);
            txtCpfMedico.setText(Mostrar);
            txtCrmMedico.setText(Mostrar);

            medicos = listarTbMedico();

            MedicoTableModel modelo = new MedicoTableModel();
            modelo.setListamedicos(medicos);

            tbMedico.setModel(modelo);

            //   alinharTbMedicos(tbMedico);
        } catch (SQLException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1MedicoActionPerformed

    private void txtNomeMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeMedicoActionPerformed

    }//GEN-LAST:event_txtNomeMedicoActionPerformed

    private void combClinicaMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combClinicaMedicoActionPerformed

    }//GEN-LAST:event_combClinicaMedicoActionPerformed

    private void btnEditarMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarMedicoActionPerformed
        conn = Banco.conecta();

        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("erro ao conectar ao banco de dados");
                System.exit(-1);

            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date aux = txtDataNascimentoMedico.getDate();

            String nome = "'" + txtNomeMedico.getText() + "'";
            String crm = "'" + txtCrmMedico.getText() + "'";
            String cpf = "'" + txtCpfMedico.getText() + "'";
            String dataNasc = "'" + sdf.format(aux) + "'";

            int k = tbMedico.getSelectedRow();
            // System.out.println(k);
            int i = ((MedicoTableModel) tbMedico.getModel()).getListamedicos().get(k).getIdmedico();

            Statement stmt = conn.createStatement();
            String sql = "UPDATE medico SET nome = " + nome + ",crm = " + crm + ",cpf = " + cpf + ",datanascimento = " + dataNasc + "WHERE idmedico = " + i + "";
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();

            JOptionPane.showMessageDialog(null, "Médico(a) Editado!");

            String Mostrar = " ";
            txtNomeMedico.setText(Mostrar);
            txtCpfMedico.setText(Mostrar);
            txtCrmMedico.setText(Mostrar);

            medicos = listarTbMedico();
            MedicoTableModel modelo = new MedicoTableModel();
            modelo.setListamedicos(medicos);
            tbMedico.setModel(modelo);

        } catch (SQLException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEditarMedicoActionPerformed

    private void btnExcluirMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirMedicoActionPerformed

        conn = Banco.conecta();

        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("erro ao conectar ao banco de dados");
                System.exit(-1);

            }

            int k = tbMedico.getSelectedRow();
            if( k == -1){
            JOptionPane.showMessageDialog(null, "Selecione um Admin para deletar");
            }else{
            // System.out.println(k);
            int i = ((MedicoTableModel) tbMedico.getModel()).getListamedicos().get(k).getIdmedico();

            Statement stmt = conn.createStatement();
            String sql = "Delete from medico where idmedico = " + i + " ";
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();

            JOptionPane.showMessageDialog(null, "Médico(a) Apagado!");

            String Mostrar = " ";
            txtNomeMedico.setText(Mostrar);
            txtCpfMedico.setText(Mostrar);
            txtCrmMedico.setText(Mostrar);

            medicos = listarTbMedico();
            MedicoTableModel modelo = new MedicoTableModel();
            modelo.setListamedicos(medicos);
            tbMedico.setModel(modelo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExcluirMedicoActionPerformed

    private void txtDataNascimentoMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataNascimentoMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataNascimentoMedicoActionPerformed

    @SuppressWarnings("empty-statement")
    private void btnEditarAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarAdminActionPerformed
        conn = Banco.conecta();

        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("erro ao conectar ao banco de dados");
                System.exit(-1);

            }

            String nome = "'" + txtNomeAdmin.getText() + "'";
            String login = "'" + txtLoginAdmin.getText() + "'";
            String senha = "'" + txtSenhaAdmin.getText() + "'";
            Integer adm = 0;
            if (chkAdm.isSelected()) {
                adm = 1;
            } else {
                adm = 0;
            };

            int k = tbAdmin.getSelectedRow();
            // System.out.println(k);

            int i = ((AdminTableModel) tbAdmin.getModel()).getListaAdmins().get(k).getIdadmin();

            try (Statement stmt = conn.createStatement()) {
                String sql = "UPDATE admin SET nome = " + nome + ",login = " + login + ",senha = " + senha + ",adm = " + adm + " WHERE idadmin = " + i + "";
                stmt.executeUpdate(sql);
            }
            conn.close();

            JOptionPane.showMessageDialog(null, "Admin Editado!");

            String Mostrar = " ";
            txtNomeAdmin.setText(Mostrar);
            txtLoginAdmin.setText(Mostrar);
            txtSenhaAdmin.setText(Mostrar);

            Admins = listarTbAdmin();
            AdminTableModel modelo = new AdminTableModel();
            modelo.setListaAdmins(Admins);
            tbAdmin.setModel(modelo);

            combAdminMedico.removeAllItems();

            ladmin = listarAdmins();

            for (int i2 = 0; i2 < ladmin.size(); i2++) {
                combAdminMedico.addItem(ladmin.get(i2));

            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEditarAdminActionPerformed

    private void btnExcluirAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirAdminActionPerformed
        conn = Banco.conecta();

        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("erro ao conectar ao banco de dados");
                System.exit(-1);

            }

            int k = tbAdmin.getSelectedRow();
            if( k == -1){
            JOptionPane.showMessageDialog(null, "Selecione um Admin para deletar");
            }else{
            
            
            // System.out.println(k);
            int i = ((AdminTableModel) tbAdmin.getModel()).getListaAdmins().get(k).getIdadmin();
            

            Statement stmt = conn.createStatement();
            if(i == 1){
                JOptionPane.showMessageDialog(null, "Nao é Possivel apagar o Admin Nativo!");
            }else{
                
            String sql = "Delete from admin where idadmin = " + i + " ";
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Admin Apagado!");
            
            }
            

            stmt.close();
            conn.close();

            

            String Mostrar = " ";
            txtNomeAdmin.setText(Mostrar);
            txtLoginAdmin.setText(Mostrar);
            txtSenhaAdmin.setText(Mostrar);

            Admins = listarTbAdmin();
            AdminTableModel modelo = new AdminTableModel();
            modelo.setListaAdmins(Admins);
            tbAdmin.setModel(modelo);

            combAdminMedico.removeAllItems();
            ladmin = listarAdmins();

            for (int i3 = 0; i3 < ladmin.size(); i3++) {
                combAdminMedico.addItem(ladmin.get(i3));

            }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExcluirAdminActionPerformed

    @SuppressWarnings("empty-statement")
    private void btnCadastrarAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarAdminActionPerformed

        conn = Banco.conecta();
        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("erro ao conectar ao banco de dados");
                System.exit(-1);
            }

            String nome = "'" + txtNomeAdmin.getText() + "'";
            String login = "'" + txtLoginAdmin.getText() + "'";
            String senha = "'" + txtSenhaAdmin.getText() + "'";
            Integer adm = null;
            if (chkAdm.isSelected()) {
                adm = 1;
            } else {
                adm = 0;
            };

            String sql = "INSERT INTO admin(nome, login, senha, adm) VALUES ("
                    + "" + nome + "," + login + "," + senha + "," + adm + ")";
            System.out.println("sql: " + sql);

            //atravez desse objeto usamos comandos sql
            Statement stmt = conn.createStatement();

            //select
            //stmt.executeQuery(sql);
            //retorna um conjunto de dados , sempre q for fazer insert usar o executeUpdate : inset, update, delete
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Admin" + nome + "cadastrado!!");

            //encerrou a conexão
            stmt.close();
            conn.close();

            String Mostrar = " ";
            txtNomeAdmin.setText(Mostrar);
            txtLoginAdmin.setText(Mostrar);
            txtSenhaAdmin.setText(Mostrar);

            Admins = listarTbAdmin();

            AdminTableModel modelo = new AdminTableModel();
            modelo.setListaAdmins(Admins);

            tbAdmin.setModel(modelo);

            for (int i = 0; i < ladmin.size(); i++) {
                combAdminMedico.addItem(ladmin.get(i));

            }
            combAdminMedico.removeAllItems();
            ladmin = listarAdmins();

            for (int i = 0; i < ladmin.size(); i++) {
                combAdminMedico.addItem(ladmin.get(i));

            }

        } catch (SQLException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCadastrarAdminActionPerformed

    private void txtLoginAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoginAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoginAdminActionPerformed

    private void txtNomeAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeAdminActionPerformed

    private void btnCadastrarClinicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarClinicaActionPerformed
        conn = Banco.conecta();
        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("erro ao conectar ao banco de dados");
                System.exit(-1);
            }

            String nome = "'" + txtNomeClinica.getText() + "'";
            String cnpj = "'" + txtCnpjClinica.getText() + "'";
            String cidade = "'" + txtCidadeClinica.getText() + "'";

            String sql = "INSERT INTO Clinica (nome, cnpj, cidadeclinica) VALUES ("
                    + "" + nome + "," + cnpj + "," + cidade + ")";
            System.out.println("sql: " + sql);

            //atravez desse objeto usamos comandos sql
            Statement stmt = conn.createStatement();

            //select
            //stmt.executeQuery(sql);
            //retorna um conjunto de dados , sempre q for fazer insert usar o executeUpdate : inset, update, delete
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Clínica  " + nome + "  cadastrada!!");

            //encerrou a conexão
            stmt.close();
            conn.close();

            String Mostrar = " ";
            txtNomeClinica.setText(Mostrar);
            txtCnpjClinica.setText(Mostrar);
            txtCidadeClinica.setText(Mostrar);

            Clinicas = listarTbClinica();

            ClinicaTableModel modelo = new ClinicaTableModel();
            modelo.setListaClinicas(Clinicas);

            tbClinica.setModel(modelo);

            combClinicaMedico.removeAllItems();

            lclinica = listarClinicas();

            for (int i = 0; i < lclinica.size(); i++) {
                combClinicaMedico.addItem(lclinica.get(i));

            }

        } catch (SQLException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCadastrarClinicaActionPerformed

    private void txtCnpjClinicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCnpjClinicaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCnpjClinicaActionPerformed

    private void txtNomeClinicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeClinicaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeClinicaActionPerformed

    private void btnEditarClinicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarClinicaActionPerformed

        conn = Banco.conecta();

        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("erro ao conectar ao banco de dados");
                System.exit(-1);

            }

            String nome = "'" + txtNomeAdmin.getText() + "'";
            String cnpj = "'" + txtCnpjClinica.getText() + "'";
            String cidade = "'" + txtCidadeClinica.getText() + "'";

            int k = tbClinica.getSelectedRow();
            // System.out.println(k);

            int i = ((ClinicaTableModel) tbClinica.getModel()).getListaclinicas().get(k).getIdclinica();

            Statement stmt = conn.createStatement();
            String sql = "UPDATE clinica SET nome = " + nome + ",cnpj = " + cnpj + ",cidadeclinica = " + cidade + " WHERE idclinica = " + i + "";
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();

            JOptionPane.showMessageDialog(null, "Clinica Editada!");

            String Mostrar = " ";
            txtNomeAdmin.setText(Mostrar);
            txtCnpjClinica.setText(Mostrar);
            txtCidadeClinica.setText(Mostrar);

            Clinicas = listarTbClinica();
            ClinicaTableModel modelo = new ClinicaTableModel();
            modelo.setListaClinicas(Clinicas);
            tbClinica.setModel(modelo);

            combClinicaMedico.removeAllItems();

            lclinica = listarClinicas();

            for (int i2 = 0; i2 < lclinica.size(); i2++) {
                combClinicaMedico.addItem(lclinica.get(i2));

            }

        } catch (SQLException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEditarClinicaActionPerformed

    private void btnExcluirClinicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirClinicaActionPerformed
        conn = Banco.conecta();

        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("erro ao conectar ao banco de dados");
                System.exit(-1);

            }

            int k = tbClinica.getSelectedRow();
            if( k == -1){
            JOptionPane.showMessageDialog(null, "Selecione um Admin para deletar");
            }else{
            // System.out.println(k);
            int i = ((ClinicaTableModel) tbClinica.getModel()).getListaclinicas().get(k).getIdclinica();

            Statement stmt = conn.createStatement();
            String sql = "Delete from clinica where idclinica = " + i + " ";
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();

            JOptionPane.showMessageDialog(null, "Clinica Apagada!");

            String Mostrar = " ";
            txtNomeAdmin.setText(Mostrar);
            txtCnpjClinica.setText(Mostrar);
            txtCidadeClinica.setText(Mostrar);

            Clinicas = listarTbClinica();
            ClinicaTableModel modelo = new ClinicaTableModel();
            modelo.setListaClinicas(Clinicas);
            tbClinica.setModel(modelo);

            combClinicaMedico.removeAllItems();

            lclinica = listarClinicas();

            for (int i2 = 0; i2 < lclinica.size(); i2++) {
                combClinicaMedico.addItem(lclinica.get(i2));

            }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExcluirClinicaActionPerformed

    private void chkAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAdmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkAdmActionPerformed

    private void txtCpfMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCpfMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCpfMedicoActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroAdm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel admin;
    private javax.swing.JButton btnCadastrarAdmin;
    private javax.swing.JButton btnCadastrarClinica;
    private javax.swing.JButton btnEditarAdmin;
    private javax.swing.JButton btnEditarClinica;
    private javax.swing.JButton btnEditarMedico;
    private javax.swing.JButton btnExcluirAdmin;
    private javax.swing.JButton btnExcluirClinica;
    private javax.swing.JButton btnExcluirMedico;
    private javax.swing.JCheckBox chkAdm;
    private javax.swing.JPanel clinica;
    private javax.swing.JComboBox<Admin> combAdminMedico;
    private javax.swing.JComboBox<Clinica> combClinicaMedico;
    private javax.swing.JButton jButton1Medico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel medico;
    private javax.swing.JTable tbAdmin;
    private javax.swing.JTable tbClinica;
    private javax.swing.JTable tbMedico;
    private javax.swing.JTextField txtCidadeClinica;
    private javax.swing.JTextField txtCnpjClinica;
    private javax.swing.JFormattedTextField txtCpfMedico;
    private javax.swing.JTextField txtCrmMedico;
    private org.jdesktop.swingx.JXDatePicker txtDataNascimentoMedico;
    private javax.swing.JTextField txtLoginAdmin;
    private javax.swing.JTextField txtNomeAdmin;
    private javax.swing.JTextField txtNomeClinica;
    private javax.swing.JTextField txtNomeMedico;
    private javax.swing.JTextField txtSenhaAdmin;
    // End of variables declaration//GEN-END:variables

}
