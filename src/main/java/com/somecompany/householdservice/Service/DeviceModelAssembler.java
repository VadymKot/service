package com.somecompany.householdservice.Service;

import com.somecompany.householdservice.Controller.HouseHoldController;
import com.somecompany.householdservice.Model.Device;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Model assembler. Adds hypermedia to objects.
 */

@Component
public class DeviceModelAssembler implements RepresentationModelAssembler<Device,EntityModel<Device>> {

    @Override
    public EntityModel<Device> toModel(Device device){

        WebMvcLinkBuilder linkId = linkTo(methodOn(HouseHoldController.class).receiveDeviceById(device.getId()));
        WebMvcLinkBuilder linkAll = linkTo(methodOn(HouseHoldController.class).receiveAllDevices());
        return new EntityModel<>(device, linkId.withSelfRel(),linkAll.withRel("All devices"));

    }
}
