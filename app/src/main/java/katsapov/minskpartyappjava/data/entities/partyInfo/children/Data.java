package katsapov.minskpartyappjava.data.entities.partyInfo.children;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    @Override
    public String toString() {
        return "Data{" +
                "title=" + title +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}