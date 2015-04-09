<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:genericPage pageTitle="Prihlásenie">
    <div class="container">
        <div id="login-panel" class="panel panel-default">
            <div class="panel-heading"><h3 class="panel-title"><strong>Prihlásenie</strong></h3></div>
            <div class="panel-body">
                <form role="form" action="<c:url value='/login.do'/>" method="POST">
                    <div class="form-group">
                        <label for="username">Prihlasovacie meno</label>
                        <input type="text" class="form-control" id="username" name="username" required autofocus>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" name="password">
                    </div>
                    <button type="submit" class="btn btn-sm btn-default">Prihlásiť</button>
                </form>
            </div>
        </div>
    </div>
</t:genericPage>
