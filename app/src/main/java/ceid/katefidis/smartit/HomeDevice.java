package ceid.katefidis.smartit;

//Class Declaration

public class HomeDevice extends Device
{
    private AirConditioner airconditioner;
    private WaterHeater waterheater;
    private CleaningRobot cleaningrobot;
    // Instance Variables

    HomeDevice(){}

    // Constructor Declaration of Class
    HomeDevice(String device_ID, Integer online_time, String device_name, Sensors data, Integer time, Boolean isEnabled)
    {
        super(device_ID, online_time, device_name, data, time, isEnabled);
    }

    // Constructor Declaration of Class 
  
    // method get 
    public String getSettings() 
    {
        if(airconditioner != null)  return "airconditioner";
        else if(waterheater !=null) return "waterheater";
        else if(cleaningrobot != null) return "cleaningrobot";
        else return null;
    }

    public AirConditioner getAirconditioner() {
        return airconditioner;
    }

    public CleaningRobot getCleaningrobot() {
        return cleaningrobot;
    }

    public WaterHeater getWaterheater() {
        return waterheater;
    }

    // method set
    public void setSettings(String settings)
    {
        if(settings.equals("airconditoner")){
            airconditioner = new AirConditioner(30, "normal", 50, 50);
        } else if(settings.equals("waterheater")){
            waterheater = new WaterHeater(30);
        } else cleaningrobot = new CleaningRobot("normal");
        
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