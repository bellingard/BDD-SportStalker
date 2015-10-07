package net.apispark.webapi.representation;

public class User implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.String id;

    private java.lang.String latitude;

    private java.lang.String longitude;

    private java.lang.String username;

    /**
     * Returns the value of property "id". 
     * Auto-generated primary key field
     */
    public java.lang.String getId() {
        return id;
    }

    /**
     * Updates the value of property "id". 
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }

    /**
     * Returns the value of property "latitude". 
     * 
     */
    public java.lang.String getLatitude() {
        return latitude;
    }

    /**
     * Updates the value of property "latitude". 
     */
    public void setLatitude(java.lang.String latitude) {
        this.latitude = latitude;
    }

    /**
     * Returns the value of property "longitude". 
     * 
     */
    public java.lang.String getLongitude() {
        return longitude;
    }

    /**
     * Updates the value of property "longitude". 
     */
    public void setLongitude(java.lang.String longitude) {
        this.longitude = longitude;
    }

    /**
     * Returns the value of property "username". 
     * 
     */
    public java.lang.String getUsername() {
        return username;
    }

    /**
     * Updates the value of property "username". 
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }

}
