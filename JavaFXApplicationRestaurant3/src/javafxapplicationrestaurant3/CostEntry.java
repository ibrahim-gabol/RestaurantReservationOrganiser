/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplicationrestaurant3;

/**
 *
 * @author hhyyt
 */
public class CostEntry
{
    
    int cost;
    int trueCost;
    boolean starred;
    boolean primed;
    int covered; // sometimes covered by more than one
    int rowPosition;
    int columnPosition;

    public CostEntry(int cost, int rowPosition, int columnPosition)
    {
        this.trueCost = cost;
        this.rowPosition = rowPosition;
        this.columnPosition = columnPosition;
    }
    
    public int getRow()
    {
        return rowPosition;
    }
    
    public int getColumn()
    {
        return columnPosition;
    }
    
    public int getCost()
    {
        return cost;
    }
    
    public void setCost(int cost)
    {
        this.cost = cost;
    }
    
    public int getTrueCost()
    {
        return trueCost;
    }
    
    public void setStarred(boolean starred)
    {
        this.starred = starred;
    }
    
    public boolean getStarred()
    {
        return starred;
    }
        
    public void setPrimed(boolean primed)
    {
        this.primed = primed;
    }
    
    public boolean getPrimed()
    {
        return primed;
    }
    
    public int getCovered()
    {
        return covered;
    }
    
    public void setCovered(int covered)
    {
        this.covered = covered;
    }
}
