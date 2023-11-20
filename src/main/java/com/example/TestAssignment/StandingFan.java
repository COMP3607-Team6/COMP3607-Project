
//816029635

public final class StandingFan extends Fan implements PortableDevice
{
    public StandingFan (){
       super(2);
       super.id = "SFAN" + super.getID();
       super.breeziness = 2;
       super.coolBy = 1;
       super.isPort = true;
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
