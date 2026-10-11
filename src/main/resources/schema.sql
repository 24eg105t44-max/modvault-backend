-- ModVault MySQL Database Schema
CREATE DATABASE IF NOT EXISTS modvault CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE modvault;

-- Drop existing tables if needed
DROP TABLE IF EXISTS community_posts;
DROP TABLE IF EXISTS recent_activities;
DROP TABLE IF EXISTS expense_entries;
DROP TABLE IF EXISTS fuel_entries;
DROP TABLE IF EXISTS maintenance_logs;
DROP TABLE IF EXISTS maintenance_reminders;
DROP TABLE IF EXISTS modifications;
DROP TABLE IF EXISTS build_plans;
DROP TABLE IF EXISTS vehicles;
DROP TABLE IF EXISTS users;

-- 1. Users Table
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    avatar_letter VARCHAR(5),
    role VARCHAR(50) DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Vehicles Table
CREATE TABLE vehicles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    make VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    year INT NOT NULL,
    trim VARCHAR(50),
    registration_number VARCHAR(50) NOT NULL UNIQUE,
    fuel_type VARCHAR(30),
    engine_capacity VARCHAR(30),
    odometer_km INT,
    transmission VARCHAR(30),
    is_primary BOOLEAN DEFAULT FALSE,
    status_badge VARCHAR(30),
    image_url VARCHAR(500),
    notes TEXT,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
);

-- 3. Modifications Table
CREATE TABLE modifications (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    part_name VARCHAR(150) NOT NULL,
    category VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    cost DECIMAL(12, 2) NOT NULL,
    installed_date DATE,
    notes TEXT,
    vehicle_id BIGINT,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(id) ON DELETE CASCADE
);

-- 4. Maintenance Reminders Table
CREATE TABLE maintenance_reminders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    interval_km INT,
    due_date DATE,
    status VARCHAR(50),
    icon_type VARCHAR(30),
    is_overdue BOOLEAN DEFAULT FALSE,
    vehicle_id BIGINT,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(id) ON DELETE CASCADE
);

-- 5. Maintenance Logs Table
CREATE TABLE maintenance_logs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    service_date DATE,
    cost DECIMAL(12, 2),
    odometer_km INT,
    notes TEXT,
    service_center VARCHAR(150),
    vehicle_id BIGINT,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(id) ON DELETE CASCADE
);

-- 6. Fuel Entries Table
CREATE TABLE fuel_entries (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    entry_date TIMESTAMP NOT NULL,
    cost DECIMAL(10, 2) NOT NULL,
    liters DECIMAL(8, 2),
    price_per_liter DECIMAL(8, 2),
    fuel_economy_km_per_l DECIMAL(6, 2),
    odometer_km INT,
    vehicle_id BIGINT,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(id) ON DELETE CASCADE
);

-- 7. Expense Entries Table
CREATE TABLE expense_entries (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    category VARCHAR(50) NOT NULL,
    amount DECIMAL(12, 2) NOT NULL,
    expense_date TIMESTAMP NOT NULL,
    notes TEXT,
    vehicle_id BIGINT,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(id) ON DELETE CASCADE
);

-- 8. Build Plans Table
CREATE TABLE build_plans (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    estimated_cost DECIMAL(14, 2),
    progress_percentage INT,
    status VARCHAR(50),
    image_url VARCHAR(500),
    tags VARCHAR(255),
    vehicle_id BIGINT,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(id) ON DELETE CASCADE
);

-- 9. Recent Activities Table
CREATE TABLE recent_activities (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    vehicle_or_category VARCHAR(150),
    timestamp TIMESTAMP,
    amount DECIMAL(12, 2),
    type VARCHAR(50),
    icon_color VARCHAR(30)
);

-- 10. Community Posts Table
CREATE TABLE community_posts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    author_name VARCHAR(100) NOT NULL,
    author_car VARCHAR(100),
    time_ago VARCHAR(50),
    content TEXT NOT NULL,
    image_url VARCHAR(500),
    additional_image_url VARCHAR(500),
    likes_count INT DEFAULT 0,
    comments_count INT DEFAULT 0,
    is_bookmarked BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
