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
<h2><i>LISTA DONACJI</i></h2>
<div class="container-div">
    <table>
        <thead>
        <tr>
            <th>NAZWA</th>
            <th>OPIS</th>
            <th>AKCJE</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${donations}" var="donation" varStatus="status">
            <tr>
                <input type="hidden" name="id" value="${institution.id}" />
                <td>${varStatus="status"}</td>
                <td>${donation.city}</td>
                <td>${donation.street}</td>
                <td>${donation.phone}</td>
                <td>${donation.pickUpComment}</td>
                <td>${donation.pickUpDate}</td>
                <td>${donation.pickUpTime}</td>
                <td>${donation.quantity}</td>
                <td>${donation.zipCode}</td>
                <td>
                    <a class="btn-option" href='<c:url value="/admin/inst-details/${institution.id}"/>'>Szczegóły</a>
                    <a class="btn-option" href='<c:url value="/admin/inst-edit/${institution.id}"/>'>Edit</a>
                    <a class="btn-option" href='<c:url value="/admin/inst-delete/${institution.id}"/>'>Usuń</a>
                </td>
            </tr>
        </c:forEach>
        <h2><a class="btn-option" href='<c:url value="../../admin/add-institution"/>'>Dodać nową fundacje</a></br></h2>
        </tbody>
    </table>
</div>
</body>
<jsp:include page="../admin/footer-admin.jsp"/>