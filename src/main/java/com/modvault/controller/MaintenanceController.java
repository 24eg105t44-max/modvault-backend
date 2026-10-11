package com.modvault.controller;

import com.modvault.entity.MaintenanceLog;
import com.modvault.entity.MaintenanceReminder;
import com.modvault.service.MaintenanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/maintenance")
public class MaintenanceController {
    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @GetMapping("/reminders")
    public ResponseEntity<List<MaintenanceReminder>> getAllReminders() {
        return ResponseEntity.ok(maintenanceService.getAllReminders());
    }

    @PostMapping("/reminders")
    public ResponseEntity<MaintenanceReminder> createReminder(@RequestBody MaintenanceReminder reminder) {
        return ResponseEntity.ok(maintenanceService.createReminder(reminder));
    }

    @PutMapping("/reminders/{id}")
    public ResponseEntity<MaintenanceReminder> updateReminder(@PathVariable Long id, @RequestBody MaintenanceReminder reminder) {
        return ResponseEntity.ok(maintenanceService.updateReminder(id, reminder));
    }

    @DeleteMapping("/reminders/{id}")
    public ResponseEntity<?> deleteReminder(@PathVariable Long id) {
        maintenanceService.deleteReminder(id);
        return ResponseEntity.ok(Map.of("message", "Reminder deleted successfully", "id", id));
    }

    @GetMapping("/logs")
    public ResponseEntity<List<MaintenanceLog>> getAllLogs() {
        return ResponseEntity.ok(maintenanceService.getAllLogs());
    }

    @PostMapping("/logs")
    public ResponseEntity<MaintenanceLog> createLog(@RequestBody MaintenanceLog log) {
        return ResponseEntity.ok(maintenanceService.createLog(log));
    }

    @DeleteMapping("/logs/{id}")
    public ResponseEntity<?> deleteLog(@PathVariable Long id) {
        maintenanceService.deleteLog(id);
        return ResponseEntity.ok(Map.of("message", "Log deleted successfully", "id", id));
    }
}
