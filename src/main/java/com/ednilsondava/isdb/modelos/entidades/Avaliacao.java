package com.ednilsondava.isdb.modelos.entidades;

import javax.persistence.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Avaliacao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean completado;
    @Temporal(TemporalType.DATE)
    private Calendar avaliadoEm;
    private Integer media;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pergunta_resposta_mapping", joinColumns = @JoinColumn(name = "avaliacao_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "resposta_id", referencedColumnName = "id"))
    @MapKeyJoinColumn(name = "pergunta")
    private Map<Pergunta, Resposta> perguntaRespostas;

    public Avaliacao() {
    }

    public Avaliacao(Long id) {
        this.id = id;
    }

    public Avaliacao(Calendar avaliadoEm) {
        this.avaliadoEm = avaliadoEm;
    }

    public void adicionaRespostaForPergunta(Pergunta pergunta, Resposta resposta) {
        if(perguntaRespostas == null)
            perguntaRespostas = new HashMap<>();
        if(perguntaRespostas.containsKey(pergunta)) {
            perguntaRespostas.put(pergunta, resposta);
            return;
        }
        perguntaRespostas.replace(pergunta, resposta);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getCompletado() {
        return completado;
    }

    public void setCompletado(Boolean completado) {
        this.completado = completado;
    }

    public Calendar getAvaliadoEm() {
        return avaliadoEm;
    }

    public void setAvaliadoEm(Calendar avaliadoEm) {
        this.avaliadoEm = avaliadoEm;
    }

    public Integer getMedia() {
        return media;
    }

    public void setMedia(Integer media) {
        this.media = media;
    }

    public Map<Pergunta, Resposta> getPerguntaRespostas() {
        return perguntaRespostas;
    }

    public void setPerguntaRespostas(Map<Pergunta, Resposta> perguntaRespostas) {
        this.perguntaRespostas = perguntaRespostas;
    }
}
