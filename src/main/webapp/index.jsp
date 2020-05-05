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
    <title>ISDB - Avaliacao de Docentes</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css"/>
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo-1.png" sizes="32x32">
</head>
<body>
<div>
    <section id="dombosco">
        <div class="dombosco-header">
            <img src="${pageContext.request.contextPath}/img/logo-1.png"/>
            <p>Avaliação desempenho do docente</p>
        </div>
        <div class="dombosco-content">
            <div id="usuario">
                <form action="${pageContext.request.contextPath}/conta" method="post">
                    <div>
                        <label>Codigo do estudante</label>
                    </div>
                    <div>
                        <input type="text" name="codigo" placeholder="XXXXXXX"/>
                    </div>
                    <div>
                        <input type="submit" value="Entrar"/>
                    </div>
                </form>
                <div>
                    <c:choose>
                        <c:when test="${param.conta}">
                            <p class="aviso">ACESSO BLOQUEADO</p>
                        </c:when>
                        <c:when test="${param.invalid}">
                            <p class="aviso">CODIGO INVALIDO</p>
                        </c:when>
                        <c:when test="${param.fc}">
                            <p class="aviso" style="color: green">REGISTRADO COM SUCESSO!</p>
                        </c:when>
                        <c:otherwise>
                            <p class="aviso">ATENÇÃO ESTUDANTE: Apenas tera acesso unica vez, certifique-se de preencher todos
                                os modulos</p>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
            <div id="estudante">
                <form action="${pageContext.request.contextPath}/Estudante" method="post">
                    <input type="hidden" name="codigo" value="${param.codigo}">
                    <div>
                        <label>Curso </label>
                        <select name="curso">
                            <c:forEach var="curso" items="${inicialBean.cursos}">
                                <option value="${curso.id}">${curso.nome}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div>
                        <label>Ano </label>
                        <select name="ano">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                        </select>
                    </div>
                    <div>
                        <input type="submit" value="Guardar"/>
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
