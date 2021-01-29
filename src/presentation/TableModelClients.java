package presentation;

import beans.Clients;
import java.util.ArrayList;

/**
 *
 * @author verla
 */
public class TableModelClients extends javax.swing.table.AbstractTableModel{

    private String[] columnNames = {"NumCli","Titre","Nom","Prenom","Adresse","CP","Ville"};
    private ArrayList <Clients> myList;

    public TableModelClients (ArrayList myList)
    {
        this.myList = myList;
    }
    
    @Override
    public int getRowCount() {
        return myList.size();  
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        Clients myCli = myList.get(row);
        switch (col)
        {
            case 0 :    return myCli.getNumCli();
            case 1 :    return myCli.getTitre();
            case 2 :    return myCli.getNom();
            case 3 :    return myCli.getPrenom();
            case 4 :    return myCli.getAdresse();
            case 5 :    return myCli.getCp();
            case 6 :    return myCli.getVille();
        }
        return null;   
    }
    
        @Override
    public Class getColumnClass(int c) {
        switch (c)
        {
            case 0 :    return Integer.class;
            case 1 :    return String.class;
            case 2 :    return String.class;
            case 3 :    return String.class;
            case 4 :    return String.class;
            case 5 :    return Integer.class;
            case 6 :    return String.class;

        }
        return null;
    }
    
}
