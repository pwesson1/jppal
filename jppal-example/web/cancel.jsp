<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cancel</title>
    </head>
    <body>
        <h1>Cancel</h1>
        <p>The payment was canceled</p>
        <dl>
        <c:forEach var="entry" items="${payment}">
            <dt>${entry.key}</dt>
            <dd><c:out value="${entry.value}"/></dd>
        </c:forEach>
        </dl>
    </body>
</html>
