USE modvault;

-- 1. Insert Geethika
INSERT INTO users (id, email, password, full_name, avatar_letter, role) VALUES 
(1, 'geethika@gmail.com', '$2a$10$7ZkK.p.fQ8t3sO3gUoW1iOHpWk3Wv.LzF2d3K9eMv1tUvG9Qh2m6K', 'Geethika', 'G', 'USER')
ON DUPLICATE KEY UPDATE full_name=VALUES(full_name);

-- 2. Insert Vehicles
INSERT INTO vehicles (id, make, model, year, trim, registration_number, fuel_type, engine_capacity, odometer_km, transmission, is_primary, status_badge, image_url, notes, user_id) VALUES
(1, 'Honda', 'Civic', 2019, 'Touring', 'TS 09 AB 1234', 'Petrol', '1.8 L', 42350, 'Manual', TRUE, 'Primary', '/images/civic-2019.jpg', 'Primary daily driver and track project.', 1),
(2, 'BMW', '330i', 2022, 'M Sport', 'KA 01 CD 5678', 'Petrol', '2.0 L', 18420, 'Automatic', FALSE, 'Secondary', '/images/bmw-330i.jpg', 'Weekend cruiser and tourer.', 1)
ON DUPLICATE KEY UPDATE make=VALUES(make);

-- 3. Maintenance Reminders
INSERT INTO maintenance_reminders (id, title, interval_km, due_date, status, icon_type, is_overdue, vehicle_id) VALUES
(1, 'Engine Oil Change', 4500, '2025-10-15', 'Due Soon', 'oil', TRUE, 1),
(2, 'Tyre Rotation', 8000, '2025-11-10', 'Due Soon', 'tyre', TRUE, 1),
(3, 'Brake Inspection', 12000, '2025-12-28', 'Due Soon', 'brake', FALSE, 1),
(4, 'Air Filter Replacement', 20000, '2026-01-15', 'Upcoming', 'filter', FALSE, 1)
ON DUPLICATE KEY UPDATE title=VALUES(title);

-- 4. Modifications
INSERT INTO modifications (id, part_name, category, status, cost, vehicle_id) VALUES
(1, 'Cold Air Intake', 'Performance', 'Installed', 25000.00, 1),
(2, 'BC Racing Coilovers', 'Suspension', 'Installed', 85000.00, 1),
(3, 'Rays 57CR Wheels', 'Wheels & Tyres', 'Planned', 42000.00, 1),
(4, 'Cat-back Exhaust', 'Performance', 'Planned', 70000.00, 1)
ON DUPLICATE KEY UPDATE part_name=VALUES(part_name);

-- 5. Build Plans
INSERT INTO build_plans (id, title, estimated_cost, progress_percentage, status, image_url, tags, vehicle_id) VALUES
(1, 'Civic Performance Build', 250000.00, 68, 'In Progress', '/images/build-civic.jpg', 'Engine,Suspension,Wheels & Tyres,Exhaust,Exterior', 1)
ON DUPLICATE KEY UPDATE title=VALUES(title);

-- 6. Recent Activities
INSERT INTO recent_activities (id, title, vehicle_or_category, timestamp, amount, type, icon_color) VALUES
(1, 'Fuel entry added', 'Honda Civic • 18 Sep 2025, 08:45 PM', '2025-09-18 20:45:00', 2500.00, 'fuel', 'blue'),
(2, 'Modification updated', 'BMW 330i • 16 Sep 2025, 02:12 PM', '2025-09-16 14:12:00', 18750.00, 'modification', 'green'),
(3, 'Service completed', 'Honda Civic • 12 Sep 2025, 04:21 PM', '2025-09-12 16:21:00', 0.00, 'maintenance', 'green'),
(4, 'Expense added', 'Car Accessories • 10 Sep 2025, 02:10 PM', '2025-09-10 14:10:00', 3200.00, 'expense', 'amber')
ON DUPLICATE KEY UPDATE title=VALUES(title);

-- 7. Community Posts
INSERT INTO community_posts (id, author_name, author_car, time_ago, content, image_url, additional_image_url, likes_count, comments_count, is_bookmarked) VALUES
(1, 'Rohan Mehta', 'BMW 330i', '2 days ago', 'Just got my new M Performance exhaust. The sound is unreal! 🔥', '/images/community-bmw-rear.jpg', '/images/community-bmw-exhaust.jpg', 124, 18, FALSE),
(2, 'Sneha Iyer', 'Honda Civic', '3 days ago', 'After 10k km, still loving the reliability of my Civic. What a machine! 🚗', NULL, NULL, 96, 12, FALSE)
ON DUPLICATE KEY UPDATE author_name=VALUES(author_name);
