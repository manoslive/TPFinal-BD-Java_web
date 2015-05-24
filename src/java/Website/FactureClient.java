/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Website;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Emmanuel
 */
@WebServlet(name = "factureclient", urlPatterns = {"/facture"})
public class FactureClient extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Initialisation des variables
        String nomSpectacle = request.getParameter("nomSpectacle");
        HttpSession session = request.getSession();
        String username = session.getAttribute("username").toString();
        Methodes fct = new Methodes();
        PrintWriter out = response.getWriter();
        long numClient;
        int numFactureInt, totalFacture = 0;
        // Formatage de la date de facture
        String dateAujourdhui = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        ArrayList<Billets> billets = new ArrayList<>();
        ArrayList<String> infoClient = new ArrayList<>();
        //ArrayList<InfoFacture> infoFacture = new ArrayList<>();
        String data = "";
        
        response.setContentType("text/html;charset=UTF-8");

        // Remplir les listes d'objets
        numClient = fct.GetNumClient(username);
        billets = fct.AfficherPanierClient(numClient); // Écrire une requête qui renvoit tous les infos : prix (section) + nomspectacle + artiste spectacle + numbillet + date + quantite billet + numfacture
        infoClient = fct.GetInfoClient(numClient);
        session.setAttribute("nombrebillet", billets.size());
        // Augmenter manuellement la séquence SEQ_NUMFACTURE pour permettre à plusieurs billets
        // d'avoir le même numéro de facture
        numFactureInt = fct.AugmenterSeqFacture();
        Long numFacture = new Long(numFactureInt);

        // Ajouter la facture avec les éléments du panier
        for (int i = 0; i < billets.size(); i++) {
            fct.AjouterFacture(numFacture, billets.get(i).getNumBillet(), numClient, billets.get(i).getQuantiteBillets());
            fct.AjouterNumFactureBillet(numFacture, billets.get(i).getNumBillet());
        }
        //infoFacture = fct.GetInfoFacture(numClient, billets.get(0).getNumBillet());
        data += "    <head>\n"
                + "        <link rel='stylesheet' type='text/css' href='facture_style.css' />\n"
                + "        <link rel='stylesheet' type='text/css' href='facture_print.css' media=\\\\\\\"print\\\\\\\"/>\n"
                + "    </head>"
                + "        <div id=\"page-wrap\">"
                + "            <textarea id=\"header\" accept-charset=\"UTF-8\" disabled>FACTURE</textarea>"
                + "            <div id=\"identity\">"
                + "                <textarea id=\"address\" accept-charset=\"UTF-8\" disabled>";
        for (int j = 0; j < infoClient.size(); j++) {
            data += infoClient.get(j) + "\n";
        }
        data += "</textarea disabled>"
                + "                <div id=\"print\">"
                + "                    <form accept-charset:\"UTF-8\";>"
                + "                        <input type=\"button\" value=\"Imprimer\" onClick=\"window.print()\">"
                + "                    </form>"
                + "                </div>"
                + "                <div style=\"clear:both\"></div>"
                + "                <div id=\"customer\">"
                + "                    <textarea id=\"customer-title\" accept-charset=\"UTF-8\" disabled>Réseau Admission\n"
                + "a/s administration</textarea>"
                + "                    <table id=\"meta\">"
                + "                        <tr>"
                + "                            <td class=\"meta-head\"># Facture</td>";

        data += "                            <td><textarea accept-charset=\"UTF-8\" disabled>" + "0000" + numFacture + "</textarea></td>"
                + "                        </tr>"
                + "                        <tr>"
                + "                            <td class=\"meta-head\">Date</td>"
                + "                            <td><textarea id=\"date\" accept-charset=\"UTF-8\" disabled>";
        data += dateAujourdhui + "</textarea disabled></td>"
                + "                        </tr>"
                + "                    </table>"
                + "                </div>"
                + "                <table id=\"items\">"
                + "                    <tr>"
                + "                        <th>Item</th>"
                + "                        <th>Description</th>"
                + "                        <th>Coût par unité</th>"
                + "                        <th>Quantité</th>"
                + "                        <th>Prix</th>"
                + "                    </tr class='border_bottom'>";
        for (int k = 0; k < billets.size(); k++) {
            int somme = billets.get(k).getQuantiteBillets() * fct.getPrixParSection(billets.get(k).getNomSection());
            totalFacture += somme;
            data += "<tr class=\"item-row\">"
                    + "<td class=\"item-name\"><div class=\"delete-wpr\">"
                    + "<textarea accept-charset=\"UTF-8\" disabled>" + billets.get(k).getNomSpectacle() + " - \n" + billets.get(k).getNomArtiste()+ "</textarea>"
                    + "</div></td>"
                    + "<td class=\"description\"><textarea accept-charset=\"UTF-8\" disabled>Section: " + billets.get(k).getNomSection() + "\nDate représentation: " + billets.get(k).getDateRepresentation() + "</textarea></td>"
                    + "<td><textarea class=\"cost\" accept-charset=\"UTF-8\" disabled>" + fct.getPrixParSection(billets.get(k).getNomSection()) + "</textarea></td>"
                    + "<td><textarea class=\"qty\" accept-charset=\"UTF-8\" disabled>" + billets.get(k).getQuantiteBillets() + "</textarea></td>"
                    + "<td><span class=\"price\" id='price" + k + "'>" + "$" + somme + ".00" + "</span></td>"
                    + "</tr>";
        }
        data += "                    <tr>"
                + "                        <td colspan=\"2\" class=\"blank\"> </td>"
                + "                        <td colspan=\"2\" class=\"total-line balance\">Montant Due</td>"
                + "                        <td class=\"total-value balance\"><div class=\"due\">" + "$" + totalFacture + ".00" + "</div></td>"
                + "                    </tr>"
                + "                </table>"
                + "                <div id=\"terms\">"
                + "                    <h5>Termes</h5>"
                + "                    <textarea accept-charset=\"UTF-8\" disabled>Vente finale. Pas d'échange ou de retour.</textarea>"
                + "                </div>"
                + "            </div>";
        out.print(data);
    }
}
