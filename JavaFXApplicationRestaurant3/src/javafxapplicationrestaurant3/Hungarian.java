/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplicationrestaurant3;

/**
 *
 * @author hhyyt
 */
public class Hungarian
{
    
    public String performHungarian(LinkedListQueueCustomerReservation queueCustomerReservation, LinkedListQueueTable queueTable)
    {
        
        Matrix matrix = new Matrix(convertQueueToArray(queueCustomerReservation, queueTable));

        matrix.print();

        matrix.subtractColumnMinima();
        matrix.subtractRowMinima();

        matrix.print();
        matrix.assignMinZeroes();
        matrix.coverAllColumnsContainingStars();

        boolean solutionFound = false;
        boolean stepSix = false;
        int min;
        while (solutionFound == false)
        {

            if (matrix.isLinesEqualMatrixLength())
            {
                solutionFound = true;
                matrix.truePrint();
            } else
            {
                while (!matrix.allZeroesCovered())
                {
                    if (matrix.primeNonCoveredZeroes())
                    {
                        matrix.removeAllPrimeZeroes();
                        matrix.removeAllCoveredLines();
                        matrix.coverAllColumnsContainingStars();
                        stepSix = false;
                        break;
                    }
                    stepSix = true;
                }
                if (stepSix)
                {
                    min = matrix.findMin();
                    matrix.addMinAllCoveredRows(min);
                    matrix.subtractMinAllUncoveredColumns(min);
                }

            }
        }
           
           return matrix.toResult(queueCustomerReservation, queueTable);
    }
    
    public int[][] convertQueueToArray(LinkedListQueueCustomerReservation queueCustomerReservation, LinkedListQueueTable queueTable)
    {
        int[][] temp;
        if (queueCustomerReservation.length() == queueTable.length())
        {
            System.out.println("option 1");
            temp = new int[queueCustomerReservation.length()][queueTable.length()];
            for (int i = 0; i < queueCustomerReservation.length(); i++)
            {
                temp[i] = queueCustomerReservation.get(i).getTableScores();
            }
        }
        else if (queueCustomerReservation.length() < queueTable.length())
        {
            temp = new int[queueTable.length()][queueTable.length()];
            for (int i = 0; i < queueCustomerReservation.length(); i++)
            {
                temp[i] = queueCustomerReservation.get(i).getTableScores();

            }
            

        }
        else
        {
            temp = new int[queueTable.length()][queueTable.length()];
            for (int i = 0; i < queueCustomerReservation.length(); i++)
            {
                temp[i] = queueCustomerReservation.get(i).getTableScores();
            }
        }

        
        return temp;
    }
    
     
    
    
}
