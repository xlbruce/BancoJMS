<%-- 
    Document   : transferencia
    Created on : 13/10/2015, 22:09:32
    Author     : bruce
--%>

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
        <jsp:useBean class="com.br.banco.dao.ClienteDAO" id="dao"/>
        <c:set var="clientes" value="${dao.read()}"/>
        <div class="container">
            <h1>Menu de transferencia</h1>
            <form action="FrontController" method="POST">
                <fieldset>
                    <legend>Transferencia entre correntistas</legend>
                    <div class="col-xs-3">
                        <p>Destinatário</p>
                        <div class="form-group">
                            <select class="form-control" name="nro_conta_destinatario">
                                <option>--Escolha--</option>
                                <c:forEach items="${clientes}" var="cliente">
                                    <option value="${cliente.nroConta}">${cliente.nome}</option>
                                </c:forEach>
                            </select>
                            <br>
                            <p>Valor:</p>
                            <input type="number" class="form-control" name="valor_transferencia"/><br>
                            <input type="submit" class="btn btn-success"value="Transferir"/>
                            <input type="hidden" name="command" value="TransferenciaCommand"/>

                        </div>
                    </div>
                </fieldset>
            </form>
            <button type="button" class="btn btn-default"><a href="index.jsp">Voltar</a></button>
            <br><br>
            <c:if test="${param.result == 'success'}">
                <style>
                    #success {
                        display: none;
                    }
                </style>
                <div id="success" class="alert alert-success" role="alert">Transferencia realizada com sucesso!</div>
                <script>
                    $('#success').slideToggle('slow');
                </script>
            </c:if>

            <c:if test="${param.result == 'failure'}">
                <style>
                    #failure {
                        display: none;
                    }
                </style>
                <div id="failure" class="alert alert-danger" role="alert">Saldo insuficiente!</div>
                <script>
                    $('#failure').slideToggle('slow');
                </script>
            </c:if>
        </div>
    </body>
</html>
