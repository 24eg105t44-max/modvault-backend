package com.modvault.controller;

import com.modvault.entity.ExpenseEntry;
import com.modvault.entity.FuelEntry;
import com.modvault.service.FuelExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FuelExpenseController {
    private final FuelExpenseService fuelExpenseService;

    public FuelExpenseController(FuelExpenseService fuelExpenseService) {
        this.fuelExpenseService = fuelExpenseService;
    }

    @GetMapping("/fuel")
    public ResponseEntity<List<FuelEntry>> getAllFuelEntries() {
        return ResponseEntity.ok(fuelExpenseService.getAllFuelEntries());
    }

    @PostMapping("/fuel")
    public ResponseEntity<FuelEntry> createFuelEntry(@RequestBody FuelEntry fuelEntry) {
        return ResponseEntity.ok(fuelExpenseService.createFuelEntry(fuelEntry));
    }

    @DeleteMapping("/fuel/{id}")
    public ResponseEntity<?> deleteFuelEntry(@PathVariable Long id) {
        fuelExpenseService.deleteFuelEntry(id);
        return ResponseEntity.ok(Map.of("message", "Fuel entry deleted", "id", id));
    }

    @GetMapping("/expenses")
    public ResponseEntity<List<ExpenseEntry>> getAllExpenses() {
        return ResponseEntity.ok(fuelExpenseService.getAllExpenseEntries());
    }

    @PostMapping("/expenses")
    public ResponseEntity<ExpenseEntry> createExpenseEntry(@RequestBody ExpenseEntry expenseEntry) {
        return ResponseEntity.ok(fuelExpenseService.createExpenseEntry(expenseEntry));
    }

    @DeleteMapping("/expenses/{id}")
    public ResponseEntity<?> deleteExpenseEntry(@PathVariable Long id) {
        fuelExpenseService.deleteExpenseEntry(id);
        return ResponseEntity.ok(Map.of("message", "Expense entry deleted", "id", id));
    }
}
