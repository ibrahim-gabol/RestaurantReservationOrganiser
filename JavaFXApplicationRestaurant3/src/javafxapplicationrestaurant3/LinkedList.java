/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplicationrestaurant3;


/**
 *
 * @author hhyyt
 */
public class LinkedList<T>
{
    
    
    private Element<T> front;
    private int length = 0;
    
 
    

    
    public void insert (T data)
    {
        Element<T> current = front;
        
        if (isEmpty())
        {
            front = new Element<>(data,  null, null);
        }
        else
        {
            while (current.Next() != null)
            {
                current = current.Next();
            }
            
           Element<T> toInsert = new Element<>(data, current, null);
            current.Next(toInsert);
        }
        length++;
    }
    

    public T remove(int position) 
    {
        Element<T> current = front;        
       
        if (position >= length)
        {
            return null;
        }

        for (int i = 0; i < position; i ++)
        {
            current = current.Next();
        }

        if (!(current == front))
        {
            current.Previous().Next(null);        
        }
        if (!(position == length - 1))
        {
            current.Next().Previous(null);
        }
        length--;
        
        return current.getData();
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
