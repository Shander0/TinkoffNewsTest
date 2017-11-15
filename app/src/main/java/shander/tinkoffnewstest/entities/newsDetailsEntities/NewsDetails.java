package shander.tinkoffnewstest.entities.newsDetailsEntities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class NewsDetails {

    @SerializedName("resultCode")
    @Expose
    private String resultCode;
    @SerializedName("payload")
    @Expose
    private SinglePayload payload;
    @SerializedName("trackingId")
    @Expose
    private String trackingId;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public SinglePayload getPayload() {
        return payload;
    }

    public void setPayload(SinglePayload payload) {
        this.payload = payload;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(trackingId).append(resultCode).append(payload).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof NewsDetails)) {
            return false;
        }
        NewsDetails rhs = ((NewsDetails) other);
        return new EqualsBuilder().append(trackingId, rhs.trackingId).append(resultCode, rhs.resultCode).append(payload, rhs.payload).isEquals();
    }

}
