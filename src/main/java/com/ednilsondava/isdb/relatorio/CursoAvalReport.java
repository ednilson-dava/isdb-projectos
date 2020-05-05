package com.ednilsondava.isdb.relatorio;

import com.ednilsondava.isdb.modelos.entidades.Curso;
import com.ednilsondava.isdb.modelos.entidades.Departamento;
import com.ednilsondava.isdb.modelos.repositorios.Cursos;
import com.ednilsondava.isdb.negocios.RelatorioBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cursoaval")
public class CursoAvalReport extends HttpServlet {
    @Inject
    private Cursos cursos;
    @Inject
    private RelatorioBean relatorioBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dp = req.getParameter("jdepar");

        if (dp != null && !dp.equals("Todos")) {
            StringBuilder builder = new StringBuilder();
            long codDep = Long.parseLong(dp);

            relatorioBean.setDepartamento(codDep);
            //TODO Apenas retorne os cursos que ja foram avaliados
            Departamento departamento = relatorioBean.getDepartamento();
            List<Curso> cursos = this.cursos.encontrarByDepartamentoOnTemAvaliacao(departamento, relatorioBean.getAnoLectivoSelected());
            if (!cursos.isEmpty())
                builder.append("<option value=\"*\">Todos</option>");
            for (Curso cu : cursos) {
                builder.append("<option value='" + cu.getId() + "'>" + cu.getNome() + "</option>");
                builder.append("\n");
            }
            resp.getWriter().print(builder.toString());
        }
    }
}
