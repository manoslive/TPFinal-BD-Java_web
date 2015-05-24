<%@page import="java.util.ArrayList"%>
<%@page import="Website.Billets"%>
<%
    Website.Methodes fct = new Website.Methodes();
    ArrayList<Billets> billets;
    String username = session.getAttribute("username").toString();

    billets = fct.AfficherPanierClient(fct.GetNumClient(username));
%>
<script>
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
    function fillQuantiteBillet(nomSection, nomSpectacle, nomSalle, numericUpDown, date) {
        var urlString = "/TPFinalBD_web/nb?nomSection=" + nomSection + "&nomSpectacle=" + nomSpectacle + "&nomSalle=" + nomSalle+ "&date=" + date;
        $.ajax({
            type: "GET",
            url: urlString,
            success: function (result) {
                if($(numericUpDown).val() >= result){
                    $(numericUpDown).attr('value',result);
                    $(numericUpDown).attr('max', result);
                }else{
                    $(numericUpDown).attr('max', result);
                }
            }
        });
    }
    function fillDateSpectacles(nomSpectacle, selectNomSalle,  input)
    {
        var urlString = "/TPFinalBD_web/dates?nomSpectacle=" + nomSpectacle+"&nomSalle="+selectNomSalle;
        $.ajax({
            type: "GET",
            url: urlString,
            success: function (result) {
                $(input).html(result);
            }
        });
    }
    function modifierItem(numBillet, numElement) {
        var Salle, Date, Section, Quantite;
        var cb_name = "CB_Salles" + numElement;
        Salle = $("#" + cb_name + " :selected").text();
        cb_name = "CB_Dates" + numElement;
        Date = $("#" + cb_name + " :selected").text();
        cb_name = "CB_Sections" + numElement;
        Section = $("#" + cb_name + " :selected").text();
        cb_name = "NUD_Quantite" + numElement;
        Quantite = $("#" + cb_name).val();

        var urlString = "/TPFinalBD_web/ModifierBillet?numBillet=" + numBillet
                + "&salle=" + Salle
                + "&date=" + Date
                + "&section=" + Section
                + "&quantite=" + Quantite
                + "&action=modifier";
        $.ajax({
            type: "GET",
            url: urlString,
            success: function () {
                $('#content').load('panier.jsp');
                $("#header").load("header.jsp");
            }
        });
    }
    function supprimerItem(numBillet) {
        var urlString = "/TPFinalBD_web/ModifierBillet?numBillet=" + numBillet + "&action=supprimer";
        $.ajax({
            type: "GET",
            url: urlString,
            success: function () {
                $('#content').load('panier.jsp');
                $("#header").load("header.jsp");
            }
        });
    }
    $(document).ready(function () {
        // Combobox salle
        $(".CB_Salles").change(function () {
            fillSectionCombobox($(this).val(), $(this).siblings(".CB_Sections"));
            var nomSpectacle = document.getElementById('titre').innerHTML;
            var selectNomSalle = document.getElementById('CB_Dates').innerHTML;
            alert(selectNomSalle);
            nomSpectacle = String(nomSpectacle);
            fillDateSpectacles(nomSpectacle, selectNomSalle,  $(this).siblings(".CB_Dates"));
        });
        $(".CB_Salles").change();
        // Combobox section
        $(".CB_Sections").change(function () {
            var nomSpectacle = document.getElementById('titre').innerHTML;
            nomSpectacle = String(nomSpectacle);
            fillQuantiteBillet($(this).val(), nomSpectacle, $(this).siblings(".CB_Salles").val(), $(this).siblings(".NUD_Quantite"), $(this).siblings(".CB_Dates").val());
        });
        var m = 1, salle, section, date, cb_name;
   
        <%  for(int k=0; k< billets.size(); k++)
            {%> 
                salle = "<%= billets.get(k).getNomSalle() %>";
                section = "<%= billets.get(k).getNomSection() %>";
                date = "<%= billets.get(k).getDateRepresentation() %>";
                cb_name = "CB_Salles"+m;
                document.getElementById(cb_name).value = salle;
                cb_name = "CB_Dates"+m;
                //alert(section);
                document.getElementById(cb_name).value = section;
                cb_name = "CB_Sections"+m;
                //alert(date);
                document.getElementById(cb_name).value = date;
                m++;
          <%}%>
    });
    function AfficherFacture() {
        $("#content").load("facture.jsp", function () {
            var urlString = "/TPFinalBD_web/facture";
            $.ajax({
                type: "GET",
                url: urlString,
                success: function (result) {
                    $('#facture').html(result);
                    // Met à jour le header(nb item panier)
                    $("#header").load("header.jsp");
                }
            });
        });
    }
    ;
</script>
<%
    /*Website.Methodes fct = new Website.Methodes();
    ArrayList<Billets> billets;
    String username = session.getAttribute("username").toString();

    billets = fct.AfficherPanierClient(fct.GetNumClient(username));*/
    boolean afficherPanier = false;
    out.print("<div id='content_panier'");
    // Boucle qui vérifie si les billets ont un numéro de facture pour
    // ne pas les afficher dans le panier
    for(int i = 0;i<billets.size();i++){
        if(billets.get(i).getNumFacture() == 0){
            afficherPanier = true;
        }
    }
    if (billets.size() > 0 && afficherPanier) {
        
        out.print("<h1> Billets pour "
                + fct.getNomCompletClient(username)
                + "</h1>"
        );
        int j = 1;
        for (int i = 0; i < billets.size(); i++) {
                out.print(" <div class='dateRepresentation'>" + billets.get(i).getNomArtiste() + "</div>"
                        + " <div class='panier'> <div class='panier_billets_ID'>ID#"+ billets.get(i).getNumBillet() +"</div>"
                        + " <img class='affiche' src='Images/" + billets.get(i).GetAfficheSpectacle() + "'>"
                        + " <h3 class='titre' id='titre'>" + billets.get(i).getNomSpectacle() + "</h3>"
                        + " <p class='artiste'>" + billets.get(i).getNomCategorie() + "</p>"
                        + " <div id='boutons'>"
                        + " <select class='CB_Salles' id='CB_Salles"+j+"'>" + fct.FillComboboxPanier(billets.get(i).getNomSpectacle()) + "</select>"
                        + " <select class='CB_Dates' id='CB_Dates"+j+"'></select>"
                        + " <select class='CB_Sections' id='CB_Sections"+j+"'></select>"
                        + " <input class='NUD_Quantite' id='NUD_Quantite"+j+"' type='number' value='" + billets.get(i).getQuantiteBillets() + "' min='0'/>"
                        + " <button class='Billets_BTN_Modifier' onclick='modifierItem("+ billets.get(i).getNumBillet()+","+j+")'>Modifier</button> <button class='Billets_BTN_Supprimer' onclick='supprimerItem("+ billets.get(i).getNumBillet()+")'>Supprimer</button>"
                        + " </div>"            
                        + " </div>"
                );
                j++;
            }
        out.print("<button id='BTN_Facture' onclick='AfficherFacture();'>Payer et afficher facture!</button>");
    } else {
        out.print("<h1>Votre panier est vide " + fct.getNomCompletClient(session.getAttribute("username").toString()) + "</h1>");
    }
    out.print("</div>");
%>
