package com.ednilsondava.isdb.controles;

import com.ednilsondava.isdb.modelos.entidades.Curso;
import com.ednilsondava.isdb.modelos.entidades.Docente;
import com.ednilsondava.isdb.modelos.entidades.Modulo;
import com.ednilsondava.isdb.modelos.repositorios.Cursos;
import com.ednilsondava.isdb.modelos.repositorios.Docentes;
import com.ednilsondava.isdb.modelos.repositorios.Modulos;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/modulos")
public class ModuloServlet extends HttpServlet {
    @Inject
    private Docentes docentes;

    @Inject
    private Modulos modulos;

    @Inject
    private Cursos cursos;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        Integer anoCurricular = Integer.parseInt(request.getParameter("anoCurricular"));
        Integer semestre = Integer.parseInt(request.getParameter("semestre"));
        Long idDocente = Long.parseLong(request.getParameter("docente"));
        Long idMonitor = Long.parseLong(request.getParameter("monitor"));
        Long idCurso = Long.parseLong(request.getParameter("curso"));

        Docente docente = docentes.encontrar(idDocente);
        Curso curso = cursos.encontrar(idCurso);
        Docente monitor = null;
        if(docente.getId() != idMonitor) {
            monitor = docentes.encontrar(idMonitor);
        }
        Modulo modulo = new Modulo();
        modulo.setNome(nome);
        modulo.setAnoCurricular(anoCurricular);
        modulo.setSemestre(semestre);
        modulo.setDocente(docente);
        modulo.setMonitor(monitor);
        modulo.setCurso(curso);
        modulos.salva(modulo);
        response.sendRedirect(request.getContextPath() + "/sistema.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String urlPost = getServletContext().getContextPath() + "/modulos";

        StringBuilder builder = new StringBuilder();
        builder.append("<div id=\"cad-modulos\">\n" +
                "        <form action=\""+urlPost+"\" method=\"post\">\n" +
                "            <table>\n" +
                "                <caption>Cadastro de modulos</caption>\n" +
                "                <tbody>\n");

        builder.append("                <tr>\n" +
                "                    <td><label>Curso</label></td>\n" +
                "                    <td>\n" +
                "                        <select name=\"curso\">\n");
        for (Curso curso : cursos.todos()) {
            builder.append("<option value=\""+curso.getId()+"\">"+curso.getNome()+"</option>\\n");
        }
        builder.append(                "                        </select>\n" +
                "                    </td>\n" +
                "                </tr>\n");

        builder.append("                <tr>\n" +
                "                    <td><label>Docente</label></td>\n" +
                "                    <td>\n" +
                "                        <select name=\"docente\">\n");
        for (Docente docente : docentes.todos()) {
            builder.append("<option value=\""+docente.getId()+"\">"+docente.getNome()+" "+docente.getApelido()+"</option>\\n");
        }
        builder.append(                "                        </select>\n" +
                "                    </td>\n" +
                "                </tr>\n");


        builder.append("                <tr>\n" +
                "                    <td><label>Nome</label></td>\n" +
                "                    <td><input name=\"nome\"/></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td><label>Ano Curricular</label></td>\n" +
                "                    <td><input type=\"number\" name=\"anoCurricular\"/></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td><label>Semestre</label></td>\n" +
                "                    <td>\n" +
                "                        <select name=\"semestre\">\n" +
                "                            <option value=\"1\">Primeiro</option>\n" +
                "                            <option value=\"2\">Segundo</option>\n" +
                "                        </select>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td><label>Monitor</label></td>\n" +
                "                    <td>\n" +
                "                        <select name=\"monitor\">\n");
        for (Docente docente : docentes.todos()) {
            builder.append("<option value=\""+docente.getId()+"\">"+docente.getNome()+" "+docente.getApelido()+"</option>\\n");
        }
        builder.append("                        </select>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td><input type=\"submit\" value=\"Guardar\"/></td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </form>\n" +
                "    </div>");
        PrintWriter writer = response.getWriter();
        if(!cursos.todos().isEmpty() && !docentes.todos().isEmpty()) {
            writer.println(builder.toString());
        } else {
            writer.println("Indisponivel");
        }
        writer.close();
    }
}
