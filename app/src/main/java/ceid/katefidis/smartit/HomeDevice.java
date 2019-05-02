package ceid.katefidis.smartit;

//Class Declaration

public class HomeDevice extends Device
{ 
    // Instance Variables

    HomeDevice(){};

    // Constructor Declaration of Class
    public HomeDevice(String device_ID, Integer online_time, String device_name, Sensors data, Integer time)
    {
        super(device_ID, online_time, device_name, data, time);
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