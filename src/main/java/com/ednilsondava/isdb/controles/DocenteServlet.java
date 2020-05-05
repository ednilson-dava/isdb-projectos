package com.ednilsondava.isdb.controles;

import com.ednilsondava.isdb.modelos.entidades.Docente;
import com.ednilsondava.isdb.modelos.repositorios.Docentes;
import com.ednilsondava.isdb.modelos.repositorios.Modulos;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestScoped
@WebServlet("/docentes")
public class DocenteServlet extends HttpServlet {
    @Inject
    private Docentes docentes;
    @Inject
    private Modulos modulos;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String apelido = request.getParameter("apelido");
        String regime = request.getParameter("regime");
        Docente docente = new Docente();
        docente.setNome(nome);
        docente.setApelido(apelido);
        docente.setRegime(regime);
        docentes.salvar(docente);
        response.sendRedirect(request.getContextPath() + "/sistema.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/docentes.jsp");
    }
}
