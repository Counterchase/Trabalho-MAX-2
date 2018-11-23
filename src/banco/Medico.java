/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.util.Calendar;

/**
 *
 * @author IFMS
 */
public class Medico {

    private String nome;
    private Integer idmedico;
    private String crm;
    private String cpf;
    private Calendar datanascimento;
    private Integer idclinica;
    private Integer idadmin;

    public Integer getIdmedico() {
        return idmedico;
    }

    public void setIdmedico(Integer idmedico) {
        this.idmedico = idmedico;
    }

    public Calendar getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(Calendar datanascimento) {
        this.datanascimento = datanascimento;
    }

    public Integer getIdclinica() {
        return idclinica;
    }

    public void setIdclinica(Integer idclinica) {
        this.idclinica = idclinica;
    }

    public Integer getIdadmin() {
        return idadmin;
    }

    public void setIdadmin(Integer idadmin) {
        this.idadmin = idadmin;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Calendar getData_nascimento() {
        return datanascimento;
    }

    public void setData_nascimento(Calendar data_nascimento) {
        this.datanascimento = datanascimento;
    }

}
