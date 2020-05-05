package com.ednilsondava.isdb.modelos.entidades;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Pergunta.class)
public class Pergunta_ {
    public static volatile SingularAttribute<Pergunta, Long> id;
    public static volatile SingularAttribute<Pergunta, Integer> ordem;
    public static volatile SingularAttribute<Pergunta, TipoPergunta> tipo;
    public static volatile SingularAttribute<Pergunta, String> descricao;
}
