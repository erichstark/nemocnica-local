<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Generic page importing bootstrap and footer" pageEncoding="UTF-8" %>
<%@attribute name="pageTitle" required="true" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="${pageContext.request.contextPath}/fav/fav.ico"/>
    <link type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
    <link type="text/css" href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet"/>
    <title>${pageTitle}</title>
</head>
<body>
<jsp:doBody/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>