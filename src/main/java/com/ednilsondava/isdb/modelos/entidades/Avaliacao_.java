package com.ednilsondava.isdb.modelos.entidades;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Calendar;

@StaticMetamodel(Avaliacao.class)
public class Avaliacao_ {
    public static volatile SingularAttribute<Avaliacao, Long> id;
    public static volatile SingularAttribute<Avaliacao,Boolean> completado;
    public static volatile SingularAttribute<Avaliacao, Calendar> avaliadoEm;
    public static volatile SingularAttribute<Avaliacao, Integer> media;
    public static volatile ListAttribute<Avaliacao, Resposta> respostas;
}
