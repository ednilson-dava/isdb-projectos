package com.ednilsondava.isdb.modelos.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Classificacao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TipoClassificacao tipo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoClassificacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoClassificacao tipo) {
        this.tipo = tipo;
    }
}
