package com.ednilsondava.isdb.negocios;

import com.ednilsondava.isdb.modelos.entidades.*;
import com.ednilsondava.isdb.modelos.repositorios.*;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class FichaBean implements Serializable{
    private Long idCurso;
    private String codigoEstudante;
    private Integer ano;

    private List<Integer> anoLectivoAvaliacao;
    private List<Modulo> modulos;
    private List<Pergunta> perguntas;
    private List<Avaliacao> avaliacoes;

    private Curso curso;
    private Estudante estudante;
    private ConfiguracaoGeral geral;
    private Date dataConfiguracao;

    @Inject
    private Avaliacoes avaliacoesRepositorio;
    @Inject
    private Cursos cursosRepositorio;
    @Inject
    private Estudantes estudantesRepositorio;
    @Inject
    private Perguntas perguntasRepositorio;
    @Inject
    private Modulos modulosRepositorio;
    @Inject
    private ConfiguracoesGeral configuracoesGeralRepositorio;


    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public List<Modulo> getModulos() {
        Curso curso = cursosRepositorio.encontrar(this.idCurso);
        ConfiguracaoGeral geral = configuracoesGeralRepositorio.encontrarUltima();
        this.modulos = modulosRepositorio.todosByCursoAndAnoAndSemestre(curso, ano, geral.getSemestre());
        return this.modulos;
    }

    public Curso getCurso() {
        this.curso = cursosRepositorio.encontrar(idCurso);
        return curso;
    }

    public String getCodigoEstudante() {
        return codigoEstudante;
    }

    public void setCodigoEstudante(String codigoEstudante) {
        this.codigoEstudante = codigoEstudante;
    }

    public Estudante getEstudante() {
        this.estudante = estudantesRepositorio.encontrarByCodigo(codigoEstudante);
        return estudante;
    }

    public List<Avaliacao> getAvaliacoes() {
        this.avaliacoes = avaliacoesRepositorio.encontrarByCursoAndEstudante(this.curso, this.estudante);
        return avaliacoes;
    }

    public List<Pergunta> getPerguntas() {
        this.perguntas = perguntasRepositorio.todas();
        return perguntas;
    }

    public void setAnoCurricular(Integer semestre) {
        this.ano = semestre;
    }

    public ConfiguracaoGeral getGeral() {
        geral = configuracoesGeralRepositorio.encontrarUltima();
        if(geral == null) {
            ConfiguracaoGeral geral = new ConfiguracaoGeral();
            geral.setSemestre(0);
            geral.setData(Calendar.getInstance());
            dataConfiguracao = geral.getData().getTime();
            return geral;
        }
        dataConfiguracao = geral.getData().getTime();
        return geral;
    }

    public Date getDataConfiguracao() {
        return dataConfiguracao;
    }

    public List<Integer> getAnoLectivoAvaliacao() {
        this.anoLectivoAvaliacao = avaliacoesRepositorio.encontrarAnoLectivos();
        return anoLectivoAvaliacao;
    }
}
