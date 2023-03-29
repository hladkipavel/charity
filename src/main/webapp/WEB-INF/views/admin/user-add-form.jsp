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
            <li><a href="/donation/donations-list" class="btn btn--without-border">Donacji</a></li>
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

<form:form cssClass="form-admin" method="post" action="/admin/add-user" modelAttribute="user">
    <h2>Dodanie nowego użytkownika</h2>
    <form:label cssClass="label-admin" path="firstName" for="firstName">Imię użytkownika:</form:label>
    <form:input cssClass="input-admin" path="firstName" type="text" name="firstName" id="firstName"/>
    <form:label cssClass="label-admin" path="lastName" for="lastName">Nazwisko użytkownika:</form:label>
    <form:input cssClass="input-admin" path="lastName" type="text" name="lastName" id="lastName"/>
    <form:label cssClass="label-admin" path="email" for="email">Email użytkownika:</form:label>
    <form:input cssClass="input-admin" path="email" type="text" name="email" id="email"/>
    <form:label cssClass="label-admin" path="password" for="password">Hasło użytkownika:</form:label>
    <form:input cssClass="input-admin" path="password" type="password" name="password" id="password"/>

    <input class="input-admin" type="submit" value="Dodać">
</form:form>
</body>
<jsp:include page="footer-admin.jsp"/>