package com.modvault.repository;

import com.modvault.entity.FuelEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FuelEntryRepository extends JpaRepository<FuelEntry, Long> {
    List<FuelEntry> findByVehicleId(Long vehicleId);
    List<FuelEntry> findAllByOrderByEntryDateDesc();
}
