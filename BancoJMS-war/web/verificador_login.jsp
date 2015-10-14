<%-- 
    Document   : verificador_login
    Created on : 13/10/2015, 22:39:48
    Author     : bruce
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${empty clienteLogado}">
    <c:redirect url="login.html"/>
</c:if>