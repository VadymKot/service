package com.somecompany.householdservice.Service;

public class DeviceNotFoundException extends Exception {

    public DeviceNotFoundException(long id) {

        super("Could not find device " + id);
    }
}
