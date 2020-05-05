<%--
  Created by IntelliJ IDEA.
  User: Ednilson
  Date: 19-Apr-20
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta content="text/html" http-equiv="Content-Type" charset="UTF-8"/>
    <title>Diagnostico do erro</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css"/>
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo-1.png" sizes="32x32">
</head>
<body>
<div>
    <section id="dombosco">
        <div class="dombosco-header">
            <img src="${pageContext.request.contextPath}/img/logo-1.png"/>
            <p>Diagnostico do erro</p>
        </div>
        <div class="dombosco-content" style="overflow: scroll; margin-bottom: 10px;">
            <div class="error-panel">
                <p><small></small>: Ocorreu um erro interno no sistema</p>
                <ul>
                    <li>Informar o Departamento de TICs</li>
                </ul>
                <div class="stack">
                    <small>${requestScope.stack}</small>
                </div>
            </div>
        </div>
        <div class="error-content">
            <a href="${pageContext.request.contextPath}/">Ir a Pagina Inicial</a>
        </div>
    </section>
    <footer>
        <small><a href="http://${initParam.website}">${initParam.copyright}</a></small>
    </footer>
</div>
</body>
</html>
