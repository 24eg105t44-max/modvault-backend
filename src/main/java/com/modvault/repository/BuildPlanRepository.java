package com.modvault.repository;

import com.modvault.entity.BuildPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BuildPlanRepository extends JpaRepository<BuildPlan, Long> {
    List<BuildPlan> findByVehicleId(Long vehicleId);
}
