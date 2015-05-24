/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Website;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "DatesSpectacleDisponibles", urlPatterns = {"/dates"})
public class DatesSpectacleDisponibles extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nomSpectacle = request.getParameter("nomSpectacle");
        String nomSalle = request.getParameter("nomSalle");
//
        Methodes fct = new Methodes();
//
        try (PrintWriter out = response.getWriter()) {
            out.print(fct.getDateRepresentation(nomSpectacle, nomSalle));
        } catch (ParseException ex) {
            Logger.getLogger(DatesSpectacleDisponibles.class.getName()).log(Level.SEVERE, null, ex);
        }
//        HttpSession session = request.getSession();
//        session.setAttribute(null, request);
        
    }

}