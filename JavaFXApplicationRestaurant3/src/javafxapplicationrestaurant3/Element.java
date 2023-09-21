/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplicationrestaurant3;

/**
 *
 * @author hhyyt
 */
public class Element<T>
{
    private T data;

    private Element previous;
    private Element next;
    
    
    public Element(T data, Element<T> previous, Element<T> next)
    {
        this.data = data;
        this.previous = previous;
        this.next = next;
    }
    
    
    public T getData()
    {
        return data;
    }
    
    public Element Previous()
    {
        return previous;
    }

    public void Previous(Element previous)
    {
        this.previous = previous;
    }

    public Element Next()
    {
        return next;
    }

    public void Next(Element next)
    {
        this.next = next;
    }
    

    
}
