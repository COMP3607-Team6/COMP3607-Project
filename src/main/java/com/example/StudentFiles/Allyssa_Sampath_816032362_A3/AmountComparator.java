package com.example.StudentFiles.Allyssa_Sampath_816032362_A3;

/**
     Allyssa Sampath
     816032362
     COMP 2603 - Assignment 3
 */

import java.util.Comparator;

public class AmountComparator implements Comparator
{

    /**
     * Constructor for objects of class AmountComparator
     */
    public AmountComparator()
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
            
            //return equality
            if (obj1.getAmount() == obj2.getAmount())
                return 0;
            
            if (obj1.getAmount() < obj2.getAmount())
                return 1;
                
            if (obj1.getAmount() > obj2.getAmount())
                return -1;
        }
        
        throw new ClassCastException("Not a Bill");
    }
}
