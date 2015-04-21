<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<t:menuFooterPage pageTitle="Lekari">
    <h2>Vyhladavanie lekarov</h2>
    <form:form cssClass="formular" method="post" commandName="zamestnanciSearchForm">
        <table>
            <tr>
                <td><label>Meno lekara</label></td>
                <td><form:input type="text" path="name"/></td>
            </tr>
            <tr>
                <td><label> priezvisko</label></td>
                <td><form:input type="text" path="surname"/></td>
            </tr>
            <tr>
                <td><label> Specializacia</label></td>
                <td><form:select path="specialization">
                    <form:option value="gynekologia" label="gynekologia"/>
                    <form:option value="chirurgia" label="chirurgia"/>
                    <form:option value="logopedia" label="logopedia"/>
                </form:select></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Potvrdiť"/></td>
            </tr>
        </table>

    </form:form>
    <t:paginator searchForm="${zamestnanciSearchForm}" pageTitle="lekari"/>
    <h1 class="page-header">Lekari</h1>
    <table>
            <%--@elvariable id="zamestnanci" type="java.util.List<sk.stuba.fei.nemocnica.model.Zamestnanec"--%>
        <c:forEach var="lekar" items="${lekari}">
            <tr>
                <td>${lekar.name} </td><td> ${lekar.surname}</td>
                <td>
                    <table>
                        <tr>
                            <c:forEach var="spec" items="${lekar.specializations}">
                                <td>
                                        ${spec}
                                </td>
                            </c:forEach>
                        </tr>
                    </table>
                </td>
            </tr>
        </c:forEach>
    </table>
</t:menuFooterPage>
