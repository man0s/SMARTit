package ceid.katefidis.smartit;
//Class Declaration
  
public class Tenant extends User
{

    // Constructor Declaration of Class
    public Tenant(Integer id, String full_name, String email, String username, String password, String phone_number)
    {
        super(id, full_name, email, username, password, phone_number);
    }
}