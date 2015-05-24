
<!--
Fait par Emmanuel Beloin le 06/05/2015
-->
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="index.css">
        <link rel="stylesheet" type="text/css" href="inscription.css">
        <link rel="stylesheet" href="styles.css">
        <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
        <script src="script.js"></script>
        <title>Index</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div id="main">
            <div id="wrapper">
                <div id="header">
                    <script>
                        $("#header").load("header.jsp");
                    </script>
                </div>
                <div id="menu">
                    <script>
                        $("#menu").load("menu.jsp");
                    </script>
                </div>        
                <div id="content">

                    <script>
                        $("#content").load("spectacles.jsp");
                    </script>
                </div>
            </div>
            <div id="push"></div>
            <div id="footer">
                <script>
                    $("#footer").load("footer.jsp");
                </script>
            </div>
        </div>
    </body>
</html>
