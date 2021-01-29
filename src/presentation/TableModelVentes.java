
package presentation;

import beans.Ventes;
import java.util.ArrayList;

/**
 *
 * @author verla
 */
public class TableModelVentes extends javax.swing.table.AbstractTableModel{

    private String[] columnNames = {"NumVente","NumCli","Date","Total","Paye"};
    private ArrayList <Ventes> myList;

    public TableModelVentes (ArrayList myList)
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
        Ventes myVen = myList.get(row);
        switch (col)
        {
            case 0 :    return myVen.getNumVente();
            case 1 :    return myVen.getNumCli().getNumCli();
            case 2 :    return myVen.getDate();
            case 3 :    return myVen.getTotal();
            case 4 :    return myVen.getPaye(); 
        }
        return null;   
    }
    
    @Override
    public Class getColumnClass(int c) {
        //return getValueAt(0, c).getClass(); ! provoque une erreur quand la table est vide et qu'il y a un sorter !
        switch (c)
        {
            case 0 :    return String.class;
            case 1 :    return String.class;
            case 2 :    return String.class;
            case 3 :    return String.class;
            case 4 :    return String.class;
        }
        return null;
    }
    
    public void setMyList (ArrayList myList)
    {
        this.myList = myList;
        this.fireTableDataChanged();
    }
    
    public ArrayList <Ventes> getMyList ()
    {
        return myList;
    }
    
    public Ventes getMyList (int index)
    {
        return myList.get(index);
    }
    
}
