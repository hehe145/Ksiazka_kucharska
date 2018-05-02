<%@ page contentType="text/html;charset=UTF-8" language="java" %>


        <nav class="navbar navbar-inverse" >
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="/">Wytwórnia smaków</a>
                </div>
                <ul class="nav navbar-nav">

                    <li><a href="/recipes/">Przepisy</a></li>
                    <li sec:authorize="isAuthenticated()">
                        <a href="/recipes/addForm">Dodaj przepis</a></li>
                    </li>
                </ul>

                <%--############################## LOGOWANIE i REJESTRACJA########################--%>
                <ul class="nav navbar-nav navbar-right">
                    <li sec:authorize="isAnonymous()">
                        <a th:href="@{/registrationForm.html}">Zarejestruj</a>
                    </li>
                    <li sec:authorize="isAnonymous()"
                        th:class="${pageName eq 'logonForm'}? 'active'">
                        <a th:href="@{/login}">Zaloguj się</a>
                    </li>
                    <li sec:authorize="isAuthenticated()">
                        <label style="color:yellow; margin-top: 15px;">
                            Witaj <span sec:authentication="name"/>!
                        </label>
                    </li>
                    <li sec:authorize="isAuthenticated()">
                        <a href="#" onclick="document.getElementById('logout').submit()">Wyloguj się</a>
                        <form th:action="@{/logout}" id="logout" method="post" style="display: none;">

                            <input
                                    type="hidden"
                                    th:name="${_csrf.parameterName}"
                                    th:value="${_csrf.token}"/>
                        </form>
                    </li>
                </ul>
                <%--##################################### LOGOWANIE i REJESTRACJA ########################--%>
            </div>

        </nav>
