package com.example.TestAssignment;
//816029635
import java.util.ArrayList;

public abstract class Fan implements Device
{
   protected String id;
   protected boolean breezy;
   protected boolean noisy;
   protected int coolBy;
   protected int breeziness;
   protected int noisiness;
   protected boolean on;
   protected boolean isPort; 
   private static int ceilCounter = 1;
   private static int standCounter = 1;
   
   public Fan (int noise){
       if(noise == 0){
            this.id = String.valueOf(ceilCounter);
            ceilCounter++;
       }
       else{
            this.id = String.valueOf(standCounter);
            standCounter++;
       }
       this.noisiness = noise;
   }
   
   public String getID(){
       return this.id;
   }
   
   public boolean isBreezy(){
       return (this.breeziness > 0) ? true : false;
   }
   
   public boolean isNoisy(){
       return (this.noisiness > 0) ? true : false;
   }
   
   public boolean isOn(){
       return this.on;
   }
   
   public void turnOn(){
       this.on = true;
   }
   
   public void turnOff(){
       this.on = false;
   }
   
   public int coolsBy(){
       return this.coolBy;
   }
   
   public int getBreeziness(){
       return this.breeziness;
   }
   
    public int getNoisiness(){
       return this.noisiness;
   }
   
}

