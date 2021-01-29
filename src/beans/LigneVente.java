package beans;

/**
 *
 * @author verla
 */
public class LigneVente {
    private Ventes numVente;
    private Produits codeProd;
    private double quantite;
    private double prix;

    public LigneVente() {
    }

    public LigneVente(Ventes numVente, Produits codeProd, double quantite, double prix) {
        this.numVente = numVente;
        this.codeProd = codeProd;
        this.quantite = quantite;
        this.prix = prix;
    }

    public Ventes getNumVente() {
        return numVente;
    }

    public void setNumVente(Ventes numVente) {
        this.numVente = numVente;
    }

    public Produits getCodeProd() {
        return codeProd;
    }

    public void setCodeProd(Produits codeProd) {
        this.codeProd = codeProd;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return numVente+" "+codeProd+" "+quantite+" "+prix; //To change body of generated methods, choose Tools | Templates.
    }
     
}
