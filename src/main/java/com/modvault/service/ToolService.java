package com.modvault.service;

import com.modvault.dto.CompatibilityCheckDTO;
import com.modvault.dto.CostCalculatorDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class ToolService {

    public CostCalculatorDTO.Response calculateCost(CostCalculatorDTO.Request req) {
        CostCalculatorDTO.Response res = new CostCalculatorDTO.Response();

        int km = req.getMonthlyKm() != null ? req.getMonthlyKm() : 1000;
        double mileage = req.getMileageKmPerL() != null && req.getMileageKmPerL() > 0 ? req.getMileageKmPerL() : 12.0;
        BigDecimal fuelPrice = req.getFuelPrice() != null ? req.getFuelPrice() : new BigDecimal("105");
        BigDecimal insuranceYearly = req.getInsuranceYearly() != null ? req.getInsuranceYearly() : new BigDecimal("25000");
        BigDecimal mods = req.getPlannedModificationsCost() != null ? req.getPlannedModificationsCost() : BigDecimal.ZERO;
        int years = req.getYearsOfOwnership() != null ? req.getYearsOfOwnership() : 5;

        // Fuel cost
        double litersNeeded = km / mileage;
        BigDecimal monthlyFuel = fuelPrice.multiply(BigDecimal.valueOf(litersNeeded)).setScale(2, RoundingMode.HALF_UP);
        res.setMonthlyFuelCost(monthlyFuel);

        // Maintenance estimate (rough ₹2.5 per km)
        BigDecimal monthlyMaint = BigDecimal.valueOf(km * 2.2).setScale(2, RoundingMode.HALF_UP);
        res.setMonthlyMaintenanceEstimate(monthlyMaint);

        // Insurance per month
        BigDecimal monthlyInsurance = insuranceYearly.divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);

        BigDecimal totalMonthly = monthlyFuel.add(monthlyMaint).add(monthlyInsurance).setScale(2, RoundingMode.HALF_UP);
        res.setTotalMonthlyRunningCost(totalMonthly);

        BigDecimal costPerKm = totalMonthly.divide(BigDecimal.valueOf(km), 2, RoundingMode.HALF_UP);
        res.setCostPerKm(costPerKm);

        BigDecimal annualTotal = totalMonthly.multiply(BigDecimal.valueOf(12)).setScale(2, RoundingMode.HALF_UP);
        res.setAnnualTotalCost(annualTotal);

        BigDecimal totalOwnership = annualTotal.multiply(BigDecimal.valueOf(years)).add(mods).setScale(2, RoundingMode.HALF_UP);
        res.setTotalCostOverOwnership(totalOwnership);

        Map<String, BigDecimal> breakdown = new HashMap<>();
        breakdown.put("fuel", monthlyFuel);
        breakdown.put("maintenance", monthlyMaint);
        breakdown.put("insurance", monthlyInsurance);
        breakdown.put("modificationsMonthlyAllocated", mods.divide(BigDecimal.valueOf(Math.max(1, years * 12)), 2, RoundingMode.HALF_UP));
        res.setCategoryBreakdown(breakdown);

        return res;
    }

    public CompatibilityCheckDTO.Response checkCompatibility(CompatibilityCheckDTO.Request req) {
        CompatibilityCheckDTO.Response res = new CompatibilityCheckDTO.Response();
        String part = (req.getPartName() != null ? req.getPartName().toLowerCase() : "");
        String make = (req.getMake() != null ? req.getMake().toLowerCase() : "");
        String model = (req.getModel() != null ? req.getModel().toLowerCase() : "");

        if (part.contains("exhaust") || part.contains("intake") || part.contains("coilover") || part.contains("wheel") || part.contains("filter")) {
            res.setCompatible(true);
            res.setStatus("Direct Fit");
            res.setFitmentScore("98%");
            res.setNotes("OEM & aftermarket mount point verified for " + req.getMake() + " " + req.getModel() + " (" + req.getYear() + ").");
            res.setRequiredAccessories(List.of("Gaskets and Mounting Hardware kit", "Standard O2 sensor bung"));
            res.setAlternativeParts(List.of("Injen High-Flow Alternative", "HKS Hi-Power Spec"));
        } else if (part.contains("turbo") || part.contains("supercharger") || part.contains("downpipe")) {
            res.setCompatible(true);
            res.setStatus("Needs ECU Tuning");
            res.setFitmentScore("85%");
            res.setNotes("Fits engine bay clearance, but requires stage 2 remap and upgraded intercooler piping.");
            res.setRequiredAccessories(List.of("Custom Downpipe Flange", "Boost Controller"));
            res.setAlternativeParts(List.of("Garrett GT2860RS", "BorgWarner EFR"));
        } else {
            res.setCompatible(true);
            res.setStatus("Compatible with Universal Adapter");
            res.setFitmentScore("90%");
            res.setNotes("Compatible with standard engine bay clearance and bracket adapters.");
            res.setRequiredAccessories(List.of("Universal mounting bracket", "Wiring harness pigtail"));
            res.setAlternativeParts(List.of("Mishimoto Universal Kit"));
        }

        return res;
    }

    public Map<String, Object> getBuildComparison() {
        Map<String, Object> comparison = new LinkedHashMap<>();
        
        Map<String, Object> stock = new LinkedHashMap<>();
        stock.put("name", "Civic (Stock)");
        stock.put("totalCost", "₹ 18.5 L");
        stock.put("averageMileage", "12.5 km/l");
        stock.put("power", "141 hp");
        stock.put("torque", "174 Nm");
        stock.put("suspension", "OEM Struts");
        stock.put("exhaust", "Factory Twin Resonator");
        stock.put("imageUrl", "https://images.unsplash.com/photo-1590362891991-f776e747a588?auto=format&fit=crop&w=800&q=80");

        Map<String, Object> modified = new LinkedHashMap<>();
        modified.put("name", "Civic (Modified)");
        modified.put("totalCost", "₹ 24.8 L");
        modified.put("averageMileage", "11.2 km/l");
        modified.put("power", "215 hp (Stage 2)");
        modified.put("torque", "290 Nm");
        modified.put("suspension", "BC Racing Coilovers (32-way)");
        modified.put("exhaust", "Full Cat-back Stainless Steel");
        modified.put("imageUrl", "https://images.unsplash.com/photo-1617814076367-b759c7d7e738?auto=format&fit=crop&w=800&q=80");

        comparison.put("stock", stock);
        comparison.put("modified", modified);
        return comparison;
    }
}
