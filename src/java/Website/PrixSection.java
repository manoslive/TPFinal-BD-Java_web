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
@WebServlet(name = "PrixSection", urlPatterns = {"/PrixSection"})
public class PrixSection extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nomSection = request.getParameter("nomSection");
        String nbBillets = request.getParameter("nbBillets");
        Methodes fct = new Methodes();
        
        int prix = fct.getPrixParSection(nomSection);
        int monPrix = prix* Integer.valueOf(nbBillets);
        PrintWriter out = response.getWriter();
        out.print(monPrix);
        out.close();
    }

}
