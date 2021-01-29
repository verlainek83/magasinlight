package daoMysql;

import beans.Clients;
import dao.ClientsDao;
import dao.DaoException;
import dao.DaoFactory;
import dao.DaoUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author verla
 */
public class ClientsDaoMysql implements ClientsDao{

    private DaoFactory daoFactory;
    
    private static final String SQL_SELECT_TOUS = "Select * from clients";
    private static final String SQL_INSERT = "Insert into clients(Titre, Nom, Prenom, Adresse, CP, Ville) values (?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE = "Delete from clients where NumCli = ?";
    private static final String SQL_UPDATE = "Update clients set Titre = ?, Nom = ?, Prenom = ?, Adresse = ?, CP = ?, Ville = ? where NumCli = ?";
    
    public ClientsDaoMysql(DaoFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }
    
    @Override
    public void deleteCLients(int NumCli) throws DaoException {
        Connection conn = null;
        PreparedStatement prepStat = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            conn = daoFactory.getConnection();

            prepStat = DaoUtil.initialisationRequetePreparee(conn, SQL_DELETE, false, NumCli);
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
    public int insertClients(Clients cli) throws DaoException {
        Connection conn = null;
        PreparedStatement prepStat = null;
        ResultSet valAuto = null;
        
        int numCli;

        try {
            /* Récupération d'une connexion depuis la Factory */
            conn = daoFactory.getConnection();

            prepStat = DaoUtil.initialisationRequetePreparee(conn, SQL_INSERT, true, 
                                cli.getTitre(),cli.getNom(),cli.getPrenom(),cli.getAdresse(),cli.getCp(),cli.getVille());
            int statut = prepStat.executeUpdate();
            if (statut == 0)
                throw new DaoException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
            /* Récupération de l'id auto-généré par la requête d'insertion */
            valAuto = prepStat.getGeneratedKeys();
            if ( valAuto.next() ) {
                numCli = valAuto.getInt(1);
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

        return numCli; 
    }

    @Override
    public ArrayList<Clients> selectClients() throws DaoException {
        Connection conn=null;
        PreparedStatement prepStat=null;
        ResultSet resu=null;
        
        ArrayList <Clients> myList = new ArrayList();
                     
        try {
            conn = daoFactory.getConnection();
            prepStat = DaoUtil.initialisationRequetePreparee(conn, SQL_SELECT_TOUS, false, (Object[])null);
            resu = prepStat.executeQuery();
            while (resu.next())
            {  
                //creation de l'objet Clients
                myList.add(new Clients(resu.getInt(1),resu.getString(2),resu.getString(3),
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
    public void updateClients(Clients cli) throws DaoException {
        Connection conn = null;
        PreparedStatement prepStat = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            conn = daoFactory.getConnection();

            prepStat = DaoUtil.initialisationRequetePreparee(conn, SQL_UPDATE, false,
                                                     cli.getNumCli(),cli.getTitre(),cli.getNom(),cli.getPrenom(),
                                                     cli.getAdresse(),cli.getCp(),cli.getVille());
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
