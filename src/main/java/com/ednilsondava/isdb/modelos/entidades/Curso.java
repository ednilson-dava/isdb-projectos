package com.ednilsondava.isdb.modelos.entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Curso {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso")
    private List<Modulo> modulos;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Docente> docentes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso")
    private List<Estudante> estudantes;
    @ManyToOne(cascade = CascadeType.ALL)
    private Departamento departamento;

    public Curso() {

    }

    public Curso(Long id) {
        this.id = id;
    }

    public Curso(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public void adicionarModulo(Modulo modulo) {
        if(modulos == null)
            modulos = new ArrayList<>();
        modulos.add(modulo);
    }

    public void adicionaEstudante(Estudante estudante) {
        if(estudantes == null)
            estudantes = new ArrayList<>();
        estudantes.add(estudante);
    }

    public void adicionarDocente(Docente docente) {
        if(docentes == null)
            docentes = new ArrayList<>();
        docentes.add(docente);
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

    public List<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(List<Modulo> modulos) {
        this.modulos = modulos;
    }

    public List<Docente> getDocentes() {
        return docentes;
    }

    public void setDocentes(List<Docente> docentes) {
        this.docentes = docentes;
    }

    public List<Estudante> getEstudantes() {
        return estudantes;
    }

    public void setEstudantes(List<Estudante> estudantes) {
        this.estudantes = estudantes;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}
