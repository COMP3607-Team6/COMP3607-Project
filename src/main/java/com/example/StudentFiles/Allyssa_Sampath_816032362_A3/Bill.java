package com.example.StudentFiles.Allyssa_Sampath_816032362_A3;

/**
     Allyssa Sampath
     816032362
     COMP 2603 - Assignment 3
 */
public class Bill implements Comparable
{
    //Required attributes
    private String billNumber;
    private double amount;
    private String type;
    private boolean paid;
    private static int count = 1000;

    /**
     * Constructor for objects of class Bill
     */
    public Bill(String type, double amount, boolean paid)
    {
        this.amount = amount;
        this.type = type;
        this.paid = paid;
        this.billNumber = count + "";
        this.count += 1;
    }

    //Required accessors
    public String getBillNumber()
    {
        return this.billNumber;
    }
    
    public double getAmount()
    {
        return this.amount;
    }
    
    public String getType()
    {
        return this.type;
    }
    
    public boolean getPaid()
    {
        return this.paid;
    }
    
    //toString method to suit requirements of project. This gives the label for a particular Bill
    public String toString()
    {
        String paidStatus = this.getPaid() ? ("PAID") : ("UNPAID");
        return this.getBillNumber() + " " + this.getType() + " " + "$" + this.getAmount() + " " + paidStatus;
    }
    
    //equals function - Written to suit requirements of assignment
    public boolean equals(Object a)
    {
        if (a instanceof Bill)
        {
            Bill in = (Bill)a;
            
            //Don't need to use accessors for in object as it is being used in Bill class
            return (this.billNumber.equals(in.getBillNumber()));
        }
        
        return false;
    }
    
    //compareTo function from Comparator interface
    public int compareTo(Object j)
    {
        if (j instanceof Bill)
        {
            Bill in = (Bill) j;
            
            int result = this.billNumber.compareTo(in.getBillNumber());
            return result;
        }
        
        throw new IllegalArgumentException("Incoming object is not a Renter");
    }
}
