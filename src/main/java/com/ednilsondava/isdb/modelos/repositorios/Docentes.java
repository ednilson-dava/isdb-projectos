package com.ednilsondava.isdb.modelos.repositorios;

import com.ednilsondava.isdb.modelos.entidades.Docente;
import com.ednilsondava.isdb.modelos.entidades.Docente_;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Docentes {
    @Inject
    private EntityManager manager;

    public void salvar(Docente docente) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(docente);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public Docente encontrar(Long id) {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Docente> criteria = builder.createQuery(Docente.class);
            Root<Docente> root = criteria.from(Docente.class);
            criteria.where(builder.equal(root.get(Docente_.id), id));
            criteria.select(root);
            TypedQuery<Docente> query = manager.createQuery(criteria);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Docente encontrarByNomeAndApelido(String nome, String apelido) {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Docente> criteria = builder.createQuery(Docente.class);
            Root<Docente> root = criteria.from(Docente.class);
            criteria.where(builder.and(builder.equal(root.get(Docente_.nome), nome), builder.equal(root.get(Docente_.apelido), apelido)));
            criteria.select(root);
            TypedQuery<Docente> query = manager.createQuery(criteria);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Docente> todos() {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Docente> criteria = builder.createQuery(Docente.class);
            Root<Docente> root = criteria.from(Docente.class);
            criteria.select(root);
            TypedQuery<Docente> query = manager.createQuery(criteria);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public Long total() {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Docente> criteria = builder.createQuery(Docente.class);
            Root<Docente> root = criteria.from(Docente.class);
            criteria.select(builder.construct(Docente.class, root.get(Docente_.id)));
            return manager.createQuery(criteria).getResultStream().count();
        } catch (Exception e) {
            return 0L;
        }
    }
}
