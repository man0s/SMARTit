package ceid.katefidis.smartit;

//Class Declaration
  
public class Lighting extends MaintenanceDevice
{ 
    // Instance Variables 
    private String color;
    private Integer hue;
  
    // Constructor Declaration of Class 
    public Lighting(String color, Integer hue)
    { 
        this.color = color;
        this.hue = hue;
    }
    // method get 
    public String getColor() 
    { 
        return color; 
    } 
  
    public Integer getHue()
    { 
        return hue; 
    }

    // method set
    public void setColor(String newcolor) 
    { 
        color = newcolor; 
    } 

    public void setHue(Integer newhue) 
    { 
        hue = newhue;
    } 
}