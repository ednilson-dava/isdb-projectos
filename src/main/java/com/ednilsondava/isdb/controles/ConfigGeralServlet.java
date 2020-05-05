package com.ednilsondava.isdb.controles;

import com.ednilsondava.isdb.modelos.entidades.ConfiguracaoGeral;
import com.ednilsondava.isdb.modelos.entidades.Curso;
import com.ednilsondava.isdb.modelos.entidades.Modulo;
import com.ednilsondava.isdb.modelos.repositorios.*;
import com.ednilsondava.isdb.negocios.RelatorioBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

@WebServlet("/configuracao")
public class ConfigGeralServlet extends HttpServlet {
    @Inject
    private ConfiguracoesGeral gerais;

    @Inject
    private Estudantes estudantes;
    @Inject
    private Docentes docentes;
    @Inject
    private Departamentos departamentos;
    @Inject
    private Cursos cursos;
    @Inject
    private Avaliacoes avaliacoes;
    @Inject
    private RelatorioBean relatorioBean;
    @Inject
    private Modulos modulos;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sem = request.getParameter("semestre");

        if (sem != null) {
            int semestre = Integer.parseInt(sem);
            ConfiguracaoGeral geral = new ConfiguracaoGeral();
            geral.setSemestre(semestre);
            geral.setData(Calendar.getInstance());
            gerais.salvar(geral);

            request.setAttribute("cEstudantes", estudantes.total());
            request.setAttribute("cDocentes", docentes.total());
            request.setAttribute("cDepartamentos", departamentos.numeroTotal());
            request.setAttribute("cCursos", cursos.total());
            request.setAttribute("cAvaliacoes", avaliacoes.total());
            request.getRequestDispatcher("/sistema.jsp").include(request, response);
            return;
        }


        String cur = request.getParameter("jCur");



        //--Cursos--


        //--Ano Curricular
        if(cur != null && !cur.equals("Todos")) {
            StringBuilder builder = new StringBuilder();
            long codCurso = Long.parseLong(cur);
            relatorioBean.setCurso(codCurso);

            //TODO Apenas retornar todos anos curriculares de modulos que tem avaliacao
            Curso curso = relatorioBean.getCurso();
            List<Modulo> modulos = this.modulos.encontrarAnosCurricularesByCursoOnTemAvaliacao(curso, relatorioBean.getDepartamento(), relatorioBean.getAnoLectivoSelected());
            builder.append("<option value=\"*\">Todos</option>");
            for (Modulo mo : modulos) {
                builder.append("<option value='" + mo.getAnoCurricular() + "'>" + mo.getAnoCurricular() + "</option>");
                builder.append("\n");
            }
            response.getWriter().print(builder.toString());
            return;
        }

        response.getWriter().print("");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cEstudantes", estudantes.total());
        req.setAttribute("cDocentes", docentes.total());
        req.setAttribute("cDepartamentos", departamentos.numeroTotal());
        req.setAttribute("cCursos", cursos.total());
        req.setAttribute("cAvaliacoes", avaliacoes.total());
        req.getRequestDispatcher("/configuracao.jsp").include(req, resp);
    }
}
