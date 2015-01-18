<%--
  Created by IntelliJ IDEA.
  User: Konrad
  Date: 2015-01-03
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Hrllo</title>
</head>
<body>
    Hey! Hi! Hello! ${str} ${customers}
    <c:set var="salary" scope="session" value="${2000*2}"/>
    <c:if test="${salary > 2000}">
    <p>My salary is: <c:out value="${salary}"/><p>
        </c:if>
    controller
</body>
</html>
