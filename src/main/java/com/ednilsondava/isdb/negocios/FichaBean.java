package com.ednilsondava.isdb.negocios;

import com.ednilsondava.isdb.modelos.entidades.*;
import com.ednilsondava.isdb.modelos.repositorios.*;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class FichaBean implements Serializable{
    private Long idCurso;
    private String codigoEstudante;

    private List<Integer> anoLectivoAvaliacao;
    private List<Modulo> modulos;
    private List<Pergunta> perguntas;
    private List<Modulo> modulosConcluidos;

    private List<Modulo> modulosPorAvaliar;
    private Modulo moduloGuardado;

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
    @Inject
    private Pendentes pendentesRepositorio;

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public List<Modulo> getModulos() {
        this.estudante = estudantesRepositorio.encontrarByCodigo(codigoEstudante);
        List<Pendente> pendentes = pendentesRepositorio.encontrarPorEstudante(estudante);
        List<Modulo> modulos = estudante.getModulos();

        modulosConcluidos = new ArrayList<>();

        for (Pendente pendente : pendentes) {
            List<Modulo> modulosAvaliadosPendente = pendente.getModulos();
            modulos.removeAll(modulosAvaliadosPendente);
            modulosConcluidos.addAll(modulosAvaliadosPendente);
        }
        this.modulos = modulos;
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

    public List<Pergunta> getPerguntas() {
        this.perguntas = perguntasRepositorio.todas();
        return perguntas;
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

    public void setModulosPorAvaliar(List<Modulo> modulos) {
        this.modulosPorAvaliar = modulos;
    }

    public List<Modulo> getModulosPorAvaliar() {
        return modulosPorAvaliar;
    }

    public Modulo getModuloGuardado() {
        return moduloGuardado;
    }

    public void setModuloGuardado(Modulo moduloGuardado) {
        this.moduloGuardado = moduloGuardado;
    }

    public List<Modulo> getModulosConcluidos() {
        return modulosConcluidos;
    }

    public void setModulosConcluidos(List<Modulo> modulosConcluidos) {
        this.modulosConcluidos = modulosConcluidos;
    }
}
