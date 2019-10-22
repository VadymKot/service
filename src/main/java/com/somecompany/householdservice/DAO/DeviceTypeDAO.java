package com.somecompany.householdservice.DAO;

import com.somecompany.householdservice.Model.DeviceGroupName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceTypeDAO extends JpaRepository<DeviceGroupName,Long> {
}
