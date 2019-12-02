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
    <title>Events</title>
    <script src="<c:url value="../../resources/styles/js/jquery.min.js" />"></script>
    <script src="<c:url value="../../resources/styles/js/bootstrap.min.js" />"></script>
    <link href="<c:url value="../../resources/styles/css/bootstrap.min.css" />"
          rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">${sportsbetting}</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="home">${home}<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="events">${events}</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    ${language}
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}?lang=hu">${hungarian}</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}?lang=en">${english}</a>
                </div>
            </li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <a href="login" class="btn btn-outline-success my-2 my-sm-0" role="button">${logout}</a>
        </ul>
    </div>
</nav>

<div class="m-3">
    <div class="card">
        <h5 class="card-header">${events}</h5>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">${event_title}</th>
                <th scope="col">${event_type}</th>
                <th scope="col">${start_date}</th>
                <th scope="col">${end_date}</th>
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
