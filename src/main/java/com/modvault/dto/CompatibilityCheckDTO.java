package com.modvault.dto;

import java.util.List;

public class CompatibilityCheckDTO {
    public static class Request {
        private String make;
        private String model;
        private Integer year;
        private String partCategory;
        private String partName;

        public Request() {}

        public String getMake() { return make; }
        public void setMake(String make) { this.make = make; }

        public String getModel() { return model; }
        public void setModel(String model) { this.model = model; }

        public Integer getYear() { return year; }
        public void setYear(Integer year) { this.year = year; }

        public String getPartCategory() { return partCategory; }
        public void setPartCategory(String partCategory) { this.partCategory = partCategory; }

        public String getPartName() { return partName; }
        public void setPartName(String partName) { this.partName = partName; }
    }

    public static class Response {
        private boolean compatible;
        private String status; // Direct Fit, Needs Modification, Incompatible
        private String fitmentScore; // 98%, 70%, 0%
        private String notes;
        private List<String> requiredAccessories;
        private List<String> alternativeParts;

        public Response() {}

        public boolean isCompatible() { return compatible; }
        public void setCompatible(boolean compatible) { this.compatible = compatible; }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }

        public String getFitmentScore() { return fitmentScore; }
        public void setFitmentScore(String fitmentScore) { this.fitmentScore = fitmentScore; }

        public String getNotes() { return notes; }
        public void setNotes(String notes) { this.notes = notes; }

        public List<String> getRequiredAccessories() { return requiredAccessories; }
        public void setRequiredAccessories(List<String> requiredAccessories) { this.requiredAccessories = requiredAccessories; }

        public List<String> getAlternativeParts() { return alternativeParts; }
        public void setAlternativeParts(List<String> alternativeParts) { this.alternativeParts = alternativeParts; }
    }
}
