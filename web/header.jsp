<script>
    $(document).ready(function () {
        $('#panier_achat').click(function () {
            $('#content').load('panier.jsp');
            });
        });
</script>
<%
    Website.Methodes fct = new Website.Methodes();
    out.print("<div id='login_form'>");
    if (session.getAttribute("username") == null || session.getAttribute("username")=="null") {
        out.print("Veuillez vous identifier!");
        out.print("<form action='LoginCheck.jsp' method='post'>"
                + "<input type='text' id='username' name='username' placeholder=\"Nom d'utilisateur\" size=\"15\"/> "
                + " <input type='password' id='password' name='password' placeholder='Mot de passe' size=\"15\"/> "
                + " <input type='submit' id='BTN_Login' value='Connection'> "
                + " </form>"
                + " </div>");
    } else {
        
        out.print("Bonjour, " + session.getAttribute("nom") + " ");
        out.print("<button onclick=\"location.href = 'Logout.jsp'\" method='post'>Déconnection</button></div>");
        out.print("<div id='panier_achat'><img src='Images/pannier.png' alt='pannier'>" + fct.getNombreItemPanier(session.getAttribute("username").toString()) + " item(s) dans votre panier" + "</div>");
    }
    out.print("<div id='image'>"
            + "<img src='Images/header_logo_last.png' alt='header'/>"
            + "</div>");
%>