package com.ednilsondava.isdb.controles;

import com.ednilsondava.isdb.modelos.entidades.Pergunta;
import com.ednilsondava.isdb.modelos.entidades.TipoPergunta;
import com.ednilsondava.isdb.modelos.repositorios.Perguntas;
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
@WebServlet("/perguntasimport")
public class PerguntaImportServlet extends HttpServlet {
    private String uploadPath;
    private RandomString randomString;

    @Inject
    private Perguntas perguntas;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
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
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(savedData), "UTF8"));

            String name = savedData.getName();
            if (name.equals("perguntas.csv")) {
                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    String[] data = currentLine.split(";");

                    int ordem = Integer.parseInt(data[0]);
                    String descricao = data[1];
                    TipoPergunta tipo = TipoPergunta.values()[Integer.parseInt(data[2])];

                    Pergunta pergunta = new Pergunta();
                    pergunta.setDescricao(descricao);
                    pergunta.setOrdem(ordem);
                    pergunta.setTipo(tipo);
                    perguntas.salva(pergunta);
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
