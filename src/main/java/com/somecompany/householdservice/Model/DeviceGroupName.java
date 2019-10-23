package com.somecompany.householdservice.Model;

import javax.persistence.*;

@Entity
public class DeviceGroupName {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String type;

    public DeviceGroupName(){}

    public DeviceGroupName(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    //@Column(unique = true)
    public void setType(String type) {
        this.type = type;
    }

}
