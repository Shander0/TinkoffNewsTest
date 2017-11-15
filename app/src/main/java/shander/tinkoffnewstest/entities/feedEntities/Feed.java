package shander.tinkoffnewstest.entities.feedEntities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.List;

public class Feed {

    @SerializedName("resultCode")
    @Expose
    private String resultCode;
    @SerializedName("payload")
    @Expose
    private List<Payload> payload = null;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public List<Payload> getPayload() {
        return payload;
    }

    public void setPayload(List<Payload> payload) {
        this.payload = payload;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(resultCode).append(payload).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Feed)) {
            return false;
        }
        Feed rhs = ((Feed) other);
        return new EqualsBuilder().append(resultCode, rhs.resultCode).append(payload, rhs.payload).isEquals();
    }

}
