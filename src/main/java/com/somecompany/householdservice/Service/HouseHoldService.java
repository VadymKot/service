package com.somecompany.householdservice.Service;

import com.somecompany.householdservice.DAO.DeviceDAO;
import com.somecompany.householdservice.DAO.DeviceTypeDAO;
import com.somecompany.householdservice.Model.Device;
import com.somecompany.householdservice.Model.DeviceGroupName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseHoldService {

    private DeviceDAO deviceRepo;
    private DeviceTypeDAO deviceTypeRepo;

    public HouseHoldService(){}

    @Autowired
    public HouseHoldService(DeviceDAO deviceRepo, DeviceTypeDAO deviceTypeRepo) {
        this.deviceRepo = deviceRepo;
        this.deviceTypeRepo = deviceTypeRepo;
    }

    public DeviceGroupName addDeviceGroupName(String typeValue){
        DeviceGroupName DeviceGroupName = new DeviceGroupName(typeValue);
        return deviceTypeRepo.save(DeviceGroupName);
    }

    public List<String> findAllDeviceGroupNames(){
        List<DeviceGroupName> tempDeviceTypes = deviceTypeRepo.findAll();
        List<String> deviceStrings = new ArrayList<>();

        for (int i=0; i<tempDeviceTypes.size();i++) {
            deviceStrings.add(tempDeviceTypes.get(i).getType());
        }
        return deviceStrings;
    }

    public Device addDevice(Device device){
        return deviceRepo.save(device);
    }

    public void deleteDeviceById(long id){
        deviceRepo.deleteById(id);
    }

    public Device updateDevice(Device updatedDevice, long id){
        return deviceRepo.findById(id)
                .map(device -> {
                    device.setDeviceGroupName(updatedDevice.getDeviceGroupName());
                    device.setProperties(updatedDevice.getProperties());
                    device.setIsBroken(updatedDevice.getIsBroken());
                    device.setDescription(updatedDevice.getDescription());
                    return deviceRepo.save(device);
                })
                .orElseGet(() -> {
                    updatedDevice.setId(id);
                    return deviceRepo.save(updatedDevice);
                });

    }

    public List<Device> findAllDevices(){
        return deviceRepo.findAll();
    }

    public Device findDeviceById(long id){
        return deviceRepo.findById(id).orElseThrow(() -> new DeviceNotFoundException(id));
    }
}
