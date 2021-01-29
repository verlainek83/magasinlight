package dao;

import beans.Clients;
import java.util.ArrayList;

/**
 *
 * @author verla
 */
public interface ClientsDao {
    void deleteCLients(int NumCli) throws DaoException;
        
    int insertClients(Clients cli) throws DaoException;

    ArrayList <Clients> selectClients() throws DaoException;

    void updateClients(Clients cli) throws DaoException;
}
