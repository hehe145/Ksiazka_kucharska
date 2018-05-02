<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css"
              href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css"
              href="/statics/css/main.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <style>body{background-image: url("/statics/images/background.jpg")};</style>
    </head>
    <body>


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

            <ul class="nav navbar-nav navbar-right">
                <security:authorize access="isAnonymous()">
                    <li class="nav-item">
                        <a class="nav-link" href="/registrationForm">Zarejestruj</a>

                    </li>
                </security:authorize>
                <security:authorize access="isAnonymous()">
                    <li class="nav-item">
                        <a class="nav-link" href="/login">Zaloguj się</a>
                    </li>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <li class="nav-item">
                        <label style="color:yellow; margin-top: 15px;">
                            Witaj <security:authentication property="principal.username"/>!
                        </label>
                    </li>
                    <li>
                        <a class="nav-link" href="#" onclick="document.getElementById('logout').submit()">Wyloguj się</a>
                        <form  class="nav-link" action="/logout" id="logout" method="post" style="display: none;">
                            <security:csrfInput/>
                        </form>
                    </li>
                </security:authorize>
            </ul>

        </div>
    </nav>


    </body>
</html>

