package sm.fr.advancedlayoutapp.model;

/**
 * Created by Formation on 16/01/2018.
 */

public class RandowUser {

    private String name;
    private String email;
    private Double latitude;
    private Double longitude;


    public RandowUser() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
