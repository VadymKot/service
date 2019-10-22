package com.somecompany.householdservice.Model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    long id;
    @OneToOne
    DeviceGroupName deviceGroupName;
    ArrayList<String> properties;
    boolean isBroken;
    String description;

    public Device(){}

    public Device(DeviceGroupName deviceGroupName, ArrayList<String> properties, boolean isBroken, String description) {
        this.deviceGroupName = deviceGroupName;
        this.properties = properties;
        this.isBroken = isBroken;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DeviceGroupName getDeviceGroupName() {
        return deviceGroupName;
    }

    public void setDeviceGroupName(DeviceGroupName deviceGroupName) {
        this.deviceGroupName = deviceGroupName;
    }

    public ArrayList<String> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<String> properties) {
        this.properties = properties;
    }

    public boolean getIsBroken() {
        return isBroken;
    }

    public void setIsBroken(boolean broken) {
        isBroken = broken;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}