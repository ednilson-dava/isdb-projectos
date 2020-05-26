package com.ednilsondava.isdb.negocios;

import com.ednilsondava.isdb.modelos.entidades.Estudante;
import com.ednilsondava.isdb.modelos.entidades.Modulo;
import com.ednilsondava.isdb.modelos.entidades.Pendente;
import com.ednilsondava.isdb.modelos.repositorios.Pendentes;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named
public class ModuloAvaliarBean {
    @Inject
    private Pendentes pendentes;
    @Inject
    private FichaBean fichaBean;

    public boolean exitemModulosAvaliar(Estudante estudante) {
        List<Pendente> pendentes = this.pendentes.encontrarPorEstudante(estudante);
        List<Modulo> modulos = estudante.getModulos();
        for (Pendente pendente : pendentes) {
            List<Modulo> modulosAvaliadosPendente = pendente.getModulos();
            modulos.removeAll(modulosAvaliadosPendente);
        }
        fichaBean.setModulosPorAvaliar(modulos);
        if (modulos.isEmpty()) {

        }
        return !modulos.isEmpty();
    }
}
