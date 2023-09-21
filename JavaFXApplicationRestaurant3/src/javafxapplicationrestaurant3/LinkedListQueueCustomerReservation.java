/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplicationrestaurant3;

/**
 *
 * @author hhyyt
 */
public class LinkedListQueueCustomerReservation
{
    private CustomerReservationElement front;
    private int length = 0;
    
 
    
    LinkedList hello;
    
    public void insert (String customerID, String firstName, String lastName, String phoneNumber, String accessibility, String preferredSeatingLevel, String groupSize)
    {
        CustomerReservationElement current = front;
        
        if (isEmpty())
        {
            front = new CustomerReservationElement( customerID,  firstName,  lastName,  phoneNumber,  accessibility,  preferredSeatingLevel, groupSize, null, null);
        }
        else
        {
            while (current.Next() != null)
            {
                current = current.Next();
            }
            
            CustomerReservationElement toInsert = new CustomerReservationElement(customerID,  firstName,  lastName,  phoneNumber,  accessibility,  preferredSeatingLevel, groupSize, current, null);
            current.Next(toInsert);
        }
        length++;
    }

    public void insert (String customerID, String firstName, String lastName, String phoneNumber, String accessibility, String preferredSeatingLevel, String groupSize, String date, String timeSlot)
    {
        CustomerReservationElement current = front;
        
        if (isEmpty())
        {
            front = new CustomerReservationElement( customerID,  firstName,  lastName,  phoneNumber,  accessibility,  preferredSeatingLevel, groupSize,date, timeSlot, null, null);
        }
        else
        {
            while (current.Next() != null)
            {
                current = current.Next();
            }
            
            CustomerReservationElement toInsert = new CustomerReservationElement(customerID,  firstName,  lastName,  phoneNumber,  accessibility,  preferredSeatingLevel, groupSize, date,timeSlot, current, null);
            current.Next(toInsert);
        }
        length++;
    }


    
    public void insert (int reservationID, int[] tableScores)
    {
        CustomerReservationElement current = front;
        
        if (isEmpty())
        {
            front = new CustomerReservationElement( reservationID, tableScores, null, null);
        }
        else
        {
            while (current.Next() != null)
            {
                current = current.Next();
            }
            
            CustomerReservationElement toInsert = new CustomerReservationElement( reservationID, tableScores, current, null);
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
    
    

    public CustomerReservationElement pop()
    {
        CustomerReservationElement current = front;

        while (current.Next() != null)
        {
            current = current.Next();
        }

        CustomerReservationElement toRemove = current;
        if (!(current == front))
        {
            current.Previous().Next(null);        
        }
        length--;
        
        return toRemove;
    }
    
    
    
    public CustomerReservationElement get(int index)
    {
        CustomerReservationElement current = front;
        
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
