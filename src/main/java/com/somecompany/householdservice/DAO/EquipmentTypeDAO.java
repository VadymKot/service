package com.somecompany.householdservice.DAO;

import com.somecompany.householdservice.Model.EquipmentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentTypeDAO extends JpaRepository<EquipmentType,Long> {
}
