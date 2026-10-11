package com.modvault.dto;

import com.modvault.entity.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class DashboardSummaryDTO {
    private String greeting;
    private String userFullName;
    private String dateString;

    // 4 Top Cards
    private Integer totalVehicles;
    private String totalVehiclesNote;
    private Integer pendingMaintenanceCount;
    private Integer overdueCount;
    private BigDecimal monthlyFuelCost;
    private String fuelCostTrend;
    private BigDecimal totalExpensesMtd;
    private String expensesTrend;

    // Middle & Bottom sections
    private List<Vehicle> vehicles;
    private List<MaintenanceReminder> upcomingMaintenance;
    private List<RecentActivity> recentActivities;
    private List<Modification> recentModifications;
    private BuildPlan activeBuildPlan;
    private List<CommunityPost> communityPosts;

    // Chart analytics data
    private Map<String, Object> fuelExpenseChart;
    private Map<String, Object> expenseBreakdown;

    public DashboardSummaryDTO() {}

    public String getGreeting() { return greeting; }
    public void setGreeting(String greeting) { this.greeting = greeting; }

    public String getUserFullName() { return userFullName; }
    public void setUserFullName(String userFullName) { this.userFullName = userFullName; }

    public String getDateString() { return dateString; }
    public void setDateString(String dateString) { this.dateString = dateString; }

    public Integer getTotalVehicles() { return totalVehicles; }
    public void setTotalVehicles(Integer totalVehicles) { this.totalVehicles = totalVehicles; }

    public String getTotalVehiclesNote() { return totalVehiclesNote; }
    public void setTotalVehiclesNote(String totalVehiclesNote) { this.totalVehiclesNote = totalVehiclesNote; }

    public Integer getPendingMaintenanceCount() { return pendingMaintenanceCount; }
    public void setPendingMaintenanceCount(Integer pendingMaintenanceCount) { this.pendingMaintenanceCount = pendingMaintenanceCount; }

    public Integer getOverdueCount() { return overdueCount; }
    public void setOverdueCount(Integer overdueCount) { this.overdueCount = overdueCount; }

    public BigDecimal getMonthlyFuelCost() { return monthlyFuelCost; }
    public void setMonthlyFuelCost(BigDecimal monthlyFuelCost) { this.monthlyFuelCost = monthlyFuelCost; }

    public String getFuelCostTrend() { return fuelCostTrend; }
    public void setFuelCostTrend(String fuelCostTrend) { this.fuelCostTrend = fuelCostTrend; }

    public BigDecimal getTotalExpensesMtd() { return totalExpensesMtd; }
    public void setTotalExpensesMtd(BigDecimal totalExpensesMtd) { this.totalExpensesMtd = totalExpensesMtd; }

    public String getExpensesTrend() { return expensesTrend; }
    public void setExpensesTrend(String expensesTrend) { this.expensesTrend = expensesTrend; }

    public List<Vehicle> getVehicles() { return vehicles; }
    public void setVehicles(List<Vehicle> vehicles) { this.vehicles = vehicles; }

    public List<MaintenanceReminder> getUpcomingMaintenance() { return upcomingMaintenance; }
    public void setUpcomingMaintenance(List<MaintenanceReminder> upcomingMaintenance) { this.upcomingMaintenance = upcomingMaintenance; }

    public List<RecentActivity> getRecentActivities() { return recentActivities; }
    public void setRecentActivities(List<RecentActivity> recentActivities) { this.recentActivities = recentActivities; }

    public List<Modification> getRecentModifications() { return recentModifications; }
    public void setRecentModifications(List<Modification> recentModifications) { this.recentModifications = recentModifications; }

    public BuildPlan getActiveBuildPlan() { return activeBuildPlan; }
    public void setActiveBuildPlan(BuildPlan activeBuildPlan) { this.activeBuildPlan = activeBuildPlan; }

    public List<CommunityPost> getCommunityPosts() { return communityPosts; }
    public void setCommunityPosts(List<CommunityPost> communityPosts) { this.communityPosts = communityPosts; }

    public Map<String, Object> getFuelExpenseChart() { return fuelExpenseChart; }
    public void setFuelExpenseChart(Map<String, Object> fuelExpenseChart) { this.fuelExpenseChart = fuelExpenseChart; }

    public Map<String, Object> getExpenseBreakdown() { return expenseBreakdown; }
    public void setExpenseBreakdown(Map<String, Object> expenseBreakdown) { this.expenseBreakdown = expenseBreakdown; }
}
