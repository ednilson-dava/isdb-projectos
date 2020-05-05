package com.ednilsondava.isdb.modelos.repositorios;

import com.ednilsondava.isdb.modelos.entidades.ConfiguracaoGeral;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;

public class ConfiguracoesGeral implements Serializable {
    @Inject
    private EntityManager manager;

    public void salvar(ConfiguracaoGeral geral) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(geral);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public ConfiguracaoGeral encontrarUltima() {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<ConfiguracaoGeral> criteria = builder.createQuery(ConfiguracaoGeral.class);
            Root<ConfiguracaoGeral> root = criteria.from(ConfiguracaoGeral.class);
            criteria.select(root);
            criteria.orderBy(builder.desc(root.get("id")));
            TypedQuery<ConfiguracaoGeral> query = manager.createQuery(criteria);
            return query.getResultList().get(0);
        } catch (Exception e) {
            return null;
        }
    }
}
