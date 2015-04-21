<%@tag description="User Page template" pageEncoding="UTF-8"
        %><%@taglib prefix="t" tagdir="/WEB-INF/tags"
        %><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
        %><%@attribute name="pageTitle" required="true"
        %><%@ attribute	name="searchForm" type="sk.stuba.fei.nemocnica.search.GenericSearchForm" required="true"
        %>
<c:set var="sf" scope="request" value="${searchForm}" />
<jsp:useBean id="sf" scope="request" class="sk.stuba.fei.nemocnica.search.GenericSearchForm"/>
<c:set var="maxPage" ><%=
(int) Math.ceil(  (double)sf.getNumFound()/(double)sf.getPerPage() )
%></c:set>

<c:url var="nextLink" value="${searForm.baseLink}" >
    <c:param name="curPage" value="${searchForm.curPage+1}"/>
</c:url>
<c:url var="lastLink" value="${searForm.baseLink}" >
    <c:param name="curPage" value="${ maxPage}"/>
</c:url>
<c:url var="previousLink" value="${searForm.baseLink}" >
    <c:param name="curPage" value="${searchForm.curPage-1}"/>
</c:url>
<c:url var="firstLink" value="${searForm.baseLink}" >
    <c:param name="curPage" value="0"/>
</c:url>


<c:choose>
    <c:when test="${searchForm.curPage le 0}">
        <span class="first">Prva</span>
        <span class="previous">Predchadzajuca</span>

    </c:when>
    <c:otherwise>
        <a class="first" href="${firstLink}">Prva</a>
        <a class="previous" href="${previousLink}">Predchadzajuca</a>

    </c:otherwise>

</c:choose>
${searchForm.numFound} záznamov, strana ${searchForm.curPage+1} / ${maxPage}
<c:choose>
    <c:when test="${searchForm.curPage ge maxPage-1 }">
        <span class="next">Nasledujuca</span>
        <span class="last"> Posledna </span>
    </c:when>
    <c:otherwise>
        <a class="next" href="${nextLink}">Nasledujuca</a>
        <a class="last" href="${lastLink}">Posledna </a>
    </c:otherwise>
</c:choose>


