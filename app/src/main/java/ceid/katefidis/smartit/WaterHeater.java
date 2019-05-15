package ceid.katefidis.smartit;

// Class Declaration
  
public class WaterHeater extends HomeDevice
{ 
    // Instance Variables 
    private Integer temperature;
  
    // Constructor Declaration of Class 
    public WaterHeater(Integer temperature) 
    { 
        this.temperature = temperature; 
    } 
  
    // method get 
    public Integer getTemperature() 
    { 
        return temperature; 
    } 
  
    // method set
    public void setTemperature(Integer newTemperature)
    { 
        temperature = newTemperature; 
    } 
}