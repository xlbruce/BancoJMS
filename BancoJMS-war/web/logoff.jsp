<%-- 
    Document   : logoff
    Created on : 13/10/2015, 22:42:34
    Author     : bruce
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    request.getSession().invalidate();
%>
