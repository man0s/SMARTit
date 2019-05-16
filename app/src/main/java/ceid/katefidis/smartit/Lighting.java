package ceid.katefidis.smartit;

//Class Declaration
  
public class Lighting extends MaintenanceDevice
{ 
    // Instance Variables 
    private Integer colorR;
    private Integer colorG;
    private Integer colorB;
    private Integer hue;
  
    // Constructor Declaration of Class 
    public Lighting(Integer colorR, Integer colorG, Integer colorB, Integer hue)
    { 
        this.colorR = colorR;
        this.colorG = colorG;
        this.colorB = colorB;
        this.hue = hue;
    }
    // method get
    public Integer getColorR() {
        return colorR;
    }

    public Integer getColorB() {
        return colorB;
    }

    public Integer getColorG() {
        return colorG;
    }

    public Integer getHue()
    { 
        return hue; 
    }

    // method set
    public void setColorR(Integer colorR) {
        this.colorR = colorR;
    }

    public void setColorG(Integer colorG) {
        this.colorG = colorG;
    }

    public void setColorB(Integer colorB) {
        this.colorB = colorB;
    }

    public void setHue(Integer newhue)
    { 
        hue = newhue;
    } 
}