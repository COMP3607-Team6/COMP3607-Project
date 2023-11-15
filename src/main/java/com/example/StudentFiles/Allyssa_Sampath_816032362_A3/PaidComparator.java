package com.example.StudentFiles.Allyssa_Sampath_816032362_A3;

/**
     Allyssa Sampath
     816032362
     COMP 2603 - Assignment 3
 */

import java.util.Comparator;

public class PaidComparator implements Comparator
{
    
    /**
     * Constructor for objects of class PaidComparator
     */
    public PaidComparator()
    {
        // initialise instance variables
        
    }

    //Compare function from Comparator interface
    public int compare(Object a, Object b)
    {
        if (a instanceof Bill && b instanceof Bill)
        {
            //Casting to type Bill
            Bill obj1 = (Bill)a;
            Bill obj2 = (Bill)b;
            
            //return equality. If both objects have same PAID/UNPAID status, comparison will be done based on Bill Number
            if (obj1.getPaid() == obj2.getPaid())
            {
                return obj1.getBillNumber().compareTo(obj2.getBillNumber());
            }
            else if (obj1.getPaid())
                return -1;               
            else 
                return 1;
        }
        
        throw new ClassCastException("Not a Bill");
    }
    
}
