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
        <title>PayPal button</title>
    </head>
    <body>
        <h1>PayPal button</h1>
        <form name="form">
<pre>
    &lt;form action="${button.baseURL}/cgi-bin/webscr" method="post"&gt;
        &lt;input type="hidden" name="cmd" value="_s-xclick"&gt;
        &lt;input type="hidden" name="encrypted" value="${button.encrypted}"&gt;
        &lt;input type="submit" name="submit" value="${button.label}"&gt;
    &lt;/form&gt;
</pre>
        </form>
        <form action="${button.baseURL}/cgi-bin/webscr" method="post">
            <input type="hidden" name="cmd" value="_s-xclick">
            <input type="hidden" name="encrypted" value="${button.encrypted}">
            <input type="submit" name="submit" value="${button.label}">
        </form>
    </body>
</html>
