package Website;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "SpectacleServlet" , urlPatterns = { "/Spectacle" }, loadOnStartup = 1)
public class SectionsSpectacle extends HttpServlet{
    
    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String nomSalle = request.getParameter("nomSalle");
        
        Methodes fct = new Methodes();
        
        String html = fct.FillSectionCombobox(nomSalle);
        PrintWriter out = response.getWriter();
        out.print(html);
        out.close();
    }
}
