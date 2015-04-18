<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:dashboardPage pageTitle="Zamestnanci">
    <h1 class="page-header">Zamestnanci</h1>
    <table>
            <%--@elvariable id="zamestnanci" type="java.util.List<sk.stuba.fei.nemocnica.model.Zamestnanec"--%>
        <c:forEach var="zamestnanec" items="${zamestnanci}">
            <tr>
                <td>${zamestnanec.username}</td>
            </tr>
        </c:forEach>
    </table>
</t:dashboardPage>
