package com.example.TestAssignment;
//816029635
import java.util.ArrayList;

public class Room
{
  private ArrayList<Device> devices;
  private int startTemp;
  private int breeziness;
  private int noisiness;
  private int endTemp;
 
  public Room(int startingTemperature){
     devices = new ArrayList<Device> ();
     this.startTemp = startingTemperature;
  }
  
  public int getStartTemp(){
      return this.startTemp;
  }
  
  public ArrayList<Device> getDevices(){
     return this.devices;     
  }
  
  public boolean addDevice(Device d){
     for(Device dev: devices){
         if(dev.getID().equals(d.getID())){
             return false;
         }
     }
     devices.add(d);
     return true;
  }
  
  public boolean removeDevice(Device d){
     for(Device dev: devices){
         if(dev instanceof PortableDevice && dev.getID().equals(d.getID()) && dev.isOn() == false){
             this.devices.remove(dev);
             return true;
         }
     }
     return false;  
  }
  
  public int getTemperatureDrop(){
     int tempDrop = 0; 
     for(Device dev: devices){
        if(dev.isOn() == true){
          tempDrop+= dev.coolsBy();
        }
     }
     return tempDrop;
  }
  
  public int getTemperature(){
     int tempDrop = 0; 
     for(Device dev: devices){
        if(dev.isOn() == true){
          tempDrop+= dev.coolsBy();
        }
     }
     return (this.startTemp - tempDrop);
    
  }
  public int getBreeziness(){
     this.breeziness= 0; 
     for(Device dev: devices){
         if(dev.isOn() == true){
             this.breeziness+= dev.getBreeziness();
         }
     }
     return this.breeziness;
  }
  
  public int getNoisiness(){
     this.noisiness = 0; 
      for(Device dev: devices){
         if(dev.isOn() == true){
             this.noisiness+= dev.getNoisiness();
         }
     }
     return this.noisiness;
  }
  
  public void printState(){
    System.out.println("Room");
    String info = "Temperature: " + getTemperature() +
    "Breeziness: " + getBreeziness() +
    "Noisiness: " + getNoisiness();
    System.out.println(info);
    System.out.println("Devices");
    for(Device dev : devices){
        System.out.println(dev.toString());
    }
}
}