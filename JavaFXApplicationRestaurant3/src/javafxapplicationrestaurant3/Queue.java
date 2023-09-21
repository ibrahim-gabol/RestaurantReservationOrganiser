/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplicationrestaurant3;

/**
 *
 * @author hhyyt
 */
public class Queue<T>
{
    private LinkedList<T> list = new LinkedList<>();
    
    
    public void add(T data)
    {
        list.insert(data);
    }
    
    public T pop()
    {
        return list.remove(list.length());
    }
    
    public int length()
    {
        return list.length();
    }
    
}
