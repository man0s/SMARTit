package ceid.katefidis.smartit;

//Class Declaration
  
public class MaintenanceDevice extends Device
{ 
    // Instance Variables
    private Sprinklers sprinkler;
    private BuildingHeating heating;
    private Lighting lighting;
  
    // Constructor Declaration of Class

    MaintenanceDevice(){};

    // Constructor Declaration of Class
    MaintenanceDevice(String device_ID, Integer online_time, String device_name, Sensors data, Integer time, Boolean isEnabled)
    {
        super(device_ID, online_time, device_name, data, time, isEnabled);
    }


    // method set
    public void setSettings(String settings)
    {
        if(settings.equals("sprinkler")){
            sprinkler = new Sprinklers(30, 50);
        } else if(settings.equals("heating")){
            heating = new BuildingHeating(27, "normal");
        } else lighting = new Lighting(50, 136 ,253, 55);

    }

    public BuildingHeating getHeating() {
        return heating;
    }
    public Sprinklers getSprinkler() {
        return sprinkler;
    }
    public Lighting getLighting() {
        return lighting;
    }

    public String getSettings()
    {
        if(sprinkler != null)  return "sprinkler";
        else if(heating !=null) return "heating";
        else if(lighting != null) return "lighting";
        else return null;
    }
}