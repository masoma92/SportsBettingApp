<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Soma
  Date: 29/11/2019
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="../../resources/styles/js/bootstrap.min.js"></script>
    <title>Events</title>
    <link rel="stylesheet"
          href="../../resources/styles/css/bootstrap.min.css" />
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">SportsBetting</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="home">Home<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="events">Events</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Language
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="#">HU</a>
                    <a class="dropdown-item" href="#">EN</a>
                </div>
            </li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <a href="login" class="btn btn-outline-success my-2 my-sm-0" role="button">Logout</a>
        </ul>
    </div>
</nav>

<div class="m-3">
    <div class="card">
        <h5 class="card-header">Events</h5>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Title</th>
                <th scope="col">Type</th>
                <th scope="col">Start date</th>
                <th scope="col">End date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sportevents}" var="item">
                <tr>
                    <td><c:out value="${item.getId()}" /></td>
                    <td><c:out value="${item.getTitle()}" /></td>
                    <td><c:out value="${item.getType()}" /></td>
                    <td><c:out value="${item.getStartDate()}" /></td>
                    <td><c:out value="${item.getEndDate()}" /></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
