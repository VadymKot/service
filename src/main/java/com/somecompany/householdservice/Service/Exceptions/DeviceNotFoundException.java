package com.somecompany.householdservice.Service.Exceptions;

public class DeviceNotFoundException extends Exception {

    public DeviceNotFoundException(long id) {

        super("Could not find device " + id);
    }
}
