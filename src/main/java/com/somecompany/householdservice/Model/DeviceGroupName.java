package com.somecompany.householdservice.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DeviceGroupName {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    long id;
    String type;

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

    public void setType(String type) {
        this.type = type;
    }

    //    Map<String,String> equipmentTypes = new HashMap<>();
//
//    public DeviceGroupName() {}
//
//    Map<String,String> receiveAllTypes(){
//        return equipmentTypes;
//    }
//
//    String getEqipmentType(String type){
//         return equipmentTypes.get(type);
//    }
//
//    void setDeviceGroupName(String type){
//
//        equipmentTypes.put(type,type);
//    }
}
