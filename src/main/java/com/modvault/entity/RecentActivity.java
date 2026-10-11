package com.modvault.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "recent_activities")
public class RecentActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String vehicleOrCategory;

    @Column(name = "activity_timestamp")
    private LocalDateTime timestamp;

    private BigDecimal amount;

    @Column(name = "activity_type")
    private String type; // fuel, modification, maintenance, expense

    private String iconColor; // green, blue, amber, red

    public RecentActivity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getVehicleOrCategory() { return vehicleOrCategory; }
    public void setVehicleOrCategory(String vehicleOrCategory) { this.vehicleOrCategory = vehicleOrCategory; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getIconColor() { return iconColor; }
    public void setIconColor(String iconColor) { this.iconColor = iconColor; }
}
