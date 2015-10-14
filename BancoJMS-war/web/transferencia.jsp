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
    </head>
    <body>
        <jsp:useBean class="com.br.banco.dao.ClienteDAO" id="dao"/>
        <c:set var="clientes" value="${dao.read()}"/>
        <h1>Menu de transferencia</h1>
        <form action="FrontController" method="POST">
            <fieldset>
                <legend>Transferencia entre correntistas</legend>
                <p>Destinatário</p>
                <select name="nro_conta_destinatario">
                    <option>--Escolha--</option>
                    <c:forEach items="${clientes}" var="cliente">
                        <option value="${cliente.nroConta}">${cliente.nome}</option>
                    </c:forEach>
                </select>
                <p>Valor:</p>
                <input type="number" name="valor_transferencia"/>
                <input type="submit" value="Transferir"/>
                <input type="hidden" name="command" value="TransferenciaCommand"/>
                       
            </fieldset>
        </form>
        <p><a href="index.jsp">Voltar</a></p>
    </body>
</html>
