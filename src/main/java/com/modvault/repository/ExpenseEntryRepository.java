package com.modvault.repository;

import com.modvault.entity.ExpenseEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ExpenseEntryRepository extends JpaRepository<ExpenseEntry, Long> {
    List<ExpenseEntry> findByVehicleId(Long vehicleId);
    List<ExpenseEntry> findAllByOrderByExpenseDateDesc();
}
