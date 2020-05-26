package com.ednilsondava.isdb.modelos.entidades;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Pendente.class)
public class Pendente_ {
    public static volatile SingularAttribute<Pendente, Long> id;
    public static volatile SingularAttribute<Pendente, Integer> media;
    public static volatile SingularAttribute<Pendente, Estudante> estudante;
    public static volatile ListAttribute<Pendente, Modulo> modulos;
}
