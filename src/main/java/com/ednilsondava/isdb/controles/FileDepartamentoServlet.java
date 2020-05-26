package com.ednilsondava.isdb.controles;

import com.ednilsondava.isdb.modelos.entidades.Curso;
import com.ednilsondava.isdb.modelos.entidades.Departamento;
import com.ednilsondava.isdb.modelos.entidades.Docente;
import com.ednilsondava.isdb.modelos.entidades.Modulo;
import com.ednilsondava.isdb.modelos.repositorios.Cursos;
import com.ednilsondava.isdb.modelos.repositorios.Departamentos;
import com.ednilsondava.isdb.modelos.repositorios.Docentes;
import com.ednilsondava.isdb.modelos.repositorios.Modulos;
import net.bytebuddy.utility.RandomString;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024, maxRequestSize = 1024 * 1024)
@WebServlet("/files")
public class FileDepartamentoServlet extends HttpServlet {
    private String uploadPath;
    private RandomString randomString;

    @Inject
    private Docentes docentes;
    @Inject
    private Departamentos departamentos;
    @Inject
    private Cursos cursos;
    @Inject
    private Modulos modulos;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String saved = null;
        for (Part part : req.getParts()) {
            String fileName = getFileName(part);
            part.write(uploadPath + File.separator + fileName);
            part.write(uploadPath + File.separator + "backup"+ File.separator + randomString.nextString()+".csv");
            saved = uploadPath + File.separator + fileName;
        }
        if (saved != null) {
            File savedData = new File(saved);
            //FileReader fr = new FileReader(savedData);
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(savedData)));

            String name = savedData.getName();
            if (name.equals("dataset.csv")) {
                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    String nome;
                    String apelido;
                    String[] data = currentLine.split(",");
                    String nomeDept = data[0];
                    nome = data[1].substring(0, data[1].lastIndexOf(' '));
                    apelido = data[1].substring(data[1].lastIndexOf(' ')+1);

                    Docente coordenador = docentes.encontrarByNomeAndApelido(nome, apelido);
                    if(departamentos.enccontrarByCoordenador(coordenador) == null) {
                        Departamento departamento = new Departamento();
                        departamento.setNome(nomeDept);
                        departamento.setCoordenador(coordenador);
                        //TODO Salvar o departamento
                        departamentos.salvar(departamento);
                    }

                    Departamento departamento = departamentos.enccontrarByCoordenador(coordenador);
                    String nomeCurso = data[2];
                    if(cursos.encontrarByNome(nomeCurso) == null) {
                        Curso curso = new Curso();
                        curso.setNome(nomeCurso);
                        curso.setDepartamento(departamento);
                        departamento.adicionarCurso(curso);
                        //TODO Actualizar o departamento
                        departamentos.actualizar(departamento);
                    }

                    Curso curso = cursos.encontrarByNome(nomeCurso);
                    Modulo modulo = new Modulo();
                    Integer ano = Integer.parseInt(data[3]);
                    Integer semestre = data[4].equals("I")?1:2;
                    String nomeModulo = data[5];
                    modulo.setNome(nomeModulo);
                    modulo.setAnoCurricular(ano);
                    modulo.setSemestre(semestre);
                    modulo.setCurso(curso);
                    nome = data[6].substring(0, data[6].lastIndexOf(' '));
                    apelido = data[6].substring(data[6].lastIndexOf(' ')+1);
                    Docente docente = docentes.encontrarByNomeAndApelido(nome, apelido);
                    modulo.setDocente(docente);
                    if(data.length >7) {
                        String nomeMonitor = data[7];
                        if (nomeMonitor != null) {
                            if (!nomeMonitor.isEmpty()) {
                                nome = data[7].substring(0, data[7].lastIndexOf(' '));
                                apelido = data[7].substring(data[7].lastIndexOf(' ') + 1);
                                Docente monitor = docentes.encontrarByNomeAndApelido(nome, apelido);
                                modulo.setMonitor(monitor);
                            }
                        }
                    }
                    //TODO Salvar o modulo
                    modulos.salva(modulo);
                }
            } else if (name.equals("teacher.csv")) {
                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    String[] data = currentLine.split(",");
                    String nome = data[0].substring(0, data[0].lastIndexOf(' '));
                    String apelido = data[0].substring(data[0].lastIndexOf(' ')+1);
                    Docente docente = new Docente();
                    docente.setNome(nome);
                    docente.setApelido(apelido);
                    docente.setRegime(data[1]);
                    //TODO Salvar docente na base de dados
                    Docente managed = docentes.encontrarByNomeAndApelido(nome, apelido);
                    if(managed==null)
                        docentes.salvar(docente);
                }
            }
        }
        resp.sendRedirect(req.getContextPath() + "/sistema.jsp");
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        uploadPath = config.getServletContext().getRealPath("") + "Data";
        File uploadDir = new File(uploadPath);
        File backupDir = new File(uploadPath+File.separator+"backup");
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
            backupDir.mkdir();
        }
        randomString = new RandomString(15);
    }
}
