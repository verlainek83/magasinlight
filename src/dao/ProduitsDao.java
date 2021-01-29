package dao;

import beans.Produits;
import java.util.ArrayList;

/**
 *
 * @author verla
 */
public interface ProduitsDao {
   ArrayList selectProduits() throws DaoException;
    
   void deleteProduits(long codeProd) throws DaoException;;

   void insertProduits(Produits pro) throws DaoException;
   
   ArrayList selectProduitsParFour(int numFour) throws DaoException;
        
   void updateProduits(Produits pro) throws DaoException;
}
