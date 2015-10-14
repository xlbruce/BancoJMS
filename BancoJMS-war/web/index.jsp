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
    </head>
    <body>        
        <h1>Bem-vindo(a) ${clienteLogado.nome}</h1>
        <h3>Selecione uma das opções abaixo</h3>
        <ul>
            <li><a href="FrontController?action=SaldoCommand">Saldo</a></li>
            <li><a href="saque.jsp">Saque</a></li>
            <li><a href="transferencia.jsp">Transferir</a></li>
            <li><a href="FrontController?action=LogoffCommand">Logoff</a></li>
        </ul>
    </body>
</html>