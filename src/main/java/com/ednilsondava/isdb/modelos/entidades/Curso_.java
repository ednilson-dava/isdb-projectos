package com.ednilsondava.isdb.modelos.entidades;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Curso.class)
public class Curso_ {
    public static volatile SingularAttribute<Curso, Long> id;
    public static volatile SingularAttribute<Curso, String> nome;
    public static volatile SingularAttribute<Curso, Departamento> departamento;
    public static volatile ListAttribute<Curso, Modulo> modulos;
    public static volatile ListAttribute<Curso, Docente> docentes;
    public static volatile ListAttribute<Curso, Estudante> estudantes;
}
