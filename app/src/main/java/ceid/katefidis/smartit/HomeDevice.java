package ceid.katefidis.smartit;

//Class Declaration

public class HomeDevice extends Device
{ 
    // Instance Variables

    HomeDevice(){};

    // Constructor Declaration of Class
    HomeDevice(String device_ID, Integer online_time, String device_name, Sensors data, Integer time, Boolean isEnabled)
    {
        super(device_ID, online_time, device_name, data, time, isEnabled);
    }

    // Constructor Declaration of Class 
  
    // method get 
    public String getSettings() 
    {

        //todo
        return null;
    } 
  
    // method set
    public void setSettings(String settings)
    { 
        
    } 
}