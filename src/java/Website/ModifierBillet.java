/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Website;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shaun
 */
@WebServlet(name = "ModifierBillet", urlPatterns = {"/ModifierBillet"})
public class ModifierBillet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String numBillet = request.getParameter("numBillet");
        String action = request.getParameter("action");
        
        Methodes fct = new Methodes();
        if(action.equals("modifier")){
            String reformattedStr="";
            String Salle = request.getParameter("salle");
            String laDate = request.getParameter("date");
            String Section = request.getParameter("section");
            int Quantite = Integer.valueOf(request.getParameter("quantite"));
            
            SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat myFormat = new SimpleDateFormat("yy-MM-dd");

            java.sql.Date sqlDate=null;
            try {
                reformattedStr = myFormat.format(fromUser.parse(laDate));
                SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
                java.util.Date madate = sdf.parse(reformattedStr);
                //sqlDate = new java.sql.Date(madate.getTime());
                //sqlDate = new java.sql.Date(Long.valueOf(reformattedStr));
            }catch(ParseException e){
               e.printStackTrace(); 
            }
            
            fct.modifierBillet(Long.valueOf(numBillet), Salle, reformattedStr/*sqlDate*/, Section, Quantite);
        }else if (action.equals("supprimer")){
            fct.supprimerBillet(Long.valueOf(numBillet));
        }
    }

}
