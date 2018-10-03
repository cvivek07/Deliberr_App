
package in.vivekchoudhary.com.deliberr_app.model.pojo.one_rocket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayloadWeight {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("kg")
    @Expose
    private Double kg;
    @SerializedName("lb")
    @Expose
    private Double lb;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getKg() {
        return kg;
    }

    public void setKg(Double kg) {
        this.kg = kg;
    }

    public Double getLb() {
        return lb;
    }

    public void setLb(Double lb) {
        this.lb = lb;
    }

}
