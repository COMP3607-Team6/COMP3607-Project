package com.example.StudentFiles.Allyssa_Sampath_816032362_A3;

/**
     Allyssa Sampath
     816032362
     COMP 2603 - Assignment 3
 */

import java.util.ArrayList;
import java.util.Random;

public class BillGenerator
{
    // Begin: The code below was used and modified from the code supplied in the COMP 2603 Assignment 3 Sheet - 2022/2023 -> Dr. Phaedra Mohammed   
    private ArrayList<Bill> billrepo;
    private Random rand;
    
    /**
     * Constructor for objects of class BillGenerator
     */
    public BillGenerator()
    {
        billrepo = new ArrayList<Bill>();
        rand = new Random();
        String[] types = {"Electric", "Internet", "Water"};
         int typeIndex = rand.nextInt(3);
         double amount = Math.floor((rand.nextDouble()*1000) * 100) / 100;
         int paidIndex = rand.nextInt(2);
         boolean paid = false;
         for(int i = 0; i<1000; i++)
         {
             boolean added = billrepo.add(new Bill(types[typeIndex], amount, paid));
             
             if (!added) //ensure 1000 bills added, no duplicates
                 i --;
                 
             paidIndex = rand.nextInt(2);
             if(paidIndex == 0) 
                 paid = false;
             else 
                 paid = true;
                 
             typeIndex =rand.nextInt(3);
             amount = Math.floor((rand.nextDouble()*1000) * 100) / 100;
         }
         
         
    }    
     // End: //The code above was used and modified from the code supplied in the COMP 2603 Assignment 3 Sheet - 2022/2023 -> Dr. Phaedra Mohammed
     
     //Return size of billrepo
     public int getSize()
     {
         return this.billrepo.size();
     }
     
     //Return the bill at the supplied index
     public Bill get(int i)
     {
         return billrepo.get(i);
     }
     
     //Removes the bill at the supplied index
     public Bill remove(int i)
     {
         return billrepo.remove(i);
     }
}
