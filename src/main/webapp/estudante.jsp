<%--
  Created by IntelliJ IDEA.
  User: Ednilson
  Date: 11-Mar-20
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta content="text/html" http-equiv="Content-Type" charset="UTF-8"/>
    <title>ISDB - SADD</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mobile.css"/>
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo-1.png" sizes="32x32">
</head>
<body>
<div>
    <section id="dombosco">
        <div class="dombosco-header">
            <img src="${pageContext.request.contextPath}/img/logo-1.png"/>
            <p>Sistema de Avaliação de desempenho do docente</p>
        </div>
        <div class="dombosco-content">
            <div id="estudante">
                <form action="${pageContext.request.contextPath}/Estudante" method="post">
                    <input type="hidden" name="codigo" value="${param.codigo}">
                    <div class="e-ui-curso">
                        <label>Curso </label>
                        <select name="curso">
                            <c:forEach var="curso" items="${inicialBean.cursos}">
                                <option value="${curso.id}">${curso.nome}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="e-ui-ano">
                        <label>Ano </label>
                        <select name="ano">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                        </select>
                    </div>
                    <div class="e-btn-avancar">
                        <input type="submit" value="Avançar"/>
                    </div>
                </form>
            </div>
        </div>
    </section>
    <footer>
        <small><a href="http://${initParam.website}">${initParam.copyright}</a></small>
    </footer>
</div>
</body>
</html>
