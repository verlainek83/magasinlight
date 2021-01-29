package presentation;

import beans.Fournisseurs;
import java.util.ArrayList;

/**
 *
 * @author verla
 */
public class TableModelFournisseurs extends javax.swing.table.AbstractTableModel{

    private String[] columnNames = {"NumFour","Societe","Nom","Prenom","Adresse","CP","Ville"};
    private ArrayList <Fournisseurs> myList;

    public TableModelFournisseurs(ArrayList myList) {
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
        Fournisseurs myFou = myList.get(row);
        switch (col)
        {
            case 0 :    return myFou.getNumFour();
            case 1 :    return myFou.getSociete();
            case 2 :    return myFou.getNom();
            case 3 :    return myFou.getPrenom();
            case 4 :    return myFou.getAdresse();
            case 5 :    return myFou.getCp();
            case 6 :    return myFou.getVille();
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
