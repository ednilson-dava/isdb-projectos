package com.ednilsondava.isdb.controles;

import com.ednilsondava.isdb.modelos.entidades.Departamento;
import com.ednilsondava.isdb.modelos.entidades.Docente;
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

@WebServlet("/departamentos")
public class DepartamentoServlet extends HttpServlet {
    @Inject
    private Cursos cursos;

    @Inject
    private Departamentos departamentos;

    @Inject
    private Docentes docentes;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        Long idCoordenador = Long.parseLong(request.getParameter("coordenador"));

        Departamento departamento = new Departamento();
        departamento.setNome(nome);
        departamento.setCoordenador(docentes.encontrar(idCoordenador));
        departamentos.salvar(departamento);
        response.sendRedirect(request.getContextPath() + "/sistema.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String urlPost = getServletContext().getContextPath() + "/departamentos";

        StringBuilder builder = new StringBuilder();
        builder.append("    <div id=\"cad-departamentos\">\n" +
                "        <form action=\""+urlPost+"\" method=\"post\">\n" +
                "            <table>\n" +
                "                <caption>Cadastro de Departamento</caption>\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td><label>Nome</label></td>\n" +
                "                    <td><input name=\"nome\"/></td>\n" +
                "                </tr>\n");
        builder.append("                <tr>\n" +
                "                    <td><label>Coordenador</label></td>\n" +
                "                    <td>\n" +
                "                        <select name=\"coordenador\">\n");
        for (Docente docente : docentes.todos()) {
            builder.append("<option value=\"" + docente.getId() + "\">" + docente.getNome() + " " + docente.getApelido() + "</option>\\n");
        }
        builder.append("                        </select>\n" +
                "                    </td>\n" +
                "                </tr>\n");
        builder.append("                <tr>\n" +
                "                    <td><input type=\"submit\" value=\"Guardar\"/></td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </form>\n" +
                "    </div>");
        PrintWriter writer = response.getWriter();
        if (!docentes.todos().isEmpty()) {
            writer.println(builder.toString());
        } else {
            writer.println("Indisponivel");
        }
        writer.close();
    }
}
