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
    <aside>
        <div>
            <label>Escolher Modulo</label>
            <br/>
            <select id="ls-modulos">
                <c:forEach var="modulo" items="${fichaBean.modulos}">
                    <option value="${modulo.id}">${modulo.nome}</option>
                </c:forEach>
            </select>
            <div>
                <button onclick="guardarModuloAvaliado()">Guardar</button>
            </div>
            <div class="it-sb">
                <p>Modulos avaliados</p>
                <ul id="modulosAvaliados">
                    <c:forEach var="avaliado" items="${fichaBean.modulosConcluidos}">
                        <li>${avaliado.nome}</li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </aside>
    <form id="frm-modulo-avaliacao" action="${pageContext.request.contextPath}/fichas" method="post">
        <div>
            <c:forEach var="pergunta" items="${fichaBean.perguntas}">
                <div class="it-pergunta">
                    <div class="it-desc">
                        <p>${pergunta.descricao}</p>
                    </div>
                    <div class="it-perg-res">
                        <c:choose>
                            <c:when test="${pergunta.tipo == 'ESCOLHA'}">
                                <label>
                                    <input name="classificacao-${pergunta.id}" type="radio" value="1">
                                    Insuficiente
                                </label>
                                <label>
                                    <input name="classificacao-${pergunta.id}" type="radio" value="2">
                                    Razoavel
                                </label>
                                <label>
                                    <input name="classificacao-${pergunta.id}" type="radio" value="3">
                                    Bom
                                </label>
                                <label>
                                    <input name="classificacao-${pergunta.id}" type="radio" value="4">
                                    Muito Bom
                                </label>
                                <label>
                                    <input name="classificacao-${pergunta.id}" type="radio" value="5">
                                    Excelente
                                </label>
                            </c:when>
                            <c:when test="${pergunta.tipo == 'TEXTO'}">
                                <div class="it-comentario">
                                    <textarea name="comentario-${pergunta.id}" rows="3"></textarea>
                                </div>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </c:forEach>
        </div>
    </form>
    <footer>
        <small><a href="http://${initParam.website}">${initParam.copyright}</a></small>
    </footer>
</div>
<script>
    function guardarModuloAvaliado() {
        let formulario = $("#frm-modulo-avaliacao")[0];
        let dados = [];
        let index = 0;
        let codModulo = $("#ls-modulos")[0].value;
        for (let i = 0; i < formulario.length; i++) {
            let name = formulario[i].name;
            if (name.startsWith("classificacao")) {
                let checked = formulario[i].checked;
                if (checked) {
                    let value = formulario[i].value;
                    let dado = {name, value};
                    dados[index] = dado;
                    index++;
                }
            } else if (name.startsWith("comentario")) {
                let value = formulario[i].value;
                let dado = {name, value};
                dados[index] = dado;
                index++;
            }
        }
        let name = "modulo"
        let value = codModulo;
        let dado = {name, value};
        dados[index] = dado;
        $.post("${pageContext.request.contextPath}/saveModuleEvaluation", dados).done(function (resp) {
            $("#ls-modulos").html(resp);
        });
    }
</script>
</body>
</html>
