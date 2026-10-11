package com.modvault.controller;

import com.modvault.dto.DashboardSummaryDTO;
import com.modvault.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardSummaryDTO> getDashboard() {
        return ResponseEntity.ok(dashboardService.getDashboardSummary());
    }

    @GetMapping("/health")
    public ResponseEntity<?> healthCheck() {
        return ResponseEntity.ok(Map.of(
                "status", "UP",
                "service", "ModVault Backend",
                "version", "1.0.0",
                "timestamp", System.currentTimeMillis()
        ));
    }
}
