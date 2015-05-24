<%@page import="java.sql.Array"%>
<%@page import="java.util.ArrayList"%>

<script type="text/javascript">
    function fillDateSpectacles(nomSpectacle,nomSalle, input)
    {
        var urlString = "/TPFinalBD_web/dates?nomSpectacle=" + nomSpectacle + "&nomSalle=" + nomSalle;
        $.ajax({
            type: "GET",
            url: urlString,
            success: function (result) {
                $(input).html(result);
            }
        });
    }
    function fillSectionCombobox(nomSalle, combo) {
        var urlString = "/TPFinalBD_web/Spectacle?nomSalle=" + nomSalle;
        $.ajax({
            type: "GET",
            url: urlString,
            success: function (result) {
                $(combo).html(result);
            }
        });
    }
    function afficherSpectacle(titre) {
        document.getElementById("content").style.height = '450';
        $("#content").load("fichespectacle.jsp", function () {
            var urlString = "/TPFinalBD_web/InfoSpectacle?nomSpectacle=" + titre;
            $.ajax({
                type: "GET",
                url: urlString,
                success: function (result) {
                    $('#fiche_spectacle').html(result);

                    $("#infoSpectacle_SelectSalle").change(function () {
                        fillSectionCombobox($(this).val(), $('#infoSpectacle_SelectSection'));
                        var selectNomSpectacle = document.getElementById('infoSpectacle_titre').innerHTML;
                        var selectNomSalle = $(this).val();
                        fillDateSpectacles(selectNomSpectacle,selectNomSalle, $('#infoSpectacle_SelectDate'));
                    });
                    $("#infoSpectacle_SelectSalle").change();
                }
            });
        });
    }
    function ReplaceContentInContainer(id, content) {
        var container = document.getElementById(id);
        container.innerHTML = content;
    }
    ;
    function construireRecherche(Affiche, Titre, Categorie, Artiste, Salle) {
        var container = '';
        container = ' <div class="dateRepresentation">' + Artiste + '</div> ' +
                ' <div class="spectacle" onclick="afficherSpectacle($(this).find(\'h3\').text())">' +
                ' <img class="affiche" src="Images/' + Affiche + '">' +
                ' <h3 class="titre">' + Titre + '</h3>' +
                ' <p class="categorie">' + Categorie + '</p>' +
                ' <p id="salle" style="visiblity:visible; display:block">' + Salle + '</p>' +
                ' <div>' +
                ' <span class="button_achat">Achat > </span>' +
                ' </div>' +
                ' </div>';
        return container;
    }
    function afficherTousSpectacle(){
        <%
        Website.Methodes lesMethodes = new Website.Methodes();
        ArrayList<Website.Spectacles> laListSpectacles = lesMethodes.afficherTousSpectaclesRecherche();
        %>
        var Affiche, Titre, Categorie, Artiste, Salle, container="";       
        <%for (int i = 0; i < laListSpectacles.size(); i++) {%>
            Affiche = "<%=laListSpectacles.get(i).getAffiche()%>";
            Titre = "<%= laListSpectacles.get(i).getNomSpectacle()%>";
            Categorie = "<%=laListSpectacles.get(i).getCategorie()%>";
            Artiste = "<%=laListSpectacles.get(i).getArtiste()%>";
            Salle = "<%=laListSpectacles.get(i).getNomSalle()%>";
            container += construireRecherche(Affiche, Titre, Categorie, Artiste, Salle);
        <%}%>
        return container;
    }
    function setCookie(titre, categorie, artiste, salle){
    
    }
    function effectuerRechercher() {
        document.getElementById('salle').style.display = 'block';
        var list_cb = document.getElementsByName("cb_detailRecherche");
        var list_choix_cb = [];
        var x, y;
        y = 0;
        for (x = 0; x < list_cb.length; x++) {
            if (list_cb[x].checked === true) {
                list_choix_cb[y] = list_cb[x];
                y++;
            }
        }
    <%Website.Methodes methodes = new Website.Methodes();%>
    <%ArrayList<Website.Spectacles> listSpectacles;%>
    <%listSpectacles = methodes.afficherTousSpectaclesRecherche();%>
        var divSpectacle = "content_spectacle";
        var container = '';
        var recherche = false;
        ReplaceContentInContainer("content_spectacle", ' ');
        if (list_choix_cb.length > 0) {
            var Affiche, Titre, Categorie, Artiste, Salle;
            for (x = 0; x < list_choix_cb.length; x++) {
    <%for (int i = 0; i < listSpectacles.size(); i++) {%>
                Affiche = "<%=listSpectacles.get(i).getAffiche()%>";
                Titre = "<%= listSpectacles.get(i).getNomSpectacle()%>";
                Categorie = "<%=listSpectacles.get(i).getCategorie()%>";
                Artiste = "<%=listSpectacles.get(i).getArtiste()%>";
                Salle = "<%=listSpectacles.get(i).getNomSalle()%>";
                if (list_choix_cb[x].value === Titre) {
                    container += construireRecherche(Affiche, Titre, Categorie, Artiste, Salle);
                    recherche = true;   
                }else if (list_choix_cb[x].value === Categorie) {
                    container += construireRecherche(Affiche, Titre, Categorie, Artiste, Salle);
                    recherche = true;   
                }else if(list_choix_cb[x].value === Artiste){
                    container += construireRecherche(Affiche, Titre, Categorie, Artiste, Salle);
                    recherche = true;
                }else if(list_choix_cb[x].value === Salle){
                    container += construireRecherche(Affiche, Titre, Categorie, Artiste, Salle);
                    recherche = true;
                }
//                if(recherche){ //setCookies
//                 <%-- if (request.getCookies() != null) {
                     boolean bTitre = false, bCategorie=false, bArtiste=false, bSalle=false;
                        for (Cookie cookie : request.getCookies()) {
                            switch(cookie.getName()){
                                case "titre": 
                                    cookie.setValue(listSpectacles.get(i).getNomSpectacle());
                                    response.addCookie(cookie);
                                    bTitre=true;
                                    break;
                                case "categorie": 
                                    cookie.setValue(listSpectacles.get(i).getCategorie());
                                    response.addCookie(cookie);
                                    bCategorie=true;
                                    break;
                                case "artiste": 
                                    cookie.setValue(listSpectacles.get(i).getArtiste());
                                    response.addCookie(cookie);
                                    bArtiste=true;
                                    break;
                                case "salle": 
                                    cookie.setValue(listSpectacles.get(i).getNomSalle());
                                    response.addCookie(cookie);
                                    bSalle=true;
                                    break;
                            }
                        }
                        if (!bTitre) {
                            Cookie titre = new Cookie("titre",listSpectacles.get(i).getNomSpectacle());
                            response.addCookie(titre);
                        }
                        if (!bCategorie){
                            Cookie categorie = new Cookie("categorie",listSpectacles.get(i).getCategorie());
                            response.addCookie(categorie);
                        }
                        if (!bArtiste) {
                            Cookie artiste = new Cookie("artist",listSpectacles.get(i).getArtiste());
                            response.addCookie(artiste);
                        }
                        if (!bSalle) {
                            Cookie salle = new Cookie("salle",listSpectacles.get(i).getNomSalle());
                            response.addCookie(salle);
                        }
//                    }--%>
//                }
    <%}%>
            }
        } else {
            container = afficherTousSpectacle();
        }
        ReplaceContentInContainer(divSpectacle, container);
    }
    ;
</script>
<div id="recherche_detail" >
    <%
        Website.Methodes methode = new Website.Methodes();
        int j = 1;
    %>
    <div id="choix_categorie">
        <fieldset class="group"> 
            <legend>Nom de spectacle</legend> 
            <ul class="checkbox_categorie"> 
                <%for (String txt1 : methode.rechercheParTitres()) {%>
                <li><input type="checkbox" name="cb_detailRecherche" id="cb<%=j%>" value="<%=txt1%>" /><label for="cb<%=j%>"><%=txt1.substring(0,1)+txt1.substring(1).toLowerCase()%></label></li> 
                    <% j += 1; %>
                    <%}%>
            </ul> 
        </fieldset> 
    </div>
    <div id="choix_categorie">
        <fieldset class="group"> 
            <legend>Catégories</legend> 
            <ul class="checkbox_categorie"> 
                <%for (String txt2 : methode.rechercheParCategorie()) {%>
                <li><input type="checkbox" name="cb_detailRecherche" id="cb<%=j%>" value="<%=txt2%>" /><label for="cb<%=j%>"><%=txt2.substring(0,1)+txt2.substring(1).toLowerCase()%></label></li> 
                    <% j += 1; %>
                    <%}%>
            </ul> 
        </fieldset> 
    </div>
    <div id="choix_salle">
        <fieldset class="group"> 
            <legend>Salles</legend> 
            <ul class="checkbox_salle"> 
                <%for (String txt3 : methode.rechercheParSalle()) {%>
                <li><input type="checkbox" name="cb_detailRecherche" id="cb<%=j%>" value="<%=txt3%>" /><label for="cb<%=j%>"><%=txt3.substring(0,1)+txt3.substring(1).toLowerCase()%></label></li>
                    <% j += 1; %>
                    <%}%>
            </ul> 
        </fieldset> 
    </div>
    <div id="choix_artiste">
        <fieldset class="group"> 
            <legend>Artistes</legend> 
            <ul class="checkbox_artiste"> 
                <%for (String txt4 : methode.rechercheParArtiste()) {%>
                <li><input type="checkbox" name="cb_detailRecherche" id="cb<%=j%>" value="<%=txt4%>" /><label for="cb<%=j%>"><%=txt4.substring(0,1)+txt4.substring(1).toLowerCase()%></label></li>
                    <% j += 1; %>
                    <%}%>
            </ul> 
        </fieldset> 
    </div>
</div>
<div id="BTN_Rechercher_Div">
    <button id="BTN_Rechercher" onclick="effectuerRechercher()">Rechercher</button>
</div>
<div id="content_spectacle" >
    <%
        Website.Methodes mesMethodes = new Website.Methodes();
        ArrayList<Website.Spectacles> maListSpectacles = mesMethodes.afficherTousSpectacles();
    %>
    <%for (int i = 0; i < maListSpectacles.size(); i++) {%>
    <div class="dateRepresentation"><%=maListSpectacles.get(i).getArtiste()%></div> 
    <div class="spectacle" onclick="afficherSpectacle($(this).find('h3').text())">
        <img class="affiche" src="Images/<%=maListSpectacles.get(i).getAffiche()%>">
        <h3 class="titre"><%= maListSpectacles.get(i).getNomSpectacle()%></h3>
        <p class="categorie"><%=maListSpectacles.get(i).getCategorie()%></p>
        <p id="salle"><%=maListSpectacles.get(i).getNomSalle()%></p>
        <div>
            <span class="button_achat">Achat > </span>
        </div>
    </div>
    <%}%> 
</div>
