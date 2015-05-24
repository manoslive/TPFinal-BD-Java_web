<%@page import="Website.Methodes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
        <%
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Website.Methodes fct = new Website.Methodes();
            if (fct.EstPresent(username, password).equals("true")) {
                session.setAttribute("username", username);
                session.setAttribute("nom", fct.getNomCompletClient(username));
            }
            else{
                session.setAttribute("connected", "yes");
            }
            response.sendRedirect("index.jsp");

        %>