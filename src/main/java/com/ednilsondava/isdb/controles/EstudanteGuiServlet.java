package com.ednilsondava.isdb.controles;

import com.ednilsondava.isdb.modelos.entidades.Estudante;
import com.ednilsondava.isdb.modelos.repositorios.Estudantes;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@WebServlet("/estudantesgui")
public class EstudanteGuiServlet extends HttpServlet {
    @Inject
    private Estudantes estudantes;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cEstudantes", estudantes.total());
        req.setAttribute("Estudantes", estudantes.todos());
        req.getRequestDispatcher("/estudantesgui.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id-estudante");
        String cod = req.getParameter("cod-estudante");

        if(cod != null) {
            Estudante estudante = estudantes.encontrarByCodigo(cod);
            req.setAttribute("cEstudantes", estudantes.total());
            req.setAttribute("Estudantes", Collections.singletonList(estudante));
            req.getRequestDispatcher("/estudantesgui.jsp").include(req, resp);
            return;
        }

        if(id != null) {
            Estudante estudante = estudantes.encontrar(Long.parseLong(id));
            estudantes.apagar(estudante);
            req.getRequestDispatcher("/sistema.jsp").include(req, resp);
        }
    }
}
