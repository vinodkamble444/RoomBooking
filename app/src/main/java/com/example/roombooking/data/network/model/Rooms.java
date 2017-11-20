package com.example.roombooking.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by vinod on 11/10/2017.
 */

public class Rooms {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("equipment")
    @Expose
    private List<String> equipment = null;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("capacity")
    @Expose
    private Integer capacity;
    @SerializedName("avail")
    @Expose
    private List<String> avail = null;
    @SerializedName("images")
    @Expose
    private List<String> images = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<String> equipment) {
        this.equipment = equipment;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public List<String> getAvail() {
        return avail;
    }

    public void setAvail(List<String> avail) {
        this.avail = avail;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
