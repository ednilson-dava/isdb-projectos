package com.ednilsondava.isdb.modelos.entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Modulo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer anoCurricular;
    private Integer semestre;
    @OneToOne(cascade = CascadeType.ALL)
    private Docente docente;
    @OneToOne(cascade = CascadeType.ALL)
    private Docente monitor;
    @ManyToOne(cascade = CascadeType.ALL)
    private Curso curso;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoes;

    public Modulo() {

    }

    public Modulo(Long id) {
        this.id = id;
    }
    public Modulo(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    public Modulo(int anoCurricular) {
        this.anoCurricular = anoCurricular;
    }

    public Modulo(Long id, int anoCurricular) {
        this.id = id;
        this.anoCurricular = anoCurricular;
    }

    public void adicionarAvaliacao(Avaliacao avaliacao) {
        if(avaliacoes == null)
            avaliacoes = new ArrayList<>();
        avaliacoes.add(avaliacao);
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

    public Integer getAnoCurricular() {
        return anoCurricular;
    }

    public void setAnoCurricular(Integer anoCurricular) {
        this.anoCurricular = anoCurricular;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Docente getMonitor() {
        return monitor;
    }

    public void setMonitor(Docente monitor) {
        this.monitor = monitor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
}
