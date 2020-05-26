<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="cad-configuracao">
    <form action="${pageContext.request.contextPath}/configuracao" method="post">
        <table>
            <caption>Configurações Gerais</caption>
            <tbody>
            <tr>
                <td><label>Semestre: ${fichaBean.geral.semestre}</label></td>
                <td><label>Data: <fmt:formatDate value="${fichaBean.dataConfiguracao}" type="date"
                                                 pattern="dd-MM-yyyy"/></label></td>
            </tr>
            <tr>
                <td><label>Semestre Actual</label></td>
                <td>
                    <select name="semestre">
                        <option value="1">Primeiro</option>
                        <option value="2">Segundo</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><input type="submit" value="Guardar"/></td>
            </tr>
            </tbody>
        </table>
    </form>
    <table>
        <caption>Informações Gerais</caption>
        <tbody>
        <tr>
            <td>Numero Total de Estudantes</td>
            <td><c:out value="${requestScope.cEstudantes}"/></td>
        </tr>
        <tr>
            <td>Numero Total de Docentes</td>
            <td><c:out value="${requestScope.cDocentes}"/></td>
        </tr>
        <tr>
            <td>Numero Total de Departamentos</td>
            <td><c:out value="${requestScope.cDepartamentos}"/></td>
        </tr>
        <tr>
            <td>Numero Total de Cursos</td>
            <td><c:out value="${requestScope.cCursos}"/></td>
        </tr>
        <tr>
            <td>Numero Total de Avaliações</td>
            <td><c:out value="${requestScope.cAvaliacoes}"/></td>
        </tr>
        <tr>
            <td>Numero Total de Estudantes Sem Avaliaçao</td>
            <td></td>
            <td><a href="#">Imprimir</a></td>
        </tr>
        </tbody>
    </table>

    <form action="${pageContext.request.contextPath}/relatorio" method="post">
        <table>
            <caption>Relatorios</caption>
            <tbody>
            <tr>
                <td>Ano Lectivo</td>
                <td>
                    <select onchange="selectAnoLectivo(this)">
                        <option>Selecionar ano</option>
                        <c:forEach var="ano" items="${fichaBean.anoLectivoAvaliacao}">
                            <option value="${ano}">${ano}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Departamento</td>
                <td>
                    <select class="jDepartamentos" onchange="selectDepartamento(this)">

                    </select>
                </td>
            </tr>
            <tr>
                <td>Curso</td>
                <td>
                    <select class="jCursos" onchange="selectCurso(this)">

                    </select>
                </td>
            </tr>
            <tr>
                <td>Ano Curricular</td>
                <td>
                    <select class="jAnoCurricular">

                    </select>
                </td>
                <td>Semestre</td>
                <td></td>
            </tr>
            <tr>
                <td>Modulo</td>
                <td></td>
                <td>Docente</td>
                <td></td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td>
                    <button>Submeter</button>
                </td>
                <td>...</td>
            </tr>
            </tfoot>
        </table>
    </form>
</div>
