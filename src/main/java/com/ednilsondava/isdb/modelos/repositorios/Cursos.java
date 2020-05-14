package com.ednilsondava.isdb.modelos.repositorios;

import com.ednilsondava.isdb.modelos.entidades.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

public class Cursos implements Serializable {
    @Inject
    private EntityManager manager;

    public void salva(Curso curso) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            if (curso.getModulos() != null)
                for (int i = 0; i < curso.getModulos().size(); i++) {
                    curso.getModulos().set(i, this.manager.merge(curso.getModulos().get(i)));
                }
            if (curso.getEstudantes() != null)
                for (int i = 0; i < curso.getEstudantes().size(); i++) {
                    curso.getEstudantes().set(i, this.manager.merge(curso.getEstudantes().get(i)));
                }
            if (curso.getDocentes() != null)
                for (int i = 0; i < curso.getDocentes().size(); i++) {
                    curso.getDocentes().set(i, manager.merge(curso.getDocentes().get(i)));
                }
            manager.persist(curso);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public Curso encontrar(Long id) {
        try {
            return manager.find(Curso.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    public Curso encontrarByNome(String nome) {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Curso> criteria = builder.createQuery(Curso.class);
            Root<Curso> root = criteria.from(Curso.class);
            criteria.where(builder.equal(root.get(Curso_.nome), nome));
            criteria.select(root);
            TypedQuery<Curso> query = manager.createQuery(criteria);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Curso> todos() {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Curso> criteria = builder.createQuery(Curso.class);
            Root<Curso> root = criteria.from(Curso.class);
            criteria.select(root);
            TypedQuery<Curso> query = manager.createQuery(criteria);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Curso> encontrarByDepartamentoOnTemAvaliacao(Departamento departamento, int anoCurricular) {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Curso> criteria = builder.createQuery(Curso.class);
            Root<Curso> root = criteria.from(Curso.class);
            Join<Curso, Modulo> moduloJoin = root.join(Curso_.modulos);
            Join<Modulo, Avaliacao> avaliacaoJoin = moduloJoin.join(Modulo_.avaliacoes);
            avaliacaoJoin.on(builder.equal(avaliacaoJoin.get(Avaliacao_.completado), true), builder.between(avaliacaoJoin.get(Avaliacao_.avaliadoEm), new GregorianCalendar(anoCurricular, Calendar.FEBRUARY, 1), new GregorianCalendar(anoCurricular, Calendar.DECEMBER, 1)));
            criteria.select(builder.construct(Curso.class, root.get(Curso_.id), root.get(Curso_.nome))).distinct(true);
            criteria.where(builder.equal(root.get(Curso_.departamento), departamento));
            TypedQuery<Curso> query = manager.createQuery(criteria);
            return query.getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Long total() {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Curso> criteria = builder.createQuery(Curso.class);
            Root<Curso> root = criteria.from(Curso.class);
            criteria.select(builder.construct(Curso.class, root.get(Curso_.id)));
            return manager.createQuery(criteria).getResultStream().count();
        } catch (Exception e) {
            return 0L;
        }
    }

    public void actualizar(Curso curso) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.merge(curso);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public List<Curso> encontrarByDepartamento(Departamento departamento) {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Curso> criteria = builder.createQuery(Curso.class);
            Root<Curso> root = criteria.from(Curso.class);
            criteria.where(builder.equal(root.get(Curso_.departamento), departamento));
            TypedQuery<Curso> query = manager.createQuery(criteria);
            return query.getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
