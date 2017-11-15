package shander.tinkoffnewstest.entities.newsDetailsEntities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import shander.tinkoffnewstest.entities.Date;

public class SinglePayload {

    @SerializedName("title")
    @Expose
    private Title title;
    @SerializedName("creationDate")
    @Expose
    private Date creationDate;
    @SerializedName("lastModificationDate")
    @Expose
    private Date lastModificationDate;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("bankInfoTypeId")
    @Expose
    private Integer bankInfoTypeId;
    @SerializedName("typeId")
    @Expose
    private String typeId;

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(Date lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getBankInfoTypeId() {
        return bankInfoTypeId;
    }

    public void setBankInfoTypeId(Integer bankInfoTypeId) {
        this.bankInfoTypeId = bankInfoTypeId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(content).append(creationDate).append(title).append(bankInfoTypeId).append(typeId).append(lastModificationDate).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SinglePayload)) {
            return false;
        }
        SinglePayload rhs = ((SinglePayload) other);
        return new EqualsBuilder().append(content, rhs.content).append(creationDate, rhs.creationDate).append(title, rhs.title).append(bankInfoTypeId, rhs.bankInfoTypeId).append(typeId, rhs.typeId).append(lastModificationDate, rhs.lastModificationDate).isEquals();
    }
}
