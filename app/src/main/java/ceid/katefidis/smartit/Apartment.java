package ceid.katefidis.smartit;

import java.util.ArrayList;

public class Apartment{
  private String Apartment_ID;
  private Tenant the_tenant;
  private ArrayList<HomeDevice> home_device_list;


  Apartment(){}

  Apartment(String p_apartment_id, Tenant p_tenant){
    Apartment_ID = p_apartment_id;
    the_tenant = p_tenant;
    home_device_list = new ArrayList<>();
  } 

  public boolean searchDevice(String device_id_query){
    for(HomeDevice obj:home_device_list){
        if(obj.getID().equals(device_id_query)) return true;
    }
    return false;
  }

  public String getApartment(){return Apartment_ID;}
  public Tenant getTenant(){return the_tenant;}
  public ArrayList<HomeDevice> getDeviceList(){ return home_device_list; }

  public void addHomeDevice(HomeDevice obj){home_device_list.add(obj);}

}
