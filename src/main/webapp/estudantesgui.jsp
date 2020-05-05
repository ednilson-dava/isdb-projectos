<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="cad-pergunta">
    <meta content="text/html" http-equiv="Content-Type" charset="UTF-8"/>
    <div class="sch-estudante">
        <form action="${pageContext.request.contextPath}/estudantesgui" method="post">
            <label>Codigo
                <input name="cod-estudante"/>
            </label>
            <input type="submit" value="Procurar">
        </form>
    </div>

    <table>
        <caption>Numero de total: <c:out value="${requestScope.cEstudantes}"/></caption>
        <thead>
        <tr>
            <td>Codigo</td>
            <td>Criado Em</td>
            <td>Ação</td>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="estudante" items="${requestScope.Estudantes}">
                <tr>
                    <td>
                        <label>${estudante.codigo}</label>
                    </td>
                    <td><label><fmt:formatDate pattern="dd.MM.yyyy" value="${estudante.usuario.criadoEm.time}"/></label></td>
                    <td>
                        <form action="${pageContext.request.contextPath}/estudantesgui" method="post">
                            <input type="hidden" name="id-estudante" value="${estudante.id}"/>
                            <button type="submit">Apagar</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>