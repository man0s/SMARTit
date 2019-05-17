package ceid.katefidis.smartit;

// Class Declaration

public class SecurityDevice extends Device
{ 
    // Instance Variables
    private Camera camera;
    private DoorLock doorlock;
  
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
        if(camera != null)  return "camera";
        else if(doorlock !=null) return "doorlock";
        else return null;
    }
    public Camera getCamera() {
        return camera;
    }
    public DoorLock getDoorlock() {
        return doorlock;
    }
    @Override
    public Integer getOnline_time() {
        return super.getOnline_time();
    }

    // method set
    public void setSettings(String settings)
    {
        if(settings.equals("camera")){
            camera = new Camera(0, 129, 0);
        } else if(settings.equals("doorlock")) {
            doorlock = new DoorLock(true, 0000, false);
        }

    }

    @Override
    public void setOnlineTime(Integer newOnlineTime) {
        super.setOnlineTime(newOnlineTime);
    }
}