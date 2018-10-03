
package in.vivekchoudhary.com.deliberr_app.model.pojo.one_rocket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SecondStage {

    @SerializedName("engines")
    @Expose
    private Double engines;
    @SerializedName("fuel_amount_tons")
    @Expose
    private Double fuelAmountTons;
    @SerializedName("burn_time_sec")
    @Expose
    private Double burnTimeSec;
    @SerializedName("thrust")
    @Expose
    private Thrust thrust;
    @SerializedName("payloads")
    @Expose
    private Payloads payloads;

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

    public Thrust getThrust() {
        return thrust;
    }

    public void setThrust(Thrust thrust) {
        this.thrust = thrust;
    }

    public Payloads getPayloads() {
        return payloads;
    }

    public void setPayloads(Payloads payloads) {
        this.payloads = payloads;
    }

}
