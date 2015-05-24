/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Website;

import java.sql.Date;

/**
 *
 * @author Emmanuel
 */
public class InfoFacture {

    long numFacture;
    long numBillet;
    int prixBillet;
    String nomSpectacle;
    String artisteSpectacle;
    Date dateRepresentation;
    int quantiteBillet;
    String nomSection;

    InfoFacture(long num_Facture, long num_Billet, int prix_Billet, String nom_Spectacle, String artiste_Spectacle, Date date_Representation, int quantite_Billet, String nom_Section) {
        numFacture = num_Facture;
        numBillet = num_Billet;
        prixBillet = prix_Billet;
        nomSpectacle = nom_Spectacle;
        artisteSpectacle = artiste_Spectacle;
        dateRepresentation = date_Representation;
        quantiteBillet = quantite_Billet;
        nomSection = nom_Section;
    }

    public long getNumFacture() {
        return numFacture;
    }

    public long getNumBillet() {
        return numBillet;
    }

    public int getPrixBillet() {
        return prixBillet;
    }

    public String getNomSpectacle() {
        return nomSpectacle;
    }

    public String getArtisteSpectacle() {
        return artisteSpectacle;
    }

    public Date getDateRepresentation() {
        return dateRepresentation;
    }

    public int getQuantiteBillet() {
        return quantiteBillet;
    }
    public String getNomSection(){
        return nomSection;
    }
}
