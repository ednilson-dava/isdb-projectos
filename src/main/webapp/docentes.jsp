<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="cad-docentes">
    <form action="${pageContext.request.contextPath}/docentes" method="post">
        <table>
            <caption>Cadastro do docente</caption>
            <tbody>
            <tr>
                <td><label>Nome</label></td>
                <td><input name="nome"/></td>
            </tr>
            <tr>
                <td><label>Apelido</label></td>
                <td><input name="apelido"/></td>
            </tr>
            <tr>
                <td><label>Regime</label></td>
                <td>
                    <select name="regime">
                        <option value="inteiro">Inteiro</option>
                        <option value="parcial">Parcial</option>
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