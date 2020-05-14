<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <div>
        <form>
            <label>Departamento</label>
            <select onchange="selectDepartamentoManger(this)">
                <option value="-1">Selecionar departamento</option>
                <c:forEach var="departamento" items="${requestScope.departamentos}">
                    <option value="${departamento.id}">${departamento.nome}</option>
                </c:forEach>
            </select>
        </form>
    </div>
    <div>
        <form action="${pageContext.request.contextPath}/coordenadores" method="post">
        <table>
            <caption id="coordenador_status">

            </caption>
            <thead>
            <tr>
                <td>Cod</td>
                <td>Curso</td>
                <td>Modulo</td>
                <td>Ano Curricular</td>
                <td>Semestre</td>
                <td>Docente</td>
                <td>Regime</td>
            </tr>
            </thead>
            <tbody class="jDepartamento-row">

            </tbody>
        </table>
            <button type="submit">Guardar</button>
        </form>
    </div>
</div>