/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Website;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Emmanuel
 */
@WebServlet(name = "NombreBilletSpectacle", urlPatterns = {"/nb"})
public class NombreBilletSpectacle extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String reformattedStr;
        String nomSection = request.getParameter("nomSection");
        String nomSalle = request.getParameter("nomSalle");
        String nomSpectacle = request.getParameter("nomSpectacle");
        String laDate = request.getParameter("date");

        SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat myFormat = new SimpleDateFormat("yy-MM-dd");

        try {
            
            reformattedStr = myFormat.format(fromUser.parse(laDate));
                Methodes fct = new Methodes();
                int nombrePlacesRestantes = fct.getNombreBilletRestantParSection(nomSpectacle, nomSalle, nomSection, reformattedStr);
                try (PrintWriter out = response.getWriter()) {
                    out.print(Integer.toString(nombrePlacesRestantes));
                }
        }catch(ParseException e){
           e.printStackTrace(); 
        }
    }
}
