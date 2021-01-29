package presentation;

import beans.LigneVente;
import java.util.ArrayList;

/**
 *
 * @author verla
 */
public class TableModelLigneVente extends javax.swing.table.AbstractTableModel{

    private String[] columnNames = {"NumVente","CodeProd","Quantité","Prix"};
    private ArrayList <LigneVente> myList;    

    public TableModelLigneVente(ArrayList<LigneVente> myList) {
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
        LigneVente myLig = myList.get(row);
        switch (col)
        {
            case 0 :    return myLig.getNumVente().getNumVente();
            case 1 :    return myLig.getCodeProd().getCodeProd();
            case 2 :    return myLig.getQuantite();
            case 3 :    return myLig.getPrix();
        }
        return null;
    }
    
    @Override
    public Class getColumnClass(int c) {
           switch (c)
        {
            case 0 :    return String.class;
            case 1 :    return String.class;
            case 2 :    return String.class;
            case 3 :    return String.class;
        }
        return null;
    }
    
    public void setMyList (ArrayList myList)
    {
        this.myList = myList;
        this.fireTableDataChanged();
    }
    
    public ArrayList <LigneVente> getMyList ()
    {
        return myList;
    }
    
    public LigneVente getMyList (int index)
    {
        return myList.get(index);
    }
    
}
