package com.ednilsondava.isdb.modelos.entidades;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Departamento.class)
public class Departamento_ {
    public static volatile SingularAttribute<Departamento, Long>  id;
    public static volatile SingularAttribute<Departamento, String>  nome;
    public static volatile SingularAttribute<Departamento, Docente> coordenador;
    public static volatile ListAttribute<Departamento, Curso> cursos;
}
