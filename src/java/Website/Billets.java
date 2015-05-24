/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Website;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;

/**
 *
 * @author Emmanuel
 */
public class Billets {

    ResultSet rset;
    Connection connection;

    //--Attributs d'un spectacle
    long numBillet;
    String nomSpectacle;
    String afficheSpectacle;
    String nomSection;
    String nomSalle;
    Date heureRepresentation;
    Date dateRepresentation;
    String nomClient;
    String nomArtiste;
    String nomCategorie;
    int quantiteBillets;
    long numFacture;

//    public Billets(long num_Billet, String nom_Spectacle, String affiche_Spectacle, String nom_Section, String nom_Salle, Date heure_Representation, Date date_Representation, String nom_Client, String nom_Artiste) {
        public Billets(long num_Billet,String nom_Spectacle, String nom_Artiste, String affiche_Spectacle, String nom_Categorie, int quantite_Billets, long num_Facture, String nom_Section, String nom_Salle, Date date_Representation) {
        numBillet = num_Billet;
        nomSpectacle = nom_Spectacle;
        afficheSpectacle = affiche_Spectacle;
        nomSection = nom_Section;
        nomSalle = nom_Salle;
//        heureRepresentation = heure_Representation;
        dateRepresentation = date_Representation;
//        nomClient = nom_Client;
        nomArtiste = nom_Artiste;
        nomCategorie = nom_Categorie;
        quantiteBillets = quantite_Billets;
        numFacture = num_Facture;
    }

    //--Accesseurs
    public long getNumBillet() {
        return numBillet;
    }

    public String getNomSpectacle() {
        return nomSpectacle;
    }

    public String GetAfficheSpectacle() {
        return afficheSpectacle;
    }

    public String getNomSection() {
        return nomSection;
    }

    public String getNomSalle() {
        return nomSalle;
    }

    public Date getHeureRepresentation() {
        return heureRepresentation;
    }

    public Date getDateRepresentation() {
        return dateRepresentation;
    }

    public String getNomClient() {
        return nomClient;
    }
    public String getNomArtiste()
    {
        return nomArtiste;
    }
    public String getNomCategorie()
    {
        return nomCategorie;
    }
    public int getQuantiteBillets()
    {
        return quantiteBillets;
    }
    public long getNumFacture()
    {
        return numFacture;
    }
}
