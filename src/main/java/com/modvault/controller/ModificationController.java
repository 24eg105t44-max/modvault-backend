package com.modvault.controller;

import com.modvault.entity.Modification;
import com.modvault.service.ModificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/modifications")
public class ModificationController {
    private final ModificationService modificationService;

    public ModificationController(ModificationService modificationService) {
        this.modificationService = modificationService;
    }

    @GetMapping
    public ResponseEntity<List<Modification>> getAllModifications() {
        return ResponseEntity.ok(modificationService.getAllModifications());
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<Modification>> getByVehicle(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(modificationService.getModificationsByVehicle(vehicleId));
    }

    @PostMapping
    public ResponseEntity<Modification> createModification(@RequestBody Modification modification) {
        return ResponseEntity.ok(modificationService.createModification(modification));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Modification> updateModification(@PathVariable Long id, @RequestBody Modification modification) {
        return ResponseEntity.ok(modificationService.updateModification(id, modification));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteModification(@PathVariable Long id) {
        modificationService.deleteModification(id);
        return ResponseEntity.ok(Map.of("message", "Modification deleted successfully", "id", id));
    }
}
