
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
public class Ventes {
    private Integer numVente;
    private Clients numCli;
    private Date date;
    private double total;
    private boolean paye;
    private List <LigneVente> list;

    public Ventes() {
    }

    public Ventes(Integer numVente, Clients numCli, Date date, Double total, boolean paye) {
        this.numVente = numVente;
        this.numCli = numCli;
        this.date = date;
        this.total = total;
        this.paye = paye;
    }

    public Ventes(Integer numVente) {
         this.numVente = numVente;
    }

    public Integer getNumVente() {
        return numVente;
    }

    public void setNumVente(Integer numVente) {
        this.numVente = numVente;
    }

    public Clients getNumCli() {
        return numCli;
    }

    public void setNumCli(Clients numCli) {
        this.numCli = numCli;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
       this.total = total;
    }

    public boolean getPaye() {
        return paye;
    }

    public void setPaye(Boolean paye) {
        this.paye = paye;
    }
    
    public String getDateBE() {
        String tmp;

        if (this.date == null)
          tmp = "";
        else
          {
          SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
          tmp = dateParser.format(this.date);
          }
        return tmp;
    }

    public void setDateBE(String date) {
        SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.date = dateParser.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(Ventes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getDateSQL() {
        String tmp;

        if (this.date == null)
          tmp = "";
        else
          {
          SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
          tmp = "'" + dateParser.format(this.date) + "'";
          }
        return tmp;
    }

    public String getDateUS() {
        String tmp;

        if (this.date == null)
          tmp = "";
        else
          {
          tmp = this.date.toString();
          }
        return tmp;
    } 

    @Override
    public String toString() {
        return numVente.toString();
    }
    
}
