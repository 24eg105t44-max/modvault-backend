package com.modvault.repository;

import com.modvault.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByUserId(Long userId);
    List<Vehicle> findAllByOrderByIsPrimaryDescIdAsc();
}
