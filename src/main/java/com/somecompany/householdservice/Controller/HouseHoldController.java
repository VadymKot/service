package com.somecompany.householdservice.Controller;

import com.somecompany.householdservice.Model.Device;
import com.somecompany.householdservice.Service.DeviceModelAssembler;
import com.somecompany.householdservice.Service.Exceptions.DeviceGroupNameException;
import com.somecompany.householdservice.Service.Exceptions.DeviceNotFoundException;
import com.somecompany.householdservice.Service.HouseHoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RequestMapping("/devices")
@RestController
public class HouseHoldController {


    private HouseHoldService houseHoldService;
    private DeviceModelAssembler assembler;

    @Autowired
    public HouseHoldController(HouseHoldService houseHoldService, DeviceModelAssembler assembler) {
        this.houseHoldService = houseHoldService;
        this.assembler = assembler;
    }

    @PostMapping
    EntityModel<?> makeNewDevice(@RequestBody Device newDevice)  {
        try {
            return assembler.toModel(houseHoldService.addDevice(newDevice));
        }
        catch (DeviceGroupNameException ex) {
            return new EntityModel<>("There is no "+newDevice.getDeviceGroupName()
                    +" group name in the Database. Please create this group for first.");
        }
    }

    @GetMapping("/id/{id}")
    public EntityModel<?> receiveDeviceById(@PathVariable("id") long id) {
        try {
            return assembler.toModel(houseHoldService.findDeviceById(id));
        }
        catch (DeviceNotFoundException ex) {
            return new EntityModel<>("Requested device not found!");
        }
    }

    @GetMapping
    public CollectionModel<EntityModel<Device>> receiveAllDevices(){
        List<EntityModel<Device>> devices = houseHoldService.findAllDevices().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<> (devices,
                linkTo(methodOn(HouseHoldController.class).receiveAllDevices()).withRel("All Devices"));
    }

    @PutMapping("/id/{id}")
    EntityModel<?> updateDeviceById(@RequestBody Device newDevice, @PathVariable("id") long id){
        try {
            return assembler.toModel(houseHoldService.updateDevice(newDevice, id));
        }
        catch (DeviceGroupNameException ex) {
            return new EntityModel<>("There is no "+newDevice.getDeviceGroupName()
                    +" group name in the Database. Please create this group for first.");
        }
    }

    @DeleteMapping("/id/{id}")
    EntityModel<?> deleteDeviceById(@PathVariable long id) {
        try {
            houseHoldService.deleteDeviceById(id);
            return new EntityModel<>("Device with id - "+ id + " was successfully deleted");
        }
        catch (DeviceNotFoundException ex) {
            return new EntityModel<>("Requested device not found!");
        }
    }
    @PostMapping("/devicegroup")
    EntityModel<?> makeNewDeviceGroup(@RequestBody String newDeviceGroup){
        return new EntityModel<>(houseHoldService.addDeviceGroupName(newDeviceGroup)+" group is created");
    }
    @GetMapping("/devicegroup")
    CollectionModel<?> receiveAllDeviceGroups(){
        return new CollectionModel<> (houseHoldService.findAllDeviceGroupNames());
    }
}

