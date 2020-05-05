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
import java.util.GregorianCalendar;
import java.util.List;

public class Modulos implements Serializable {
    @Inject
    private EntityManager manager;

    public void salva(Modulo modulo) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            modulo.setDocente(manager.merge(modulo.getDocente()));
            modulo.setCurso(manager.merge(modulo.getCurso()));
            if (modulo.getMonitor() != null)
                modulo.setMonitor(manager.merge(modulo.getMonitor()));
            manager.persist(modulo);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public void actualizar(Modulo modulo) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.merge(modulo);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public Modulo encontrar(Long id) {
        try {
            return manager.find(Modulo.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Modulo> todosByCursoAndAnoAndSemestre(Curso curso, Integer ano, Integer semestre) {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Modulo> criteria = builder.createQuery(Modulo.class);
            Root<Modulo> root = criteria.from(Modulo.class);
            criteria.where(builder.and(builder.equal(root.get(Modulo_.curso), curso), builder.equal(root.get(Modulo_.anoCurricular), ano), builder.equal(root.get(Modulo_.semestre), semestre)));
            criteria.select(root);
            TypedQuery<Modulo> query = manager.createQuery(criteria);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Modulo> encontrarByCursoOnTemAvaliacao(Curso curso) {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Modulo> criteria = builder.createQuery(Modulo.class);
            Root<Modulo> root = criteria.from(Modulo.class);
            Join<Modulo, Avaliacao> avaliacaoJoin = root.join(Modulo_.avaliacoes);
            avaliacaoJoin.on(builder.equal(avaliacaoJoin.get(Avaliacao_.completado), true));
            //TODO Estamos a obter o id porque sera util quando queremos construir o relatorio por um determinado curso
            criteria.select(builder.construct(Modulo.class, root.get(Modulo_.id), root.get(Modulo_.nome))).distinct(true);
            criteria.where(builder.equal(root.get(Modulo_.curso), curso));
            TypedQuery<Modulo> query = manager.createQuery(criteria);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Modulo> encontrarAnosCurricularesByCursoOnTemAvaliacao(Curso curso, Departamento departamento, int anoCurricular) {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Modulo> criteria = builder.createQuery(Modulo.class);
            Root<Modulo> root = criteria.from(Modulo.class);
            Join<Modulo, Curso> cursoJoin = root.join(Modulo_.curso);
            cursoJoin.on(builder.equal(cursoJoin.get(Curso_.departamento), departamento));
            Join<Modulo, Avaliacao> avaliacaoJoin = root.join(Modulo_.avaliacoes);
            avaliacaoJoin.on(builder.equal(avaliacaoJoin.get(Avaliacao_.completado), true));
            avaliacaoJoin.on(builder.equal(avaliacaoJoin.get(Avaliacao_.completado), true), builder.between(avaliacaoJoin.get(Avaliacao_.avaliadoEm), new GregorianCalendar(anoCurricular, Calendar.FEBRUARY, 1), new GregorianCalendar(anoCurricular, Calendar.DECEMBER, 1)));
            //TODO Estamos a obter o id porque sera util quando queremos construir o relatorio por um determinado curso
            criteria.select(builder.construct(Modulo.class, root.get(Modulo_.anoCurricular))).distinct(true);
            criteria.where(builder.equal(root.get(Modulo_.curso), curso));
            TypedQuery<Modulo> query = manager.createQuery(criteria);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    //--OFF
    public List<Modulo> encontrarSemestresByAnoCurricularOnTemAvaliacao(Curso curso, int anoCurricular) {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Modulo> criteria = builder.createQuery(Modulo.class);
            Root<Modulo> root = criteria.from(Modulo.class);
            Join<Modulo, Avaliacao> avaliacaoJoin = root.join(Modulo_.avaliacoes);
            avaliacaoJoin.on(builder.equal(avaliacaoJoin.get(Avaliacao_.completado), true));
            //TODO Estamos a obter o id porque sera util quando queremos construir o relatorio de todos os semestre por um determinado ano curricular
            criteria.select(builder.construct(Modulo.class, root.get(Modulo_.id), root.get(Modulo_.semestre))).distinct(true);
            criteria.where(builder.equal(root.get(Modulo_.anoCurricular), anoCurricular));
            TypedQuery<Modulo> query = manager.createQuery(criteria);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Modulo> encontrarByAnoCurricularSemestreOnTemAvaliacao(Curso curso, int anoCurricular, int semestre) {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Modulo> criteria = builder.createQuery(Modulo.class);
            Root<Modulo> root = criteria.from(Modulo.class);
            Join<Modulo, Avaliacao> avaliacaoJoin = root.join(Modulo_.avaliacoes);
            avaliacaoJoin.on(builder.equal(avaliacaoJoin.get(Avaliacao_.completado), true));
            criteria.select(builder.construct(Modulo.class,  root.get(Modulo_.id), root.get(Modulo_.nome))).distinct(true);
            criteria.where(builder.equal(root.get(Modulo_.curso), curso), builder.equal(root.get(Modulo_.anoCurricular), anoCurricular), builder.equal(root.get(Modulo_.semestre), semestre));
            TypedQuery<Modulo> query = manager.createQuery(criteria);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Modulo> todos() {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Modulo> criteria = builder.createQuery(Modulo.class);
            Root<Modulo> root = criteria.from(Modulo.class);
            criteria.select(root);
            TypedQuery<Modulo> query = manager.createQuery(criteria);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
