package com.ednilsondava.isdb.modelos.entidades;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Docente.class)
public class Docente_ {
    public static volatile SingularAttribute<Docente, Long> id;
    public static volatile SingularAttribute<Docente, String> nome;
    public static volatile SingularAttribute<Docente, String> apelido;
    public static volatile SingularAttribute<Docente, String> regime;
}
