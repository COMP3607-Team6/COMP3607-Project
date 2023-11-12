package com.example.Avinash_Roopnarine_816029635_A2;
//816029635

public class AC implements Device 
{
   protected String id;
   protected boolean breezy;
   protected boolean noisy;
   private int coolBy;
   protected int breeziness;
   protected int noisiness;
   protected boolean isPort;
   protected boolean on;
   private static int counter = 0;
   
   public AC (){
       this.id = "AC" + String.valueOf(counter);
       this.breeziness = 0;
       this.coolBy = 5;
       this.noisiness = 0;
       this.isPort = false;
       this.on = false;
       counter++;
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

   public int coolsBy(int num, CeilingFan f){
       return this.coolBy + num + f.coolsBy();
   }

    public int coolsBy(int num, Room r, int num2, TestClass c){
       return this.coolBy + num +  r.getStartTemp() + num2 + c.num1 + c.num2;
   }
   
   
   public int getBreeziness(){
       return this.breeziness;
   }
   
    public int getNoisiness(){
       return this.noisiness;
   } 
}
