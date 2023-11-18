package com.example.StudentFile;
//816029635
import java.util.ArrayList;

public class CoolingSimulation
{
    private ArrayList<Room> rooms;
    
    public CoolingSimulation()
    {
       rooms = new ArrayList<Room>();
    }
    
    public ArrayList<Room> getRooms(){
       return this.rooms;
    }
    
    public Room getRoom(int i){
       i--;
       return (i > -1 && i < 4) ? rooms.get(i): null;
    }
    
    public void createRooms(){
        int temps[] = {30, 35, 37, 25}; 
        for(int i = 0; i < 4; i++){
            Room r = new Room(temps[i]);
            this.rooms.add(r);
        }
    }
    
    public void createDevices(){
        for(Room r: this.rooms){
            if(r.getStartTemp() == 30){
               Device dev = null;
               dev = new AC();
               r.addDevice(dev);
               dev = new CeilingFan();
               r.addDevice(dev);
               for(int i = 0 ; i < 3; i++){
                  dev = new StandingFan();
                  r.addDevice(dev);
               }
            }
            if(r.getStartTemp() == 35){
              Device dev = null;
              for(int i = 0 ; i < 2; i++){
                  dev = new AC();
                  r.addDevice(dev);
               }
              dev = new CeilingFan();
              r.addDevice(dev);
              for(int i = 0 ; i < 4; i++){
                  dev = new StandingFan();
                  r.addDevice(dev);
               }
            }
            if(r.getStartTemp() == 37){
               Device dev = new AC();
               r.addDevice(dev);
               for(int i = 0 ; i < 2; i++){
                  dev = new CeilingFan();
                  r.addDevice(dev);
               }
               for(int i = 0 ; i < 2; i++){
                  dev = new StandingFan();
                  r.addDevice(dev);
               }
            } 
            if(r.getStartTemp() == 25){
               Device dev = new AC();
               r.addDevice(dev);
               dev = new CeilingFan();
               r.addDevice(dev);
               dev = new StandingFan();
               r.addDevice(dev);
            }
        }   
    }
    
    public void swapPortableDevices(Room r1, Room r2){
        ArrayList<Device> devices = r1.getDevices();
        ArrayList<Device> addDev = new ArrayList<Device> ();
        for(Device dev: devices){
           if(dev instanceof PortableDevice && dev.isOn() == false){
               addDev.add(dev);
           }
        }
        for(Device dev: addDev){
            r1.removeDevice(dev);
            r2.addDevice(dev);
        }
    }
    
    public void coolRoom1(){
        Room r = getRoom(1);
        ArrayList<Device> devices = r.getDevices();
        for(Device dev : devices){
            if(dev instanceof PortableDevice || (dev instanceof Device && dev.isBreezy() == true)){
                dev.turnOn();
            }
        }
    }
    
    public void coolRoom2(){
        Room r = getRoom(2);
        ArrayList<Device> devices = r.getDevices();
        for(Device dev : devices){
            if(dev instanceof Device && dev.isBreezy() == false && dev.isNoisy() == false){
                dev.turnOn();
            }
        } 
    }
    
    public void coolRoom3(){
       Room r = getRoom(3);
        ArrayList<Device> devices = r.getDevices();
        for(Device dev : devices){
            if(!(dev instanceof PortableDevice)){
                dev.turnOn();
            }
        }
    }
    
    public void coolRoom4(){
        Room r = getRoom(4);
        swapPortableDevices(getRoom(1),r);
        swapPortableDevices(getRoom(2),r);
        swapPortableDevices(getRoom(3),r);
        ArrayList<Device> devices = r.getDevices();
        for(Device dev : devices){
            dev.turnOn();    
        }
    }
    
    public void printStates(){
        for(Room r : this.rooms){
            r.printState();
        }
    }
    
    public static void main(String[] args){
     CoolingSimulation sim = new CoolingSimulation();
     sim.createRooms();
     sim.createDevices();
     sim.coolRoom1();
     sim.coolRoom2();
     sim.coolRoom3();
     sim.coolRoom4();
     sim.printStates(); 
 }
    

}
