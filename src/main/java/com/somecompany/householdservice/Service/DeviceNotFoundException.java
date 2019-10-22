package com.somecompany.householdservice.Service;

public class DeviceNotFoundException extends RuntimeException {

    public DeviceNotFoundException(long id) {

        super("Could not find device " + id);
    }
}
