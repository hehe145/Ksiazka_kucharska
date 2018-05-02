<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:import url="../shared/header.jsp">
    <c:param name="pageName" value="recipeDetails"></c:param>
</c:import>
<html>
    <head>

        <title>${recipe.name}</title>
    </head>
    <body>
        <div class="container" style="background-color: #f1df8b">
            <img src="/statics/images/${recipe.plik}" alt="">
            <h1>${recipe.name}</h1>
            <security:authorize access="hasRole('ROLE_USER')">
                <a class="btn btn-success" href="/recipe/pdf?id=${recipe.id}">Zapisz w pdf</a>
                <a class="btn btn-success" href="/recipeForm/?id=${recipe.id}">Edytuj</a
            </security:authorize>
            <security:authorize access="hasRole('ROLE_ADMIN')">
                <a class="btn btn-success" href="/recipe/pdf?id=${recipe.id}">Zapisz w pdf</a>
                <a class="btn btn-success" href="/recipeForm/?id=${recipe.id}">Edytuj</a>
                <a class="btn btn-danger" href="/recipes/?did=${recipe.id}">Usuń</a>
            </security:authorize>
            <br><br>
            Typ dania: <b>${recipe.category.name}</b><br>
            Trudność wykonania: <b>${recipe.levelOfDificulty.name}</b><br>
            Lista skladników:<br>
                <table class="table table-striped table-dark">
                    <thead>
                    <tr>
                        <th>Nazwa</th>
                        <th>Miara</th>
                        <th>Ilość</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${ingredientList}" var="ingredient">
                            <tr>
                                <td>${ingredient.name}</td>
                                <td>${ingredient.measure}</td>
                                <td>${ingredient.quantity}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            Przygotowanie:<br>
                <c:forEach items="${stepsOfRecipe}" var="step" varStatus="i">
                    <b>Krok ${i.index+1}</b><br>
                    <label>${step.content}</label><br>
                </c:forEach>
        </div>
    </body>
</html>
<c:import url="../shared/footer.jsp"/>