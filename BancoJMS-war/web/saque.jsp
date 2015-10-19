<%-- 
    Document   : saque
    Created on : 13/10/2015, 20:01:56
    Author     : bruce
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <h1>Quanto deseja sacar?</h1>
            <div class="jumbotron">
                <form class="" action="FrontController" method="POST">
                    <div class="col-lg-2">
                        <input class="form-control" type="number" name="valor_saque"/>
                    </div>
                    <input class="btn btn-default"type="submit" value="Sacar"/>
                    <input type="hidden" name="command" value="SaqueCommand"/>
                    <button type="button" class="btn btn-default"><a href="index.jsp">Voltar</a></button>
                </form>
            </div>
            <c:if test="${param.result == 'sacado'}">    
                <style>
                    #success {
                        display: none;
                    }
                </style>
                <div id="success" class="alert alert-success" role="alert">Saque efetuado com sucesso!</div>
                <script>
                    $('#success').slideToggle('slow');
                </script>
            </c:if>

            <c:if test="${param.result == 'saldo_insuficiente'}">
                <style>
                    #saldo_insuficiente {
                        display: none;
                    }
                </style>
                <div id="saldo_insuficiente" class="alert alert-danger" role="alert">Saldo insuficiente!</div>
                <script>
                    $('#saldo_insuficiente').slideToggle('slow');
                </script>
            </c:if>
        </div>
</html>
