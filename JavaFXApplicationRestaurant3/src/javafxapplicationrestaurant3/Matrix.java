/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplicationrestaurant3;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author hhyyt
 */


public class Matrix
{
   
    CostEntry[][] matrix;
    int matrixLength;
    
    boolean[] columnCovered, rowCovered;
    
    
    public Matrix(int[][] tempArray)
    {
        matrixLength = tempArray[0].length;
        matrix = new CostEntry[matrixLength][matrixLength];
        
        columnCovered = new boolean[matrixLength];
        rowCovered = new boolean[matrixLength];
        
        System.out.println("hello");
        System.out.println(Arrays.deepToString(tempArray));
        
        for (int i = 0; i < matrixLength; i++)
        {
            columnCovered[i] = false;
            rowCovered[i] = false;
            for (int j = 0; j < matrixLength; j++)
            {
                matrix[i][j] = new CostEntry(tempArray[i][j], i, j);
                matrix[i][j].setCost(tempArray[i][j]);
                
            }
            
        }
        
        

    }
    
    public String toResult(LinkedListQueueCustomerReservation queueCustomerReservation, LinkedListQueueTable queueTable)
    {
        String temp = "";
        DatabaseConnect conn = new DatabaseConnect();
        for (int i = 0; i < matrixLength; i++)
        {
            for (int j = 0; j < matrixLength; j++)
            {
                if ((i < queueCustomerReservation.length()) && matrix[i][j].getStarred())
                {
                    temp = temp + "\n- " + conn.getCustomerName(String.valueOf(queueCustomerReservation.get(i).getReservationID())) +  "  to Table No. " + j;
                }
            }
            
        }
        return temp;
        
    }
    
    public void subtractRowMinima()
    {
        int smallestNumber;

        for (int i = 0; i < matrixLength; i++)
        {
            smallestNumber = 999999999;
            for (int j = 0; j < matrixLength; j++)
            {
                if (matrix[i][j].getCost() < smallestNumber)
                {
                    smallestNumber = matrix[i][j].getCost(); //????????????
                }
            }
            for (int k = 0; k < matrixLength; k++)
            {
                matrix[i][k].setCost(matrix[i][k].getCost() - smallestNumber);
            }

        }
    }

    public void subtractColumnMinima()
    {
        int smallestNumber = 99999;

        for (int j = 0; j < matrixLength; j++)
        {
            smallestNumber = 999999;
            for (int i = 0; i < matrixLength; i++)
            {
                if (matrix[i][j].getCost() < smallestNumber)
                {
                    smallestNumber = matrix[i][j].getCost(); //   ???????????
                }
            }
            for (int k = 0; k < matrixLength; k++)
            {
                int newCost = matrix[k][j].getCost() - smallestNumber;
                if (newCost <= 0)
                {
                    matrix[k][j].setCost(0);
                } else
                {
                    matrix[k][j].setCost(newCost);
                }

            }
        }

    }

    public void assignMinZeroes()
    {

        for (int i = 0; i < matrixLength; i++)
        {
            for (int j = 0; j < matrixLength; j++)
            {
                if (matrix[i][j].getCost() == 0 && matrix[i][j].getPrimed() == false)
                {

                    if (!(checkOtherStarredZeroesOnRow(i, j) || checkOtherStarredZeroesOnColumn(i, j)))
                    {
                        matrix[i][j].setStarred(true);
                    }
                }
            }
        }
    }

    public boolean checkOtherStarredZeroesOnRow(int i, int j)
    {
        boolean foundStarredZero = false;
        for (int k = 0; k < matrixLength; k++)
        {
            if (matrix[i][k].getStarred() && (k != j))
            {
                foundStarredZero = true;
            }
        }
        return foundStarredZero;
    }

    public boolean checkOtherStarredZeroesOnColumn(int i, int j)
    {
        boolean foundStarredZero = false;
        for (int k = 0; k < matrixLength; k++)
        {
            if (matrix[k][j].getStarred() && (k != i))
            {
                foundStarredZero = true;
            }
        }
        return foundStarredZero;
    }

    public void coverAllColumnsContainingStars()
    {

        for (int i = 0; i < matrixLength; i++)
        {
            for (int j = 0; j < matrixLength; j++)
            {
                if (matrix[i][j].getStarred())
                {
                    columnCovered[j] = true;
                }
            }

        }
    }

    public int findColumnStarredZero(int i, int j)
    {
        int foundColumn = 0;

        for (int k = 0; k < matrixLength; k++)
        {
            if ((matrix[i][k].getStarred()) && (k != j))
            {
                foundColumn = k;
            }
        }
        //System.out.println(foundColumn);
        return foundColumn;
    }

    public int findColumnPrimedElement(int i, int j)
    {
        int foundColumn = 0;

        for (int k = 0; k < matrixLength; k++)
        {
            if (matrix[i][k].getPrimed() && (k != j))
            {
                foundColumn = k;
            }
        }
        //System.out.println(foundColumn);
        return foundColumn;
    }

    public int findRowStarredZero(int i, int j)
    {
        int foundRow = 0;

        for (int k = 0; k < matrixLength; k++)
        {
            if (matrix[k][j].getStarred() && (k != i))
            {
                foundRow = k;
            }
        }
        //System.out.println(foundColumn);
        return foundRow;
    }

    public boolean checkOtherPrimedZeroesOnRow(int i, int j)
    {
        boolean foundPrimedZero = false;
        for (int k = 0; k < matrixLength; k++)
        {
            if (matrix[i][k].getPrimed() && (k != j))
            {
                foundPrimedZero = true;
            }
        }
        return foundPrimedZero;
    }

    public boolean checkOtherPrimedZeroesOnColumn(int i, int j)
    {
        boolean foundPrimedZero = false;
        for (int k = 0; k < matrixLength; k++)
        {
            if (matrix[k][j].getPrimed() && (k != i))
            {
                foundPrimedZero = true;
            }
        }
        return foundPrimedZero;
    }

    public boolean allZeroesCovered()
    {
        boolean allZeroesCovered = true;
        for (int i = 0; i < matrixLength; i++)
        {
            for (int j = 0; j < matrixLength; j++)
            {
                if ((matrix[i][j].getCost() == 0) && ((columnCovered[j] == false) && (rowCovered[i] == false)))
                {
                    allZeroesCovered = false;
                }
            }
        }
        return allZeroesCovered;
    }

    public boolean primeNonCoveredZeroes()
    {
        for (int i = 0; i < matrixLength; i++)
        {
            for (int j = 0; j < matrixLength; j++)
            {
                if ((matrix[i][j].getCost() == 0) && (matrix[i][j].getStarred() == false) && (columnCovered[j] == false) && (rowCovered[i] == false))
                {
                    matrix[i][j].setPrimed(true);
                    System.out.println("" + i + j);

                    if (checkOtherStarredZeroesOnRow(i, j))
                    {
                        rowCovered[i] = true;
                        columnCovered[findColumnStarredZero(i, j)] = false; // uncover column of starred zero
                    } else
                    {
                        ArrayList<CostEntry> primedZeroesToStar = new ArrayList<CostEntry>();
                        ArrayList<CostEntry> starredZeroesToUnstar = new ArrayList<CostEntry>();

                        boolean finishedWhileLoop = false;
                        boolean completedSwap = false;
                        int tempi = i;
                        int tempj = j;

                        primedZeroesToStar.add(matrix[i][j]);

                        while (completedSwap == false)
                        {
                            if (checkOtherStarredZeroesOnColumn(tempi, tempj))
                            {

                                tempi = findRowStarredZero(tempi, tempj);             // unstar the starred zero on column

                                starredZeroesToUnstar.add(matrix[tempi][tempj]);

                                tempj = findColumnPrimedElement(tempi, tempj);

                            } else
                            {
                                completedSwap = true;
                            }
                        }

                        for (int k = 0; k < primedZeroesToStar.size(); k++)
                        {
                            matrix[primedZeroesToStar.get(k).getRow()][primedZeroesToStar.get(k).getColumn()].setStarred(true);
                            matrix[primedZeroesToStar.get(k).getRow()][primedZeroesToStar.get(k).getColumn()].setPrimed(false);
                            //matrix[primedZeroesToStar.get(k).getRow()][primedZeroesToStar.get(k).getColumn()].setPrimed(true);

                        }
                        for (int k = 0; k < starredZeroesToUnstar.size(); k++)
                        {
                            matrix[starredZeroesToUnstar.get(k).getRow()][starredZeroesToUnstar.get(k).getColumn()].setStarred(false);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void subtractMinFromAllEntries()
    {
        int smallestNumber = 10000;
        for (int i = 0; i < matrixLength; i++)
        {
            for (int j = 0; j < matrixLength; j++)
            {
                if ((matrix[i][j].getCost() < smallestNumber) && (columnCovered[j] == false) && (rowCovered[i] == false))
                {
                    smallestNumber = matrix[i][j].getCost();
                }
            }
        }

        for (int i = 0; i < matrixLength; i++)
        {
            for (int j = 0; j < matrixLength; j++)
            {
                if ((columnCovered[j] == false) && (rowCovered[i] == false))
                {
                    int newCost = matrix[i][j].getCost() - smallestNumber;
                    if (newCost <= 0)
                    {
                        matrix[i][j].setCost(0);
                    } else
                    {
                        matrix[i][j].setCost(newCost);
                    }
                } else if ((columnCovered[j] == true) && (rowCovered[i] == true))
                {
                    matrix[i][j].setCost(matrix[i][j].getCost() + smallestNumber);
                }

            }
        }

    }

    public void removeAllPrimeZeroes()
    {
        for (int i = 0; i < matrixLength; i++)
        {
            for (int j = 0; j < matrixLength; j++)
            {
                matrix[i][j].setPrimed(false);
            }
        }
    }

    public void print()
    {
        System.out.print("  ");
        for (int i = 0; i < matrixLength; i++)
        {
            if (columnCovered[i] == true)
            {
                System.out.print("x  ");
            } else
            {
                System.out.print(".  ");
            }

        }
        System.out.println("");
        for (int i = 0; i < matrixLength; i++)
        {
            if (rowCovered[i] == true)
            {
                System.out.println("x  ");
            } else
            {
                System.out.println(".  ");
            }
        }
        for (int i = 0; i < matrixLength; i++)
        {
            for (int j = 0; j < matrixLength; j++)
            {
                int temp = matrix[i][j].getCost();
                String temp2 = "";
                if (matrix[i][j].getPrimed())
                {
                    temp2 = temp2 + "'";
                }
                if (matrix[i][j].getStarred())
                {
                    temp2 = temp2 + "*";
                }

                System.out.print("  " + temp + temp2 + "  ");
            }
            System.out.println();
        }
    }

    public void removeAllCoveredLines()
    {
        for (int i = 0; i < matrixLength; i++)
        {
            columnCovered[i] = false;
            rowCovered[i] = false;
        }
    }

    public void removeAllStarredZeroes()
    {
        for (int i = 0; i < matrixLength; i++)
        {
            for (int j = 0; j < matrixLength; j++)
            {
                matrix[i][j].setStarred(false);
            }
        }
    }

    public boolean isLinesEqualMatrixLength()
    {
        int sum = 0;
        for (int i = 0; i < matrixLength; i++)
        {
            if (columnCovered[i] == true)
            {
                sum++;
            }
            if (rowCovered[i] == true)
            {
                sum++;
            }
        }

        if (sum == matrixLength)
        {
            return true;
        } else
        {
            return false;
        }
    }

    public void truePrint()
    {
        System.out.print("  ");
        for (int i = 0; i < matrixLength; i++)
        {
            if (columnCovered[i] == true)
            {
                System.out.print("x  ");
            } else
            {
                System.out.print(".  ");
            }

        }
        System.out.println("");
        for (int i = 0; i < matrixLength; i++)
        {
            if (rowCovered[i] == true)
            {
                System.out.println("x  ");
            } else
            {
                System.out.println(".  ");
            }
        }
        for (int i = 0; i < matrixLength; i++)
        {
            for (int j = 0; j < matrixLength; j++)
            {
                int temp = matrix[i][j].getTrueCost();
                String temp2 = "";
                if (matrix[i][j].getPrimed())
                {
                    temp2 = temp2 + "'";
                }
                if (matrix[i][j].getStarred())
                {
                    temp2 = temp2 + "*";
                }

                System.out.print("  " + temp + temp2 + "  ");
            }
            System.out.println();
        }
    }

    public int findMin()
    {
        int smallestNumber = 100000;
        for (int i = 0; i < matrixLength; i++)
        {
            for (int j = 0; j < matrixLength; j++)
            {
                if ((matrix[i][j].getCost() < smallestNumber) && (columnCovered[j] == false) && (rowCovered[i] == false))
                {
                    smallestNumber = matrix[i][j].getCost();
                }
            }
        }

        return smallestNumber;
    }

    public void addMinAllCoveredRows(int min)
    {
        for (int i = 0; i < matrixLength; i++)
        {
            for (int j = 0; j < matrixLength; j++)
            {
                if (rowCovered[i])
                {
                    matrix[i][j].setCost(matrix[i][j].getCost() + min);
                }
            }
        }
    }

    public void subtractMinAllUncoveredColumns(int min)
    {
        for (int i = 0; i < matrixLength; i++)
        {
            for (int j = 0; j < matrixLength; j++)
            {
                if (columnCovered[j] == false)
                {
                    if (matrix[i][j].getCost() - min < 0)
                    {
                        matrix[i][j].setCost(0);
                    } else
                    {
                        matrix[i][j].setCost(matrix[i][j].getCost() - min);
                    }
                }
            }
        }

    }
}

