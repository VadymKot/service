package com.somecompany.householdservice.Controller;

import com.somecompany.householdservice.Model.Device;
import com.somecompany.householdservice.Model.DeviceGroupName;
import com.somecompany.householdservice.Service.DeviceGroupNameException;
import com.somecompany.householdservice.Service.DeviceNotFoundException;
import com.somecompany.householdservice.Service.HouseHoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/devices")
@RestController
public class HouseHoldController {


    HouseHoldService houseHoldService;

    @Autowired
    public HouseHoldController(HouseHoldService houseHoldService) {
        this.houseHoldService = houseHoldService;
    }

    @PostMapping
    Device makeNewDevice(@RequestBody Device newDevice) throws DeviceGroupNameException {
        return houseHoldService.addDevice(newDevice);
    }

    @GetMapping("/id/{id}")
    Device receiveDeviceById(@PathVariable("id") long id) throws DeviceNotFoundException{
        return houseHoldService.findDeviceById(id);
    }

    @GetMapping
    List<Device> receiveAllDevices(){
        return houseHoldService.findAllDevices();
    }

    @PutMapping("/id/{id}")
    Device updateDeviceById(@RequestBody Device newDevice, @PathVariable("id") long id){
        return houseHoldService.updateDevice(newDevice, id);
    }

    @DeleteMapping("/id/{id}")
    void deleteDeviceById(@PathVariable long id) throws DeviceNotFoundException {
        houseHoldService.deleteDeviceById(id);

    }
    @PostMapping("/devicegroup")
    String makeNewDeviceGroup(@RequestBody String newDeviceGroup){
        return houseHoldService.addDeviceGroupName(newDeviceGroup);
    }
    @GetMapping("/devicegroup")
    List<String> receiveAllDeviceGroups(){
        return houseHoldService.findAllDeviceGroupNames();
    }
}

