/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplicationrestaurant3;

/**
 *
 * @author hhyyt
 */
public class LinkedListQueueTable // write exceptions
{
    private TableElement front;
    private int length = 0;
    
    public void insert (int tableID, int floorNumber, int seatAmount)
    {
        TableElement current = front;
        
        if (isEmpty())
        {
            front = new TableElement( tableID , floorNumber, seatAmount, null, null);
        }
        else
        {
            while (current.Next() != null)
            {
                current = current.Next();
            }
            
            TableElement toInsert = new TableElement( tableID, floorNumber, seatAmount, current, null);
            current.Next(toInsert);
        }
        length++;
    }
    /*public String returnConnections()
    {
        String connections = "";
        
        Element current = front;

        for (int i = 0; i < length(); i++)
        {
            connections = (connections + current.Connection() + "-" + current.Weight() + ", ");
            if (current.Next() != null)
            {
                current = current.Next();
            }
        }
        return connections;
    }*/
    
    

    public TableElement pop()
    {
        String returnString = "";
        TableElement current = front;

        while (current.Next() != null)
        {
            current = current.Next();
        }

        TableElement toRemove = current;
        if (!(current == front))
        {
            current.Previous().Next(null);        
        }
        length--;
        
        return toRemove;
    }
    
    public TableElement get(int index)
    {
        TableElement current = front;
        
        while (index > 0)
        {
            if (current.Next() != null)
            {
                current = current.Next();
            }
            index--;
        }
        
        
        return current;
      }
    
    public boolean isEmpty()
    {
        return (length==0);
    }
    
    public int length()
    {
        return length;
    }
}
