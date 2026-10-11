package com.modvault.service;

import com.modvault.entity.ExpenseEntry;
import com.modvault.entity.FuelEntry;
import com.modvault.entity.RecentActivity;
import com.modvault.repository.ExpenseEntryRepository;
import com.modvault.repository.FuelEntryRepository;
import com.modvault.repository.RecentActivityRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FuelExpenseService {
    private final FuelEntryRepository fuelEntryRepository;
    private final ExpenseEntryRepository expenseEntryRepository;
    private final RecentActivityRepository recentActivityRepository;

    public FuelExpenseService(FuelEntryRepository fuelEntryRepository,
                              ExpenseEntryRepository expenseEntryRepository,
                              RecentActivityRepository recentActivityRepository) {
        this.fuelEntryRepository = fuelEntryRepository;
        this.expenseEntryRepository = expenseEntryRepository;
        this.recentActivityRepository = recentActivityRepository;
    }

    public List<FuelEntry> getAllFuelEntries() {
        return fuelEntryRepository.findAllByOrderByEntryDateDesc();
    }

    public FuelEntry createFuelEntry(FuelEntry entry) {
        FuelEntry saved = fuelEntryRepository.save(entry);

        // Record activity
        RecentActivity activity = new RecentActivity();
        activity.setTitle("Fuel entry added");
        String carName = (entry.getVehicle() != null) ? entry.getVehicle().getMake() + " " + entry.getVehicle().getModel() : "Vehicle";
        activity.setVehicleOrCategory(carName);
        activity.setTimestamp(entry.getEntryDate() != null ? entry.getEntryDate() : LocalDateTime.now());
        activity.setAmount(entry.getCost());
        activity.setType("fuel");
        activity.setIconColor("blue");
        recentActivityRepository.save(activity);

        return saved;
    }

    public void deleteFuelEntry(Long id) {
        fuelEntryRepository.deleteById(id);
    }

    public List<ExpenseEntry> getAllExpenseEntries() {
        return expenseEntryRepository.findAllByOrderByExpenseDateDesc();
    }

    public ExpenseEntry createExpenseEntry(ExpenseEntry entry) {
        ExpenseEntry saved = expenseEntryRepository.save(entry);

        // Record activity
        RecentActivity activity = new RecentActivity();
        activity.setTitle("Expense added");
        activity.setVehicleOrCategory(entry.getTitle());
        activity.setTimestamp(entry.getExpenseDate() != null ? entry.getExpenseDate() : LocalDateTime.now());
        activity.setAmount(entry.getAmount());
        activity.setType("expense");
        activity.setIconColor("amber");
        recentActivityRepository.save(activity);

        return saved;
    }

    public void deleteExpenseEntry(Long id) {
        expenseEntryRepository.deleteById(id);
    }
}
