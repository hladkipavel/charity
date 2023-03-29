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
                    <li><a href="/admin/details/${institution.id}">Profil</a></li>
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
<div class="container-div">
    <table>
        <h2><i>SZCZEGÓŁY UŻYTKOWNIKA</i></h2>
        <thead>
        <tr>
            <th>IMIĘ</th>
            <th>NAZWISKO</th>
            <th>EMAIL</th>
            <th>ROLE</th>
            <th>STATUS</th>
            <th>AKCJE</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <input type="hidden" name="id" value="${user.id}" />
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
            <td>
                <c:forEach items="${user.roles}" var="role">
                    ${role.name}</br>
                </c:forEach>
            </td>
            <c:if test="${user.isBlocked()}">
                <td style="color: red ">Zablokowany</td>
            </c:if>
            <c:if test="${!user.isBlocked()}">
                <td style="color: green ">Aktywny</td>
            </c:if>
            <td>
                <c:if test="${!user.isBlocked()}">
                    <a class="btn-option" href='<c:url value="/admin/user-block/${user.id}"/>'>Zablokuj</a>
                </c:if>
                <c:if test="${user.isBlocked()}">
                    <a class="btn-option" href='<c:url value="/admin/user-unblock/${user.id}"/>'>Odblokuj</a>
                </c:if>
                <a class="btn-option" href='<c:url value="/admin/user-edit/${user.id}"/>'>Edytuj</a>
                <a class="btn-option" href='<c:url value="/admin/user-delete/${user.id}"/>'>Usuń</a>
            </td>
        </tr>
        <h2><a class="btn-option" href='<c:url value="/admin/add-user"/>'>Dodać nowego użytkownika</a></br></h2>
        </tbody>
    </table>
</div>
</body>
<jsp:include page="footer-admin.jsp"/>