package com.modvault.service;

import com.modvault.entity.MaintenanceLog;
import com.modvault.entity.MaintenanceReminder;
import com.modvault.repository.MaintenanceLogRepository;
import com.modvault.repository.MaintenanceReminderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceService {
    private final MaintenanceReminderRepository reminderRepository;
    private final MaintenanceLogRepository logRepository;

    public MaintenanceService(MaintenanceReminderRepository reminderRepository, MaintenanceLogRepository logRepository) {
        this.reminderRepository = reminderRepository;
        this.logRepository = logRepository;
    }

    public List<MaintenanceReminder> getAllReminders() {
        return reminderRepository.findAllByOrderByDueDateAsc();
    }

    public MaintenanceReminder createReminder(MaintenanceReminder reminder) {
        return reminderRepository.save(reminder);
    }

    public MaintenanceReminder updateReminder(Long id, MaintenanceReminder updated) {
        MaintenanceReminder existing = reminderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reminder not found: " + id));
        existing.setTitle(updated.getTitle());
        existing.setIntervalKm(updated.getIntervalKm());
        existing.setDueDate(updated.getDueDate());
        existing.setStatus(updated.getStatus());
        existing.setIconType(updated.getIconType());
        existing.setIsOverdue(updated.getIsOverdue());
        return reminderRepository.save(existing);
    }

    public void deleteReminder(Long id) {
        reminderRepository.deleteById(id);
    }

    public List<MaintenanceLog> getAllLogs() {
        return logRepository.findAllByOrderByServiceDateDesc();
    }

    public MaintenanceLog createLog(MaintenanceLog log) {
        return logRepository.save(log);
    }

    public void deleteLog(Long id) {
        logRepository.deleteById(id);
    }
}
