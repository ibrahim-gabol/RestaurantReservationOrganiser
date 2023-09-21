/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplicationrestaurant3;

/**
 *
 * @author hhyyt
 */
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sqlitetutorial.net
 */
public class DatabaseConnect
{

    private Connection conn = null;

    public DatabaseConnect()
    {
        // Create connection with Restaurant Database
        try
        {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:RestaurantDatabase.db");
            conn.setAutoCommit(false);
            System.out.println("Opened database successfully");
        } catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void close()
    {
        try
        {
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(DatabaseConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 




    


    public void  addCustomer(String FirstName, String LastName, String PhoneNumber) // This can be sent as a queue
    {
        boolean submitted = false;
        Statement stmt = null;

        //String[] addressInfo = address.split(",");
        ResultSet rs = null;


        try
        {
           
            stmt = conn.createStatement();

            String sql = "INSERT INTO Customer ( FirstName, LastName, PhoneNumber) "
                    + "VALUES ( '" + FirstName + "', '" + LastName + "', '" + PhoneNumber + "');";

            stmt.executeUpdate(sql);

            stmt.close();
            conn.commit();
            submitted = true;

        } catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }


    }
    

    public boolean updateCustomer(String customerID, String FirstName, String LastName, String PhoneNumber)
    {
        boolean bUpdate = false;
        Statement stmt = null;

        try
        {
            stmt = conn.createStatement();
            String sql = "UPDATE Customer set FirstName = '" + FirstName + "', LastName='" + LastName + "', PhoneNumber= '" + PhoneNumber + "'  WHERE CustomerID =" + customerID + " ;";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.commit();
            bUpdate = true;
        } catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return bUpdate;
    }
    
        public boolean deleteCustomer(String CustomerID)
    {
        boolean bUpdate = false;
        Statement stmt = null;

        try
        {
            stmt = conn.createStatement();
            String sql = "DELETE FROM Customer WHERE CustomerID= " + CustomerID + ";";

            stmt.executeUpdate(sql);
            stmt.close();
            conn.commit();
            bUpdate = true;
        } catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return bUpdate;
    }

        
    // Return False if:
    // If there are too many reservations at that date and time
    // If this person already has booked at this date and time
     public boolean canAddReservation(String customerID, String date, String timeSlot) 
    {
        boolean canSubmit = true;
        Statement stmt = null;

        //String[] addressInfo = address.split(",");
        ResultSet rs = null;
        int numOfReservations = 0;
        int numOfTables = 0;

        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) FROM Reservation, ReservationDateTime WHERE Date = '" + date  + "' AND Time = '" + timeSlot  + "' AND ReservationDateTime.ReservationDateTimeID=Reservation.ReservationDateTimeID;");
            
            while (rs.next())
            {
                numOfReservations = rs.getInt("COUNT(*)");
                //System.out.println("Num of reservations at this date and time is " + numOfReservations);
                
            }
            rs.close();  
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) FROM Tables");
            while (rs.next())
            {
                numOfTables = rs.getInt("COUNT(*)");                
                //System.out.println("Num of tables is " + numOfTables);

            }
            if (numOfReservations > numOfTables)
            {
                canSubmit  = false;
            }
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) FROM Reservation, ReservationDateTime WHERE CustomerID = " + customerID + " AND Date= '" + date + "' AND Time = '" + timeSlot + "' AND ReservationDateTime.ReservationDateTimeID=Reservation.ReservationDateTimeID; ");
            while (rs.next())
            {
                numOfReservations = rs.getInt("COUNT(*)");
                //System.out.println("Num of customerreservations at this time and dateis " + numOfReservations);
            }
            if (numOfReservations > 0)
            {
                canSubmit = false;
            }
            rs.close();
            stmt.close();
            conn.commit();

        } catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return canSubmit;
    }
    
     
    public void addReservation(String customerID, String groupSize, String accessibilityArrangements, String preferredSeatingLevel,  String date, String timeSlot)
    {
        String customerReservationInformationID = getCustomerReservationInformationID(groupSize, accessibilityArrangements, preferredSeatingLevel);
        String reservationDateTimeID = getReservationDateTimeID(date, timeSlot);
        
        boolean bInsert = false;
        Statement stmt = null;

        //String[] addressInfo = address.split(",");
        ResultSet rs = null;

        try
        {
            
            stmt = conn.createStatement();

            String sql = "INSERT INTO Reservation( CustomerID , ReservationDateTimeID, CustomerReservationInformationID) "
                    + "VALUES (  " + customerID + "  ,'" + reservationDateTimeID + "', '" + customerReservationInformationID + "');";
            stmt.executeUpdate(sql);

            stmt.close();
            conn.commit();
            bInsert = true;

        } catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    public boolean canUpdateReservation(String customerID, String date, String timeSlot)
    {
        boolean exists = false;
        Statement stmt = null;

        ResultSet rs = null;
        int numOfReservationDateTimes = 0;

        try
        {         
            stmt = conn.createStatement();

               rs = stmt.executeQuery("SELECT COUNT(*) FROM Reservation, ReservationDateTime WHERE CustomerID = " + customerID + " AND Date= '" + date + "' AND Time = '" + timeSlot + "' AND ReservationDateTime.ReservationDateTimeID=Reservation.ReservationDateTimeID; ");

            
            while (rs.next())
            {
                numOfReservationDateTimes = rs.getInt("COUNT(*)");
                
            }
            
            if (numOfReservationDateTimes > 0)
            {
                exists = true;
            }
            
            rs.close();  
            stmt.close();
            conn.commit();

        } catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return exists;
    }
    
    
    public boolean updateReservation(String customerID, String oldDate, String oldTimeSlot, String groupSize, String accessibilityArrangements, String preferredSeatingLevel,  String newDate, String newTimeSlot)
    {

        boolean bUpdate = false;
        Statement stmt = null;
        String customerReservationInformationID = getCustomerReservationInformationID(groupSize, accessibilityArrangements, preferredSeatingLevel);
        String oldReservationDateTimeID = getReservationDateTimeID(oldDate, oldTimeSlot);
        String newReservationDateTimeID = getReservationDateTimeID(newDate, newTimeSlot);
        try
        {
            stmt = conn.createStatement();
            String sql = "UPDATE Reservation set ReservationDateTimeID = " + newReservationDateTimeID + ", customerReservationInformationID=" + customerReservationInformationID + " "
                    + "WHERE CustomerID = " + customerID + " AND reservationDateTimeID = " + oldReservationDateTimeID + ";";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.commit();
            bUpdate = true;
        } catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return bUpdate;
    }



 
    public boolean deleteReservation(String CustomerID, String date, String timeSlot)
    {
        boolean bUpdate = false;
        Statement stmt = null;
        String reservationDateTimeID = getReservationDateTimeID(date, timeSlot);

        try
        {
            stmt = conn.createStatement();
            String sql = "DELETE FROM Reservation WHERE CustomerID= " + CustomerID + " AND ReservationDateTimeID = " + reservationDateTimeID + ";";

            stmt.executeUpdate(sql);
            stmt.close();
            conn.commit();
            bUpdate = true;
        } catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return bUpdate;
    }

    // Every record in the CustomerTable will be returned, compiled as a linkedListQueue
    public Queue retrieveCustomerInformation()
    {
        Statement stmt = null;
        ResultSet rs = null;
        Queue<CustomerInformation> queue = new Queue<>();
        
        try
        {
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Customer");
            
            while (rs.next())
            {
                String customerID = "" + rs.getInt("CustomerID");
                String firstName = "" + rs.getString("FirstName");
                String lastName  = "" + rs.getString("LastName");
                String phoneNumber = "" + rs.getString("PhoneNumber");

                
                queue.add(new CustomerInformation(customerID, firstName, lastName, phoneNumber));
            }
            rs.close();
            stmt.close();
            conn.commit();
        } catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return queue; // queue
    }

    public boolean foundCustomerID(String customerID)
    {
        Statement stmt = null;
        ResultSet rs = null;
        boolean returnBoolean = false;

        try
        {

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Customer WHERE CustomerID =" + customerID);

            // If there is a resultSet, we have found a record with the specificed customerID
            while(rs.next())
            {
                returnBoolean = true;
            }
            rs.close();
            stmt.close();
            conn.commit();
        }
        catch (Exception e)
        {

        }

        return returnBoolean; // queue
    }

    
    public boolean foundReservationID(String reservationID)
    {
        Statement stmt = null;
        ResultSet rs = null;
        boolean returnBoolean = false;

        try
        {

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Reservation WHERE ReservationID =" + reservationID);

            // If there is a resultSet, we have found a record with the specificed reservationID
            while(rs.next())
            {
                returnBoolean = true;
            }
            rs.close();
            stmt.close();
            conn.commit();
        }
        catch (Exception e)
        {

        }

        return returnBoolean; // queue
    }
    
    
    
    public String getCustomerName(String reservationID)
    {
        Statement stmt = null;
        ResultSet rs = null;
        String returnString = null;

        try
        {

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT FirstName, LastName FROM Customer, Reservation WHERE Reservation.CustomerID = Customer.CustomerID AND ReservationID = " + reservationID);

            while (rs.next())
            {
                returnString = rs.getString("FirstName");
                returnString = returnString + " " + rs.getString("LastName");
                
                
            }
            rs.close();
            stmt.close();
            conn.commit();
        } catch (Exception e)
        {

        }

        return returnString; // queue
    }
    
    
    
           
    // Return all reservations at specified date and time as a linkedlistqueue
    public LinkedListQueueCustomerReservation getReservations(String date, String timeSlot)
    {
       Statement stmt = null;
       ResultSet rs = null;
       boolean returnBoolean = false;   
       
       LinkedListQueueCustomerReservation queueCustomerReservation = new LinkedListQueueCustomerReservation();
       LinkedListQueueTable queueTable = new LinkedListQueueTable();

        try
        {                     
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Tables;");

            while (rs.next())
            {
                queueTable.insert(rs.getInt("TableID"), rs.getInt("FloorNumber"), rs.getInt("SeatAmount"));
            }


            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT ReservationID, PreferredSeatingLevel, GroupSize  FROM Customer, Reservation WHERE Date='" + date + "'" + "AND TimeSlot ='" + timeSlot + "' AND Reservation.CustomerID = Customer.CustomerID;");

            
            while (rs.next())
            {
                int reservationID = rs.getInt("ReservationID");
                int[] tableScores = new int[queueTable.length()];
                int preferredSeatingLevel = rs.getInt("PreferredSeatingLevel");
                int groupSize = rs.getInt("GroupSize");
                for (int i = 0; i < queueTable.length(); i++)
                {
                   int tableScore = 0;

                   int floorNumber = queueTable.get(i).getFloorNumber();
                   int seatAmount = queueTable.get(i).getSeatAmount();
                   
                   if (!(preferredSeatingLevel == floorNumber))
                   {
                       tableScore += 40;
                   }
                   if (groupSize > seatAmount)
                   {
                       tableScore += (groupSize - seatAmount) * 50;
                   }
                   else if (groupSize < seatAmount)
                   {
                       tableScore += (seatAmount - groupSize) * 10;
                   }
                   tableScores[i] = tableScore;
                    System.out.println(queueTable.get(i).getTableID());
                       
                }
                queueCustomerReservation.insert(reservationID, tableScores);
                System.out.println(Arrays.toString(tableScores));
                
            }
            rs.close();
            stmt.close();
            conn.commit();
        } catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return queueCustomerReservation; // queue

    }
   
    
    public LinkedListQueueTable getTables()
    {
       Statement stmt = null;
       ResultSet rs = null;
       boolean returnBoolean = false;   
       
       LinkedListQueueTable queueTable = new LinkedListQueueTable();
       
        try
        {                     
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Tables;");

            while (rs.next())
            {
                queueTable.insert(rs.getInt("TableID"), rs.getInt("FloorNumber"), rs.getInt("SeatAmount"));
            }
            rs.close();
            stmt.close();
            conn.commit();
        } catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return queueTable; // queue

    }

    
    
    private boolean customerReservationInformationExists(String groupSize, String accessibilityArrangements, String preferredSeatingLevel)
    {
        boolean exists = false;
        Statement stmt = null;

        ResultSet rs = null;
        int numOfReservationInformations = 0;

        try
        {         
            stmt = conn.createStatement();

            if (accessibilityArrangements.isEmpty())
            {
                rs = stmt.executeQuery("SELECT COUNT(*) FROM CustomerReservationInformation WHERE GroupSize = " + groupSize  + " AND Accessibility ISNULL AND  PreferredSeatingLevel =" + preferredSeatingLevel +";");
   
            }
            else
            {
                rs = stmt.executeQuery("SELECT COUNT(*) FROM CustomerReservationInformation WHERE GroupSize = " + groupSize  + " AND Accessibility = '" + accessibilityArrangements  + "' AND  PreferredSeatingLevel = " + preferredSeatingLevel +";");
            }
            
            while (rs.next())
            {
                numOfReservationInformations = rs.getInt("COUNT(*)");
                
            }
            
            if (numOfReservationInformations > 0)
            {
                exists = true;
            }
            
            rs.close();  
            stmt.close();
            conn.commit();

        } catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return exists;
    }
    
    private boolean reservationDateTimeExists(String date, String timeSlot)
    {
        boolean exists = false;
        Statement stmt = null;

        ResultSet rs = null;
        int numOfReservationDateTimes = 0;

        try
        {         
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT COUNT(*) FROM ReservationDateTime WHERE Date = '" + date  + "'  AND  Time = '" + timeSlot +"';");
   
            
            while (rs.next())
            {
                numOfReservationDateTimes = rs.getInt("COUNT(*)");
                
            }
            
            if (numOfReservationDateTimes > 0)
            {
                exists = true;
            }
            
            rs.close();  
            stmt.close();
            conn.commit();

        } catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return exists;
    }
       
    private String getCustomerReservationInformationID(String groupSize, String accessibilityArrangements, String preferredSeatingLevel )
    {
        Statement stmt = null;
        ResultSet rs = null;
        String returnString = null;

        try
        {

            stmt = conn.createStatement();
            if (!customerReservationInformationExists(groupSize, accessibilityArrangements, preferredSeatingLevel))
            {
                stmt.executeQuery("INSERT INTO CustomerReservationInformation(GroupSize, Accessibility, PreferredSeatingLevel) VALUES (" + groupSize + ", '" + accessibilityArrangements + "'," + preferredSeatingLevel + ");");
                

            }
            rs.close();
            stmt.close();
            conn.commit();
        } catch (Exception e)
        {

        }
        stmt = null;
        rs = null;
        
        try
        {
            stmt = conn.createStatement();
            if (accessibilityArrangements.isEmpty())
            {
                rs = stmt.executeQuery("SELECT * FROM CustomerReservationInformation WHERE GroupSize = " + groupSize  + " AND Accessibility ISNULL AND  PreferredSeatingLevel = " + preferredSeatingLevel +";");
                System.out.println("finished with taht");
                while (rs.next())
                {
                    returnString = Integer.toString(rs.getInt("CustomerReservationInformationID"));
                }                  
            }
            else
            {
                rs = stmt.executeQuery("SELECT * FROM CustomerReservationInformation WHERE GroupSize = " + groupSize  + " AND Accessibility='" + accessibilityArrangements + "' AND  PreferredSeatingLevel = " + preferredSeatingLevel +";");
                System.out.println("finished with that number 2");
                while (rs.next())
                {
                    returnString = Integer.toString(rs.getInt("CustomerReservationInformationID"));
                }                      
            }
            rs.close();
            stmt.close();
            conn.commit();
        } catch (Exception E)
        {
            
        }
        
        

        return returnString; // queue
    }
    
    private String getReservationDateTimeID(String date, String timeSlot)
    {
        Statement stmt = null;
        ResultSet rs = null;
        String returnString = null;

        try
        {

            stmt = conn.createStatement();
            if (!reservationDateTimeExists(date, timeSlot))
            {
                stmt.executeQuery("INSERT INTO ReservationDateTime(Date, Time) VALUES ('" + date + "', '" + timeSlot + "');");                
            }            
            rs.close();
            stmt.close();
            conn.commit();
        } catch (Exception e)
        {

        }

        
        stmt = null;
        rs = null;
        
        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM ReservationDateTime WHERE Date = '" + date  + "'  AND  Time = '" + timeSlot +"';");
            
            while (rs.next())
            {
                returnString = Integer.toString(rs.getInt("ReservationDateTimeID"));
            }
            rs.close();
            stmt.close();
            conn.commit();
        }catch(Exception e)
        {


        }
        
        
        return returnString; // queue
    }

   




}
