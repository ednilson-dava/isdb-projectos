package com.ednilsondava.isdb.modelos.repositorios;

import com.ednilsondava.isdb.modelos.entidades.Avaliacao;
import com.ednilsondava.isdb.modelos.entidades.Avaliacao_;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Avaliacoes implements Serializable {
    @Inject
    private EntityManager manager;

    public void salva(Avaliacao avaliacao) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(avaliacao);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public List<Integer> encontrarAnoLectivos() {
        try {
            List<Integer> anosL = new ArrayList<>();
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Avaliacao> criteria = builder.createQuery(Avaliacao.class);
            Root<Avaliacao> root = criteria.from(Avaliacao.class);
            criteria.select(builder.construct(Avaliacao.class, root.get(Avaliacao_.avaliadoEm))).distinct(true);
            List<Avaliacao> q = manager.createQuery(criteria).getResultList();
            for (Avaliacao a : q) {
                anosL.add(a.getAvaliadoEm().get(Calendar.YEAR));
            }
            return anosL;
        } catch (Exception e) {
            return null;
        }
    }

    public void actualizar(Avaliacao avaliacao) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.merge(avaliacao);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public List<Avaliacao> todos() {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Avaliacao> criteria = builder.createQuery(Avaliacao.class);
            Root<Avaliacao> root = criteria.from(Avaliacao.class);
            criteria.select(root);
            TypedQuery<Avaliacao> query = manager.createQuery(criteria);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public Long total() {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Avaliacao> criteria = builder.createQuery(Avaliacao.class);
            Root<Avaliacao> root = criteria.from(Avaliacao.class);
            criteria.select(builder.construct(Avaliacao.class, root.get(Avaliacao_.id)));
            return manager.createQuery(criteria).getResultStream().count();
        } catch (Exception e) {
            return 0L;
        }
    }
}
