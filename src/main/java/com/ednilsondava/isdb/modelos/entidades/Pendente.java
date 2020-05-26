package com.ednilsondava.isdb.modelos.entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class Pendente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer media;
    @ManyToOne
    private Estudante estudante;
    @ManyToMany
    private List<Modulo> modulos;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pergunta_resposta_pendentes",
            joinColumns = @JoinColumn(name = "pendente_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "resposta_id", referencedColumnName = "id"))
    @MapKeyJoinColumn(name = "pergunta")
    private Map<Pergunta, Resposta> mapaPerguntasRespostasPendentes;

    public void adicionarModulo(Modulo modulo) {
        if(modulos == null)
            this.modulos = new ArrayList<>();
        modulos.add(modulo);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public List<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(List<Modulo> modulos) {
        this.modulos = modulos;
    }

    public Map<Pergunta, Resposta> getMapaPerguntasRespostasPendentes() {
        return mapaPerguntasRespostasPendentes;
    }

    public void setMapaPerguntasRespostasPendentes(Map<Pergunta, Resposta> mapaPerguntasRespostasPendentes) {
        this.mapaPerguntasRespostasPendentes = mapaPerguntasRespostasPendentes;
    }

    public Integer getMedia() {
        return media;
    }

    public void setMedia(Integer media) {
        this.media = media;
    }
}
