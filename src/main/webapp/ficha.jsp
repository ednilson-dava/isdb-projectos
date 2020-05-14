<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta content="text/html" http-equiv="Content-Type" charset="UTF-8"/>
    <title>Ficha de avaliacao</title>
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo-1.png" sizes="32x32">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ficha.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mobile.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script>
        function modulochange(id) {
            console.log(id.value);
            $.post('${pageContext.request.contextPath}/ModSem', {idmodulo: id.value}, function (res) {
                console.log(res);
            });
        }
    </script>
</head>
<body>
<div>
    <header id="ficha-header">
        <div class="dombosco-brand">
            <a href="${pageContext.request.contextPath}/">
                <img src="${pageContext.request.contextPath}/img/logo-black.png"/>
            </a>
        </div>
        <div class="ficha-informacao">
            <p>${fichaBean.curso.nome}</p>
            <div>
                <div>
                    <p>${fichaBean.estudante.codigo}</p>
                </div>
                <div>
                    <a href="${pageContext.request.contextPath}/logout">
                        Terminar sessão
                    </a>
                </div>
            </div>
        </div>
        <button class="f-btn-navbar"><span class="f-ic-nav">
            <span> </span>
            <span class="f-ic-2"> </span>
        </span></button>
        <div id="f-nav-ficha">
            <div class="f-nav-info">
                <p>${fichaBean.curso.nome}</p>
                <p>${fichaBean.estudante.codigo}</p>
            </div>
            <ul class="f-nav-menu">
                <li>
                    <a href="${pageContext.request.contextPath}/logout">
                        Terminar sessão
                    </a>
                </li>
            </ul>
        </div>
    </header>
    <form action="${pageContext.request.contextPath}/fichas" method="post">
        <aside>
            <div>
                <c:choose>
                    <c:when test="${fichaBean.avaliacoes.size() != 0}">
                        <ul>
                            <c:forEach var="aval" items="${fichaBean.avaliacoes}">
                                <li>${aval.modulo.nome}</li>
                            </c:forEach>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <p>Nenhuma avaliacao feita</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </aside>
        <div>
            <c:forEach var="pergunta" items="${fichaBean.perguntas}">
                <div class="it-pergunta">
                    <div class="it-desc">
                        <p>${pergunta.descricao}</p>
                    </div>
                    <c:forEach var="modl" items="${fichaBean.modulos}">
                        <div class="it-modl">
                            <div class="it-perg-mod">
                                <p>${modl.nome}</p>
                            </div>
                            <div class="it-perg-res">
                                <c:choose>
                                    <c:when test="${pergunta.tipo == 'ESCOLHA'}">
                                        <label>
                                            <input name="classificao-${pergunta.id}-${modl.id}" type="radio" value="1">
                                            Insuficiente
                                        </label>
                                        <label>
                                            <input name="classificao-${pergunta.id}-${modl.id}" type="radio" value="2">
                                            Razoavel
                                        </label>
                                        <label>
                                            <input name="classificao-${pergunta.id}-${modl.id}" type="radio" value="3">
                                            Bom
                                        </label>
                                        <label>
                                            <input name="classificao-${pergunta.id}-${modl.id}" type="radio" value="4">
                                            Muito Bom
                                        </label>
                                        <label>
                                            <input name="classificao-${pergunta.id}-${modl.id}" type="radio" value="5">
                                            Excelente
                                        </label>
                                    </c:when>
                                    <c:when test="${pergunta.tipo == 'TEXTO'}">
                                        <div class="it-comentario">
                                            <textarea name="comentario-${pergunta.id}-${modl.id}" cols="80"
                                                      rows="3"></textarea>
                                        </div>
                                    </c:when>
                                </c:choose>
                            </div>
                            <div class="it-perg-res-mob">
                                <c:choose>
                                    <c:when test="${pergunta.tipo == 'ESCOLHA'}">
                                        <label>
                                            <input name="classificao-${pergunta.id}-${modl.id}" type="radio" value="1">
                                            1
                                        </label>
                                        <label>
                                            <input name="classificao-${pergunta.id}-${modl.id}" type="radio" value="2">
                                            2
                                        </label>
                                        <label>
                                            <input name="classificao-${pergunta.id}-${modl.id}" type="radio" value="3">
                                            3
                                        </label>
                                        <label>
                                            <input name="classificao-${pergunta.id}-${modl.id}" type="radio" value="4">
                                            4
                                        </label>
                                        <label>
                                            <input name="classificao-${pergunta.id}-${modl.id}" type="radio" value="5">
                                            5
                                        </label>
                                    </c:when>
                                    <c:when test="${pergunta.tipo == 'TEXTO'}">
                                        <div class="it-comentario">
                                            <textarea name="comentario-${pergunta.id}-${modl.id}" cols="80"
                                                      rows="3"></textarea>
                                        </div>
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:forEach>
            <div class="it-sb">
                <input type="submit" value="Submeter"/>
            </div>
        </div>
    </form>
    <footer>
        <small><a href="http://${initParam.website}">${initParam.copyright}</a></small>
    </footer>
</div>
</body>
</html>
