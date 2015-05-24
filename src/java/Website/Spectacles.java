/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Website;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author shaun
 */
public class Spectacles {
    ResultSet rset;
    Connection connection;
    
    //--Attributs d'un spectacle
    long numeroSpectacle;
    String nomSpectacle;
    String nomCategorie;
    String artiste;
    String affiche;
    Date dateRepresentation;
    String nomSalle;
    
    public Spectacles(long num, String nom, String nomCat, String artisteSpectacle, String afficheSpectacle, Date representation, String salle) {
        numeroSpectacle = num;
        nomSpectacle = nom;
        nomCategorie = nomCat;
        artiste = artisteSpectacle;
        affiche = afficheSpectacle;
        dateRepresentation = representation;
        nomSalle = salle;
    }
    
    public Spectacles(String nom, String artisteSpectacle, String afficheSpectacle, String nomCat)
    {
        nomSpectacle = nom;
        artiste = artisteSpectacle;
        affiche = afficheSpectacle;
        nomCategorie = nomCat;
    }
    public String getNomSpectacle()
    {
        return nomSpectacle;
    }
    public String getCategorie()
    {
        return nomCategorie;
    }
    public String getArtiste()
    {
        return artiste;
    }
    public String getAffiche()
    {
        return affiche;
    }
    public Date getDateRepresentation()
    {
        return dateRepresentation;
    }
    public String getNomSalle()
    {
        return nomSalle;
    }
}

