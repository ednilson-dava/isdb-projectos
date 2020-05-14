package com.ednilsondava.isdb.controles;

import com.ednilsondava.isdb.modelos.entidades.Curso;
import com.ednilsondava.isdb.modelos.entidades.Departamento;
import com.ednilsondava.isdb.modelos.entidades.Modulo;
import com.ednilsondava.isdb.modelos.repositorios.Cursos;
import com.ednilsondava.isdb.modelos.repositorios.Departamentos;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/depmanagersearch")
public class DepManagerSearchServlet extends HttpServlet {
    @Inject
    private Departamentos departamentos;
    @Inject
    private Cursos cursos;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long cod_departamento = Integer.parseInt(req.getParameter("cod-departamento"));
        StringBuilder builder = new StringBuilder();
        if (cod_departamento>0) {
            Departamento departamento = departamentos.encontrar(cod_departamento);
            for (Curso curso : cursos.encontrarByDepartamento(departamento)) {
                for (Modulo modulo : curso.getModulos()) {
                    builder.append("<tr>");
                    builder.append("<td>").append(modulo.getId()).
                            append("<input type='hidden' name='codCurso' value='").append(curso.getId()).append("'/>").
                            append("<input type='hidden' name='codModulo' value='").append(modulo.getId()).append("'/>").
                            append("<input type='hidden' name='codDocente' value='").append(modulo.getDocente().getId()).append("'/>").
                            append("</td>");

                    builder.append("<td>").
                            append("<input name='curso' value='").append(curso.getNome()).append("'/>").
                            append("</td>");

                    builder.append("<td>").
                            append("<input name='modulo' value='").append(modulo.getNome()).append("'/>").
                            append("</td>");

                    builder.append("<td>").
                            append("<input name='ano' value='").append(modulo.getAnoCurricular()).append("'/>").
                            append("</td>");

                    builder.append("<td>").
                            append("<input name='semestre' value='").append(modulo.getSemestre()).append("'/>").
                            append("</td>");

                    builder.append("<td>").
                            append("<input name='docente' value='").append(modulo.getDocente().getNome()).append(" ").append(modulo.getDocente().getApelido()).append("'/>").
                            append("</td>");

                    builder.append("<td>").
                            append("<input name='regime' value='").append(modulo.getDocente().getRegime()).append("'/>").
                            append("</td>");
                    builder.append("</tr>");
                }
            }
        }
        PrintWriter writer = resp.getWriter();
        writer.print(builder.toString());
        writer.close();
    }
}
