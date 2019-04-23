package com.example.mockup.mvvm.business_logic.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.mockup.constants.SqlStorage;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Contract;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = SqlStorage.Entities.events)
public class Event implements Parcelable {
    @ColumnInfo(name = SqlStorage.Columns.event_code_id)
    @PrimaryKey(autoGenerate = true)
    @SerializedName("codeId")
    @Expose(serialize = false)
    private int codeId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("webUrl")
    @Expose
    private String webUrl;

    @SerializedName("eventDate")
    @Expose
    private String eventDate;

    @SerializedName("updatedAt")
    @Expose(serialize = false)
    private String updatedAt;

    @SerializedName("createdAt")
    @Expose(serialize = false)
    private String createdAt;

    @ColumnInfo(name = SqlStorage.Columns.event_userId)
    private int userId;

    public Event() {
    }

    @Ignore
    public Event(int codeId, String name, String webUrl, String eventDate, String updatedAt, String createdAt, int userId) {
        this.codeId = codeId;
        this.name = name;
        this.webUrl = webUrl;
        this.eventDate = eventDate;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.userId = userId;
    }

    @Ignore
    public Event(String name, String webUrl, String eventDate) {
        this.name = name;
        this.webUrl = webUrl;
        this.eventDate = eventDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getCodeId() {
        return codeId;
    }

    public void setCodeId(int codeId) {
        this.codeId = codeId;
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(@Nullable Object obj) {
        boolean equals = false;
        if (obj != null)
            if (getClass() == obj.getClass())
                equals = (this.hashCode() == obj.hashCode());
        return equals;
    }

    @Override
    public int hashCode() {
        return codeId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(codeId);
        dest.writeInt(userId);
        dest.writeString(name);
        dest.writeString(webUrl);
        dest.writeString(eventDate);
        dest.writeString(updatedAt);
        dest.writeString(createdAt);
    }

    protected Event(@NonNull Parcel in) {
        this.codeId = in.readInt();
        this.createdAt = in.readString();
        this.eventDate = in.readString();
        this.name = in.readString();
        this.updatedAt = in.readString();
        this.userId = in.readInt();
        this.webUrl = in.readString();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Contract("_ -> new")
        @NonNull
        @Override
        public Event createFromParcel(Parcel source) {
            return new Event(source);
        }
        @Contract(value = "_ -> new", pure = true)
        @NonNull
        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
}
