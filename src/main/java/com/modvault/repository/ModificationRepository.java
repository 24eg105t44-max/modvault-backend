package com.modvault.repository;

import com.modvault.entity.Modification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ModificationRepository extends JpaRepository<Modification, Long> {
    List<Modification> findByVehicleId(Long vehicleId);
    List<Modification> findAllByOrderByIdDesc();
}
