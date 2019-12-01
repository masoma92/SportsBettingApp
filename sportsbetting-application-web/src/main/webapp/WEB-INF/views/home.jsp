<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Soma
  Date: 28/11/2019
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="../../resources/styles/js/bootstrap.min.js"></script>
    <title>Home</title>
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
        <h5 class="card-header">Account details</h5>
        <form method="post" class="m-3">
            <div class="form-group">
                <label>Name</label>
                <input class="form-control" placeholder="Enter name" name="playername" required>
            </div>
            <div class="form-group">
                <label>Date of birth</label>
                <input type="date" class="form-control" placeholder="Enter date of birth" name="dateofbirth" required>
            </div>
            <div class="form-group">
                <label>Account number</label>
                <input class="form-control" placeholder="XXXXXXXX-XXXXXXXX" name="accountnumber" pattern="^\d{8}-\d{8}$" required>
            </div>
            <div class="form-group">
                <label for="exampleFormControlSelect1">Example select</label>
                <select class="form-control" id="exampleFormControlSelect1" name="currency">
                    <option>HUF</option>
                    <option>EUR</option>
                    <option>USD</option>
                </select>
            </div>
            <div class="form-group">
                <label>Balance</label>
                <input type="number" min="1000" class="form-control"placeholder="Minimum amount 1000" name="balance" required>
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
        </form>
    </div>
</div>

<div class="m-3">
    <div class="card">
        <h5 class="card-header">Player info</h5>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">E-mail</th>
                <th scope="col">Account number</th>
                <th scope="col">Birth</th>
                <th scope="col">Balance</th>
                <th scope="col">Currency</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${player.getName()}</td>
                <td>${player.getEmail()}</td>
                <td>${player.getAccountNumber()}</td>
                <td>${player.getBirth()}</td>
                <td>${player.getBalance()}</td>
                <td>${player.getCurrency()}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<div class="m-3">
    <div class="card">
        <h5 class="card-header">Wagers</h5>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col"> </th>
                <th scope="col">#</th>
                <th scope="col">Event title</th>
                <th scope="col">Event type</th>
                <th scope="col">Bet type</th>
                <th scope="col">Outcome value</th>
                <th scope="col">Outcome odd</th>
                <th scope="col">Wager amount</th>
                <th scope="col">Winner</th>
                <th scope="col">Processed</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${wagers}" var="item">
                    <tr>
                        <td><button type="button" class="btn btn-primary">Remove</button></td>
                        <td><c:out value="${item.getId()}" /></td>
                        <td><c:out value="${item.getOdd().getOutcome().getBet().getEvent().getTitle()} - ${item.getOdd().getOutcome().getBet().getEvent().getStartDate()}" /></td>
                        <td><c:out value="${item.getOdd().getOutcome().getBet().getEvent().getType()}" /></td>
                        <td><c:out value="${item.getOdd().getOutcome().getBet().getType()}" /></td>
                        <td><c:out value="${item.getOdd().getOutcome().getDescription()}" /></td>
                        <td><c:out value="${item.getOdd().getValue()}" /></td>
                        <td><c:out value="${item.getAmount()} ${item.getCurrency()}" /></td>
                        <td><c:out value="${item.isWin()}" /></td>
                        <td><c:out value="${item.isProcessed()}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
