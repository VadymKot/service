package com.somecompany.householdservice.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceGroupName that = (DeviceGroupName) o;
        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(type);
    }
}
