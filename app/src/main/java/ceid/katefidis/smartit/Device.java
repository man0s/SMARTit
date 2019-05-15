package ceid.katefidis.smartit;

// Class Declaration
  
public class Device
{ 
    // Instance Variables 
    protected String device_ID;
    protected Integer online_time;
    protected String device_name;
    protected Sensors data;
    protected Integer time;
    protected boolean isEnabled;

    Device(){}
  
    // Constructor Declaration of Class 
    public Device(String device_ID, Integer online_time, String device_name, Sensors data, Integer time, Boolean isEnabled)
    { 
        this.device_ID = device_ID;
        this.online_time = online_time;
        this.device_name = device_name;
        this.data = data;
        this.time = time;
        this.isEnabled = isEnabled;
    } 

    // method set
    public void setID(String newID) 
    { 
       device_ID = newID; 
    }

    public void setOnlineTime(Integer newOnlineTime) 
    { 
       online_time = newOnlineTime;
    }

    public void setName(String newName) 
    { 
       device_name = newName;
    }

    public void saveSettings(Boolean saveSett) 
    { 
       //todo
    }

    public void setTime(Integer newTime)
    {
        time = newTime;
    }

    public void setEnabled(Boolean isit)
    {
        isEnabled = isit;
    }
  
    // method get 

    public String getID() 
    { 
        return device_ID; 
    }
    
    public Integer getTime() 
    { 
        return time; 
    }

    public boolean getEnabled()
    {
        return isEnabled;
    }

    public void haltDevice()
    { 
        //todo
    }

    public Sensors getSensorData() {
        return data;
    }
}