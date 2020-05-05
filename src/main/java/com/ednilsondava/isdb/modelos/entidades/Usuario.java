package com.ednilsondava.isdb.modelos.entidades;

import javax.persistence.*;
import java.util.Calendar;
@Entity
public class Usuario {
    @Id
    private String nomeUsuario;
    private String senhaUsuario;
    private String email;
    @Temporal(TemporalType.DATE)
    private Calendar criadoEm;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinTable(name = "usuarios_acesso", joinColumns = @JoinColumn(name = "nomeUsuario", referencedColumnName = "nomeUsuario"), inverseJoinColumns = @JoinColumn(name = "tipo", referencedColumnName = "tipo"))
    private Acesso acesso;

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Calendar getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Calendar criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Acesso getAcesso() {
        return acesso;
    }

    public void setAcesso(Acesso acesso) {
        this.acesso = acesso;
    }
}
