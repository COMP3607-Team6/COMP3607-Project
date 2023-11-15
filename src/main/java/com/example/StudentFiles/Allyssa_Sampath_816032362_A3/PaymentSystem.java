package com.example.StudentFiles.Allyssa_Sampath_816032362_A3;

/**
     Allyssa Sampath
     816032362
     COMP 2603 - Assignment 3
 */

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Vector;

public class PaymentSystem
{
    //Required attributes
    private ArrayList<Renter> renters;
    private BillGenerator bills;

    /**
     * Constructor for objects of class PaymentSystem
     */
    public PaymentSystem()
    {
        renters = new ArrayList<>();
        bills = new BillGenerator();
        
    }

    //Required functions
    private Renter getRenter(Renter r)
    {
        for (Renter a : this.renters)
        {
            if (a.getUser().equals(r.getUser()) && a.getPassword().equals(r.getPassword()))
            {
                return a;
            }
        }
        
        return null; 
    }
    
    public java.lang.String[] getRenterBills(java.lang.String username, java.lang.String password)
    {
        //Determine if renter is present in system
        Renter r = new Renter(username, password);       
        Renter renter = getRenter(r);        
        
        //Array and vector of String contains bills. Initially add all to vector then convert to String[]
        String[] billsString;
        Vector<String> billsVector = new Vector();
        
        if(renter != null)
        {
            java.util.Collection bills = renter.getBills();
           
           for (Object a: bills)
           {
                billsVector.add(a.toString());
           }
            
           //Convert bills to type String[]
           billsString = billsVector.toArray(new String[billsVector.size()]);
            
           return billsString;
        }
        
        //Return empty String[]
        String[] empty = {};
        return empty;
        
    }
    
    public java.lang.String[] getSortedRenterBillsByAmount(java.lang.String username, java.lang.String password)
    {
        //Determine if renter is present in system
        Renter r = new Renter(username, password);       
        Renter renter = getRenter(r);
        
        //Array and vector of String contains bills. Initially add all to vector then convert to String[]
        String[] billsString;
        Vector<String> billsVector = new Vector();
        
        if(renter != null)
        {
            TreeSet bills = renter.getBillsAmount();
            
            for (Object a: bills)
            {
                billsVector.add(a.toString());
            }
            
            //Convert bills to type String[]
            billsString = billsVector.toArray(new String[billsVector.size()]);
            
            return billsString;
        }
        
        //Return empty String[]
        String[] empty = {};
        return empty;
    }
    
    public java.lang.String[] getSortedRenterBillsByPaid(java.lang.String username, java.lang.String password)
    {
        //Determine if renter is present in system
        Renter r = new Renter(username, password);       
        Renter renter = getRenter(r);      
        
        //Array and vector of String contains bills. Initially add all to vector then convert to String[]
        String[] billsString;
        Vector<String> billsVector = new Vector();
        
        if(renter != null)
        {
            TreeSet bills = renter.getBillsPaid();
            
            for (Object a: bills)
            {
                billsVector.add(a.toString());
            }
            
            //Convert bills to type String[]
            billsString = billsVector.toArray(new String[billsVector.size()]);
            
            return billsString;
        }
        
        //Return empty String[]
        String[] empty = {};
        return empty;
    }
    
    //Assigns 4 bills to users
    public boolean registerRenter(java.lang.String username, java.lang.String password)
    {
        boolean found = validateRenter(username,password);
        if (found)
            return false;
        
        Renter r = new Renter(username,password);
        
        java.util.Random rand = new java.util.Random(); //Used to randomly assign bills
        
        //Assign 4 bills. Assumes that number of bills to be allocated will not exceed 1000
        for (int i = 0; i < 4; i++)
        {
            int num = rand.nextInt(bills.getSize());          
            r.setBill(bills.get(num));
            
            bills.remove(num);
        }
        
        renters.add(r);
        return true;
       
    }
    
    public boolean validateRenter(java.lang.String username, java.lang.String password)
    {
        for (Renter r : this.renters)
        {
            if (r.getUser().equals(username) && r.getPassword().equals(password))
            {
                return true;
            }
        }
        return false;
    }
    
    //accessors
    public BillGenerator getBill()
    {
        return this.bills;
    }
    
    public ArrayList<Renter> getRenters()
    {
        return this.renters;
    }
}
