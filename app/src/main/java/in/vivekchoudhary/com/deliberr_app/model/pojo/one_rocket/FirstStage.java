
package in.vivekchoudhary.com.deliberr_app.model.pojo.one_rocket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FirstStage {

    @SerializedName("reusable")
    @Expose
    private Boolean reusable;
    @SerializedName("engines")
    @Expose
    private Double engines;
    @SerializedName("fuel_amount_tons")
    @Expose
    private Double fuelAmountTons;
    @SerializedName("burn_time_sec")
    @Expose
    private Double burnTimeSec;
    @SerializedName("thrust_sea_level")
    @Expose
    private ThrustSeaLevel thrustSeaLevel;
    @SerializedName("thrust_vacuum")
    @Expose
    private ThrustVacuum thrustVacuum;

    public Boolean getReusable() {
        return reusable;
    }

    public void setReusable(Boolean reusable) {
        this.reusable = reusable;
    }

    public Double getEngines() {
        return engines;
    }

    public void setEngines(Double engines) {
        this.engines = engines;
    }

    public Double getFuelAmountTons() {
        return fuelAmountTons;
    }

    public void setFuelAmountTons(Double fuelAmountTons) {
        this.fuelAmountTons = fuelAmountTons;
    }

    public Double getBurnTimeSec() {
        return burnTimeSec;
    }

    public void setBurnTimeSec(Double burnTimeSec) {
        this.burnTimeSec = burnTimeSec;
    }

    public ThrustSeaLevel getThrustSeaLevel() {
        return thrustSeaLevel;
    }

    public void setThrustSeaLevel(ThrustSeaLevel thrustSeaLevel) {
        this.thrustSeaLevel = thrustSeaLevel;
    }

    public ThrustVacuum getThrustVacuum() {
        return thrustVacuum;
    }

    public void setThrustVacuum(ThrustVacuum thrustVacuum) {
        this.thrustVacuum = thrustVacuum;
    }

}
