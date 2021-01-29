package dao;

import beans.Fournisseurs;
import java.util.ArrayList;

/**
 *
 * @author verla
 */
public interface FournisseursDao {
     void deleteFournisseurs(int NumFour) throws DaoException;
        
    int insertFournisseurs(Fournisseurs fou) throws DaoException;

    ArrayList <Fournisseurs> selectFournisseurs() throws DaoException;

    void updateFournisseurs(Fournisseurs fou) throws DaoException;
}
