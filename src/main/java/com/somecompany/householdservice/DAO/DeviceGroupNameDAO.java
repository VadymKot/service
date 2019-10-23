package com.somecompany.householdservice.DAO;

import com.somecompany.householdservice.Model.DeviceGroupName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceGroupNameDAO extends JpaRepository<DeviceGroupName,Long> {

    DeviceGroupName findByTypeStartsWithIgnoreCase(String type);
}
