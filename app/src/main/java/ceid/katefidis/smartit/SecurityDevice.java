package ceid.katefidis.smartit;

// Class Declaration

public class SecurityDevice extends Device
{ 
    // Instance Variables 
  
    // Constructor Declaration of Class

    SecurityDevice(){};

    // Constructor Declaration of Class
    SecurityDevice(String device_ID, Integer online_time, String device_name, Sensors data, Integer time, Boolean isEnabled)
    {
        super(device_ID, online_time, device_name, data, time, isEnabled);
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

    @Override
    public void setOnlineTime(Integer newOnlineTime) {
        super.setOnlineTime(newOnlineTime);
    }

    @Override
    public Integer getOnline_time() {
        return super.getOnline_time();
    }
}