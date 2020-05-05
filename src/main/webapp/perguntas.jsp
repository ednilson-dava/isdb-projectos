<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="cad-pergunta">
    <meta content="text/html" http-equiv="Content-Type" charset="UTF-8"/>
    <div class="upl-pergunta">
        <form action="${pageContext.request.contextPath}/perguntasimport" method="post"
            enctype="multipart/form-data">
            <input type="file" name="pergunta-file"/>
            <input type="submit" value="Upload">
        </form>
    </div>
    <form action="${pageContext.request.contextPath}/perguntas" method="post">
        <table>
            <caption>Cadastro de perguntas</caption>
            <tbody>
            <tr>
                <td><label>Descrição</label></td>
                <td><textarea name="descricao" rows="4"></textarea></td>
            </tr>
            <tr>
                <td><label>Tipo Resposta</label></td>
                <td>
                    <select name="tipo">
                        <option value="escolha">Escolha</option>
                        <option value="texto">Texto</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><input type="submit" value="Guardar"/></td>
            </tr>
            </tbody>
        </table>
    </form>
</div>