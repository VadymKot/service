package com.somecompany.householdservice.Service;

import com.somecompany.householdservice.DAO.DeviceDAO;
import com.somecompany.householdservice.DAO.DeviceGroupNameDAO;
import com.somecompany.householdservice.Model.Device;
import com.somecompany.householdservice.Model.DeviceGroupName;
import com.somecompany.householdservice.Service.Exceptions.DeviceGroupNameException;
import com.somecompany.householdservice.Service.Exceptions.DeviceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseHoldService {

    private DeviceDAO deviceRepo;
    private DeviceGroupNameDAO deviceTypeRepo;

    public HouseHoldService(){}

    @Autowired
    public HouseHoldService(DeviceDAO deviceRepo, DeviceGroupNameDAO deviceTypeRepo) {
        this.deviceRepo = deviceRepo;
        this.deviceTypeRepo = deviceTypeRepo;
    }

    public String addDeviceGroupName(String typeValue){
        DeviceGroupName deviceGroupName = new DeviceGroupName(typeValue);
        if (!isExistInDeviceGroupName(typeValue)){
        return deviceTypeRepo.save(deviceGroupName).getType();
        } else return typeValue+" group is already exist";
    }

    public List<String> findAllDeviceGroupNames(){
        List<DeviceGroupName> tempDeviceTypes = deviceTypeRepo.findAll();
        List<String> deviceStrings = new ArrayList<>();

        for (int i=0; i<tempDeviceTypes.size();i++) {
            deviceStrings.add(tempDeviceTypes.get(i).getType());
        }
        return deviceStrings;
    }

    public Device addDevice(Device device) throws DeviceGroupNameException {
        if (isExistInDeviceGroupName(device.getDeviceGroupName())) {
            return deviceRepo.save(device);
        } else throw new DeviceGroupNameException(device.getDeviceGroupName());
    }

    private boolean isExistInDeviceGroupName(String groupName) {
        return (deviceTypeRepo.findByTypeEqualsIgnoreCase(groupName)!=null);
    }

    public void deleteDeviceById(long id) throws DeviceNotFoundException {
        boolean isDeviceInBase = (findDeviceById(id) != null);
        if (isDeviceInBase) {
            deviceRepo.deleteById(id);
        }

    }

    public Device updateDevice(Device updatedDevice, long id) throws DeviceGroupNameException {
        if (isExistInDeviceGroupName(updatedDevice.getDeviceGroupName())) {
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

        } else throw new DeviceGroupNameException(updatedDevice.getDeviceGroupName());
    }


    public List<Device> findAllDevices(){
        return deviceRepo.findAll();
    }

    public Device findDeviceById(long id) throws DeviceNotFoundException{
        return deviceRepo.findById(id).orElseThrow(() -> new DeviceNotFoundException(id));
    }
}
