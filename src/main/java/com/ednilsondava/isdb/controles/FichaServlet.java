package com.ednilsondava.isdb.controles;

import com.ednilsondava.isdb.modelos.entidades.*;
import com.ednilsondava.isdb.modelos.repositorios.Modulos;
import com.ednilsondava.isdb.modelos.repositorios.Perguntas;
import com.ednilsondava.isdb.negocios.FichaBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/fichas")
public class FichaServlet extends HttpServlet {
    @Inject
    private Modulos modulos;
    @Inject
    private Perguntas perguntas;
    @Inject
    private FichaBean fichaBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Pattern pattern = Pattern.compile("(.*)-(.*)-(.*)");
        Enumeration<String> params = request.getParameterNames();
        Map<String, String[]> parameterMap = request.getParameterMap();
        boolean dev = Boolean.parseBoolean(request.getParameter("dev"));

        if(dev) {
			request.logout();
			response.sendRedirect(request.getContextPath() + "/?fc=true");
        	return;
		}

        List<Modulo> modulosCursoAnoSemestre = fichaBean.getModulos();

        List<String> sparams = new ArrayList<>();
        String currentLine;
        while (params.hasMoreElements()) {
			currentLine = params.nextElement();
        	sparams.add(currentLine);
		}

        for (Modulo modulo : modulosCursoAnoSemestre) {
			Avaliacao al = new Avaliacao();
			Map<Pergunta, Resposta> pr = new HashMap<>();
			int[] media = new int[1];

			for (String param : sparams) {
	            Matcher matcher = pattern.matcher(param);
	            if(matcher.find()) {
	            	Long idPergunta = Long.parseLong(matcher.group(2));
	                Long idModulo = Long.parseLong(matcher.group(3));
	                Modulo md = modulos.encontrar(idModulo);
	                
	                if(md.getId() == modulo.getId()) {
	                	Pergunta pergunta = perguntas.encontrar(idPergunta);
	                    Resposta resposta = new Resposta();
	                    resposta.setPergunta(pergunta);
	                    String resp = parameterMap.get(param)[0];

	                    if (pergunta.getTipo() == TipoPergunta.ESCOLHA) {
	                        Classificacao classificacao = new Classificacao();
	                        classificacao.setTipo(TipoClassificacao.values()[Integer.parseInt(resp)]);
	                        resposta.setClassificacao(classificacao);
	                        media[0] += classificacao.getTipo().ordinal();
	                    } else if (pergunta.getTipo() == TipoPergunta.TEXTO) {
	                        Comentario comentario = new Comentario();
	                        comentario.setMensagem(resp);
	                        resposta.setComentario(comentario);
	                    }
	                    pr.put(pergunta, resposta);
	                }
	            }
			};
			al.setAvaliadoEm(Calendar.getInstance());
            al.setPerguntaRespostas(pr);
            al.setCompletado(true);
            al.setMedia(media[0]);
            modulo.adicionarAvaliacao(al);
            modulos.actualizar(modulo);
		}
        request.logout();
        response.sendRedirect(request.getContextPath() + "/?fc=true");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
