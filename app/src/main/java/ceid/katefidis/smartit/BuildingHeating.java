package ceid.katefidis.smartit;

//Class Declaration
  
public class BuildingHeating extends MaintenanceDevice
{ 
    // Instance Variables 
    private String mode;
    private Integer temperature;
  
    // Constructor Declaration of Class

    public BuildingHeating(Integer temperature, String mode)

    { 
        this.temperature = temperature;
        this.mode = mode;
    } 
    
    // method get 
    public String getMode() 
    { 
        return mode; 
    } 
  
    public Integer getTemperature()
    { 
        return temperature; 
    }

    // method set
    public void setMode(String newmode) 
    { 
        mode = newmode; 
    } 

    public void setTemperature(Integer newtemperature) 
    { 
        temperature = newtemperature;
    } 
}