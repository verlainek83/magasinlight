package beans;

/**
 *
 * @author verla
 */
public class Fournisseurs {
    private Integer numFour;
    private String societe;
    private String nom;
    private String prenom;
    private String adresse;
    private Integer cp;
    private String ville;

    public Fournisseurs(Integer numFour, String societe, String nom, String prenom, String adresse, Integer cp, String ville) {
        this.numFour = numFour;
        this.societe = societe;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
    }

    public Fournisseurs(Integer numFour) {
        this.numFour = numFour;
    }

    public Integer getNumFour() {
        return numFour;
    }

    public void setNumFour(Integer numFour) {
        this.numFour = numFour;
    }

    public String getSociete() {
        return societe;
    }

    public void setSociété(String societe) {
        this.societe = societe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getCp() {
        return cp;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return societe+" "+nom+" "+prenom+" "+adresse+" "+cp+" "+ville; 
    }
    
    
}
