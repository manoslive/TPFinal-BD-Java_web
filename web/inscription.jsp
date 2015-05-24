<% if (session.getAttribute("username") == null) { %>
<div class='content_inscription'>
    <form class="form-container" action='FaireInscription.jsp' method="post">
        <div class="form-title"><h2>Inscription</h2></div>

        <div class="form-title">Nom d'usager</div>
        <input class="form-field" type="text" name="username" id="username" placeholder="(sensible à la case)" required/><br />

        <div class="form-title">Mot de passe</div>
        <input class="form-field" type="password" name="password" id="password" placeholder="(sensible à la case)" required/><br />

        <div class="form-title">Confirmer mot de passe</div>
        <input class="form-field" type="password" name="password2" id="password2" placeholder="Répétez le mot de passe" required/><br />

<!--        <div class="form-title">Nom complet</div>
        <input class="form-field" type="text" name="nom" id="nom" placeholder="Prénom et nom" required=""/><br />-->

        <div class="form-title">Nom complet</div>
        <input class="form-field" type="text" name="nomcomplet" id="nomcomplet" placeholder="Prénom et nom" required=""/><br />


        <div class="form-title">Adresse</div>
        <input class="form-field" type="text" name="adresse" id="adresse" placeholder="Votre adresse complète" required=""/><br />

        <div class="form-title">Numéro de téléphone</div>
        <input class="form-field" type="tel" name="telephone" id="adresse" placeholder="(xxx)xxx-xxxx" required/><br />

        <div class="form-title">Courriel</div>
        <input class="form-field" type="email" name="courriel" id="courriel" placeholder="abc@abc.abc" required/><br />


        <div class="submit-container">
            <input class="submit-button" type="submit" value="Submit" />
        </div>
    </form>
</div>
<%
    } else {
        out.print("<div class='content_inscription'><h2 class=\"dejaConnecterMessage\">Vous êtes déjà inscrit!</h2></div>");
    }
%>
<!--<script>
    $(document).ready(function () {
        document.getElementById("#content").style.padding= "30%";
    });
</script>-->