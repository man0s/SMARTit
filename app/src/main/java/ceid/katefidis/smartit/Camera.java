package ceid.katefidis.smartit;

// Class Declaration
  
public class Camera extends SecurityDevice
{ 
    // Instance Variables 
    private Integer zoom;
    private Integer rotation;
    private Integer playback;
  
    // Constructor Declaration of Class 
    public Camera(Integer zoom, Integer rotation, Integer playback) 
    { 
        this.zoom = zoom;
        this.rotation = rotation;
        this.playback = playback; 
    } 
  
    // method get 
    public Integer getZoom() 
    { 
        return zoom; 
    }

    public Integer getRotation() 
    { 
        return rotation; 
    }

    public Integer getPlayback() 
    { 
        return playback; 
    }
  
    // method set
    public void setZoom(Integer newZoom) 
    { 
        zoom = newZoom; 
    }

    public void setRotation(Integer newRotation) 
    { 
        rotation = newRotation; 
    }

    public void setPlayback(Integer newPlayback) 
    { 
        playback = newPlayback; 
    }
}