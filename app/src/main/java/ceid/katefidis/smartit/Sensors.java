package ceid.katefidis.smartit;

import java.util.Date;

public class Sensors{
    private int Timestamp;
    private int Temperature;
    private int lighting_level;
    private int smoke_detection;
    private Sensors previousData;
    
    public Sensors(int Timestamp, int Temperature, int lighting_level, int smoke_detection){
        this.Timestamp = Timestamp;
        this.Temperature = Temperature;
        this.lighting_level = lighting_level;
        this.smoke_detection = smoke_detection;
    }

   
    

    public int getTimestamp(){return Timestamp;}
    public int getTemperature(){return Temperature;}
    public int getLighting(){return lighting_level;}
    public int getSmokeDetection(){return smoke_detection;}


    public void setTimestamp(int p_timestamp){Timestamp = p_timestamp;}
    public void setTemparature(int p_temp){Temperature = p_temp;}
    public void setLightingLevel(int p_light){lighting_level = p_light;}
    public void setSmokeDetection(int p_smoke){smoke_detection = p_smoke;}


    public void pickEnviromentalData(){
        int timestamp_picked = (int) (new Date().getTime()/1000);
        int temperature_picked = 26;
        int lighting_level_picked = 85;
        int smoke_detection_picked = 0;

        if(Math.abs(temperature_picked - Temperature) > 5){ //la8os dedomena, xrhsimopoiw ta dedomena ths prohgoumenhs metrhshs
            Timestamp = previousData.getTimestamp();
            Temperature = previousData.getTemperature();
            lighting_level = previousData.getLighting();
            smoke_detection = previousData.getSmokeDetection();
        }else{ //swsta dedomena, prwta apo8hkeuw ta apotelesmata ths prohgoumenhs metrhshs kai meta apo8hkeuw ta kainouria
            previousData.setTimestamp(Timestamp);
            previousData.setTemparature(Temperature);
            previousData.setLightingLevel(lighting_level);
            previousData.setSmokeDetection(smoke_detection);

            Timestamp = timestamp_picked;
            Temperature = temperature_picked;
            lighting_level = lighting_level_picked;
            smoke_detection = smoke_detection_picked;
            
        }  
    } 
}









