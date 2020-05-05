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

public class Departamentos implements Serializable {
    @Inject
    private EntityManager manager;

    public void salvar(Departamento departamento) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            departamento.setCoordenador(manager.merge(departamento.getCoordenador()));
            if (departamento.getCursos() != null) {
                for (int i = 0; i < departamento.getCursos().size(); i++) {
                    departamento.getCursos().set(i, manager.merge(departamento.getCursos().get(i)));
                }
            }
            manager.persist(departamento);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public Departamento encontrar(Long id) {
        try {
            return manager.find(Departamento.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    public Departamento enccontrarByCoordenador(Docente docente) {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Departamento> criteria = builder.createQuery(Departamento.class);
            Root<Departamento> root = criteria.from(Departamento.class);
            criteria.where(builder.equal(root.get(Departamento_.coordenador), docente));
            criteria.select(root);
            TypedQuery<Departamento> query = manager.createQuery(criteria);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    public List<Departamento> encontrarByAnoLectivoOnAvaliacao(int anoLectivoAvaliacao) {
        Calendar calendarStart = new GregorianCalendar(anoLectivoAvaliacao, Calendar.FEBRUARY, 1);
        Calendar calendarEnd = new GregorianCalendar(anoLectivoAvaliacao, Calendar.DECEMBER, 1);

        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Departamento> criteria = builder.createQuery(Departamento.class);
            Root<Departamento> root = criteria.from(Departamento.class);

            Join<Departamento, Curso> cursoJoin = root.join(Departamento_.cursos);
            Join<Curso, Modulo> moduloJoin = cursoJoin.join(Curso_.modulos);
            Join<Modulo, Avaliacao> avaliacaoJoin = moduloJoin.join(Modulo_.avaliacoes);
            avaliacaoJoin.on(builder.between(avaliacaoJoin.get(Avaliacao_.avaliadoEm), calendarStart, calendarEnd), builder.equal(avaliacaoJoin.get(Avaliacao_.completado), true));

            criteria.select(builder.construct(Departamento.class, root.get(Departamento_.id), root.get(Departamento_.nome))).distinct(true);
            TypedQuery<Departamento> query = manager.createQuery(criteria);
            return query.getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public void actualizar(Departamento departamento) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.merge(departamento);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public List<Departamento> todos() {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Departamento> criteria = builder.createQuery(Departamento.class);
            Root<Departamento> root = criteria.from(Departamento.class);
            criteria.select(root);
            TypedQuery<Departamento> query = manager.createQuery(criteria);
            return query.getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Long numeroTotal() {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Departamento> criteria = builder.createQuery(Departamento.class);
            Root<Departamento> root = criteria.from(Departamento.class);
            criteria.select(builder.construct(Departamento.class, root.get(Departamento_.id)));
            return manager.createQuery(criteria).getResultStream().count();
        } catch (Exception e) {
            return 0L;
        }
    }
}
