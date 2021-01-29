package beans;

/**
 *
 * @author verla
 */
public class Clients {
    private Integer numCli;
    private String titre;
    private String nom;
    private String prenom;
    private String adresse;
    private Integer cp;
    private String ville;

    public Clients(Integer numCli, String titre, String nom, String prenom, String adresse, Integer cp, String ville) {
        this.numCli = numCli;
        this.titre = titre;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
    }

    public Clients(Integer numCli, String nom) {
        this.numCli = numCli;
        this.nom = nom;
    }
       
    public Clients(Integer numCli) {
        this.numCli = numCli;
    }

    public Integer getNumCli() {
        return numCli;
    }

    public void setNumCli(Integer numCli) {
        this.numCli = numCli;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
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
        return titre+" "+nom+" "+prenom+" "+adresse+" "+cp+" "+ville;
    }
    
}
