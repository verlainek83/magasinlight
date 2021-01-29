package daoMysql;

import beans.LigneVente;
import beans.Produits;
import beans.Ventes;
import dao.DaoException;
import dao.DaoFactory;
import dao.DaoUtil;
import dao.LigneVenteDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author verla
 */
public class LigneVenteDaoMysql implements LigneVenteDao{
    
    private DaoFactory daoFactory;
    
    private static final String SQL_SELECT_TOUS = "Select L.NumVente, L.CodeProd, L.Quantité, L.prix "
                                                + "from lignevente L ";
            
    private static final String SQL_INSERT = "Insert into lignevente (NumVente, CodeProd, Quantité, Prix) "+
            "values (?, ?, ?, ?)";
    
    private static final String SQL_UPDATE_Auto = "UPDATE ventes ve SET ve.Total=(SELECT SUM(l.Prix) "
                                                + "FROM lignevente l INNER join ventes v on l.NumVente = v.NumVente "
                                                + "WHERE l.NumVente = ?) WHERE ve.NumVente = ?";
    
    private static final String SQL_UPDATE = "Update lignevente l set l.NumVente = ?, l.CodeProd = ? ," + 
            "l.Quantité = ?, l.Prix = ? where l.NumVente = ? and l.CodeProd = ?";
    
    private static final String SQL_DELETE = "delete from ventes where NumVente = ?"; 
    
    public LigneVenteDaoMysql(DaoFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }        
    @Override
    public void deleteLigneVente(int numVente) throws DaoException {
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
        }   }

    @Override
    
    public void insertLigneVente(LigneVente lig) throws DaoException {
        Connection conn = null;
        PreparedStatement prepStat = null;
        PreparedStatement prepStat1 = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            conn = daoFactory.getConnection();
            
            prepStat1 = DaoUtil.initialisationRequetePreparee(conn, SQL_UPDATE_Auto, false,
                                                            lig.getNumVente().getNumVente(),
                                                            lig.getNumVente().getNumVente()
                                                            );

            prepStat = DaoUtil.initialisationRequetePreparee(conn, SQL_INSERT, false,
                                                            lig.getNumVente().getNumVente(),
                                                            lig.getCodeProd().getCodeProd(),
                                                            lig.getQuantite(),
                                                            lig.getPrix()
                                                            );
           // System.out.println(prepStat);
            prepStat.executeUpdate();
            prepStat1.executeUpdate();
           // System.out.println( prepStat.executeUpdate());
        } 
        catch (SQLException e) {
            throw new DaoException(e);
        }
        finally {
            DaoUtil.fermeturesSilencieuses(prepStat, conn);
        }  
    }

    @Override
    public ArrayList selectLigneVente() throws DaoException {
        Connection conn=null;
        PreparedStatement prepStat=null;
        ResultSet resu=null;
        
        ArrayList <LigneVente> myList = new ArrayList();
                     
        try {
            conn = daoFactory.getConnection();
            prepStat = DaoUtil.initialisationRequetePreparee(conn, SQL_SELECT_TOUS, false, (Object[])null);
            resu = prepStat.executeQuery();
            while (resu.next())
            {  
                //creation de l'objet LigneVente
                myList.add(new LigneVente(
                                           new Ventes(resu.getInt(1)),
                                           new Produits(resu.getLong(2)),
                                           resu.getDouble(3),
                                           resu.getDouble(4)
                                           )
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
    public void updateLigneVente(LigneVente lig) throws DaoException {
        Connection conn = null;
        PreparedStatement prepStat = null;

        try {
        /* Récupération d'une connexion depuis la Factory */
        conn = daoFactory.getConnection();

            prepStat = DaoUtil.initialisationRequetePreparee(conn, SQL_UPDATE, false,
                    lig.getNumVente().getNumVente(), 
                    lig.getCodeProd().getCodeProd(),
                    lig.getQuantite(),
                    lig.getPrix(),
                    lig.getNumVente().getNumVente(), 
                    lig.getCodeProd().getCodeProd());
            System.out.println(prepStat);
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
    public void updateLigneVentePrix(LigneVente lig) throws DaoException {
        Connection conn = null;
        PreparedStatement prepStat = null;

        try {
        /* Récupération d'une connexion depuis la Factory */
        conn = daoFactory.getConnection();

            prepStat = DaoUtil.initialisationRequetePreparee(conn, SQL_UPDATE_Auto, false,
                    lig.getNumVente(),
                    lig.getNumVente());
            System.out.println(prepStat);
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
    public ArrayList selectLigneVenteParProduits(long codeProd) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList selectLigneVenteParVentes(int numVente) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
