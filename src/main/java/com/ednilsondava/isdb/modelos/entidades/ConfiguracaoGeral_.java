package com.ednilsondava.isdb.modelos.entidades;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Calendar;

@StaticMetamodel(ConfiguracaoGeral.class)
public class ConfiguracaoGeral_ {
    public static volatile SingularAttribute<Curso, Long> id;
    public static volatile SingularAttribute<Curso, Integer> semestre;
    public static volatile SingularAttribute<Curso, Calendar> data;
}
