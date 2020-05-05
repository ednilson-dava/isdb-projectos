package com.ednilsondava.isdb.modelos.entidades;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Calendar;

@StaticMetamodel(Usuario.class)
public class Usuario_ {
    public static volatile SingularAttribute<Usuario, String> nomeUsuario;
    public static volatile SingularAttribute<Usuario, String> senhaUsuario;
    public static volatile SingularAttribute<Usuario, String> email;
    public static volatile SingularAttribute<Usuario, Calendar> criadoEm;
    public static volatile SingularAttribute<Usuario, Acesso> acesso;
}
