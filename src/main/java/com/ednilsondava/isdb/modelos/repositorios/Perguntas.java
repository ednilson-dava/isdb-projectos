package com.ednilsondava.isdb.modelos.repositorios;

import com.ednilsondava.isdb.modelos.entidades.Pergunta;
import com.ednilsondava.isdb.modelos.entidades.Pergunta_;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public class Perguntas implements Serializable {
    @Inject
    private EntityManager manager;

    public void salva(Pergunta pergunta) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(pergunta);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public Pergunta encontrar(Long id) {
        try {
            return manager.find(Pergunta.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    public Pergunta encontrarPorOrdem(Integer ordem) {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Pergunta> criteria = builder.createQuery(Pergunta.class);
            Root<Pergunta> root = criteria.from(Pergunta.class);
            criteria.where(builder.equal(root.get(Pergunta_.ordem), ordem));
            criteria.select(root);
            TypedQuery<Pergunta> query = manager.createQuery(criteria);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public void actualizar(Pergunta pergunta) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.merge(pergunta);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public List<Pergunta> todas() {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Pergunta> criteria = builder.createQuery(Pergunta.class);
            Root<Pergunta> root = criteria.from(Pergunta.class);
            criteria.select(root);
            TypedQuery<Pergunta> query = manager.createQuery(criteria);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
