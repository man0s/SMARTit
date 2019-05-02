package ceid.katefidis.smartit;

// Class Declaration
  
public class DoorLock extends SecurityDevice
{ 
    // Instance Variables 
    private Boolean locked;
    private Integer password;
    private Boolean isLockpicked;
  
    // Constructor Declaration of Class 
    public DoorLock(Boolean locked, Integer password, Boolean isLockpicked) 
    { 
        this.locked = locked;
        this.password = password;
        this.isLockpicked = isLockpicked; 
    } 
  
    // method get 
    public Boolean getLocked() 
    { 
        return locked; 
    }

    public Integer getPassword() 
    { 
        return password; 
    }

    public Boolean getLockpicked() 
    { 
        return isLockpicked; 
    }
  
    // method set
    public void setZoom(Boolean newLocked) 
    { 
        locked = newLocked; 
    }

    public void setRotation(Integer newPassword)
    { 
        password = newPassword; 
    }

    public void setPlayback(Boolean newLockpicked) 
    { 
        isLockpicked = newLockpicked; 
    }
}