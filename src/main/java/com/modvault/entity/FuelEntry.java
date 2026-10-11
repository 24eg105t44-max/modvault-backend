package com.modvault.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "fuel_entries")
public class FuelEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime entryDate;

    @Column(nullable = false)
    private BigDecimal cost;

    private BigDecimal liters;
    private BigDecimal pricePerLiter;
    private BigDecimal fuelEconomyKmPerL;
    private Integer odometerKm;

    @com.fasterxml.jackson.annotation.JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    public FuelEntry() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getEntryDate() { return entryDate; }
    public void setEntryDate(LocalDateTime entryDate) { this.entryDate = entryDate; }

    public BigDecimal getCost() { return cost; }
    public void setCost(BigDecimal cost) { this.cost = cost; }

    public BigDecimal getLiters() { return liters; }
    public void setLiters(BigDecimal liters) { this.liters = liters; }

    public BigDecimal getPricePerLiter() { return pricePerLiter; }
    public void setPricePerLiter(BigDecimal pricePerLiter) { this.pricePerLiter = pricePerLiter; }

    public BigDecimal getFuelEconomyKmPerL() { return fuelEconomyKmPerL; }
    public void setFuelEconomyKmPerL(BigDecimal fuelEconomyKmPerL) { this.fuelEconomyKmPerL = fuelEconomyKmPerL; }

    public Integer getOdometerKm() { return odometerKm; }
    public void setOdometerKm(Integer odometerKm) { this.odometerKm = odometerKm; }

    public Vehicle getVehicle() { return vehicle; }
    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }
}
