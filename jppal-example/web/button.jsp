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
