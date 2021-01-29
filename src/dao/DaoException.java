/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

public class DaoException extends RuntimeException {
    /*
     * Constructeurs
     */
    public DaoException( String message ) {
        super( message );
    }

    public DaoException( String message, Throwable cause ) {
        super( message, cause );
    }

    public DaoException( Throwable cause ) {
        super( cause );
    }
}    
