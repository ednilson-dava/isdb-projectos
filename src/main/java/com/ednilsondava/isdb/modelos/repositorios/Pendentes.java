package com.ednilsondava.isdb.modelos.repositorios;

import com.ednilsondava.isdb.modelos.entidades.Estudante;
import com.ednilsondava.isdb.modelos.entidades.Pendente;
import com.ednilsondava.isdb.modelos.entidades.Pendente_;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public class Pendentes implements Serializable {
    @Inject
    private EntityManager manager;

    public void salva(Pendente pendente) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(pendente);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public void actualiza(Pendente pendente) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.merge(pendente);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public List<Pendente> encontrarPorEstudante(Estudante estudante) {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Pendente> criteria = builder.createQuery(Pendente.class);
            Root<Pendente> root = criteria.from(Pendente.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get(Pendente_.estudante), estudante));
            TypedQuery<Pendente> query = manager.createQuery(criteria);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public void apagar(Pendente pendente) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.merge(pendente);
            manager.remove(pendente);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public List<Pendente> todos() {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Pendente> criteria = builder.createQuery(Pendente.class);
            Root<Pendente> root = criteria.from(Pendente.class);
            criteria.select(root);
            TypedQuery<Pendente> query = manager.createQuery(criteria);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
