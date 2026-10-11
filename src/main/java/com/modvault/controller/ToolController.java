package com.modvault.controller;

import com.modvault.dto.CompatibilityCheckDTO;
import com.modvault.dto.CostCalculatorDTO;
import com.modvault.entity.BuildPlan;
import com.modvault.repository.BuildPlanRepository;
import com.modvault.service.ToolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tools")
public class ToolController {
    private final ToolService toolService;
    private final BuildPlanRepository buildPlanRepository;

    public ToolController(ToolService toolService, BuildPlanRepository buildPlanRepository) {
        this.toolService = toolService;
        this.buildPlanRepository = buildPlanRepository;
    }

    @PostMapping("/cost-calculator")
    public ResponseEntity<CostCalculatorDTO.Response> calculateCost(@RequestBody CostCalculatorDTO.Request request) {
        return ResponseEntity.ok(toolService.calculateCost(request));
    }

    @PostMapping("/compatibility-check")
    public ResponseEntity<CompatibilityCheckDTO.Response> checkCompatibility(@RequestBody CompatibilityCheckDTO.Request request) {
        return ResponseEntity.ok(toolService.checkCompatibility(request));
    }

    @GetMapping("/build-comparison")
    public ResponseEntity<Map<String, Object>> getBuildComparison() {
        return ResponseEntity.ok(toolService.getBuildComparison());
    }

    @GetMapping("/build-plans")
    public ResponseEntity<List<BuildPlan>> getBuildPlans() {
        return ResponseEntity.ok(buildPlanRepository.findAll());
    }

    @PostMapping("/build-plans")
    public ResponseEntity<BuildPlan> createBuildPlan(@RequestBody BuildPlan plan) {
        return ResponseEntity.ok(buildPlanRepository.save(plan));
    }
}
