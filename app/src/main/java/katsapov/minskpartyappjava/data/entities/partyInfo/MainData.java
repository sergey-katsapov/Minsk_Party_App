package katsapov.minskpartyappjava.data.entities.partyInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import katsapov.minskpartyappjava.data.entities.partyInfo.children.Children;

public class MainData {

    @SerializedName("modhash")
    @Expose
    private String modhash;

    @SerializedName("children")
    @Expose
    private ArrayList<Children> children;

    public String getModhash() {
        return modhash;
    }

    public void setModhash(String modhash) {
        this.modhash = modhash;
    }

    public ArrayList<Children> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Children> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "MainData{" +
                "modhash='" + modhash + '\'' +
                ", children=" + children +
                '}';
    }
}