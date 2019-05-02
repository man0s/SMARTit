package ceid.katefidis.smartit;


// Class Declaration
  
public class Notification
{ 
    // Instance Variables 
    private String type;
    private String message;
  
    // Constructor Declaration of Class 
    public Notification(String type, String message)
    {
        this.type = type;
        this.message = message;
    }
    // method get 
    public String getType() 
    { 
        return type; 
    } 
  
    public String getMessage() 
    { 
        return message; 
    } 

    // method set
    public void setType(String newType) 
    { 
        type = newType; 
    } 

    public void setMessage(String newMessage) 
    { 
        message = newMessage; 
    } 
}