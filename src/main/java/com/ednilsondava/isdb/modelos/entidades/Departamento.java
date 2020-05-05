package com.ednilsondava.isdb.modelos.entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Departamento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToOne(cascade = CascadeType.ALL)
    private Docente coordenador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departamento")
    private List<Curso> cursos;

    public Departamento() {

    }

    public Departamento(Long id) {
        this.id = id;
    }

    public Departamento(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public void adicionarCurso(Curso curso) {
        if(cursos == null)
            cursos = new ArrayList<>();
        cursos.add(curso);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Docente getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Docente coordenador) {
        this.coordenador = coordenador;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
}
