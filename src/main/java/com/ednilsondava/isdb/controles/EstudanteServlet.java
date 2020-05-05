package com.ednilsondava.isdb.controles;

import com.ednilsondava.isdb.modelos.entidades.Curso;
import com.ednilsondava.isdb.modelos.entidades.Estudante;
import com.ednilsondava.isdb.modelos.entidades.Usuario;
import com.ednilsondava.isdb.modelos.repositorios.Cursos;
import com.ednilsondava.isdb.modelos.repositorios.Estudantes;
import com.ednilsondava.isdb.modelos.repositorios.Usuarios;
import com.ednilsondava.isdb.negocios.FichaBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Estudante")
public class EstudanteServlet extends HttpServlet {
    @Inject
    private Estudantes estudantes;

    @Inject
    private Cursos cursos;

    @Inject
    private Usuarios usuarios;

    @Inject
    private FichaBean fichaBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long idCurso = Long.parseLong(request.getParameter("curso"));
        String codigo = request.getParameter("codigo");
        Integer ano = Integer.parseInt(request.getParameter("ano"));

        Curso curso = cursos.encontrar(idCurso);
        Usuario usuario = usuarios.encontrarByNomeUsuario(codigo);

        Estudante estudante = new Estudante();
        estudante.setCodigo(codigo);
        estudante.setCurso(curso);
        estudante.setUsuario(usuario);
        estudantes.salva(estudante);
        fichaBean.setIdCurso(curso.getId());
        fichaBean.setCodigoEstudante(codigo);
        fichaBean.setAnoCurricular(ano);
        response.sendRedirect(getServletContext().getContextPath() + "/ficha.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigo = request.getParameter("codigo");

        Estudante managed = estudantes.encontrarByCodigo(codigo);
        if (managed == null) {
            request.setAttribute("codigo", codigo);
            request.getServletContext().getRequestDispatcher("/estudante.jsp").include(request, response);
            return;
        }
        request.logout();
        response.sendRedirect(getServletContext().getContextPath() + "/?conta=true");
    }
}
