package daoMysql;

import beans.Fournisseurs;
import dao.DaoException;
import dao.DaoFactory;
import dao.DaoUtil;
import dao.FournisseursDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author verla
 */
public class FournisseursDaoMysql implements FournisseursDao{

    private DaoFactory daoFactory;
    
    private static final String SQL_SELECT_TOUS = "Select * from fournisseurs";
    private static final String SQL_INSERT = "Insert into clients(Societe, Nom, Prenom, Adresse, CP, Ville) values (?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE = "Delete from clients where NumFour = ?";
    private static final String SQL_UPDATE = "Update fournisseurs set Societe = ?, Nom = ?, Prenom = ?, Adresse = ?, CP = ?, Ville = ? where NumFour = ?";
    
    public FournisseursDaoMysql(DaoFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }
    
    @Override
    public void deleteFournisseurs(int NumFour) throws DaoException {
        Connection conn = null;
        PreparedStatement prepStat = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            conn = daoFactory.getConnection();

            prepStat = DaoUtil.initialisationRequetePreparee(conn, SQL_DELETE, false, NumFour);
            prepStat.executeUpdate();
        } 
        catch (SQLException e) {
            throw new DaoException(e);
        }
        finally {
            DaoUtil.fermeturesSilencieuses(prepStat, conn);
        } 
    }

    @Override
    public int insertFournisseurs(Fournisseurs fou) throws DaoException {
        Connection conn = null;
        PreparedStatement prepStat = null;
        ResultSet valAuto = null;
        
        int numFou;

        try {
            /* Récupération d'une connexion depuis la Factory */
            conn = daoFactory.getConnection();

            prepStat = DaoUtil.initialisationRequetePreparee(conn, SQL_INSERT, true, 
                                fou.getSociete(),fou.getNom(),fou.getPrenom(),fou.getAdresse(),fou.getCp(),fou.getVille());
            int statut = prepStat.executeUpdate();
            if (statut == 0)
                throw new DaoException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
            /* Récupération de l'id auto-généré par la requête d'insertion */
            valAuto = prepStat.getGeneratedKeys();
            if ( valAuto.next() ) {
                numFou = valAuto.getInt(1);
            } else {
                throw new DaoException( "Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
            }
        } 
        catch (SQLException e) {
            throw new DaoException(e);
        }
        finally {
            DaoUtil.fermeturesSilencieuses(prepStat, conn);
        }

        return numFou;   }

    @Override
    public ArrayList<Fournisseurs> selectFournisseurs() throws DaoException {
        Connection conn=null;
        PreparedStatement prepStat=null;
        ResultSet resu=null;
        
        ArrayList <Fournisseurs> myList = new ArrayList();
                     
        try {
            conn = daoFactory.getConnection();
            prepStat = DaoUtil.initialisationRequetePreparee(conn, SQL_SELECT_TOUS, false, (Object[])null);
            resu = prepStat.executeQuery();
            while (resu.next())
            {  
                //creation de l'objet Clients
                myList.add(new Fournisseurs(resu.getInt(1),resu.getString(2),resu.getString(3),
                                        resu.getString(4),resu.getString(5),resu.getInt(6),resu.getString(7)));
             }
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
        finally {
            DaoUtil.fermeturesSilencieuses(prepStat, conn);
        }
            
        return myList; 
    }

    @Override
    public void updateFournisseurs(Fournisseurs fou) throws DaoException {
        Connection conn = null;
        PreparedStatement prepStat = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            conn = daoFactory.getConnection();

            prepStat = DaoUtil.initialisationRequetePreparee(conn, SQL_UPDATE, false,
                                                     fou.getNumFour(),fou.getSociete(),fou.getNom(),fou.getPrenom(),
                                                     fou.getAdresse(),fou.getCp(),fou.getVille());
            int statut = prepStat.executeUpdate();
            if (statut == 0)
                throw new DaoException( "Échec de la modification de l'utilisateur." );
        } 
        catch (SQLException e) {
            throw new DaoException(e);
        }
        finally {
            DaoUtil.fermeturesSilencieuses(prepStat, conn);
        }
    }
    
}
