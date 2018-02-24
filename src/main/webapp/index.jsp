<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ page import="java.util.* , javax.sql.* , java.sql.*" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: compilx
  Date: 3/30/16
  Time: 2:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <script type="text/javascript" src="vendor/jquery-2.2.0.min.js"></script>
    <script type="text/javascript" src="vendor/bootstrap-3.3.6-dist/js/bootstrap.js"></script>
    <script src="vendor/jquery-multipage/jquery.multipage.js"></script>
    <link rel="stylesheet" type="text/css" href="vendor/jquery-multipage/jquery.multipage.css" />
    <script type="text/javascript" src="js/scouter.js"></script>
    <link href="vendor/bootstrap-3.3.6-dist/css/bootstrap-theme.css" rel="stylesheet">
    <link href="vendor/bootstrap-3.3.6-dist/css/bootstrap.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>Scouting Sheet</title>
</head>

<body>
<sql:setDataSource var="matchSchedule" driver="com.mysql.jdbc.Driver"
                   url="jdbc:mysql://localhost:3306/tko1351_2016"
                   user="root"  password="babutastic"/>
<div id="header">Scouting Sheet: Stronghold</div>
<form id="initial-data" method="post" action="page2.jsp">
        <div class="row form-inline">
            <div style="text-align: center">
                <sql:query dataSource="${matchSchedule}" var="resultSchedule">
                    SELECT * from CalGamesSchedule ORDER BY matchLevel, matchNum;
                </sql:query>
                Match Number<select style="width: 175px; display: block; margin: 0 auto;" class="form-control" id="match-number" name="match-number">
                <c:forEach var="option" items="${resultSchedule.rows}">
                    <c:set var="number" value="${option.matchNum}"/>
                    <c:set var="type" value="${option.matchLevel}"/>
                    <option><%=pageContext.getAttribute("type")+" "+pageContext.getAttribute("number")%></option>
                </c:forEach>
            </select>
                <br>
                Alliance<select style="width: 175px; display: block; margin: 0 auto;" class="form-control" id="alliance" name="alliance">
                <option>Red</option>
                <option>Blue</option>
            </select>
            </div>
            <br>

            <input type="submit" class="btn btn-primary pull-right" id="page2-button" style="margin-right: 20px" value="Next Page"/>

        </div>
</form>
</body>
</html>