package com.ednilsondava.isdb.modelos.repositorios;

import com.ednilsondava.isdb.modelos.entidades.Estudante;
import com.ednilsondava.isdb.modelos.entidades.Estudante_;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Estudantes implements Serializable {
    @Inject
    private EntityManager manager;

    public void salva(Estudante estudante) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            estudante.setUsuario(manager.merge(estudante.getUsuario()));
            estudante.setCurso(manager.merge(estudante.getCurso()));
            manager.persist(estudante);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public void apagar(Estudante estudante) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.merge(estudante);
            manager.remove(estudante);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public Estudante encontrar(Long id) {
        try {
            return manager.find(Estudante.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    public Estudante encontrarByCodigo(String codigo) {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Estudante> criteria = builder.createQuery(Estudante.class);
            Root<Estudante> root = criteria.from(Estudante.class);
            criteria.where(builder.equal(root.get(Estudante_.codigo), codigo));
            criteria.select(root);
            TypedQuery<Estudante> query = manager.createQuery(criteria);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Estudante> todos() {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Estudante> criteria = builder.createQuery(Estudante.class);
            Root<Estudante> root = criteria.from(Estudante.class);
            criteria.select(root);
            TypedQuery<Estudante> query = manager.createQuery(criteria);
            return query.getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Long total() {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Estudante> criteria = builder.createQuery(Estudante.class);
            Root<Estudante> root = criteria.from(Estudante.class);
            criteria.select(builder.construct(Estudante.class, root.get(Estudante_.id)));
            return manager.createQuery(criteria).getResultStream().count();
        } catch (Exception e) {
            return 0L;
        }
    }
}
