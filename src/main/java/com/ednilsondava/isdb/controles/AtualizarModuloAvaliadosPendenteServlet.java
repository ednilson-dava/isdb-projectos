package com.ednilsondava.isdb.controles;

import com.ednilsondava.isdb.modelos.entidades.Modulo;
import com.ednilsondava.isdb.negocios.FichaBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/updateModulesEvaluated")
public class AtualizarModuloAvaliadosPendenteServlet extends HttpServlet {
    @Inject
    private FichaBean fichaBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        fichaBean.getModulos();//Para atualizar modulos concluidos
        List<Modulo> modulos = fichaBean.getModulosConcluidos();
        StringBuilder builder = new StringBuilder();

        for (Modulo modulo : modulos) {
            builder.append("<li>"+modulo.getNome()+"</li>");
        }

        PrintWriter writer = resp.getWriter();
        writer.println(builder.toString());
        writer.close();
    }
}
