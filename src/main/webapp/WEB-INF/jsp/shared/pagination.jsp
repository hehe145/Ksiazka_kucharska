

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url var="firstPageUrl" value="${mainUrl}?page=0&size=${page.size}"/>
<c:url var="prevPageUrl" value="${mainUrl}?page=${page.number - 1}&size=${page.size}"/>
<c:url var="nextPageUrl" value="${mainUrl}?page=${page.number + 1}&size=${page.size}"/>
<c:url var="lastPageUrl" value="${mainUrl}?page=${page.totalPages - 1}&size=${page.size}"/>

<nav>
    <ul class="pagination">

        <li ${page.first?'class="disabled"':''}>
            <a class="btn btn-success" href="${page.first?'#':firstPageUrl}">
                <span>Pierwsza</span>
            </a>
        </li>


        <c:forEach var="pageIdx" begin="${0}" end="${page.totalPages-1}">
            <c:url var="pageUrl" value="${mainUrl}?page=${pageIdx}&size=${page.size}"/>
            <li ${pageIdx == page.number?'class="active"':''}>
                <a class="btn btn-success" href="${pageUrl}">${pageIdx+1}</a>
            </li>
        </c:forEach>



        <li ${page.last?'class="disabled"':''}>
            <a class="btn btn-success" href="${page.last?'#':lastPageUrl}">
                <span>Ostatnia</span>
            </a>
        </li>

    </ul>

</nav>
