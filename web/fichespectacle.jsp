<script type="text/javascript">
    function AllerInscription() {
        $("#content").load("inscription.jsp");
    }
    function onChange_Choix() {
        var selectNomSpectacle = document.getElementById('infoSpectacle_titre').innerHTML;
        var selectSection = document.getElementById('infoSpectacle_SelectSection').value;
        var selectNbBillets = document.getElementById('infoSpectacle_SelectNbBillets').value;
        var selectSalle = document.getElementById('infoSpectacle_SelectSalle').value;
        var selectDate = document.getElementById('infoSpectacle_SelectDate').value;
        var connected = "<%= session.getAttribute("username") %>";
        if(connected !== "null"){
            if (selectNbBillets !== '0' ) {
                document.getElementById('infoSpectacle').style.minHeight = "400px";
                var container = document.getElementById('info_spectacle_confirmation');
                container.innerHTML = '<hr id="hr">' +
                        '<table id="infoSpectacle_confimer_table">' +
                        '<tr>' +
                        '<td><p>Spectacle: </p></td>' +
                        '<td><p id="info_spectacle_confirmer_nomSpectacle">' + selectNomSpectacle + '</p></td>' +
                        '</tr>' +
                        '<tr>' +
                        '<td><p>Date: </p></td>' +
                        '<td><p id="info_spectacle_confirmer_date">' + selectDate + '</p></td>' +
                        '</tr>' +
                        '<tr>' +
                        '<td><p>Salle: </p></td>' +
                        '<td><p id="info_spectacle_confirmer_salle">' + selectSalle + '</p></td>' +
                        '</tr>' +
                        '<tr>' +
                        '<td><p>Section: </p></td>' +
                        '<td><p id="info_spectacle_confirmer_section">' + selectSection + '</p></td>' +
                        '</tr>' +
                        '<tr>' +
                        '<td><p>Nombre de billets: </p></td>' +
                        '<td><p id="info_spectacle_confirmer_nbBillets">' + selectNbBillets + '</p></td>' +
                        '</tr>' +
                        '<tr>' +
                        '<td><p>Prix: </p></td>' +
                        '<td><p id="info_spectacle_confirmer_prix">' + '</p></td>' +
                        '</tr>' +
                        '</table>' +
                        '<span id="button_confirmer" onclick="confirmerAjout()">Ajouter ></span>';
                        getPrix();
            }else{
                var container = document.getElementById('info_spectacle_confirmation');
                document.getElementById('infoSpectacle').style.minHeight = "250px";
                container.innerHTML = '';
            }
        }else{
            var container = document.getElementById('info_spectacle_confirmation');
            container.innerHTML = '<span id="button_message_connecter" onclick=\"AllerInscription()\">S.V.P vous connecter pour pouvoir acheter un billet!</span>';
        }
    }
//    function fillSectionCombobox(nomSalle, combo) {
//        var urlString = "/TPFinalBD_web/Spectacle?nomSalle=" + nomSalle;
//        $.ajax({
//            type: "GET",
//            url: urlString,
//            success: function (result) {
//                $(combo).html(result);
//            }
//        });
//    }
    function confirmerAjout(){
        var NomSpectacle = document.getElementById('info_spectacle_confirmer_nomSpectacle').innerHTML;
        var Section = document.getElementById('info_spectacle_confirmer_section').innerHTML;
        var NbBillets = document.getElementById('info_spectacle_confirmer_nbBillets').innerHTML;
        var Salle = document.getElementById('info_spectacle_confirmer_salle').innerHTML;
        var maDate = document.getElementById('info_spectacle_confirmer_date').innerHTML;
        var urlString = "/TPFinalBD_web/AjouterAuPanier?nomSpectacle=" + NomSpectacle + "&section="+Section+ "&nbBillets="+NbBillets+
                                                                     "&salle="+Salle+ "&date="+maDate;
        $.ajax({
            type: "GET",
            url: urlString,
            success: function () {
                $("#header").load("header.jsp");
                $('#content').load('panier.jsp');
            }
        });
    }
    function getPrix(){
        var Section = document.getElementById('info_spectacle_confirmer_section').innerHTML;
        var NbBillets = document.getElementById('info_spectacle_confirmer_nbBillets').innerHTML;
        var urlString = "/TPFinalBD_web/PrixSection?nomSection=" + Section + "&nbBillets="+NbBillets;
        $.ajax({
            type: "GET",
            url: urlString,
            success: function (result) {
                $('#info_spectacle_confirmer_prix').html(result);
            }
        });
    };
</script>

<div id="fiche_spectacle">

</div>
