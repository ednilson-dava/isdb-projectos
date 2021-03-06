<%--
  Created by IntelliJ IDEA.
  User: Ednilson
  Date: 10-Mar-20
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta content="text/html" http-equiv="Content-Type" charset="UTF-8"/>
    <title>ISDB Admin Console</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/console.css"/>
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo-1.png" sizes="32x32">
</head>
<body>
<div>
    <header>
        <nav class="nav navbar">
            <ul id="navigation-menu">
                <li><a onclick="optionchange('${pageContext.request.contextPath}/docentes')" href="#">Docentes</a></li>
                <li><a onclick="optionchange('${pageContext.request.contextPath}/modulos')" href="#">Modulos</a></li>
                <li><a onclick="optionchange('${pageContext.request.contextPath}/cursos')" href="#">Cursos</a></li>
                <li><a onclick="optionchange('${pageContext.request.contextPath}/departamentos')" href="#">Departamentos</a></li>
                <li><a onclick="optionchange('${pageContext.request.contextPath}/perguntas')" href="#">Perguntas</a></li>
                <li><a onclick="optionchange('${pageContext.request.contextPath}/estudantesgui')" href="#">Estudantes</a></li>
                <li><a onclick="optionchange('${pageContext.request.contextPath}/coordenadores')" href="#">Coordenador</a></li>
                <li><a onclick="optionchange('${pageContext.request.contextPath}/configuracao')" href="#">Configuração</a></li>
            </ul>
            <div class="dataset-upload">
                <form action="${pageContext.request.contextPath}/files" method="post"
                      enctype="multipart/form-data">
                    <input type="file" name="departamento-file"/>
                    <input type="submit" value="Upload">
                </form>
                <a href="${pageContext.request.contextPath}/logout">
                    Terminar sessão
                </a>
            </div>
        </nav>
    </header>
    <section id="sistema-console">

    </section>
    <footer>
        <small><a href="http://${initParam.website}">${initParam.copyright}</a></small>
    </footer>
</div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script>
    function selectAnoLectivo(anoLectivo) {
        $.ajax("${pageContext.request.contextPath}/anolecectivoaval", {
            type: 'post',
            data: {
                "janol": anoLectivo.value
            }
        }).done(function (resp) {
            $(".jDepartamentos").html(resp);

            $(".jCursos").html(" ");
            $(".jAnoCurricular").html(" ");
        })
    }

    function selectDepartamento(jdep) {
        $.ajax("${pageContext.request.contextPath}/configuracao", {
            type: 'post',
            data: {
                "jDp": jdep.value
            }
        }).done(function (resp) {
            $(".jCursos").html(resp);
            $(".jAnoCurricular").html(" ");
        })
    }

    function selectCurso(cur) {
        $.ajax("${pageContext.request.contextPath}/configuracao", {
            type: 'post',
            data: {
                "jCur": cur.value
            }
        }).done(function (resp) {
            $(".jAnoCurricular").html(resp);
        })
    }

    function optionchange(page) {
        $.ajax(page, {
            beforeSend: function () {
                $("#sistema-console").html("Aguarde..");
            }
        }).done(function (resp) {
            $("#sistema-console").html(resp);
        })
    }

    function selectDepartamentoManger(dep) {
        $.ajax("${pageContext.request.contextPath}/depmanagersearch", {
            type: 'post',
            data: {
                "cod-departamento": dep.value
            }
        }).done(function (resp) {
            $(".jDepartamento-row").html(resp);
        })
    }

    function jCoordenardor(codCurso, codModulo, codDocente, curso, modulo, ano, semestre, docente, regime) {

    }

    function guardarDepMan() {
        let table = $(".jDepartamento-row")[0].rows;
        let dados = [];
        for(let i = 0; i <table.length; i++) {
            let col = table[i].cells;
            let dado = {};
            dado.codCurso = col[0].children[0].value;
            dado.codModulo = col[0].children[1].value;
            dado.codDocente = col[0].children[2].value;
            dado.curso = col[1].children[0].value;
            dado.modulo = col[2].children[0].value;
            dado.ano = col[3].children[0].value;
            dado.semestre = col[4].children[0].value;
            dado.docente = col[5].children[0].value;
            dado.regime = col[6].children[0].value;
            dados[i] = dado;
        }

        $.each(dados, function (index) {
            $.post("${pageContext.request.contextPath}/coordenadores", dados[index]);
        });

    }
</script>
</body>
</html>
