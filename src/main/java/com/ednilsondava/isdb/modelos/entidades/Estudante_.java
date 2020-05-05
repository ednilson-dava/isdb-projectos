package com.ednilsondava.isdb.modelos.entidades;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Estudante.class)
public class Estudante_ {
    public static volatile SingularAttribute<Estudante, Long> id;
    public static volatile SingularAttribute<Estudante, String> codigo;
    public static volatile SingularAttribute<Estudante, Curso> curso;
    public static volatile SingularAttribute<Estudante, Usuario> usuario;
}
