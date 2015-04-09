<%@tag description="User Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="error" required="true" %>
<%@attribute name="details" required="true" %>
<t:genericPage pageTitle="${error}">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="error-template">
                    <h1>Oops!</h1>

                    <h2>${error}</h2>

                    <div class="error-details">
                            ${details}
                    </div>
                    <div class="error-actions">
                        <button onclick="history.back()" class="btn btn-primary btn-lg">
                            <span class="glyphicon glyphicon-home"></span> Späť
                        </button>
                        <a href="${pageContext.request.contextPath}/" class="btn btn-default btn-lg">
                            <span class="glyphicon glyphicon-envelope"></span> Home
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</t:genericPage>