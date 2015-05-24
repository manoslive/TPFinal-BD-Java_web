<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<script>
    var recherche = false;
    // load accueil dans le div #content
    // load inscription dans le div #content
    $(document).ready(function () {
        $('#accueil').click(function () {
            $('#content').load('spectacles.jsp');
            document.getElementById("content").style.display = 'inline-flex';
            recherche = false;
        });
        $('#inscription').click(function () {
            $('#content').load('inscription.jsp');
        });
        $('#Surprise').click(function () {
            $('#content').html("<div class='IMG_Hobbit'><img src='Images/hobbit_cage.png' class='IMG_Hobbit'</img></div>");
        });
        $('#menu_recherche').click(function () {
            if (recherche === false) {
                document.getElementById("recherche_detail").style.display = 'block';
                document.getElementById("content_spectacle").style.display = 'block';
                document.getElementById("BTN_Rechercher_Div").style.display = 'inline-block';
                document.getElementById("content").style.display = 'inline-block';
                recherche = !recherche;
            }
        });
    });
</script>
</head>
<div id="cssmenu">
    <% Website.Methodes methode = new Website.Methodes();%>
    <ul>
        <li class='active'><a id="accueil" data-title='Accueil'>Accueil</a></li>
        <li><a id="inscription" data-title='Inscription'>Inscription</a></li>
        <li><a id="menu_recherche" data-title='Recherche'>Recherche</a></li>
        <li><a id="Surprise" data-title='Surprise'>Surprise</a></li>
    </ul>
</div>
