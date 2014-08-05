<%-- 
    Document   : hello_spnego
    Created on : 03.04.2013, 12:31:15
    Author     : panfilov_ms
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Hello SPNEGO Example</title>
</head>
<body>
Hello <%= request.getRemoteUser() %> !
</body>
</html>
