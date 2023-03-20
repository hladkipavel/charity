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
                Witaj <sec:authentication property="principal.username"/>
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
            <li><a href="/admin/users-list" class="btn btn--without-border">Użytkowniki</a></li>
            <li><a href="/" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>
</header>
<form class="form-delete" action="/admin/user-delete" method="post">
    <input type="hidden" name="id" value="${user.id}">
    <div class="form-container-delete">
        <div class="form-delete">
            <h3 class="h3-delete"> ${message}, skontaktujsie z odziłem pomocy żeby rawązac problem!</h3>
            <div class="form-buttons-delete">
                <input  type="submit" name="delete" value="Tak">
                <a href='<c:url value="/"/>'>Strona główna</a>
                <a href='<c:url value="/"/>'>Kontakt</a>
            </div>
        </div>
    </div>
</form>
    <footer>
        <div class="contact">
            <h2>Skontaktuj się z nami</h2>
            <h3>Formularz kontaktowy</h3>
            <form class="form--contact">
                <div class="form-group form-group--50"><input type="text" name="name" placeholder="Imię"/></div>
                <div class="form-group form-group--50"><input type="text" name="surname" placeholder="Nazwisko"/></div>

                <div class="form-group"><textarea name="message" placeholder="Wiadomość" rows="1"></textarea></div>

                <button class="btn" type="submit">Wyślij</button>
            </form>
        </div>
        <div class="bottom-line">
            <span class="bottom-line--copy">Copyright &copy; 2018</span>
            <div class="bottom-line--icons">
                <a href="https://www.facebook.com" class="btn btn--small"><img src="<c:url value="../../resources/images/icon-facebook.svg"/>"/></a> <a href="https://www.instagram.com"
                                                                                                                                                        class="btn btn--small">
                <img src="<c:url value="../../resources/images/icon-instagram.svg"/>"/></a>
            </div>
        </div>
    </footer>

    <script src="<c:url value="../../resources/js/app.js"/>"></script>
</html>