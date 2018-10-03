
package in.vivekchoudhary.com.deliberr_app.model.pojo.one_rocket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Diameter {

    @SerializedName("meters")
    @Expose
    private Double meters;
    @SerializedName("feet")
    @Expose
    private Double feet;

    public Double getMeters() {
        return meters;
    }

    public void setMeters(Double meters) {
        this.meters = meters;
    }

    public Double getFeet() {
        return feet;
    }

    public void setFeet(Double feet) {
        this.feet = feet;
    }

}
