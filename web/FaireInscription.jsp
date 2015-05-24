<%-- 
    Document   : FaireInscription
    Created on : 2015-05-09, 18:34:59
    Author     : Emmanuel
--%>

<%@page import="javax.swing.JOptionPane"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String password2 = request.getParameter("password2");
            String nomComplet = request.getParameter("nomcomplet");
            String adresse = request.getParameter("adresse");
            String telephone = request.getParameter("telephone");
            String courriel = request.getParameter("courriel");

            Website.Methodes fct = new Website.Methodes();
//            if (fct.VerifierUsager(username) == "false") {
//                if (password == password2 && password != null) {
            fct.AjouterClient(nomComplet, username, password, adresse, telephone, courriel);
            session.setAttribute("username", username);
            session.setAttribute("nom", nomComplet);
            response.sendRedirect("index.jsp");
//                }
//            } else {
//                %>
        //                <script>
        //                    $('#content').load('inscription.jsp');
        //                </script>
        //                <%
        //            }

        %> 
    </body>
</html>
