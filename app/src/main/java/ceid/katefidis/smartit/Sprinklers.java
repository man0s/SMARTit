package ceid.katefidis.smartit;

//Class Declaration
  
public class Sprinklers extends MaintenanceDevice
{ 
    // Instance Variables 
    private Integer speed;
    private Integer pressure;
  
    // Constructor Declaration of Class

    public Sprinklers(Integer speed, Integer pressure)
    {
        this.speed = speed;
        this.pressure = pressure; 
    } 
    
    // method get 
    public Integer getSpeed()
    { 
        return speed; 
    }
    public Integer getPressure()
    { 
        return pressure; 
    }

    // method set
    public void setSpeed(Integer newspeed) 
    { 
        speed = newspeed; 
    }
    public void setPressure(Integer newpressure)
    { 
        pressure = newpressure;
    } 
}