
package in.vivekchoudhary.com.deliberr_app.model.pojo.launches;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Launch implements Parcelable {

    @SerializedName("flight_number")
    @Expose
    private Integer flightNumber;
    @SerializedName("mission_name")
    @Expose
    private String missionName;
    @SerializedName("mission_id")
    @Expose
    private List<String> missionId = null;
    @SerializedName("launch_year")
    @Expose
    private String launchYear;
    @SerializedName("launch_date_unix")
    @Expose
    private Integer launchDateUnix;
    @SerializedName("launch_date_utc")
    @Expose
    private String launchDateUtc;
    @SerializedName("launch_date_local")
    @Expose
    private String launchDateLocal;
    @SerializedName("is_tentative")
    @Expose
    private Boolean isTentative;
    @SerializedName("tentative_max_precision")
    @Expose
    private String tentativeMaxPrecision;
    @SerializedName("rocket")
    @Expose
    private Rocket rocket;
    @SerializedName("ships")
    @Expose
    private List<String> ships = null;
    @SerializedName("telemetry")
    @Expose
    private Telemetry telemetry;
    @SerializedName("reuse")
    @Expose
    private Reuse reuse;
    @SerializedName("launch_site")
    @Expose
    private LaunchSite launchSite;
    @SerializedName("launch_success")
    @Expose
    private Boolean launchSuccess;
    @SerializedName("links")
    @Expose
    private Links links;
    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("upcoming")
    @Expose
    private Boolean upcoming;
    @SerializedName("static_fire_date_utc")
    @Expose
    private String staticFireDateUtc;
    @SerializedName("static_fire_date_unix")
    @Expose
    private Integer staticFireDateUnix;

    protected Launch(Parcel in) {
        if (in.readByte() == 0) {
            flightNumber = null;
        } else {
            flightNumber = in.readInt();
        }
        missionName = in.readString();
        missionId = in.createStringArrayList();
        launchYear = in.readString();
        if (in.readByte() == 0) {
            launchDateUnix = null;
        } else {
            launchDateUnix = in.readInt();
        }
        launchDateUtc = in.readString();
        launchDateLocal = in.readString();
        byte tmpIsTentative = in.readByte();
        isTentative = tmpIsTentative == 0 ? null : tmpIsTentative == 1;
        tentativeMaxPrecision = in.readString();
        ships = in.createStringArrayList();
        byte tmpLaunchSuccess = in.readByte();
        launchSuccess = tmpLaunchSuccess == 0 ? null : tmpLaunchSuccess == 1;
        details = in.readString();
        byte tmpUpcoming = in.readByte();
        upcoming = tmpUpcoming == 0 ? null : tmpUpcoming == 1;
        staticFireDateUtc = in.readString();
        if (in.readByte() == 0) {
            staticFireDateUnix = null;
        } else {
            staticFireDateUnix = in.readInt();
        }
    }

    public static final Creator<Launch> CREATOR = new Creator<Launch>() {
        @Override
        public Launch createFromParcel(Parcel in) {
            return new Launch(in);
        }

        @Override
        public Launch[] newArray(int size) {
            return new Launch[size];
        }
    };

    public Integer getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Integer flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public List<String> getMissionId() {
        return missionId;
    }

    public void setMissionId(List<String> missionId) {
        this.missionId = missionId;
    }

    public String getLaunchYear() {
        return launchYear;
    }

    public void setLaunchYear(String launchYear) {
        this.launchYear = launchYear;
    }

    public Integer getLaunchDateUnix() {
        return launchDateUnix;
    }

    public void setLaunchDateUnix(Integer launchDateUnix) {
        this.launchDateUnix = launchDateUnix;
    }

    public String getLaunchDateUtc() {
        return launchDateUtc;
    }

    public void setLaunchDateUtc(String launchDateUtc) {
        this.launchDateUtc = launchDateUtc;
    }

    public String getLaunchDateLocal() {
        return launchDateLocal;
    }

    public void setLaunchDateLocal(String launchDateLocal) {
        this.launchDateLocal = launchDateLocal;
    }

    public Boolean getIsTentative() {
        return isTentative;
    }

    public void setIsTentative(Boolean isTentative) {
        this.isTentative = isTentative;
    }

    public String getTentativeMaxPrecision() {
        return tentativeMaxPrecision;
    }

    public void setTentativeMaxPrecision(String tentativeMaxPrecision) {
        this.tentativeMaxPrecision = tentativeMaxPrecision;
    }

    public Rocket getRocket() {
        return rocket;
    }

    public void setRocket(Rocket rocket) {
        this.rocket = rocket;
    }

    public List<String> getShips() {
        return ships;
    }

    public void setShips(List<String> ships) {
        this.ships = ships;
    }

    public Telemetry getTelemetry() {
        return telemetry;
    }

    public void setTelemetry(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    public Reuse getReuse() {
        return reuse;
    }

    public void setReuse(Reuse reuse) {
        this.reuse = reuse;
    }

    public LaunchSite getLaunchSite() {
        return launchSite;
    }

    public void setLaunchSite(LaunchSite launchSite) {
        this.launchSite = launchSite;
    }

    public Boolean getLaunchSuccess() {
        return launchSuccess;
    }

    public void setLaunchSuccess(Boolean launchSuccess) {
        this.launchSuccess = launchSuccess;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Boolean getUpcoming() {
        return upcoming;
    }

    public void setUpcoming(Boolean upcoming) {
        this.upcoming = upcoming;
    }

    public String getStaticFireDateUtc() {
        return staticFireDateUtc;
    }

    public void setStaticFireDateUtc(String staticFireDateUtc) {
        this.staticFireDateUtc = staticFireDateUtc;
    }

    public Integer getStaticFireDateUnix() {
        return staticFireDateUnix;
    }

    public void setStaticFireDateUnix(Integer staticFireDateUnix) {
        this.staticFireDateUnix = staticFireDateUnix;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (flightNumber == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(flightNumber);
        }
        dest.writeString(missionName);
        dest.writeStringList(missionId);
        dest.writeString(launchYear);
        if (launchDateUnix == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(launchDateUnix);
        }
        dest.writeString(launchDateUtc);
        dest.writeString(launchDateLocal);
        dest.writeByte((byte) (isTentative == null ? 0 : isTentative ? 1 : 2));
        dest.writeString(tentativeMaxPrecision);
        dest.writeStringList(ships);
        dest.writeByte((byte) (launchSuccess == null ? 0 : launchSuccess ? 1 : 2));
        dest.writeString(details);
        dest.writeByte((byte) (upcoming == null ? 0 : upcoming ? 1 : 2));
        dest.writeString(staticFireDateUtc);
        if (staticFireDateUnix == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(staticFireDateUnix);
        }
    }
}
