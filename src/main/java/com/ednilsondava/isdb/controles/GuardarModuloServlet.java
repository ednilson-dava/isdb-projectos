package com.ednilsondava.isdb.controles;

import com.ednilsondava.isdb.modelos.entidades.*;
import com.ednilsondava.isdb.modelos.repositorios.Avaliacoes;
import com.ednilsondava.isdb.modelos.repositorios.Modulos;
import com.ednilsondava.isdb.modelos.repositorios.Pendentes;
import com.ednilsondava.isdb.modelos.repositorios.Perguntas;
import com.ednilsondava.isdb.negocios.FichaBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/saveModuleEvaluation")
public class GuardarModuloServlet extends HttpServlet {
    @Inject
    private FichaBean fichaBean;
    @Inject
    private Perguntas perguntasRepositorio;
    @Inject
    private Pendentes pendentesRepositorio;
    @Inject
    private Modulos modulosRepositorio;
    @Inject
    private Avaliacoes avaliacoesRepositorio;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> perguntasRespondidas = req.getParameterMap();
        Pattern pattern = Pattern.compile("(.*)-(.*)");

        Long codigoModulo = Long.parseLong(perguntasRespondidas.get("modulo")[0]);
        Modulo moduloGuardado = modulosRepositorio.encontrar(codigoModulo);
        String[] names = perguntasRespondidas.keySet().toArray(new String[0]);
        Avaliacao avaliacao = new Avaliacao();
        Map<Pergunta, Resposta> mapaPerguntaResposta = new HashMap<>();
        int[] mediaSomada = new int[1];

        for (String name : names) {
            Matcher matcher = pattern.matcher(name);
            if (matcher.find()) {
                Long codigoPergunta = Long.parseLong(matcher.group(2));
                Pergunta pergunta = perguntasRepositorio.encontrar(codigoPergunta);
                Resposta resposta = new Resposta();
                //resposta.setPergunta(pergunta);

                String value = perguntasRespondidas.get(name)[0];
                if (pergunta.getTipo() == TipoPergunta.ESCOLHA) {
                    Classificacao classificacao = new Classificacao();
                    classificacao.setTipo(TipoClassificacao.values()[Integer.parseInt(value)]);
                    resposta.setClassificacao(classificacao);
                    mediaSomada[0] += classificacao.getTipo().ordinal();
                } else if (pergunta.getTipo() == TipoPergunta.TEXTO) {
                    Comentario comentario = new Comentario();
                    comentario.setMensagem(value);
                    resposta.setComentario(comentario);
                }
                mapaPerguntaResposta.put(pergunta, resposta);
            }
        }

        Pendente pendente = new Pendente();
        pendente.adicionarModulo(moduloGuardado);
        pendente.setMedia(mediaSomada[0]);
        pendente.setMapaPerguntasRespostasPendentes(mapaPerguntaResposta);
        pendente.setEstudante(fichaBean.getEstudante());
        pendentesRepositorio.salva(pendente);

        //TODO Perste atencao nesta abordagem pode ser a solucao para muitas falhas sobre atualizacao. Ajuda a fazer o refresh antes de renderizar a pagina
        //boolean temModulosAvaliar = moduloAvaliarBean.exitemModulosAvaliar(fichaBean.getEstudante());

        StringBuilder builder = new StringBuilder();
        for (Modulo modulo : fichaBean.getModulos()) {
            builder.append("<option value=\"" + modulo.getId() + "\">" + modulo.getNome() + "</option>");
        }
        PrintWriter writer = resp.getWriter();
        writer.println(builder.toString());
        writer.close();

        if(fichaBean.getModulos().isEmpty()) {
            List<Pendente> pendentes = pendentesRepositorio.encontrarPorEstudante(fichaBean.getEstudante());
            for (Pendente p : pendentes) {
                Avaliacao aval = new Avaliacao();
                aval.setAvaliadoEm(Calendar.getInstance());
                aval.setCompletado(true);
                aval.setMedia(p.getMedia());
                aval.setPerguntaRespostas(p.getMapaPerguntasRespostasPendentes());
                p.getModulos().get(0).adicionarAvaliacao(aval);
                modulosRepositorio.actualizar(p.getModulos().get(0));
            }
            req.logout();
            resp.sendRedirect(req.getContextPath() + "/?fc=true");
            return;
        }

    }
}
