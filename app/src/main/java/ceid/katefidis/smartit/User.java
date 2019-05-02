package ceid.katefidis.smartit;
// Class Declaration

public class User
{
// Instance Variables
  private Integer id;
  private String full_name;
  private String email;
  private String username;
  private String password;
  private String phone_number;

  User(){}

// Constructor Declaration of Class
public User(Integer id, String full_name, String email, String username, String password, String phone_number)
{
   this.id = id;
   this.full_name = full_name;
   this.email = email;
   this.username = username;
   this.password = password;
   this.phone_number = phone_number;
}

   // method get
    public Integer getId() { return id; }
    public String getName()
        {
            return full_name;
        }
    public String getUsername()
        {
            return username;
        }
    public String getPassword()
        {
            return password;
        }
    public String getPhone()
        {
            return phone_number;
        }

}
 