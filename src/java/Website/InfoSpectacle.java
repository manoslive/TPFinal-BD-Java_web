/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Website;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shaun
 */
@WebServlet(name = "InfoSpectacleServlet" , urlPatterns = { "/InfoSpectacle" }, loadOnStartup = 1)
public class InfoSpectacle extends HttpServlet  {
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                      throws ServletException, IOException {
        String nomSpectacle = request.getParameter("nomSpectacle");
        
        Methodes fct = new Methodes();
        
        Spectacles monSpectacle = fct.rechercheParNomSpectacle(nomSpectacle);
        PrintWriter out = response.getWriter();
        out.print(  " <div id='infoSpectacle_date' class='dateRepresentation'>"+ monSpectacle.getArtiste() +"</div> "+
                    " <div id='infoSpectacle'>" +
                    " <img id='infoSpectacle_affiche' src='Images/"+ monSpectacle.getAffiche()  +"'>"+
                    " <h3 id='infoSpectacle_titre' class='titre'>" + monSpectacle.getNomSpectacle() + "</h3>"+
                    " <p id='infoSpectacle_categorie' class='categorie'>" + monSpectacle.getCategorie() + "</p>"+
                    " <div id='infoSpectacle_choix'>"+
                    " <div id='infoSpectacle_salle' class='infoSpectacle_lesChoix'>Salle: <select id='infoSpectacle_SelectSalle' onchange='onChange_Choix()'>" +fct.FillComboboxPanier(monSpectacle.getNomSpectacle())+"</select></div>"+
                    " <div id='infoSpectacle_section' class='infoSpectacle_lesChoix'>Section: <select id='infoSpectacle_SelectSection' onchange='onChange_Choix()'>"+
                                                                                              "<option>choisir</option></select></div>"+
                    " <div id='infoSpectacle_dateRepresentation'>Date de representation: <select id='infoSpectacle_SelectDate' onchange='onChange_Choix()'>"+
                                                                                              "<option>choisir</option></select></div>"+
                    " <div id='infoSpectacle_nbBillets' class='infoSpectacle_lesChoix'>Nombre de billets: <input id='infoSpectacle_SelectNbBillets' type='number' onchange='onChange_Choix()' value='0' min='0' max='100'/>"+
                    "</div>"+
                    " <div>"+
                    "<div id='info_spectacle_confirmation'>"+
                    "</div>");
        out.close();
    }
}
