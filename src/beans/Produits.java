package beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author verla
 */
public class Produits {
   private long codeProd;
   private Fournisseurs numFour;
   private String designation;
   private double prix;
   private double stock;
   private double stockMin;
   private Date dernArrivage;

    public Produits() {
    }

    public Produits(long codeProd, Fournisseurs numFour, String designation, double prix, double stock, double stockMin, Date dernArrivage) {
        this.codeProd = codeProd;
        this.numFour = numFour;
        this.designation = designation;
        this.prix = prix;
        this.stock = stock;
        this.stockMin = stockMin;
        this.dernArrivage = dernArrivage;
    }

    public Produits(long codeProd) {
        this.codeProd = codeProd;
    }
   
    public long getCodeProd() {
        return codeProd;
    }

    public void setCodeProd(long codeProd) {
        this.codeProd = codeProd;
    }

    public Fournisseurs getNumFour() {
        return numFour;
    }

    public void setNumFour(Fournisseurs numFour) {
        this.numFour = numFour;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public double getStockMin() {
        return stockMin;
    }

    public void setStockMin(double stockMin) {
        this.stockMin = stockMin;
    }

    public Date getDernArrivage() {
        return dernArrivage;
    }

    public void setDernArrivage(Date DernArrivage) {
        this.dernArrivage = dernArrivage;
    }
   
   public String getDernArrivageBE() {
        String tmp;

        if (this.dernArrivage == null)
          tmp = "";
        else
          {
          SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
          tmp = dateParser.format(this.dernArrivage);
          }
        return tmp;
    }

    public void setDernArrivageBE(String dernArrivage) {
        SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.dernArrivage = dateParser.parse(dernArrivage);
        } catch (ParseException ex) {
            Logger.getLogger(Ventes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getDernArrivageSQL() {
        String tmp;

        if (this.dernArrivage == null)
          tmp = "";
        else
          {
          SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
          tmp = "'" + dateParser.format(this.dernArrivage) + "'";
          }
        return tmp;
    }

    public String getDernArrivageUS() {
        String tmp;

        if (this.dernArrivage == null)
          tmp = "";
        else
          {
          tmp = this.dernArrivage.toString();
          }
        return tmp;
    }

    public String toString() {
        long nb = codeProd;
        String str = Long.toString(nb);
        return str;
    }

   
    
}
