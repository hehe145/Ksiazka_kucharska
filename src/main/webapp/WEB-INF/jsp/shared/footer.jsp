<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<footer>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container container-fluid">
            <a class="navbar-brand" href="/">Wytwórnia smaków</a>

            <ul class="nav navbar-nav navbar-left">

                <li class="nav-item">
                    <a class="nav-link" href="/recipes?all">Przepisy</a>
                </li>
                <security:authorize access="hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')">

                    <li class="nav-item">
                        <a class="nav-link" href="/recipeForm">Dodaj przepis</a>
                    </li>

                </security:authorize>
            </ul>
        <span class="text-muted">2018 Robert Tarczyński</span>
        </div>
    </nav>

</footer>
