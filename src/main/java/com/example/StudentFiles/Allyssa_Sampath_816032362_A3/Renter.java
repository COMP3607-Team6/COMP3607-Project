package com.example.StudentFiles.Allyssa_Sampath_816032362_A3;

/**
     Allyssa Sampath
     816032362
     COMP 2603 - Assignment 3
 */

import java.util.TreeSet;
import java.util.Collection;

public class Renter
{
    //Required Attributes
    private String user;
    private String password;
    private PaidComparator billsPaid = new PaidComparator();
    private AmountComparator billsAmount = new AmountComparator();
    
    private Collection<Bill> bills;

    /**
     * Constructor for objects of class Renter
     */
    public Renter(java.lang.String user, java.lang.String password)
    {
        bills = new TreeSet<Bill>();
        this.user = user;
        this.password = password;
    }

    //Accessors
    TreeSet<Bill> getBillsPaid() // Using PaidComparator
    {
        TreeSet<Bill> paidBills = new TreeSet<>(billsPaid);
        paidBills.addAll(bills);
        
        return paidBills;
    }
    
    TreeSet<Bill> getBillsAmount() //Using AmountComparator
    {
        TreeSet<Bill> amountBills = new TreeSet<>(billsAmount);
        amountBills.addAll(bills);
        
        return amountBills;
    }
    
    public String getUser()
    {
        return this.user;
    }
    
    public String getPassword()
    {
        return this.password;
    }
    
    public Collection getBills()
    {
        return this.bills;
    }
    
    public PaidComparator getPaidComparator()
    {
        return this.billsPaid;
    }
    
    public AmountComparator getAmountComparator()
    {
        return this.billsAmount;
    }
    
    //Add a bill to the TreeSet of bills belonging to a Renter
    public void setBill(Object b)
    {
        if (b instanceof Bill)
        {
            Bill a = (Bill) b;
            this.bills.add(a);
        }
        
    }
}
