package dao;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DaoFactory {
    private static final String FICHIER_PROPERTIES       = "dao/config.properties";
    private static final String PROPERTY_URL             = "url";
    private static final String PROPERTY_DRIVER          = "driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
    private static final String PROPERTY_MOT_DE_PASSE    = "motdepasse";
    private static final String PROPERTY_VEN_DAO         = "ventesdao";
    private static final String PROPERTY_CLI_DAO         = "clientsdao";
    private static final String PROPERTY_LIG_DAO         = "ligneventedao";
    private static final String PROPERTY_FOU_DAO         = "fournisseursdao";
    private static final String PROPERTY_PRO_DAO         = "produitsdao";

    
    private String              url;
    private String              driver;
    private String              username;
    private String              password;
    private String              venDao;
    private String              cliDao;
    private String              ligDao;
    private String              fouDao;
    private String              proDao;
        
    /* Pattern Singleton */
    private static DaoFactory uniqueInstance = new DaoFactory();
    public static DaoFactory getInstance()
    {
        return uniqueInstance;
    }
    
    private DaoFactory() throws DaoConfigurationException
    {
        Properties properties = new Properties();
            
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );

        if (fichierProperties == null) {
            throw new DaoConfigurationException( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        }
        
        try {
            properties.load( fichierProperties );
            url = properties.getProperty( PROPERTY_URL );
            driver = properties.getProperty( PROPERTY_DRIVER );
            username = properties.getProperty( PROPERTY_NOM_UTILISATEUR );
            password = properties.getProperty( PROPERTY_MOT_DE_PASSE );
            venDao = properties.getProperty(PROPERTY_VEN_DAO);
            cliDao = properties.getProperty(PROPERTY_CLI_DAO);
            fouDao = properties.getProperty(PROPERTY_FOU_DAO);
            ligDao = properties.getProperty(PROPERTY_LIG_DAO);
            proDao = properties.getProperty(PROPERTY_PRO_DAO);
        

        } catch (IOException e) {
            throw new DaoConfigurationException( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e );
        }

        try {
            Class.forName(driver);
            }
        catch (ClassNotFoundException e)
            {
            throw new DaoConfigurationException("Le driver est introuvable", e);
            }
    }

    public Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(url, username,password);
    }
    
    public VentesDao getDAOVentes()
    {
        //return new VentesDaoMysql(this);
        try {
            Class venDaoClass = Class.forName(venDao);
            Constructor[] constr = venDaoClass.getConstructors();
            return (VentesDao)constr[0].newInstance(this);
            
        } catch (Exception e) {
            throw new DaoConfigurationException("Probleme d'injection avec la classe ventes", e);
        }
    }

    public ClientsDao getDAOClients()
    {
        //return new ClientsDaoMysql(this);
        try {
            Class cliDaoClass = Class.forName(cliDao);
            Constructor[] constr = cliDaoClass.getConstructors();
            return (ClientsDao)constr[0].newInstance(this);
            
        } catch (Exception e) {
            throw new DaoConfigurationException("Probleme d'injection avec la classe clients", e);
        }
    }
    
    public FournisseursDao getDAOFournisseurs()
    {
        //return new FournisseursDaoMysql(this);
        try {
            Class fouDaoClass = Class.forName(fouDao);
            Constructor[] constr = fouDaoClass.getConstructors();
            return (FournisseursDao)constr[0].newInstance(this);
            
        } catch (Exception e) {
            throw new DaoConfigurationException("Probleme d'injection avec la classe fournisseurs", e);
        }
    }
        
    public ProduitsDao getDAOProduits()
    {
        //return new ProduitsDaoMysql(this);
        try {
            Class proDaoClass = Class.forName(proDao);
            Constructor[] constr = proDaoClass.getConstructors();
            return (ProduitsDao)constr[0].newInstance(this);
            
        } catch (Exception e) {
            throw new DaoConfigurationException("Probleme d'injection avec la classe Produits", e);
        }
    }
    
    public LigneVenteDao getDAOLigneVente()
    {
    try {
        //System.out.println(ligDao);
            Class ligneVenteDaoClass = Class.forName(ligDao);
           // System.out.println(ligneVenteDaoClass);
            Constructor[] constr = ligneVenteDaoClass.getConstructors();
          //  System.out.println(constr);
            return (LigneVenteDao)constr[0].newInstance(this);
            
            
        } catch (Exception e) {
            throw new DaoConfigurationException("Probleme d'injection avec la classe lignevente", e);
        }
    }
}
