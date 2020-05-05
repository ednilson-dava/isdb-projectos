package com.ednilsondava.isdb.negocios;

import com.ednilsondava.isdb.modelos.entidades.Curso;
import com.ednilsondava.isdb.modelos.entidades.Departamento;
import com.ednilsondava.isdb.modelos.repositorios.Cursos;
import com.ednilsondava.isdb.modelos.repositorios.Departamentos;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;

@SessionScoped
public class RelatorioBean implements Serializable {
    private Departamento departamento;
    private Curso curso;
    private int anoLectivoSelected;


    @Inject
    private Departamentos departamentos;
    @Inject
    private Cursos cursos;

    public void setDepartamento(Long id) {
        departamento = departamentos.encontrar(id);
    }

    public int getAnoLectivoSelected() {
        return anoLectivoSelected;
    }

    public void setAnoLectivoSelected(int anoLectivoSelected) {
        this.anoLectivoSelected = anoLectivoSelected;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Long id) {
        this.curso = cursos.encontrar(id);
    }
}
