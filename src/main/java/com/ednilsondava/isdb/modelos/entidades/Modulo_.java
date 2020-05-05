package com.ednilsondava.isdb.modelos.entidades;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Modulo.class)
public class Modulo_ {
    public static volatile SingularAttribute<Modulo, Long> id;
    public static volatile SingularAttribute<Modulo, String> nome;
    public static volatile SingularAttribute<Modulo, Integer> anoCurricular;
    public static volatile SingularAttribute<Modulo, Integer> semestre;
    public static volatile SingularAttribute<Modulo, Docente> docente;
    public static volatile SingularAttribute<Modulo, Docente> monitor;
    public static volatile SingularAttribute<Modulo, Curso> curso;
    public static volatile ListAttribute<Modulo, Avaliacao> avaliacoes;
}
