package com.ednilsondava.isdb.relatorio;

import com.ednilsondava.isdb.modelos.entidades.Departamento;
import com.ednilsondava.isdb.modelos.repositorios.Avaliacoes;
import com.ednilsondava.isdb.modelos.repositorios.Departamentos;
import com.ednilsondava.isdb.negocios.RelatorioBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/anolecectivoaval")
public class DepartamentoAvalReport extends HttpServlet {
    @Inject
    private RelatorioBean relatorioBean;

    @Inject
    private Avaliacoes avaliacoes;

    @Inject
    private Departamentos departamentos;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String al = req.getParameter("janol");

        StringBuilder builder = new StringBuilder();

        //--Departamentos--
        if (al != null && !al.equals("Selecionar ano")) {
            int anoLec = Integer.parseInt(al);
            if (relatorioBean.getAnoLectivoSelected() != anoLec) {
                relatorioBean.setAnoLectivoSelected(anoLec);

                //TODO Restorna os departamento que tem avaliacao neste ano lectivo
                List<Departamento> departamentos = this.departamentos.encontrarByAnoLectivoOnAvaliacao(anoLec);
                if(!departamentos.isEmpty())
                    builder.append("<option value=\"*\">Todos</option>");
                for (Departamento dep : departamentos) {
                    builder.append("<option value='").append(dep.getId()).append("'>").append(dep.getNome()).append("</option>");
                    builder.append("\n");
                }
            }
        }
        resp.getWriter().print(builder.toString());
    }
}
