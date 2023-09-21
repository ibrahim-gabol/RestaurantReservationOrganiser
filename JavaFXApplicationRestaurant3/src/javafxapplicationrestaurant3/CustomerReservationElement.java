/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplicationrestaurant3;

/**
 *
 * @author hhyyt
 */
public class CustomerReservationElement
{

    private String  customerID, firstName,lastName,phoneNumber,
                    accessibility,preferredSeatingLevel,groupSize,date,timeSlot;
    
    private int reservationID;


    private int[] tableScores;
    
    // LinkedList Elements
    private CustomerReservationElement previous;
    private CustomerReservationElement next;
    
    // Used when updating tableView and SQL table
    public CustomerReservationElement(String customerID, String firstName, String lastName, String phoneNumber, String accessibility, String preferredSeatingLevel, String groupSize, CustomerReservationElement previous, CustomerReservationElement next)
    {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.accessibility = accessibility;
        this.preferredSeatingLevel = preferredSeatingLevel;
        this.groupSize = groupSize;
        this.previous = previous;
        this.next = next;
        
    }

    public CustomerReservationElement(String customerID, String firstName, String lastName, String phoneNumber, String accessibility, String preferredSeatingLevel, String groupSize, String date, String timeSlot, CustomerReservationElement previous, CustomerReservationElement next)
    {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.accessibility = accessibility;
        this.preferredSeatingLevel = preferredSeatingLevel;
        this.groupSize = groupSize;
        this.date = date;
        this.timeSlot = timeSlot;
        this.previous = previous;
        this.next = next;
        
    }

    
    // Used for matching tables with Hungarian
    public CustomerReservationElement(int reservationID, int[] tableScores, CustomerReservationElement previous, CustomerReservationElement next)
    {
        this.reservationID = reservationID;
        this.tableScores = tableScores;
    }
    
    
     public CustomerReservationElement Previous()
    {
        return previous;
    }

    public void Previous(CustomerReservationElement value)
    {
        previous = value;
    }

    public CustomerReservationElement Next()
    {
        return next;
    }

    public void Next(CustomerReservationElement value)
    {
        next = value;
    }

    public String getCustomerID()
    {
        return customerID;
    }

    public void setCustomerID(String customerID)
    {
        this.customerID = customerID;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getAccessibility()
    {
        return accessibility;
    }

    public void setAccessibility(String accessibility)
    {
        this.accessibility = accessibility;
    }

    public String getPreferredSeatingLevel()
    {
        return preferredSeatingLevel;
    }

    public void setPreferredSeatingLevel(String preferredSeatingLevel)
    {
        this.preferredSeatingLevel = preferredSeatingLevel;
    }

    public String getGroupSize()
    {
        return groupSize;
    }

    public void setGroupSize(String groupSize)
    {
        this.groupSize = groupSize;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getTimeSlot()
    {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot)
    {
        this.timeSlot = timeSlot;
    }    

    public int getReservationID()
    {
        return reservationID;
    }

    public void setReservationID(int reservationID)
    {
        this.reservationID = reservationID;
    }

    public int[] getTableScores()
    {
        return tableScores;
    }

    public void setTableScores(int[] tableScores)
    {
        this.tableScores = tableScores;
    }
}
