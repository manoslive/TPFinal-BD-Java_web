<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <% session.removeAttribute("username");
            session.removeAttribute("password");
            session.removeAttribute("connected");
            session.invalidate();
            response.sendRedirect("index.jsp");
        %> 
        <h1>La déconnection fut un succès.</h1>