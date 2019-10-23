package com.somecompany.householdservice.DAO;

import com.somecompany.householdservice.Model.DeviceGroupName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceGroupNameDAO extends JpaRepository<DeviceGroupName,Long> {

    List<DeviceGroupName> findByTypeStartsWithIgnoreCase(String type);
}
