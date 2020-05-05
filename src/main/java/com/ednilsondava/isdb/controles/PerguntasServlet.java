package com.ednilsondava.isdb.controles;

import com.ednilsondava.isdb.modelos.entidades.Pergunta;
import com.ednilsondava.isdb.modelos.entidades.TipoPergunta;
import com.ednilsondava.isdb.modelos.repositorios.Perguntas;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/perguntas")
public class PerguntasServlet extends HttpServlet {
    @Inject
    private Perguntas perguntas;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String descricao = request.getParameter("descricao");
        String tipo = request.getParameter("tipo").toUpperCase();
        Pergunta pergunta = new Pergunta();
        pergunta.setDescricao(descricao);
        pergunta.setTipo(TipoPergunta.valueOf(tipo));
        perguntas.salva(pergunta);
        response.sendRedirect(request.getContextPath() + "/sistema.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/perguntas.jsp");
    }
}
