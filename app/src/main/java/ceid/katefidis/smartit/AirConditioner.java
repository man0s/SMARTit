package ceid.katefidis.smartit;

// Class Declaration
  
public class AirConditioner extends HomeDevice
{ 
    // Instance Variables 
    private Integer temperature;
    private String mode;
    private Integer directionFan;
    private Integer intensityFan;
  
    // Constructor Declaration of Class 
    public AirConditioner(Integer temperature, String mode, Integer directionFan, Integer intensityFan)
    {
        this.temperature = temperature;
        this.mode = mode;
        this.directionFan = directionFan;
        this.intensityFan = intensityFan;
    } 
  
    // method get 
    public Integer getTemperature() 
    { 
        return temperature; 
    }
    public String getMode() 
    { 
        return mode; 
    }
    public Integer getDirection() 
    { 
        return directionFan; 
    }
    public Integer getIntensity() 
    { 
        return intensityFan; 
    }
  
    // method set
    public void setTemperature(Integer newTemperature) 
    { 
        temperature = newTemperature; 
    }
    public void setMode(String newMode) 
    { 
        mode = newMode;
    }
    public void setDirection(Integer newDirection) 
    { 
        directionFan = newDirection; 
    }
    public void setIntensityFan(Integer newIntensity)
    { 
        intensityFan = newIntensity;
    }
}