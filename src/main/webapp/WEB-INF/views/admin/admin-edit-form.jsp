<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>

    <link rel="stylesheet" href="<c:url value="../../resources/css/style.css"/>"/>
</head>
<header class="header--form-page">
    <nav class="container container--70">
        <ul class="nav--actions">
            <li class="logged-user">
                Witaj <sec:authentication property="principal.name"/>
                <ul class="dropdown">
                    <li><a href="#">Profil</a></li>
                    <li><a href="#">Moje zbiórki</a></li>
                    <li><a href="/logout">Wyloguj</a></li>
                </ul>
            </li>
        </ul>

        <ul>
            <li><a href="/" class="btn btn--without-border active">Start</a></li>
            <li><a href="/admin/institution" class="btn btn--without-border">Fundacji</a></li>
            <li><a href="/admin/users-list" class="btn btn--without-border">Użytkownicy</a></li>
            <li><a href="/admin/admins-list" class="btn btn--without-border">Administratorzy</a></li>
            <li><a href="#contact1" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Oddaj rzeczy, których już nie chcesz<br />
                <span class="uppercase">potrzebującym</span>
            </h1>

            <div class="slogan--steps">
                <div class="slogan--steps-title">Wystarczą 4 proste kroki:</div>
                <ul class="slogan--steps-boxes">
                    <li>
                        <div><em>1</em><span>Wybierz rzeczy</span></div>
                    </li>
                    <li>
                        <div><em>2</em><span>Spakuj je w worki</span></div>
                    </li>
                    <li>
                        <div><em>3</em><span>Wybierz fundację</span></div>
                    </li>
                    <li>
                        <div><em>4</em><span>Zamów kuriera</span></div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>
<body>
<form:form cssClass="form-admin" method="post" action="/admin/admin-edit" modelAttribute="admin">
    <h2>Edycja danych fundacji</h2>
    <input class="input-admin" type="hidden" name="id" value="${admin.id}" />
    <label class="label-admin" for="firstName">Imię administratora:</label>
    <input class="input-admin" type="text" name="firstName" id="firstName" required value="${admin.firstName}">
    <label class="label-admin" for="lastName">Nazwisko administratora:</label>
    <input class="input-admin" type="text" name="lastName" id="lastName" required value="${admin.lastName}">
    <label class="label-admin" for="email">Email administratora:</label>
    <input class="input-admin" type="text" name="email" id="email" required value="${admin.email}">
    <label class="label-admin" for="password">Hasło administratora:</label>
    <input class="input-admin" type="password" name="password" id="password" required value="${admin.password}">
    <input class="input-admin" type="submit" value="Edytować">
</form:form>
</body>
<jsp:include page="footer-admin.jsp"/>