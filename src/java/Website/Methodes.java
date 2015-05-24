/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Website;

import java.util.ArrayList;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpSession;

import oracle.jdbc.OracleTypes;

/**
 *
 * @author Emmanuel
 */
public class Methodes {

    ResultSet rset;
    Connection connection;

    // Afficher les spectacles sans les salles
    public ArrayList<Spectacles> afficherTousSpectacles() {
        Website.ConnectionOracle con = new Website.ConnectionOracle();
        con.setConnection("Cooperch", "ORACLE1");
        con.connecter();
        connection = con.getConnection();
        ArrayList<Spectacles> listSpectacles = new ArrayList<>();
        try {
            CallableStatement stm = connection.prepareCall("{ ? = call GESTION.AFFICHERSPECTACLES()}");
            stm.registerOutParameter(1, OracleTypes.CURSOR);
            stm.execute(); //execution de la fonction
            // Caster le paramètre de retour en ResultSet
            //Website.Spectacles spectacle = new  Website.Spectacles();
            rset = (ResultSet) stm.getObject(1);
            while (rset.next()) {
                listSpectacles.add(new Website.Spectacles(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4)));
            }
            stm.close();
            connection.close();
            con.deconnecter();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listSpectacles;
    }

    // Afficher les spectacles avec les salles
    public ArrayList<Spectacles> afficherTousSpectaclesRecherche() {
        Website.ConnectionOracle con = new Website.ConnectionOracle();
        con.setConnection("Cooperch", "ORACLE1");
        con.connecter();
        connection = con.getConnection();
        ArrayList<Spectacles> listSpectacles = new ArrayList<>();
        try {
            CallableStatement stm = connection.prepareCall("{ ? = call GESTION.AFFICHERSPECTACLESRECHERCHE()}");
            stm.registerOutParameter(1, OracleTypes.CURSOR);
            stm.execute(); //execution de la fonction
            // Caster le paramètre de retour en ResultSet
            //Website.Spectacles spectacle = new  Website.Spectacles();
            rset = (ResultSet) stm.getObject(1);
            while (rset.next()) {
                listSpectacles.add(new Website.Spectacles(rset.getLong(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getDate(6), rset.getString(7)));
            }
            stm.close();
            connection.close();
            con.deconnecter();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listSpectacles;
    }

    public Spectacles rechercheParNomSpectacle(String titre) {
        Website.ConnectionOracle con = new Website.ConnectionOracle();
        con.setConnection("Cooperch", "ORACLE1");
        con.connecter();
        connection = con.getConnection();
        Spectacles leSpectacle = null;
        try {
            CallableStatement stm = connection.prepareCall("{ ? = call GESTION.AFFICHERSPECTACLEPARNOM(?)}");
            stm.registerOutParameter(1, OracleTypes.CURSOR);
            stm.setString(2, titre);
            stm.executeQuery(); //execution de la fonction
            rset = (ResultSet) stm.getObject(1);
            while (rset.next()) {
                leSpectacle = new Website.Spectacles(rset.getLong(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getDate(6), rset.getString(7));
            }
            stm.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return leSpectacle;
    }
    public ArrayList<String> rechercheParTitres() {
        Website.ConnectionOracle con = new Website.ConnectionOracle();
        con.setConnection("Cooperch", "ORACLE1");
        con.connecter();
        connection = con.getConnection();
        ArrayList<String> resultats = new ArrayList<>();
        try {
            CallableStatement stm = connection.prepareCall("{ ? = call GESTION.AFFICHERTITRES()}");
            stm.registerOutParameter(1, OracleTypes.CURSOR);
            stm.execute(); //execution de la fonction
            // Caster le paramètre de retour en ResultSet
            rset = (ResultSet) stm.getObject(1);
            while (rset.next()) {
                resultats.add(rset.getString(1));
            }
            stm.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultats;
    }
    public ArrayList<String> rechercheParCategorie() {
        Website.ConnectionOracle con = new Website.ConnectionOracle();
        con.setConnection("Cooperch", "ORACLE1");
        con.connecter();
        connection = con.getConnection();
        ArrayList<String> resultats = new ArrayList<>();
        try {
            CallableStatement stm = connection.prepareCall("{ ? = call GESTION.AFFICHERCATEGORIES()}");
            stm.registerOutParameter(1, OracleTypes.CURSOR);
            stm.execute(); //execution de la fonction
            // Caster le paramètre de retour en ResultSet
            rset = (ResultSet) stm.getObject(1);
            while (rset.next()) {
                resultats.add(rset.getString(2));
            }
            stm.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultats;
    }

    public ArrayList<String> rechercheParSalle() {
        Website.ConnectionOracle con = new Website.ConnectionOracle();
        con.setConnection("Cooperch", "ORACLE1");
        con.connecter();
        connection = con.getConnection();
        ArrayList<String> resultats = new ArrayList<>();
        try {
            CallableStatement stm = connection.prepareCall("{ ? = call GESTION.AFFICHERSALLES()}");
            stm.registerOutParameter(1, OracleTypes.CURSOR);
            stm.execute(); //execution de la fonction
            // Caster le paramètre de retour en ResultSet
            rset = (ResultSet) stm.getObject(1);
            while (rset.next()) {
                resultats.add(rset.getString(1));
            }
            stm.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultats;
    }

    public ArrayList<String> rechercheParArtiste() {
        Website.ConnectionOracle con = new Website.ConnectionOracle();
        con.setConnection("Cooperch", "ORACLE1");
        con.connecter();
        connection = con.getConnection();
        ArrayList<String> resultats = new ArrayList<>();
        try {
            CallableStatement stm = connection.prepareCall("{ ? = call GESTION.AFFICHERARTISTES()}");
            stm.registerOutParameter(1, OracleTypes.CURSOR);
            stm.execute(); //execution de la fonction
            // Caster le paramètre de retour en ResultSet
            rset = (ResultSet) stm.getObject(1);
            while (rset.next()) {
                resultats.add(rset.getString(1));
            }
            stm.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultats;
    }

    public String EstPresent(String username, String password) {
        Website.ConnectionOracle con = new Website.ConnectionOracle();
        con.setConnection("Cooperch", "ORACLE1");
        con.connecter();
        connection = con.getConnection();
        String estPresent = "RIEN";
        try {
            CallableStatement stm = connection.prepareCall("{ ? = call GESTION.VERIFIERACCES(?,?)}");
            stm.registerOutParameter(1, OracleTypes.VARCHAR);
            stm.setString(2, username);
            stm.setString(3, password);
            stm.executeQuery(); //execution de la fonction
            // Caster le paramètre de retour en ResultSet
            estPresent = (String) stm.getObject(1);
            //estPresent = rset.getString(1);
            stm.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return estPresent;
    }

    public String getNomCompletClient(String nomUsager) {
        // Connection à la BD
        Website.ConnectionOracle con = new Website.ConnectionOracle();
        con.setConnection("Cooperch", "ORACLE1");
        con.connecter();

        // Variables
        connection = con.getConnection();
        String nomComplet = "";

        try {
            CallableStatement stm = connection.prepareCall("{ ? = call GESTION.GETNOMCLIENT(?)}");
            stm.registerOutParameter(1, OracleTypes.VARCHAR);
            stm.setString(2, nomUsager);
            stm.executeQuery(); //execution de la fonction
            // Caster le paramètre de retour en ResultSet
            nomComplet = (String) stm.getObject(1);
            //estPresent = rset.getString(1);
            stm.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nomComplet;
    }

    public void AjouterClient(String nomComplet, String nomUsager, String motDePasse, String adresse, String telephone, String courriel) {
        // Connection à la BD
        Website.ConnectionOracle con = new Website.ConnectionOracle();
        con.setConnection("Cooperch", "ORACLE1");
        con.connecter();

        // Variable
        connection = con.getConnection();

        try {
            CallableStatement stm = connection.prepareCall("{ call GESTION.AJOUTERCLIENT(?,?,?,?,?,?) }");
            stm.setString(1, nomComplet);
            stm.setString(2, nomUsager);
            stm.setString(3, motDePasse);
            stm.setString(4, adresse);
            stm.setString(5, telephone);
            stm.setString(6, courriel);
            stm.executeUpdate();
            stm.clearParameters();
            stm.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String VerifierUsager(String nomUsager) {
        Website.ConnectionOracle con = new Website.ConnectionOracle();
        con.setConnection("Cooperch", "ORACLE1");
        con.connecter();
        connection = con.getConnection();
        String estPresent = "";
        try {
            CallableStatement stm = connection.prepareCall("{ ? = call GESTION.VERIFIERACCES(?,?)}");
            stm.registerOutParameter(1, OracleTypes.VARCHAR);
            stm.setString(2, nomUsager);
            stm.executeQuery(); //execution de la fonction

            estPresent = (String) stm.getObject(1);
            //estPresent = rset.getString(1);
            stm.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return estPresent;
    }

    public Spectacles GetInfoSpectacle(String titre) {
        Website.ConnectionOracle con = new Website.ConnectionOracle();
        con.setConnection("Cooperch", "ORACLE1");
        con.connecter();
        connection = con.getConnection();
        Spectacles infoSpectacle = null;
        try {
            CallableStatement stm = connection.prepareCall("{ ? = call GESTION.GETINFOSPECTACLE(?)}");
            stm.registerOutParameter(1, OracleTypes.CURSOR);
            stm.execute(); //execution de la fonction
            // Caster le paramètre de retour en ResultSet
            //Website.Spectacles spectacle = new  Website.Spectacles();
            rset = (ResultSet) stm.getObject(1);
            while (rset.next()) {
                infoSpectacle = new Spectacles(rset.getLong(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getDate(6), rset.getString(7));
            }
            stm.close();
            connection.close();
            con.deconnecter();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return infoSpectacle;
    }

    public long GetNumClient(String nomUsager) {
        Website.ConnectionOracle con = new Website.ConnectionOracle();
        con.setConnection("Cooperch", "ORACLE1");
        con.connecter();
        connection = con.getConnection();
        long numclient = -1;
        try {
            CallableStatement stm = connection.prepareCall("{ ? = call GESTION.GETNUMCLIENT(?)}");
            stm.registerOutParameter(1, OracleTypes.BIGINT);
            stm.setString(2, nomUsager);
            stm.execute(); //execution de la fonction
            // Caster le paramètre de retour en ResultSet

            numclient = (long) stm.getObject(1);
            stm.close();
            connection.close();
            con.deconnecter();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return numclient;
    }

    public ArrayList<Billets> AfficherPanierClient(long numClient) {
        Website.ConnectionOracle con = new Website.ConnectionOracle();
        con.setConnection("Cooperch", "ORACLE1");
        con.connecter();
        connection = con.getConnection();
        ArrayList<Website.Billets> listeBillets = new ArrayList<>();
        try {
            CallableStatement stm = connection.prepareCall("{ ? = call GESTION.AFFICHERPANIERCLIENT(?)}");
            stm.registerOutParameter(1, OracleTypes.CURSOR);

            stm.setLong(2, numClient);
            stm.execute(); //execution de la fonction
            rset = (ResultSet) stm.getObject(1);
            while (rset.next()) {
//                listeBillets.add(new Website.Billets(rset.getLong(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getDate(6), rset.getDate(7), rset.getString(8), rset.getString(9)));
                listeBillets.add(new Billets(rset.getLong(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getInt(6), rset.getLong(7),rset.getString(8), rset.getString(9), rset.getDate(10)));
            }
            stm.close();
            connection.close();
            con.deconnecter();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeBillets;
    }

    public String FillSectionCombobox(String nomSalle) {
        String data = "";
        Website.ConnectionOracle con = new Website.ConnectionOracle();
        con.setConnection("Cooperch", "ORACLE1");
        con.connecter();
        connection = con.getConnection();
        ArrayList<String> listeSections = new ArrayList<>();

        try {
            CallableStatement stm = connection.prepareCall("{ ? = call GESTION.GETSECTIONSPARSALLE(?)}");
            stm.registerOutParameter(1, OracleTypes.CURSOR);

            stm.setString(2, nomSalle);
            stm.execute(); //execution de la fonction
            rset = (ResultSet) stm.getObject(1);
            while (rset.next()) {
                listeSections.add((rset.getString(1)));
            }
            stm.close();
            connection.close();
            con.deconnecter();
            
//            data += "<select id='CB_Sections'>";
            for (int i = 0; i < listeSections.size(); i++) {
                    data += "<option value='"+listeSections.get(i)+"'>" + listeSections.get(i) + "</option>";
            }
//            data += "</select>";
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return data;
    }

    public String FillComboboxPanier(String nomSpectacle) {
        String data = "";
        Website.ConnectionOracle con = new Website.ConnectionOracle();
        con.setConnection("Cooperch", "ORACLE1");
        con.connecter();
        connection = con.getConnection();
        ArrayList<String> listeSalles = new ArrayList<>();

        try {
            CallableStatement stm = connection.prepareCall("{ ? = call GESTION.GETSALLEPARSPECTACLE(?)}");
            stm.registerOutParameter(1, OracleTypes.CURSOR);

            stm.setString(2, nomSpectacle);
            stm.execute(); //execution de la fonction
            rset = (ResultSet) stm.getObject(1);
            while (rset.next()) {
                listeSalles.add((rset.getString(1)));
            }
            connection.close();
            con.deconnecter();
            for (int i = 0; i < listeSalles.size(); i++) {
                data += "<option value='"+listeSalles.get(i)+"'>" + listeSalles.get(i) + "</option>";
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return data;
    }

    public int getNombreItemPanier(String nomUsager) {
        // Connection à la BD
        Website.ConnectionOracle con = new Website.ConnectionOracle();
        con.setConnection("Cooperch", "ORACLE1");
        con.connecter();

        // Variables
        connection = con.getConnection();
        int nombreItem = -1;
        try {
            CallableStatement stm = connection.prepareCall("{ ? = call GESTION.GETNBITEMPANIER(?) }");
            stm.registerOutParameter(1, OracleTypes.INTEGER);
            stm.setString(2, nomUsager);
            stm.executeQuery(); //execution de la fonction

            nombreItem = (int) stm.getObject(1);
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nombreItem;
    }

    public int getNombreBilletRestantParSection(String nomSpectacle, String nomSalle, String nomSection, String date) {
        Website.ConnectionOracle con = new Website.ConnectionOracle();
        con.setConnection("Cooperch", "ORACLE1");
        con.connecter();
        connection = con.getConnection();
        int nombre = -1;

        try {
            CallableStatement stm = connection.prepareCall("{ ? = call GESTION.GETNBBILLETRESTANTPARSECTION(?,?,?,?)}");
            stm.registerOutParameter(1, OracleTypes.INTEGER);

            stm.setString(2, nomSection);
            stm.setString(3, nomSpectacle);
            stm.setString(4, nomSalle);
            stm.setString(5, date);
            stm.execute(); //execution de la fonction
            nombre = (int) stm.getObject(1);

            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nombre;
    }

    public String getDateRepresentation(String nomSpectacle, String nomSalle) throws ParseException {
        String data = "";
        Website.ConnectionOracle con = new Website.ConnectionOracle();
        con.setConnection("Cooperch", "ORACLE1");
        con.connecter();
        connection = con.getConnection();
        ArrayList<String> listeDates = new ArrayList<>();

        try {
            CallableStatement stm = connection.prepareCall("{ ? = call GESTION.GETDATEREPRESENTATION(?,?)}");
            stm.registerOutParameter(1, OracleTypes.CURSOR);

            stm.setString(2, nomSpectacle);
            stm.setString(3, nomSalle);
            stm.execute(); //execution de la fonction
            rset = (ResultSet) stm.getObject(1);
            while (rset.next()) {
                listeDates.add((rset.getString(1)));
            }
            connection.close();
            con.deconnecter();
            for (int i = 0; i < listeDates.size(); i++) {
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(listeDates.get(i));
                String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
                data += "<option value='"+formattedDate+"'>" + formattedDate + "</option>";
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return data;
    }

    public int getPrixParSection(String section) {
        int prix = 0;
        Website.ConnectionOracle con = new Website.ConnectionOracle();
        con.setConnection("Cooperch", "ORACLE1");
        con.connecter();
        connection = con.getConnection();

        try {
            CallableStatement stm = connection.prepareCall("{ ? = call GESTION.GETPRIXPARSECTION(?)}");
            stm.registerOutParameter(1, OracleTypes.INTEGER);

            stm.setString(2, section);
            stm.execute(); //execution de la fonction
            prix = (int) stm.getObject(1);
            connection.close();
            con.deconnecter();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return prix;
    }

    public void AjouterAuPanier(String NomSpectacle, String Salle, String Section, Date date, int NbBillets, String UserName) {
        Website.ConnectionOracle con = new Website.ConnectionOracle();
        con.setConnection("Cooperch", "ORACLE1");
        con.connecter();
        connection = con.getConnection();

        try {
            CallableStatement stm = connection.prepareCall("{call GESTION.AJOUTERAUPANIER(?,?,?,?,?,?)}");

            stm.setString(1, NomSpectacle);
            stm.setString(2, Salle);
            stm.setString(3, Section);
            stm.setDate(4, date);
            stm.setLong(5, NbBillets);
            stm.setString(6, UserName);
            stm.execute(); //execution de la fonction
            connection.close();
            con.deconnecter();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void modifierBillet(long numBillet, String salle, String laDate, String section, int quantite){
        Website.ConnectionOracle con = new Website.ConnectionOracle();
        con.setConnection("Cooperch", "ORACLE1");
        con.connecter();
        connection = con.getConnection();

        try {
            CallableStatement stm = connection.prepareCall("{call GESTION.MODIFIERBILLET(?,?,?,?,?)}");
            stm.setLong(1, numBillet);
            stm.setString(2, salle);
            stm.setString(3, laDate);
            stm.setString(4, section);
            stm.setInt(5, quantite);
            stm.execute(); //execution de la fonction
            connection.close();
            con.deconnecter();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerBillet(long numBillet) {
        Website.ConnectionOracle con = new Website.ConnectionOracle();
        con.setConnection("Cooperch", "ORACLE1");
        con.connecter();
        connection = con.getConnection();

        try {
            CallableStatement stm = connection.prepareCall("{call GESTION.SUPPRIMERBILLET(?)}");
            stm.setLong(1, numBillet);
            stm.execute(); //execution de la fonction
            connection.close();
            con.deconnecter();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<String> GetInfoClient(long numClient) {
        Website.ConnectionOracle con = new Website.ConnectionOracle();
        con.setConnection("Cooperch", "ORACLE1");
        con.connecter();
        connection = con.getConnection();
        ArrayList<String> liste = new ArrayList<>();
        try {
            CallableStatement stm = connection.prepareCall("{ ? = call GESTION.GETINFOCLIENT(?)}");
            stm.registerOutParameter(1, OracleTypes.CURSOR);
            stm.setLong(2, numClient);
            stm.execute(); //execution de la fonction
            rset = (ResultSet) stm.getObject(1);
            while (rset.next()) {
                liste.add(rset.getString(1));
                liste.add(rset.getString(2));
                liste.add(rset.getString(3));
                liste.add(rset.getString(4));
            }
            connection.close();
            con.deconnecter();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return liste;
    }

    public ArrayList<InfoFacture> GetInfoFacture(long numClient, long numBillet) {
        ArrayList<InfoFacture> liste = new ArrayList<>();
        Website.ConnectionOracle con = new Website.ConnectionOracle();
        con.setConnection("Cooperch", "ORACLE1");
        con.connecter();
        connection = con.getConnection();
        try {
            CallableStatement stm = connection.prepareCall("{ ? = call GESTION.GETINFOFACTURE(?,?)}");
            stm.registerOutParameter(1, OracleTypes.CURSOR);
            stm.setLong(2, numClient);
            stm.setLong(3, numBillet);
            stm.execute(); //execution de la fonction
            rset = (ResultSet) stm.getObject(1);
            while (rset.next()) {
                liste.add(new InfoFacture(rset.getLong(1), rset.getLong(2), rset.getInt(3), rset.getString(4), rset.getString(5), rset.getDate(6), rset.getInt(7), rset.getString(8)));
            }
            connection.close();
            con.deconnecter();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return liste;
    }

    public void AjouterFacture(long numFacture, long numBillet, long numClient, int quantiteBillet) {
        Website.ConnectionOracle con = new Website.ConnectionOracle();
        con.setConnection("Cooperch", "ORACLE1");
        con.connecter();
        // Variable
        connection = con.getConnection();
        try {
            CallableStatement stm = connection.prepareCall("{ call GESTION.AJOUTERFACTURE(?,?,?,?) }");
            stm.setLong(1, numFacture);
            stm.setLong(2, numBillet);
            stm.setLong(3, numClient);
            stm.setInt(4, quantiteBillet);
            stm.executeUpdate();
            stm.clearParameters();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int AugmenterSeqFacture() {
        Website.ConnectionOracle con = new Website.ConnectionOracle();
        con.setConnection("Cooperch", "ORACLE1");
        con.connecter();
        int numSequence = -1;

        // Variable
        connection = con.getConnection();
        try {
            CallableStatement stm = connection.prepareCall("{ ? = call GESTION.AUGMENTERSEQFACTURE() }");
            stm.registerOutParameter(1, OracleTypes.INTEGER);
            stm.execute();
            numSequence = (int) stm.getObject(1);
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return numSequence;
    }

    public void AjouterNumFactureBillet(long numFacture, long numBillet) {
        Website.ConnectionOracle con = new Website.ConnectionOracle();
        con.setConnection("Cooperch", "ORACLE1");
        con.connecter();
        // Variable
        connection = con.getConnection();
        try {
            CallableStatement stm = connection.prepareCall("{ call GESTION.AJOUTERNUMFACTUREBILLET(?,?) }");
            stm.setLong(1, numFacture);
            stm.setLong(2, numBillet);
            stm.executeUpdate();
            stm.clearParameters();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
