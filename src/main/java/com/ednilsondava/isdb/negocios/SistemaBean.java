package com.ednilsondava.isdb.negocios;

import com.ednilsondava.isdb.modelos.entidades.Curso;
import com.ednilsondava.isdb.modelos.entidades.Docente;
import com.ednilsondava.isdb.modelos.entidades.Modulo;
import com.ednilsondava.isdb.modelos.repositorios.Cursos;
import com.ednilsondava.isdb.modelos.repositorios.Docentes;
import com.ednilsondava.isdb.modelos.repositorios.Modulos;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class SistemaBean {
    private List<Docente> docentes;
    private List<Curso> cursos;
    private List<Modulo> modulos;

    @Inject
    private Modulos modulosRepositorio;
    @Inject
    private Cursos cursosRepositorio;
    @Inject
    private Docentes docentesRepositorio;

    public List<Docente> getDocentes() {
        this.docentes = docentesRepositorio.todos();
        return docentes;
    }

    public List<Curso> getCursos() {
        this.cursos = cursosRepositorio.todos();
        return cursos;
    }

    public List<Modulo> getModulos() {
        this.modulos = modulosRepositorio.todos();
        return modulos;
    }
}
