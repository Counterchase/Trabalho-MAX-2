/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.Model;

/**
 *
 * @author IFMS
 */
public class Clinica {

    private Integer idclinica;
    private String nome;
    private String cnpj;
    private String cidadeclinica;

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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCidadeclinica() {
        return cidadeclinica;
    }

    public void setCidadeclinica(String cidadeclinica) {
        this.cidadeclinica = cidadeclinica;
    }

    @Override
    public String toString() {
        return getNome();
    }
    
}
