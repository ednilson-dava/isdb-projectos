package com.ednilsondava.isdb.negocios;

import com.ednilsondava.isdb.modelos.entidades.Acesso;
import com.ednilsondava.isdb.modelos.entidades.Curso;
import com.ednilsondava.isdb.modelos.entidades.Modulo;
import com.ednilsondava.isdb.modelos.entidades.Usuario;
import com.ednilsondava.isdb.modelos.repositorios.Acessos;
import com.ednilsondava.isdb.modelos.repositorios.Cursos;
import com.ednilsondava.isdb.modelos.repositorios.Modulos;
import com.ednilsondava.isdb.modelos.repositorios.Usuarios;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Calendar;
import java.util.List;

@Named
@RequestScoped
public class InicialBean {
    private List<Modulo> modulos;
    private List<Curso> cursos;

    @Inject
    private Modulos modulosRepositorio;
    @Inject
    private Cursos cursosRepositorio;
    @Inject
    private Acessos acessosRepositorio;
    @Inject
    private Usuarios usuariosRepositorio;

    public List<Modulo> getModulos() {
        return modulosRepositorio.todos();
    }
    public List<Curso> getCursos() {
        return cursosRepositorio.todos();
    }

    public void configurarAdministrador() {
        Usuario managed = usuariosRepositorio.encontrarByNomeUsuario("@tics");
        if(managed == null) {
            String tipo = "ADMINISTRATOR";
            Acesso acesso = acessosRepositorio.encontrar(tipo);
            if (acesso == null) {
                acesso = new Acesso();
                acesso.setTipo(tipo);
            }
            Usuario usuario = new Usuario();
            usuario.setNomeUsuario("@tics");
            usuario.setSenhaUsuario("@Admin1234");
            usuario.setAcesso(acesso);
            usuario.setCriadoEm(Calendar.getInstance());
            usuariosRepositorio.salva(usuario);
        }
    }
}
