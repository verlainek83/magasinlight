
package daoMysql;

import beans.Fournisseurs;
import beans.Produits;
import dao.DaoException;
import dao.DaoFactory;
import dao.DaoUtil;
import dao.ProduitsDao;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author verla
 */
public class ProduitsDaoMysql implements ProduitsDao{
    
    private DaoFactory daoFactory;
    
    private static final String SQL_SELECT_TOUS = "Select CodeProd, P.NumFour, Societe, Nom, Prenom, Adresse,"
            + " CP, Ville, Designation, Prix, Stock, StockMin, DernArrivage "
            + "from produits P, fournisseurs F where P.NumFour = F.NumFour order by 1";
     
    private static final String SQL_SELECT_PAR_FOU = "Select CodeProd, P.NumFour, Societe, Nom, Prenom, Adresse,"
            + " CP, Ville, Designation, Prix, Stock, StockMin, DernArrivage "
            + "from produits P, fournisseurs F where P.NumFour = F.NumFour and P.NumFour = ? order by 1";
    
    private static final String SQL_INSERT = " Insert into produits (CodeProd, NumFour, Designation, Prix, Stock, StockMin, DernArrivage) "+
            "values (?, ?, ?, ?, ?, ?, ?)";
    
    private static final String SQL_DELETE = "delete from produits where CodeProd = ?"; 
    
    private static final String SQL_UPDATE = "Update produits set CodeProd = ? ," + 
            "NumFour= ?, Designation = ?, Prix = ? Stock = ?, StockMin = ?, DernArrivage = ? where CodeProd = ?";
    
     public ProduitsDaoMysql(DaoFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }

    @Override
    public ArrayList selectProduits() throws DaoException {
        Connection conn=null;
        PreparedStatement prepStat=null;
        ResultSet resu=null;
        
        ArrayList <Produits> myList = new ArrayList();
                     
        try {
            conn = daoFactory.getConnection();
            prepStat = DaoUtil.initialisationRequetePreparee(conn, SQL_SELECT_TOUS, false, (Object[])null);
            resu = prepStat.executeQuery();
           // BigInteger bi = BigInteger.valueOf(resu.getLong(1));  
            while (resu.next())
            {              
                //creation de l'objet Produits
               myList.add(new Produits(resu.getLong(1), 
                       new Fournisseurs(resu.getInt(2), resu.getString(3), resu.getString(4),resu.getString(5),
                              resu.getString(6),resu.getInt(7),resu.getString(8)),
                            resu.getString(9), resu.getDouble(10), resu.getDouble(11), resu.getDouble(12),
                        resu.getDate(13))
               );
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
    public void insertProduits(Produits pro) throws DaoException {
        Connection conn = null;
        PreparedStatement prepStat = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            conn = daoFactory.getConnection();

            prepStat = DaoUtil.initialisationRequetePreparee(conn, SQL_INSERT, false,
                    pro.getCodeProd(),pro.getNumFour().getNumFour(),pro.getDesignation(),
                    pro.getPrix(),pro.getStock(),pro.getStockMin(),pro.getDernArrivage());
            prepStat.executeUpdate();
        } 
        catch (SQLException e) {
            throw new DaoException(e);
        }
        finally {
            DaoUtil.fermeturesSilencieuses(prepStat, conn);
        }  }

    @Override
    public ArrayList selectProduitsParFour(int numFour) throws DaoException {
        Connection conn=null;
        PreparedStatement prepStat=null;
        ResultSet resu=null;
        
        ArrayList myList = new ArrayList();
        
        try {
            conn = daoFactory.getConnection();
            if (numFour == -1)
                prepStat = DaoUtil.initialisationRequetePreparee(conn, SQL_SELECT_TOUS, false, (Object[])null);
            else
                prepStat = DaoUtil.initialisationRequetePreparee(conn, SQL_SELECT_PAR_FOU, false, numFour);
            
            resu = prepStat.executeQuery();
           // BigInteger bi = BigInteger.valueOf(resu.getLong(1));
            while (resu.next())
            {                
                //création de l'objet Ventes
                myList.add(new Produits(resu.getLong(1), 
                       new Fournisseurs(resu.getInt(2), resu.getString(3), resu.getString(4),resu.getString(5),
                              resu.getString(6),resu.getInt(7),resu.getString(8)),
                            resu.getString(9), resu.getDouble(10), resu.getDouble(11), resu.getDouble(12),
                        resu.getDate(13)));
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
    public void updateProduits(Produits pro) throws DaoException {
        Connection conn = null;
        PreparedStatement prepStat = null;

        try {
        /* Récupération d'une connexion depuis la Factory */
        conn = daoFactory.getConnection();

            prepStat = DaoUtil.initialisationRequetePreparee(conn, SQL_UPDATE, false,
                    pro.getCodeProd(),
                    pro.getNumFour(),
                    pro.getDesignation(),
                    pro.getPrix(),
                    pro.getStock(),
                    pro.getStockMin(),
                    pro.getDernArrivage());
            prepStat.executeUpdate();
        } 
        catch (SQLException e) {
            throw new DaoException(e);
        }
        finally {
            DaoUtil.fermeturesSilencieuses(prepStat, conn);
        }   }

    @Override
    public void deleteProduits(long codeProd) throws DaoException {
        Connection conn = null;
        PreparedStatement prepStat = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            conn = daoFactory.getConnection();

            prepStat = DaoUtil.initialisationRequetePreparee(conn, SQL_DELETE, false, codeProd);
            prepStat.executeUpdate();
        } 
        catch (SQLException e) {
            throw new DaoException(e);
        }
        finally {
            DaoUtil.fermeturesSilencieuses(prepStat, conn);
        } 
    }
      
}
