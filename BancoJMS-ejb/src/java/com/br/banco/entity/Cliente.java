package com.br.banco.entity;

import java.io.Serializable;
import java.text.NumberFormat;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bruce
 */
@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByNroConta", query = "SELECT c FROM Cliente c WHERE c.nroConta = :nroConta"),
    @NamedQuery(name = "Cliente.findByNome", query = "SELECT c FROM Cliente c WHERE c.nome = :nome"),
    @NamedQuery(name = "Cliente.findBySaldo", query = "SELECT c FROM Cliente c WHERE c.saldo = :saldo"),
    @NamedQuery(name = "Cliente.findBySenha", query = "SELECT c FROM Cliente c WHERE c.senha = :senha")})
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nro_conta")
    private Integer nroConta;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "saldo")
    private double saldo;
    @Basic(optional = false)
    @Column(name = "senha")
    private String senha;

    public Cliente() {
    }

    public Cliente(Integer nroConta) {
        this.nroConta = nroConta;
    }

    public Cliente(Integer nroConta, String nome, double saldo, String senha) {
        this.nroConta = nroConta;
        this.nome = nome;
        this.saldo = saldo;
        this.senha = senha;
    }

    public Integer getNroConta() {
        return nroConta;
    }

    public void setNroConta(Integer nroConta) {
        this.nroConta = nroConta;
    }
    
    public boolean sacar(double qtde) {
        if (this.saldo >= qtde) {
            this.saldo -= qtde;
            return true;
        }
        return false;
    }
    
    public String getSaldoFormatado() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        return nf.format(this.saldo);
    }
    
    public boolean depositar(double qtde) {
        if (qtde > 0) {
            this.saldo += qtde;
            return true;
        }
        return false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nroConta != null ? nroConta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.nroConta == null && other.nroConta != null) || (this.nroConta != null && !this.nroConta.equals(other.nroConta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.banco.entity.Cliente[ nroConta=" + nroConta + " ]";
    }
    
}
