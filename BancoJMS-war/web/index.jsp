<%-- 
    Document   : index
    Created on : 08/10/2015, 20:57:46
    Author     : bruce
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="verificador_login.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Banco JMS</title>
        <%@include file="include_bootstrap.html" %>
    </head>
    <body>        
        <div class="container" role="group" aria-label="...">
            <h1>Bem-vindo(a) ${clienteLogado.nome}</h1>
            <div class="jumbotron">
            <h3>Selecione uma das opções abaixo</h3>
            <div class="btn-group-vertical">
                <button type="button" class="btn btn-default"><a href="FrontController?action=SaldoCommand">Saldo</a></button>
                <button type="button" class="btn btn-default"><a href="saque.jsp">Saque</a></button>
                <button type="button" class="btn btn-default"><a href="transferencia.jsp">Transferir</a></button>
                <button type="button" class="btn btn-default"><a href="FrontController?action=LogoffCommand">Logoff</a></button>
            </div>
            </div>
        </div>
    </body>
</html>