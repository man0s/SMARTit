package ceid.katefidis.smartit;

// Class Declaration
  
public class CleaningRobot extends HomeDevice
{ 
    // Instance Variables 
    private String mode;
  
    // Constructor Declaration of Class 
    public CleaningRobot(String mode) 
    { 
        this.mode = mode; 
    } 
  
    // method get 
    public String getMode() 
    { 
        return mode; 
    } 
  
    // method set
    public void setMode(String newMode) 
    { 
        mode = newMode; 
    } 
}