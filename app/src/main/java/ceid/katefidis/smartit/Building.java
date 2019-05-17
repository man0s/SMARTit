package ceid.katefidis.smartit;

import java.util.ArrayList;

public class Building{
  private String address;
  private Admin the_admin;
  private SecurityStaff the_security;
  private ArrayList<Apartment> apartment_list;
  private ArrayList<MaintenanceDevice> maintence_device_list;
  private ArrayList<SecurityDevice> security_device_list;

  Building(){}

  Building(String p_address, Admin p_admin, SecurityStaff p_security){
    
        address = p_address;
        the_admin = p_admin;
        the_security = p_security;
        apartment_list = new ArrayList<Apartment>();
        maintence_device_list = new ArrayList<MaintenanceDevice>();
        security_device_list = new ArrayList<SecurityDevice>();
  }

  public boolean searchDevice(String device_id_query){
    for(MaintenanceDevice obj:maintence_device_list){
        if(obj.getID().equals(device_id_query)) return true;
    }
    return false;
  }

  public boolean searchSecDevice(String device_id_query){
    for(SecurityDevice obj:security_device_list){
        if(obj.getID().equals(device_id_query)) return true;
    }
    return false;
  }


  public String getAddress(){return address;}
  public Admin getAdmin(){return the_admin;}
  public SecurityStaff getSecurity(){return the_security;}
  public ArrayList<MaintenanceDevice> getMaintanceDeviceList(){ return maintence_device_list; }
  public ArrayList<SecurityDevice> getSecurityDeviceList(){ return security_device_list; }

  public void addApartment(Apartment obj){apartment_list.add(obj);}
  public void addMaintenceDevice(MaintenanceDevice obj){maintence_device_list.add(obj);}
  public void addSecurityDevice(SecurityDevice obj){security_device_list.add(obj);}

  public void deleteApartment(Apartment obj){apartment_list.remove(obj);}
  public void deleteMaintenceDevice(MaintenanceDevice obj){maintence_device_list.remove(obj);}
  public void deleteSecurityDevice(SecurityDevice obj){security_device_list.remove(obj);}

}
