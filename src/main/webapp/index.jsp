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
            <div id="usuario">
                <form action="${pageContext.request.contextPath}/conta" method="post">
                    <div class="u-lbl-codigo">
                        <label>Codigo do estudante</label>
                    </div>
                    <div class="u-txt-codigo">
                        <input type="text" name="codigo" placeholder="XXXXXXX"/>
                    </div>
                    <div class="u-btn-entrar">
                        <input type="submit" value="Entrar"/>
                    </div>
                </form>
                <div class="u-wa-estudante">
                    <c:choose>
                        <c:when test="${param.conta}">
                            <p class="aviso">ACESSO BLOQUEADO, SO É PERMITIDO PREENCHER UMA VEZ</p>
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
        </div>
    </section>
    <footer>
        <small><a href="http://${initParam.website}">${initParam.copyright}</a></small>
    </footer>
</div>
</body>
</html>
