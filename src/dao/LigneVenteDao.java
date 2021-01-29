package dao;

import beans.LigneVente;
import java.util.ArrayList;

/**
 *
 * @author verla
 */
public interface LigneVenteDao {
    void deleteLigneVente(int numVente)throws DaoException;
    
    void insertLigneVente(LigneVente lig) throws DaoException;
    
    ArrayList selectLigneVente() throws DaoException;
    
    void updateLigneVente(LigneVente lig) throws DaoException;
    
    void updateLigneVentePrix(LigneVente lig) throws DaoException;
    
    ArrayList selectLigneVenteParProduits(long codeProd) throws DaoException;
    
    ArrayList selectLigneVenteParVentes(int numVente) throws DaoException;
}
