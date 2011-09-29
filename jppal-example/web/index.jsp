<%--
    jppal, a Paypal button generator in Java
    Copyright (C) 2011  Maurice Perry <maurice@perry.ch>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
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
                    <dd><input type="text" name="${prop}" value=""></dd>
                </c:forTokens>
                <dd><input type="submit" name="submit" value="OK"></dd>
            </dl>
        </form>
    </body>
</html>
