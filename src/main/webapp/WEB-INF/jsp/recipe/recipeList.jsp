<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:import url="../shared/header.jsp"/>
<html>
<head>
    <title>Lista przepisów</title>
</head>
    <body>
        <div id="main" class="container" style="background-color: #f1df8b">
            <H1>Lista przepisów</H1>

            <form:form id="searchForm" modelAttribute="searchCommand">
                <div class="row">
                    <div class="col-md-6">
                        <label for="phrase">Szukana fraza:</label>
                        <form:input path="phrase" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                        <form:errors path="phrase" cssClass="error text-danger" element="div"/>
                    </div>


                    <div class="col-md-3">
                    <label>Typ Przepisu:</label>
                    <form:select path="category" cssClass="form-control" cssErrorClass="form-control is-invalid" required="required">
                        <form:option value="0" >--wybierz typ przpisu--</form:option>
                        <form:options items="${categories}" itemLabel="name" itemValue="id"/>
                    </form:select>
                    </div>

                    <div class="col-md-3">
                    <label>Trudność wykonania:</label>
                    <form:select path="levelOfDificulty" cssClass="form-control" cssErrorClass="form-control is-invalid" required="required">
                        <form:option value="0">--Trudność wykonania--</form:option>
                        <form:options items="${levelsOfDificulty}" itemLabel="name" itemValue="id"/>
                    </form:select>
                    </div>
                </div>

                <div class="row">

                    <div class="form-group col-md-2">
                        <c:if test="${searchCommand.isEmpty() eq false}">
                            <a href="/recipes?all" class="btn btn-success">
                                <span class="glyphicon glyphicon-refresh"></span> Pokaż wszystkie
                            </a>
                        </c:if>
                    </div>

                    <div class="form-group col-md-2">
                        <button type="submit" class="btn btn-info">
                            <span class="glyphicon glyphicon-search"></span> Wyszukaj
                        </button>
                    </div>
                </div>
            </form:form>



            <div class="container">
                <div class="gallery col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <h1 class="gallery-title">Lista znalezionych przepisów</h1>
                </div>
                <div class="pagination" style="float: left">
                    <c:set var="pageSizes" value="${[2, 4, 10, 20]}"/>
                    <c:forEach var="size" items="${pageSizes}">
                        <c:url var="pageUrl" value="${mainUrl}?page=0&size=${size}" />
                        <li ${page.size eq size?'class="active"':''}>
                            <a class="btn btn-success" href="${pageUrl}">
                                <span>${size}</span>
                            </a>
                        </li>
                    </c:forEach>
                </div><br><br><br>
                <div class="row text-center text-lg-left">
                    <c:if test="${empty recipeList.content}">
                        ${searchCommand.isEmpty() ? 'Lista przepisów jest pusta':'Brak wyników wyszukiwania'}
                    </c:if>
                        <c:forEach items="${recipeList.content}" var="recipe">
                                <div class="col-lg-3 col-md-4 col-xs-6">
                                    <a href="/recipes/?id=${recipe.id}" class="d-block mb-4 h-100">
                                        <img src="/statics/images/${recipe.plik}" class="img-responsive" alt="${recipe.name}" width="250" height="200">
                                        <label>${recipe.name}</label> <br>
                                    </a>
                                </div>
                        </c:forEach>

                </div>

            </div>
            <div class="container">
                <c:set var="page" value="${recipeList}" scope="request"/>
                <c:set var="mainUrl" value="/recipes" scope="request"/>
                <jsp:include page="../shared/pagination.jsp"/>
            </div>
        </div>
    </body>
</html>
<c:import url="../shared/footer.jsp"/>