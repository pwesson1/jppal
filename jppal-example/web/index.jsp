<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Button parameters</title>
    </head>
    <body>
        <h1>Button parameters</h1>
        <form method="POST" action="build">
            <dl>
                <c:forTokens var="prop" delims=";" items="language;label;customerId;itemCode;itemLabel;price;currency;email;firstName;lastName;address1;address2;zip;city;state;country">
                    <dt>${prop}</dt>
                    <dd><input type="text" name="${prop}" value="${button[prop]}"></dd>
                </c:forTokens>
                <dd><input type="submit" name="submit" value="OK"></dd>
            </dl>
        </form>
    </body>
</html>
