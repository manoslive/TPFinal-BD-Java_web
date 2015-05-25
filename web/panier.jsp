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
        var urlString = "/TPFinal-BD-Java_web/Spectacle?nomSalle=" + nomSalle;
        $.ajax({
            type: "GET",
            url: urlString,
            success: function (result) {
                $(combo).html(result);
                setComboSection();
            }
        });
    }
    function fillQuantiteBillet(nomSection, nomSpectacle, nomSalle, numericUpDown, date) {
        var urlString = "/TPFinal-BD-Java_web/nb?nomSection=" + nomSection + "&nomSpectacle=" + nomSpectacle + "&nomSalle=" + nomSalle+ "&date=" + date;
        $.ajax({
            type: "GET",
            url: urlString,
            success: function (result) {
                if($(numericUpDown > result)){
                    //$(numericUpDown).attr('value',"0");
                    $(numericUpDown).attr('max', result);
                }else{
                    $(numericUpDown).attr('max', result);
                }
            }
        });
    }
    function fillDateSpectacles(nomSpectacle, selectNomSalle,  input)
    {
        var urlString = "/TPFinal-BD-Java_web/dates?nomSpectacle=" + nomSpectacle+"&nomSalle="+selectNomSalle;
        $.ajax({
            type: "GET",
            url: urlString,
            success: function (result) {
                $(input).html(result);
                //setComboDate();
            }
        });
    }
    function modifierItem(numBillet, numElement, facture) {
        var Salle, Date, Section, Quantite;
        var cb_name = "CB_Salles" + numElement;
        Salle = $("#" + cb_name + " :selected").text();
        cb_name = "CB_Dates" + numElement;
        Date = $("#" + cb_name + " :selected").text();
        cb_name = "CB_Sections" + numElement;
        Section = $("#" + cb_name + " :selected").text();
        cb_name = "NUD_Quantite" + numElement;
        Quantite = $("#" + cb_name).val();

        var urlString = "/TPFinal-BD-Java_web/ModifierBillet?numBillet=" + numBillet
                + "&salle=" + Salle
                + "&date=" + Date
                + "&section=" + Section
                + "&quantite=" + Quantite
                + "&action=modifier";
        $.ajax({
            type: "GET",
            url: urlString,
            success: function () {
                if(facture === false){
                    $('#content').load('panier.jsp');
                    $("#header").load("header.jsp");
                }
            }
        });
    }
    function modifierToutPanier(){
        <%for (int h = 0; h < billets.size(); h++) {%>
            modifierItem(<%=billets.get(h).getNumBillet()%>, <%=h+1%>, true);
        <%}%>
    }
    function supprimerItem(numBillet) {
        var urlString = "/TPFinal-BD-Java_web/ModifierBillet?numBillet=" + numBillet + "&action=supprimer";
        $.ajax({
            type: "GET",
            url: urlString,
            success: function () {
                $('#content').load('panier.jsp');
                $("#header").load("header.jsp");
            }
        });
    }
    function setComboDate(){
        //Remplir les champs selon le panier du client
        var m = 1, date, cb_name;
        <%  for(int k=0; k< billets.size(); k++)
            {%> 
                date = "<%= billets.get(k).getDateRepresentation() %>";
                cb_name = "CB_Dates"+m;
                document.getElementById(cb_name).value = date;
                m++;
          <%}%>
    }
    function setComboSalle(){
        //Remplir les champs selon le panier du client
        var m = 1, salle, cb_name;
        <%  for(int k=0; k< billets.size(); k++)
            {%> 
                salle = "<%= billets.get(k).getNomSalle() %>";
                cb_name = "CB_Salles"+m;
                document.getElementById(cb_name).value = salle;
                m++;
          <%}%>
    }
    function setComboSection(){
        //Remplir les champs selon le panier du client
        var m = 1, section, cb_name;
        <%  for(int k=0; k< billets.size(); k++)
            {%> 
                section = "<%= billets.get(k).getNomSection() %>";
                cb_name = "CB_Sections"+m;
                document.getElementById(cb_name).value = section;
                m++;
          <%}%>
    }
    function setComboQuantite(){
        //Remplir les champs selon le panier du client
        var m = 1, quantite, cb_name;
        <%  for(int k=0; k< billets.size(); k++)
            {%> 
                quantite = "<%= billets.get(k).getQuantiteBillets()%>";
                //cb_name = "CB_Sections"+m;
                $("NUD_Quantite").attr('value', quantite);
                //document.getElementById(cb_name).value = section;
                m++;
          <%}%>
    }
    $(document).ready(function () {   
        // Combobox salle
        $(".CB_Salles").change(function () {
            var nomSpectacle = document.getElementById('titre').innerHTML;
            var selectNomSalle = $(this).val();
            nomSpectacle = String(nomSpectacle);
            fillDateSpectacles(nomSpectacle, selectNomSalle,  $(this).siblings(".CB_Dates"));
            fillSectionCombobox($(this).val(), $(this).siblings(".CB_Sections"));
        });
        $(".CB_Salles").change();
        setComboSalle();
        // Combobox section
        $(".CB_Sections").change(function () {
            var nomSpectacle = document.getElementById('titre').innerHTML;
            if(nomSpectacle !== null){
                fillQuantiteBillet($(this).val(), nomSpectacle, $(this).siblings(".CB_Salles").val(), $(this).siblings(".NUD_Quantite"), $(this).siblings(".CB_Dates").val());
            }
        });
        //setComboQuantite();
    });
    function AfficherFacture() {
        modifierToutPanier();
        $("#content").load("facture.jsp", function () {
            var urlString = "/TPFinal-BD-Java_web/facture";
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
        int j = 1, totalFacture = 0;
        for (int i = 0; i < billets.size(); i++) {
            int somme = billets.get(i).getQuantiteBillets() * fct.getPrixParSection(billets.get(i).getNomSection());
            totalFacture += somme;
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
                        + " <button class='Billets_BTN_Modifier' onclick='modifierItem("+ billets.get(i).getNumBillet()+","+j+", false)'>Modifier</button> <button class='Billets_BTN_Supprimer' onclick='supprimerItem("+ billets.get(i).getNumBillet()+")'>Supprimer</button>"
                        + " </div>"            
                        + " </div>"
                );
                j++;
            }
        out.print("<p>"+"Total: $" + totalFacture + ".00"+"</p><button id='BTN_Facture' onclick='AfficherFacture();'>Payer et afficher facture!</button>");
    } else {
        out.print("<h1>Votre panier est vide " + fct.getNomCompletClient(session.getAttribute("username").toString()) + "</h1>");
    }
    out.print("</div>");
%>
