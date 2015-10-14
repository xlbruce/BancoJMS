<%-- 
    Document   : saque
    Created on : 13/10/2015, 20:01:56
    Author     : bruce
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Banco JMS</title>
    </head>
    <body>
        <h1>Quanto deseja sacar?</h1>
        <form action="FrontController" method="POST">
            <p><input type="number" name="valor_saque"/>
                <input type="submit" value="Sacar"/></p>
            <input type="hidden" name="command" value="SaqueCommand"/>
        </form>
        <p><a href="index.jsp">Voltar</a></p>
</html>
