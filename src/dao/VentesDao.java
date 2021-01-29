package dao;

import beans.Ventes;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author verla
 */
public interface VentesDao {
   void deleteVentes(int numVente) throws DaoException;;

   void insertVentes(Ventes ven) throws DaoException;

   ArrayList selectVentes() throws DaoException;
    
   ArrayList selectVentesParCli(int numCli) throws DaoException;
   
   ArrayList selectVentesParDate(Date debut, Date fin) throws DaoException;
   
   ArrayList selectVentesParTotal(double debut, double fin) throws DaoException;
        
   void updateVentes(Ventes app) throws DaoException; 
}
