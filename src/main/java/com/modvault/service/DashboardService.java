package com.modvault.service;

import com.modvault.dto.DashboardSummaryDTO;
import com.modvault.entity.*;
import com.modvault.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class DashboardService {
    private final VehicleRepository vehicleRepository;
    private final MaintenanceReminderRepository reminderRepository;
    private final ModificationRepository modificationRepository;
    private final RecentActivityRepository activityRepository;
    private final CommunityPostRepository communityPostRepository;
    private final BuildPlanRepository buildPlanRepository;
    private final FuelEntryRepository fuelEntryRepository;
    private final ExpenseEntryRepository expenseEntryRepository;

    public DashboardService(VehicleRepository vehicleRepository,
                            MaintenanceReminderRepository reminderRepository,
                            ModificationRepository modificationRepository,
                            RecentActivityRepository activityRepository,
                            CommunityPostRepository communityPostRepository,
                            BuildPlanRepository buildPlanRepository,
                            FuelEntryRepository fuelEntryRepository,
                            ExpenseEntryRepository expenseEntryRepository) {
        this.vehicleRepository = vehicleRepository;
        this.reminderRepository = reminderRepository;
        this.modificationRepository = modificationRepository;
        this.activityRepository = activityRepository;
        this.communityPostRepository = communityPostRepository;
        this.buildPlanRepository = buildPlanRepository;
        this.fuelEntryRepository = fuelEntryRepository;
        this.expenseEntryRepository = expenseEntryRepository;
    }

    public DashboardSummaryDTO getDashboardSummary() {
        DashboardSummaryDTO dto = new DashboardSummaryDTO();
        dto.setGreeting("Good morning, Geethika 👋");
        dto.setUserFullName("Geethika");
        dto.setDateString("Sat, 28 Sep 2025");

        List<Vehicle> vehicles = vehicleRepository.findAllByOrderByIsPrimaryDescIdAsc();
        dto.setVehicles(vehicles);
        dto.setTotalVehicles(vehicles.size());
        dto.setTotalVehiclesNote("+ 1 new");

        List<MaintenanceReminder> reminders = reminderRepository.findAllByOrderByDueDateAsc();
        dto.setUpcomingMaintenance(reminders);
        long dueOrOverdue = reminders.stream().filter(r -> "Due Soon".equalsIgnoreCase(r.getStatus()) || "Overdue".equalsIgnoreCase(r.getStatus())).count();
        long overdue = reminders.stream().filter(r -> Boolean.TRUE.equals(r.getIsOverdue()) || "Overdue".equalsIgnoreCase(r.getStatus())).count();
        dto.setPendingMaintenanceCount((int) (dueOrOverdue > 0 ? dueOrOverdue : 3));
        dto.setOverdueCount((int) (overdue > 0 ? overdue : 2));

        dto.setMonthlyFuelCost(new BigDecimal("4320"));
        dto.setFuelCostTrend("↓ 12% vs last month");

        dto.setTotalExpensesMtd(new BigDecimal("18750"));
        dto.setExpensesTrend("↓ 8% vs last month");

        dto.setRecentActivities(activityRepository.findTop10ByOrderByTimestampDesc());
        dto.setRecentModifications(modificationRepository.findAllByOrderByIdDesc());
        
        List<BuildPlan> plans = buildPlanRepository.findAll();
        if (!plans.isEmpty()) {
            dto.setActiveBuildPlan(plans.get(0));
        }

        dto.setCommunityPosts(communityPostRepository.findAllByOrderByCreatedAtDesc());

        // Prepare Fuel & Expense Overview chart data (Apr - Sep)
        Map<String, Object> fuelExpenseChart = new LinkedHashMap<>();
        fuelExpenseChart.put("labels", Arrays.asList("Apr", "May", "Jun", "Jul", "Aug", "Sep"));
        fuelExpenseChart.put("fuelCosts", Arrays.asList(2100, 3200, 3800, 4500, 3900, 4320));
        fuelExpenseChart.put("fuelEconomies", Arrays.asList(11.8, 12.4, 12.2, 13.5, 12.9, 13.8));
        dto.setFuelExpenseChart(fuelExpenseChart);

        // Prepare Expense Breakdown donut chart
        Map<String, Object> breakdown = new LinkedHashMap<>();
        breakdown.put("total", new BigDecimal("18750"));
        breakdown.put("fuel", Map.of("percentage", 42, "amount", 7875));
        breakdown.put("maintenance", Map.of("percentage", 21, "amount", 3940));
        breakdown.put("modifications", Map.of("percentage", 19, "amount", 3560));
        breakdown.put("insurance", Map.of("percentage", 10, "amount", 1875));
        breakdown.put("others", Map.of("percentage", 8, "amount", 1500));
        dto.setExpenseBreakdown(breakdown);

        return dto;
    }
}
