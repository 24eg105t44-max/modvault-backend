package com.modvault.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class CostCalculatorDTO {
    public static class Request {
        private String vehicleType;
        private Integer monthlyKm;
        private BigDecimal fuelPrice;
        private Double mileageKmPerL;
        private BigDecimal insuranceYearly;
        private BigDecimal plannedModificationsCost;
        private Integer yearsOfOwnership;

        public Request() {}

        public String getVehicleType() { return vehicleType; }
        public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

        public Integer getMonthlyKm() { return monthlyKm; }
        public void setMonthlyKm(Integer monthlyKm) { this.monthlyKm = monthlyKm; }

        public BigDecimal getFuelPrice() { return fuelPrice; }
        public void setFuelPrice(BigDecimal fuelPrice) { this.fuelPrice = fuelPrice; }

        public Double getMileageKmPerL() { return mileageKmPerL; }
        public void setMileageKmPerL(Double mileageKmPerL) { this.mileageKmPerL = mileageKmPerL; }

        public BigDecimal getInsuranceYearly() { return insuranceYearly; }
        public void setInsuranceYearly(BigDecimal insuranceYearly) { this.insuranceYearly = insuranceYearly; }

        public BigDecimal getPlannedModificationsCost() { return plannedModificationsCost; }
        public void setPlannedModificationsCost(BigDecimal plannedModificationsCost) { this.plannedModificationsCost = plannedModificationsCost; }

        public Integer getYearsOfOwnership() { return yearsOfOwnership; }
        public void setYearsOfOwnership(Integer yearsOfOwnership) { this.yearsOfOwnership = yearsOfOwnership; }
    }

    public static class Response {
        private BigDecimal monthlyFuelCost;
        private BigDecimal monthlyMaintenanceEstimate;
        private BigDecimal totalMonthlyRunningCost;
        private BigDecimal costPerKm;
        private BigDecimal annualTotalCost;
        private BigDecimal totalCostOverOwnership;
        private Map<String, BigDecimal> categoryBreakdown;

        public Response() {}

        public BigDecimal getMonthlyFuelCost() { return monthlyFuelCost; }
        public void setMonthlyFuelCost(BigDecimal monthlyFuelCost) { this.monthlyFuelCost = monthlyFuelCost; }

        public BigDecimal getMonthlyMaintenanceEstimate() { return monthlyMaintenanceEstimate; }
        public void setMonthlyMaintenanceEstimate(BigDecimal monthlyMaintenanceEstimate) { this.monthlyMaintenanceEstimate = monthlyMaintenanceEstimate; }

        public BigDecimal getTotalMonthlyRunningCost() { return totalMonthlyRunningCost; }
        public void setTotalMonthlyRunningCost(BigDecimal totalMonthlyRunningCost) { this.totalMonthlyRunningCost = totalMonthlyRunningCost; }

        public BigDecimal getCostPerKm() { return costPerKm; }
        public void setCostPerKm(BigDecimal costPerKm) { this.costPerKm = costPerKm; }

        public BigDecimal getAnnualTotalCost() { return annualTotalCost; }
        public void setAnnualTotalCost(BigDecimal annualTotalCost) { this.annualTotalCost = annualTotalCost; }

        public BigDecimal getTotalCostOverOwnership() { return totalCostOverOwnership; }
        public void setTotalCostOverOwnership(BigDecimal totalCostOverOwnership) { this.totalCostOverOwnership = totalCostOverOwnership; }

        public Map<String, BigDecimal> getCategoryBreakdown() { return categoryBreakdown; }
        public void setCategoryBreakdown(Map<String, BigDecimal> categoryBreakdown) { this.categoryBreakdown = categoryBreakdown; }
    }
}
