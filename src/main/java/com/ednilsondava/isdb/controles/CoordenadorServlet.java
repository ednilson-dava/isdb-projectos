package com.ednilsondava.isdb.controles;

import com.ednilsondava.isdb.modelos.entidades.Curso;
import com.ednilsondava.isdb.modelos.entidades.Departamento;
import com.ednilsondava.isdb.modelos.entidades.Docente;
import com.ednilsondava.isdb.modelos.entidades.Modulo;
import com.ednilsondava.isdb.modelos.repositorios.Cursos;
import com.ednilsondava.isdb.modelos.repositorios.Departamentos;
import com.ednilsondava.isdb.modelos.repositorios.Docentes;
import com.ednilsondava.isdb.modelos.repositorios.Modulos;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/coordenadores")
public class CoordenadorServlet extends HttpServlet {
    @Inject
    private Modulos modulos;
    @Inject
    private Docentes docentes;
    @Inject
    private Cursos cursos;
    @Inject
    private Departamentos departamentos;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Departamento> departamentoList = departamentos.todos();
        req.setAttribute("departamentos", departamentoList);
        req.getRequestDispatcher("/coordenador.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        String[] getCursoCod = parameterMap.get("codCurso");
        String[] getModuloCod = parameterMap.get("codModulo");
        String[] getDocenteCod = parameterMap.get("codDocente");
        String[] getCurso = parameterMap.get("curso");
        String[] getModulo = parameterMap.get("modulo");
        String[] getAno = parameterMap.get("ano");
        String[] getSemestre = parameterMap.get("semestre");
        String[] getDocente = parameterMap.get("docente");
        String[] getRegime = parameterMap.get("regime");


        for (int i = 0; i < getCursoCod.length; i++) {
            long cod_curso = Long.parseLong(getCursoCod[i]);
            long cod_modulo = Long.parseLong(getModuloCod[i]);
            long cod_docente = Long.parseLong(getDocenteCod[i]);

            String nomeCurso = getCurso[i].trim();
            String nomeModulo = getModulo[i].trim();
            int ano = Integer.parseInt(getAno[i]);
            int semestre = Integer.parseInt(getSemestre[i]);
            String nomeDocente = getDocente[i].trim();
            String nome = nomeDocente.substring(0, nomeDocente.lastIndexOf(' '));
            String apelido = nomeDocente.substring(nomeDocente.lastIndexOf(' ')+1);
            String regime = getRegime[i].trim();

            Curso curso = cursos.encontrar(cod_curso);
            Modulo modulo = modulos.encontrar(cod_modulo);
            Docente docente = docentes.encontrar(cod_docente);


            curso.setNome(nomeCurso);
            modulo.setNome(nomeModulo);
            modulo.setAnoCurricular(ano);
            modulo.setSemestre(semestre);
            docente.setNome(nome);
            docente.setApelido(apelido);
            docente.setRegime(regime);

            cursos.actualizar(curso);
            modulos.actualizar(modulo);
            docentes.actualizar(docente);
            departamentos.actualizar(curso.getDepartamento());
        }
        List<Departamento> departamentoList = departamentos.todos();
        req.setAttribute("departamentos", departamentoList);
        req.getRequestDispatcher("/sistema.jsp").include(req, resp);
    }
}
