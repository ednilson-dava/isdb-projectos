package com.ednilsondava.isdb.modelos.utilitarios;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class BancoDadosFabrica {
    private EntityManagerFactory factory;

    public BancoDadosFabrica() {
        factory = Persistence.createEntityManagerFactory("aval-docente-pu");
    }

    @Produces
    public EntityManager criarConexao() {
        return factory.createEntityManager();
    }

    public void terminarConexao(@Disposes EntityManager entityManager) {
        entityManager.close();
    }

    @PreDestroy
    public void destroiConexao() {
        factory.close();
    }
}
