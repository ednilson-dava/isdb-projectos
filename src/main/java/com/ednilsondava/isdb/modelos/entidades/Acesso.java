package com.ednilsondava.isdb.modelos.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Acesso {
    @Id
    private String tipo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
