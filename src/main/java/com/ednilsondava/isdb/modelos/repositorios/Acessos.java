package com.ednilsondava.isdb.modelos.repositorios;

import com.ednilsondava.isdb.modelos.entidades.Acesso;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class Acessos {
    @Inject
    private EntityManager manager;

    public Acesso encontrar(String tipo) {
        try {
            return manager.find(Acesso.class, tipo);
        } catch (Exception e) {
            return null;
        }
    }
}
