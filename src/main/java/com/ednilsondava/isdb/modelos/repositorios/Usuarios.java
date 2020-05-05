package com.ednilsondava.isdb.modelos.repositorios;

import com.ednilsondava.isdb.modelos.entidades.Usuario;
import com.ednilsondava.isdb.modelos.entidades.Usuario_;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;

public class Usuarios {
    @Inject
    private EntityManager manager;
    private Validator validator;

    @PostConstruct
    public void initValidator() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    public void salva(Usuario usuario) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            usuario.setAcesso(manager.merge(usuario.getAcesso()));
            manager.persist(usuario);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public Usuario encontrar(Long id) {
        try {
            return manager.find(Usuario.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    public Usuario encontrarByNomeUsuario(String nomeUsuario) {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
            Root<Usuario> root = criteria.from(Usuario.class);
            criteria.where(builder.equal(root.get(Usuario_.nomeUsuario), nomeUsuario));
            criteria.select(root);
            TypedQuery<Usuario> query = manager.createQuery(criteria);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public void actualizar(Usuario usuario) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.merge(usuario);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public List<Usuario> todos() {
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
            Root<Usuario> root = criteria.from(Usuario.class);
            criteria.select(root);
            TypedQuery<Usuario> query = manager.createQuery(criteria);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
