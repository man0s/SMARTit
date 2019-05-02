package ceid.katefidis.smartit;

//Class Declaration
  
public class BuildingHeating extends MaintenanceDevice
{ 
    // Instance Variables 
    private String mode;
    private Integer temperature;
  
    // Constructor Declaration of Class 
    public BuildingHeating(String mode)
    { 
        this.mode = mode; 
    }

    public BuildingHeating(Integer temperature) 
    { 
        this.temperature = temperature; 
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