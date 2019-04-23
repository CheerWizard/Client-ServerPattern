package com.example.mockup.mvvm.business_logic.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.mockup.constants.SqlStorage;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Contract;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = SqlStorage.Entities.users)
public class User implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose(serialize = false)
    private int id;

    @ColumnInfo(name = SqlStorage.Columns.user_id)
    @SerializedName("userId")
    @Expose(serialize = false)
    private int userId;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("createdAt")
    @Expose(serialize = false)
    private String createdAt;

    @SerializedName("updatedAt")
    @Expose(serialize = false)
    private String updatedAt;

    public User() {}

    @Ignore
    public User(int id, int userId, String email, String createdAt, String updatedAt) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int hashCode() {
        return userId;
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object other) {
        boolean equals = false;
        if (other != null)
            if (getClass() == other.getClass())
                equals = this.hashCode() == other.hashCode();
        return equals;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(userId);
        dest.writeString(email);
        dest.writeString(updatedAt);
        dest.writeString(createdAt);
    }

    protected User(@NonNull Parcel in) {
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
        this.email = in.readString();
        this.userId = in.readInt();
        this.id = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Contract("_ -> new")
        @NonNull
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }
        @Contract(value = "_ -> new", pure = true)
        @NonNull
        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
