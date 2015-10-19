<%-- 
    Document   : saldo
    Created on : 08/10/2015, 22:21:44
    Author     : bruce
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Banco JMS</title>
        <%@include file="include_bootstrap.html" %>
    </head>
    <body>
        <div class="container">
            <div class="jumbotron">
            <h2>Saldo atual: ${clienteLogado.saldoFormatado}</h2>
            <button type="button" class="btn btn-default"><a href="index.jsp">Voltar</a></button>
            </div>
        </div>
    </body>
</html>
