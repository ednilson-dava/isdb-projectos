<%--
  Created by IntelliJ IDEA.
  User: Ednilson
  Date: 19-Apr-20
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta content="text/html" http-equiv="Content-Type" charset="UTF-8"/>
    <title>Acesso negado</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css"/>
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo-1.png" sizes="32x32">
</head>
<body>
<div>
    <section id="dombosco">
        <div class="dombosco-header">
            <img src="${pageContext.request.contextPath}/img/logo-1.png"/>
            <p>O acesso foi recusado</p>
        </div>
        <div class="dombosco-content">
            <div class="alert-panel">
                <div class="alert-content">
                    <p>Não tem autorização para ver esta página. Contacte o Departamento de TICs.</p>
                    <a href="${pageContext.request.contextPath}/">Ir a Pagina Inicial</a>
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

