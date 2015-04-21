<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<t:menuFooterPage pageTitle="Ambulancie">
    <h2>Vyhladavanie ambulancii</h2>
    <form:form cssClass="formular" method="post" commandName="ambulancieSearchForm">
        <table>
            <tr>
                <td><label>Názov ambulanie</label></td>
                <td><form:input type="text" path="meno"></form:input></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Potvrdiť"></input></td>
            </tr>
        </table>

    </form:form>
    <t:paginator searchForm="${ambulancieSearchForm}" pageTitle="Ambulancie"/>
    <h1 class="page-header">Ambulancie</h1>
    <table>
            <%--@elvariable id="zamestnanci" type="java.util.List<sk.stuba.fei.nemocnica.model.Zamestnanec"--%>
        <c:forEach var="ambulancia" items="${ambulancie}">
            <tr>
                <td>${ambulancia.name}</td>
            </tr>
        </c:forEach>
    </table>
</t:menuFooterPage>
