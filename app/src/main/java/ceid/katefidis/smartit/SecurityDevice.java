package ceid.katefidis.smartit;

// Class Declaration

public class SecurityDevice extends Device
{ 
    // Instance Variables 
  
    // Constructor Declaration of Class

    SecurityDevice(){};

    // Constructor Declaration of Class
    SecurityDevice(String device_ID, Integer online_time, String device_name, Sensors data, Integer time)
    {
        super(device_ID, online_time, device_name, data, time);
    }
  
    // method get 
    public String getSettings() 
    { 
        return null;
    } 
  
    // method set
    public void setSettings(String  test)
    { 
        
    } 
}