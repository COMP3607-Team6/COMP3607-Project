package com.example.Avinash_Roopnarine_816029635_A2;
//816029635

public class CeilingFan extends Fan 
{
    public CeilingFan (){
       super(0);
       super.id = "CFAN" + super.getID();
       super.breeziness = 2;
       super.coolBy = 3;
       super.isPort = false;
       super.on = false;
    }
    
    public String toString(){
        String info = "ID: " + super.getID() +
        "Breeziness: " + super.getBreeziness() +
        "Noisiness: " + super.getNoisiness() +
        "Cooling power: " + super.coolsBy();
        return info;
    }
    
}