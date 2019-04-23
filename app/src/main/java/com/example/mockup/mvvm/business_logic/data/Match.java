package com.example.mockup.mvvm.business_logic.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Contract;

import androidx.annotation.NonNull;
import androidx.room.Ignore;

/**Just a simple model that serializes and deserializes for client-server data's transfer*/
public class Match implements Parcelable {
    private int id;

    @SerializedName("userId")
    @Expose
    private int userId;

    @SerializedName("codeId")
    @Expose
    private int codeId;

    public Match() {}

    @Ignore
    public Match(int userId, int codeId) {
        this.userId = userId;
        this.codeId = codeId;
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

    public int getCodeId() {
        return codeId;
    }

    public void setCodeId(int codeId) {
        this.codeId = codeId;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object other) {
        boolean equals = false;
        if (other != null)
            if (getClass() == other.getClass())
                equals = (this.hashCode() == other.hashCode());
        return equals;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(codeId);
        dest.writeInt(userId);
    }

    protected Match(@NonNull Parcel in) {
        this.codeId = in.readInt();
        this.userId = in.readInt();
    }

    public static final Creator<Match> CREATOR = new Creator<Match>() {
        @Contract("_ -> new")
        @NonNull
        @Override
        public Match createFromParcel(Parcel source) {
            return new Match(source);
        }
        @Contract(value = "_ -> new", pure = true)
        @NonNull
        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };
}
