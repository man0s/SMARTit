package ceid.katefidis.smartit;

//Class Declaration
  
public class MaintenanceDevice extends Device
{ 
    // Instance Variables 
  
    // Constructor Declaration of Class

    MaintenanceDevice(){};

    // Constructor Declaration of Class
    MaintenanceDevice(String device_ID, Integer online_time, String device_name, Sensors data, Integer time, Boolean isEnabled)
    {
        super(device_ID, online_time, device_name, data, time, isEnabled);
    }


    // method get 
    public String getSettings() 
    { 
        return null;
    } 
  
    // method set
    public void setSettings(String test)
    { 
        
    } 
}