package com.modvault.service;

import com.modvault.entity.Modification;
import com.modvault.repository.ModificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModificationService {
    private final ModificationRepository modificationRepository;

    public ModificationService(ModificationRepository modificationRepository) {
        this.modificationRepository = modificationRepository;
    }

    public List<Modification> getAllModifications() {
        return modificationRepository.findAllByOrderByIdDesc();
    }

    public List<Modification> getModificationsByVehicle(Long vehicleId) {
        return modificationRepository.findByVehicleId(vehicleId);
    }

    public Modification createModification(Modification modification) {
        return modificationRepository.save(modification);
    }

    public Modification updateModification(Long id, Modification updated) {
        Modification existing = modificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Modification not found: " + id));
        existing.setPartName(updated.getPartName());
        existing.setCategory(updated.getCategory());
        existing.setStatus(updated.getStatus());
        existing.setCost(updated.getCost());
        existing.setInstalledDate(updated.getInstalledDate());
        existing.setNotes(updated.getNotes());
        return modificationRepository.save(existing);
    }

    public void deleteModification(Long id) {
        modificationRepository.deleteById(id);
    }
}
