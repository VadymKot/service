package com.somecompany.householdservice.Service;

public class DeviceGroupNameException extends Exception {

    public DeviceGroupNameException(String type) {

        super("Could not find this type of households: " +type+ " Please, for first, add this type");
        }


}
