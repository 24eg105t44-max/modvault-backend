package com.modvault.service;

import com.modvault.entity.Vehicle;
import com.modvault.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAllByOrderByIsPrimaryDescIdAsc();
    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + id));
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        if (Boolean.TRUE.equals(vehicle.getIsPrimary())) {
            unsetOtherPrimaries();
        }
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(Long id, Vehicle updated) {
        Vehicle existing = getVehicleById(id);
        existing.setMake(updated.getMake());
        existing.setModel(updated.getModel());
        existing.setYear(updated.getYear());
        existing.setTrim(updated.getTrim());
        existing.setRegistrationNumber(updated.getRegistrationNumber());
        existing.setFuelType(updated.getFuelType());
        existing.setEngineCapacity(updated.getEngineCapacity());
        existing.setOdometerKm(updated.getOdometerKm());
        existing.setTransmission(updated.getTransmission());
        existing.setStatusBadge(updated.getStatusBadge());
        existing.setImageUrl(updated.getImageUrl());
        existing.setNotes(updated.getNotes());

        if (Boolean.TRUE.equals(updated.getIsPrimary()) && !Boolean.TRUE.equals(existing.getIsPrimary())) {
            unsetOtherPrimaries();
            existing.setIsPrimary(true);
        }

        return vehicleRepository.save(existing);
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    private void unsetOtherPrimaries() {
        List<Vehicle> all = vehicleRepository.findAll();
        for (Vehicle v : all) {
            if (Boolean.TRUE.equals(v.getIsPrimary())) {
                v.setIsPrimary(false);
                v.setStatusBadge("Secondary");
                vehicleRepository.save(v);
            }
        }
    }
}
