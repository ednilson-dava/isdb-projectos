package com.ednilsondava.isdb.modelos.entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Estudante {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String codigo;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Curso curso;
    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;
    @OneToMany(mappedBy = "estudante")
    private List<Pendente> avaliacoesPendentes;
    @ManyToMany
    private List<Modulo> modulos;

    public Estudante() {
    }

    public void adicionarModulo(Modulo modulo) {
        if(modulos == null)
            modulos = new ArrayList<>();
        modulos.add(modulo);
    }

    public Estudante(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Pendente> getAvaliacoesPendentes() {
        return avaliacoesPendentes;
    }

    public void setAvaliacoesPendentes(List<Pendente> avaliacoesPendentes) {
        this.avaliacoesPendentes = avaliacoesPendentes;
    }

    public List<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(List<Modulo> modulos) {
        this.modulos = modulos;
    }
}
