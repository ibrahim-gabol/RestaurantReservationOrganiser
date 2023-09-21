/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplicationrestaurant3;

/**
 *
 * @author hhyyt
 */
public class TableElement
{
    
    private int tableID;
    private int floorNumber;
    private int seatAmount;
    
    // LinkedList Elements
    private TableElement previous;
    private TableElement next;
    
    // Used for matching tables with Hungarian
    public TableElement(int tableId, int floorNumber, int seatAmount, TableElement previous, TableElement next)
    {
        this.tableID = tableId;
        this.floorNumber = floorNumber;
        this.seatAmount = seatAmount;
    }
    
    
     public TableElement Previous()
    {
        return previous;
    }

    public void Previous(TableElement value)
    {
        previous = value;
    }

    public TableElement Next()
    {
        return next;
    }

    public void Next(TableElement value)
    {
        next = value;
    }

    public int getTableID()
    {
        return tableID;
    }

    public void setTableID(int tableID )
    {
        this.tableID = tableID;
    }

    public int getFloorNumber()
    {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber)
    {
        this.floorNumber = floorNumber;
    }

    public int getSeatAmount()
    {
        return seatAmount;
    }

    public void setSeatAmount(int seatAmount)
    {
        this.seatAmount = seatAmount;
    }
    


}
    
