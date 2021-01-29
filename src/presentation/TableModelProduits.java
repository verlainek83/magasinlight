package presentation;

import beans.Produits;
import java.util.ArrayList;

/**
 *
 * @author verla
 */
public class TableModelProduits extends javax.swing.table.AbstractTableModel{

    private String[] columnNames = {"CodeProd","NumFour","Designation","Prix","Stock","StockMin","DernArrivage"};
    private ArrayList <Produits> myList;
    
    public TableModelProduits (ArrayList myList)
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
        Produits myPro = myList.get(row);
        switch (col)
        {
            case 0 :    return myPro.getCodeProd();
            case 1 :    return myPro.getNumFour().getNumFour();
            case 2 :    return myPro.getDesignation();
            case 3 :    return myPro.getPrix();
            case 4 :    return myPro.getStock();
            case 5 :    return myPro.getStockMin();
            case 6 :    return myPro.getDernArrivage();
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
            case 4 :    return String.class;
            case 5 :    return String.class;
            case 6 :    return String.class;
        }
        return null;
    }
    
     public void setMyList (ArrayList myList)
    {
        this.myList = myList;
        this.fireTableDataChanged();
    }
    
    public ArrayList <Produits> getMyList ()
    {
        return myList;
    }
    
    public Produits getMyList (int index)
    {
        return myList.get(index);
    }
    
}
