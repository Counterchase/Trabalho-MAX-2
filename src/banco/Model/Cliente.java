/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.Model;

import java.util.Calendar;

/**
 *
 * @author IFMS
 */
public class Cliente {

    private String nome;
    private Integer idcliente;
    private String rg;
    private String cpf;
    private Calendar datanascimento;
    private Integer idclinica;
    private Integer idleito;
    private Integer idmedico;

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Integer getIdleito() {
        return idleito;
    }

    public void setIdleito(Integer idleito) {
        this.idleito = idleito;
    }

    public Integer getIdmedico() {
        return idmedico;
    }

    public void setIdmedico(Integer idmedico) {
        this.idmedico = idmedico;
    }

    public Integer getIdCliente() {
        return idcliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idcliente = idCliente;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
