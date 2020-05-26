package com.ednilsondava.isdb.controles;

import com.ednilsondava.isdb.modelos.entidades.Curso;
import com.ednilsondava.isdb.modelos.entidades.Estudante;
import com.ednilsondava.isdb.modelos.entidades.Modulo;
import com.ednilsondava.isdb.modelos.entidades.Usuario;
import com.ednilsondava.isdb.modelos.repositorios.Cursos;
import com.ednilsondava.isdb.modelos.repositorios.Estudantes;
import com.ednilsondava.isdb.modelos.repositorios.Modulos;
import com.ednilsondava.isdb.modelos.repositorios.Usuarios;
import com.ednilsondava.isdb.negocios.FichaBean;
import com.ednilsondava.isdb.negocios.ModuloAvaliarBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Estudante")
public class EstudanteServlet extends HttpServlet {
    @Inject
    private Estudantes estudantes;
    @Inject
    private Modulos modulosReposito;

    @Inject
    private Cursos cursos;

    @Inject
    private Usuarios usuarios;

    @Inject
    private FichaBean fichaBean;

    @Inject
    private ModuloAvaliarBean moduloAvaliarBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long idCurso = Long.parseLong(request.getParameter("curso"));
        String codigo = request.getParameter("codigo");
        Integer ano = Integer.parseInt(request.getParameter("ano"));

        Curso curso = cursos.encontrar(idCurso);

        fichaBean.setIdCurso(curso.getId());
        fichaBean.setCodigoEstudante(codigo);

        Usuario usuario = usuarios.encontrarByNomeUsuario(codigo);
        List<Modulo> modulos = modulosReposito.todosByCursoAndAnoAndSemestre(curso, ano, fichaBean.getGeral().getSemestre());
        Estudante estudante = new Estudante();
        estudante.setCodigo(codigo);
        estudante.setCurso(curso);
        estudante.setUsuario(usuario);
        estudante.setModulos(modulos);

        estudantes.salva(estudante);
        response.sendRedirect(getServletContext().getContextPath() + "/ficha.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigo = request.getParameter("codigo");

        Estudante managed = estudantes.encontrarByCodigo(codigo);
        fichaBean.setCodigoEstudante(codigo);

        if (managed == null) {
            request.setAttribute("codigo", codigo);
            request.getServletContext().getRequestDispatcher("/estudante.jsp").include(request, response);
            return;
        } else if (!fichaBean.getModulos().isEmpty()) {
            response.sendRedirect(getServletContext().getContextPath() + "/ficha.jsp");
            return;
        }
        request.logout();
        response.sendRedirect(getServletContext().getContextPath() + "/?conta=true");

    }
}
