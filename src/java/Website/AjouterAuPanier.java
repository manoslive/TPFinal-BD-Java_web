/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Website;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shaun
 */
@WebServlet(name = "AjouterAuPanier", urlPatterns = {"/AjouterAuPanier"})
public class AjouterAuPanier extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String reformattedStr="";
        String NomSpectacle = request.getParameter("nomSpectacle");
        String Salle = request.getParameter("salle");
        String Section = request.getParameter("section");
        String laDate = request.getParameter("date");
        SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            reformattedStr = myFormat.format(fromUser.parse(laDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date madate = Date.valueOf(reformattedStr);
        int NbBillets = Integer.valueOf(request.getParameter("nbBillets"));
        HttpSession session = request.getSession();
        String UserName = (String)session.getAttribute("username");
        Methodes fct = new Methodes();
        
        fct.AjouterAuPanier(NomSpectacle,Salle,Section,madate,NbBillets,UserName);
        PrintWriter out = response.getWriter();
        //out.print();
        out.close();
    }
}
