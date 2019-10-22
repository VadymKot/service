package com.somecompany.householdservice.DAO;

import com.somecompany.householdservice.Model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceDAO extends JpaRepository<Device,Long> {


}
