package daoMysql;

import beans.Clients;
import beans.Ventes;
import dao.DaoException;
import dao.DaoFactory;
import dao.DaoUtil;
import dao.VentesDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author verla
 */
public class VentesDaoMysql implements VentesDao{
    
    private DaoFactory daoFactory;
    
    private static final String SQL_SELECT_TOUS = "Select NumVente, V.NumCli, Titre, Nom, Prenom, Adresse, CP, Ville,"+
            " Date, Total, Paye from ventes V, clients C where V.NumCli = C.NumCli order by 1";
    
    private static final String SQL_SELECT_PAR_CLI = "Select NumVente, V.NumCli, Titre, Nom, Prenom, Adresse, CP, Ville,"+
            " Date, Total, Paye from ventes V, clients C where V.NumCli = C.NumCli and V.NumCli = ? order by 1";
    
    private static final String SQL_INSERT = " Insert into ventes (NumVente, NumCli, Date, Total, Paye) "+
            "values (?, ?, ?, ?, ?)";
    
    private static final String SQL_DELETE = "delete from ventes where NumVente = ?"; 
    
    private static final String SQL_UPDATE = "Update ventes v set v.NumVente = ?, v.NumCli = ? ," + 
            "v.Date = ?, v.Total = ?, v.Paye= ? where v.NumVente = ?";
                
    private static final String SQL_TRI_DATE = "Select NumVente, V.NumCli, Titre, Nom, Prenom, Adresse, CP, Ville,"+
            " Date, Total, Paye from ventes V, clients C where V.NumCli = C.NumCli and Date >= ? and Date <= ? order by Date";
    
    private static final String SQL_TRI_TOTAL = "Select NumVente, V.NumCli, Titre, Nom, Prenom, Adresse, CP, Ville,"+
            " Date, Total, Paye from ventes V, clients C where V.NumCli = C.NumCli and Total >= ? and Total <= ? order by Total";
    
    
    public VentesDaoMysql(DaoFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }
    
    @Override
    public void deleteVentes(int numVente) throws DaoException {   
    Connection conn = null;
        PreparedStatement prepStat = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            conn = daoFactory.getConnection();

            prepStat = DaoUtil.initialisationRequetePreparee(conn, SQL_DELETE, false, numVente);
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
    public void insertVentes(Ventes ven) throws DaoException {
        Connection conn = null;
        PreparedStatement prepStat = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            conn = daoFactory.getConnection();

            prepStat = DaoUtil.initialisationRequetePreparee(conn, SQL_INSERT, false,
                    ven.getNumVente(),ven.getNumCli().getNumCli(),ven.getDate(),
                    ven.getTotal(),ven.getPaye());
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
    public ArrayList selectVentes() throws DaoException {
        Connection conn=null;
        PreparedStatement prepStat=null;
        ResultSet resu=null;
        
        ArrayList <Ventes> myList = new ArrayList();
                     
        try {
            conn = daoFactory.getConnection();
            prepStat = DaoUtil.initialisationRequetePreparee(conn, SQL_SELECT_TOUS, false, (Object[])null);
            resu = prepStat.executeQuery();
            while (resu.next())
            {  
                //creation de l'objet Ventes
                myList.add(new Ventes(resu.getInt(1),
                        new Clients(resu.getInt(2), resu.getString(3),resu.getString(4),resu.getString(5),
                        resu.getString(6),resu.getInt(7),resu.getString(8)),
                        resu.getDate(9),resu.getDouble(10),resu.getBoolean(11)));
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
    public ArrayList selectVentesParCli(int numCli) throws DaoException {
        Connection conn=null;
        PreparedStatement prepStat=null;
        ResultSet resu=null;
        
        ArrayList myList = new ArrayList();
        
        try {
            conn = daoFactory.getConnection();
            if (numCli == -1)
                prepStat = DaoUtil.initialisationRequetePreparee(conn, SQL_SELECT_TOUS, false, (Object[])null);
            else
                prepStat = DaoUtil.initialisationRequetePreparee(conn, SQL_SELECT_PAR_CLI, false, numCli);
            
            resu = prepStat.executeQuery();
            while (resu.next())
            {                
                //création de l'objet Ventes
                myList.add(new Ventes(resu.getInt(1),
                        new Clients(resu.getInt(2), resu.getString(3),resu.getString(4),resu.getString(5),
                        resu.getString(6),resu.getInt(7),resu.getString(8)),
                        resu.getDate(9),resu.getDouble(10),resu.getBoolean(11)));
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
    public void updateVentes(Ventes ven) throws DaoException {
        Connection conn = null;
        PreparedStatement prepStat = null;

        try {
        /* Récupération d'une connexion depuis la Factory */
        conn = daoFactory.getConnection();

            prepStat = DaoUtil.initialisationRequetePreparee(conn, SQL_UPDATE, false,
                    ven.getNumVente(), 
                    ven.getNumCli().getNumCli(),
                    ven.getDate(),
                    ven.getTotal(),                    
                    ven.getPaye(),
                    ven.getNumVente());
                    //Integer numve = ven.getNumVente();
            //System.out.println(prepStat);
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
    public ArrayList selectVentesParTotal(double debut, double fin) throws DaoException {
        Connection conn=null;
        PreparedStatement prepStat=null;
        ResultSet resu=null;
        
        ArrayList <Ventes> myList = new ArrayList();
                     
        try {
            conn = daoFactory.getConnection();
            prepStat = DaoUtil.initialisationRequetePreparee(conn, SQL_TRI_TOTAL, false, debut, fin);
            resu = prepStat.executeQuery();
            while (resu.next())
            {  
                myList.add(new Ventes(resu.getInt(1),
                        new Clients(resu.getInt(2), resu.getString(3),resu.getString(4),resu.getString(5),
                        resu.getString(6),resu.getInt(7),resu.getString(8)),
                        resu.getDate(9),resu.getDouble(10),resu.getBoolean(11)));
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
    public ArrayList selectVentesParDate(Date debut, Date fin) throws DaoException {
        Connection conn=null;
        PreparedStatement prepStat=null;
        ResultSet resu=null;
        
        ArrayList <Ventes> myList = new ArrayList();
                     
        try {
            conn = daoFactory.getConnection();
            prepStat = DaoUtil.initialisationRequetePreparee(conn, SQL_TRI_DATE, false, debut, fin);
            resu = prepStat.executeQuery();
            while (resu.next())
            {  
                myList.add(new Ventes(resu.getInt(1),
                        new Clients(resu.getInt(2), resu.getString(3),resu.getString(4),resu.getString(5),
                        resu.getString(6),resu.getInt(7),resu.getString(8)),
                        resu.getDate(9),resu.getDouble(10),resu.getBoolean(11)));
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
}
