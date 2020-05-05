package com.ednilsondava.isdb.controles;

import com.ednilsondava.isdb.modelos.entidades.Curso;
import com.ednilsondava.isdb.modelos.entidades.Departamento;
import com.ednilsondava.isdb.modelos.repositorios.Cursos;
import com.ednilsondava.isdb.modelos.repositorios.Departamentos;
import com.ednilsondava.isdb.modelos.repositorios.Docentes;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cursos")
public class CursoServlet extends HttpServlet {
    @Inject
    private Departamentos departamentos;

    @Inject
    private Docentes docentes;

    @Inject
    private Cursos cursos;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String urlPost = getServletContext().getContextPath() + "/cursos";
        StringBuilder builder = new StringBuilder();
        builder.append("<div id=\"cad-cursos\">\n" +
                "        <form action=\""+urlPost+"\" method=\"post\">\n" +
                "            <table>\n" +
                "                <caption>Cadastro de cursos</caption>\n" +
                "                <tbody>\n");
        builder.append("                <tr>\n" +
                "                    <td><label>Departamento</label></td>\n" +
                "                    <td>\n" +
                "                        <select name=\"departamento\">\n");
        for (Departamento departamento : departamentos.todos()) {
            builder.append("<option value=\"" + departamento.getId() + "\">" + departamento.getNome() + "</option>\\n");
        }
        builder.append("                        </select>\n" +
                "                    </td>\n" +
                "                </tr>\n");
        builder.append("                <tr>\n" +
                "                    <td><label>Nome</label></td>\n" +
                "                    <td><input name=\"nome\"/></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td><input type=\"submit\" value=\"Guardar\"/></td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </form>\n" +
                "    </div>");
        PrintWriter writer = resp.getWriter();
        if (!departamentos.todos().isEmpty()) {
            writer.println(builder.toString());
        } else {
            writer.println("Indisponivel");
        }
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        Long idDepartamento = Long.parseLong(req.getParameter("departamento"));

        Departamento departamento = departamentos.encontrar(idDepartamento);
        Curso curso = new Curso();
        curso.setNome(nome);
        curso.setDepartamento(departamento);
        departamento.adicionarCurso(curso);
        departamentos.actualizar(departamento);
        resp.sendRedirect(req.getContextPath() + "/sistema.jsp");
    }
}
