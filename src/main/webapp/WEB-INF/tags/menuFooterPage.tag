<%@tag description="User Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@attribute name="pageTitle" required="true" %>
<sec:authentication var="user" property="principal"/>
<t:genericPage pageTitle="${pageTitle}">
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="${pageContext.request.contextPath}">Nemocnica Local</a>
            </div>
            <div class="navbar-header pull-right">
                <a href="#"> ${user}</a> <a href="${pageContext.request.contextPath}/logout">Logout</a>
            </div>
                <%--TODO generate menu from attribute--%>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/admin">Admin</a></li>
                    <li><a href="#">Link1</a></li>
                    <li><a href="#">Link2</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                           aria-expanded="false">Dropdown
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="#">Action1</a></li>
                            <li><a href="#">Action2</a></li>
                            <li><a href="#">Action3</a></li>
                            <li class="divider"></li>
                            <li class="dropdown-header">Nav header</li>
                            <li><a href="#">Separated link</a></li>
                            <li><a href="#">One more separated link</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container">
        <jsp:doBody/>
    </div>
    <footer class="footer">
        <div class="container">
            <p class="text-muted">Place sticky footer content here.</p>
        </div>
    </footer>
</t:genericPage>
