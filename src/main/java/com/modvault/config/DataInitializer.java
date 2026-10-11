package com.modvault.config;

import com.modvault.entity.*;
import com.modvault.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;
    private final MaintenanceReminderRepository reminderRepository;
    private final ModificationRepository modificationRepository;
    private final RecentActivityRepository activityRepository;
    private final CommunityPostRepository postRepository;
    private final BuildPlanRepository buildPlanRepository;
    private final FuelEntryRepository fuelEntryRepository;
    private final ExpenseEntryRepository expenseEntryRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           VehicleRepository vehicleRepository,
                           MaintenanceReminderRepository reminderRepository,
                           ModificationRepository modificationRepository,
                           RecentActivityRepository activityRepository,
                           CommunityPostRepository postRepository,
                           BuildPlanRepository buildPlanRepository,
                           FuelEntryRepository fuelEntryRepository,
                           ExpenseEntryRepository expenseEntryRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
        this.reminderRepository = reminderRepository;
        this.modificationRepository = modificationRepository;
        this.activityRepository = activityRepository;
        this.postRepository = postRepository;
        this.buildPlanRepository = buildPlanRepository;
        this.fuelEntryRepository = fuelEntryRepository;
        this.expenseEntryRepository = expenseEntryRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() > 0) {
            return;
        }

        // 1. Seed User Geethika with Anurag student email (24eg105t34@anurag.edu.in) and alias
        User studentUser = new User();
        studentUser.setFullName("Geethika");
        studentUser.setEmail("24eg105t34@anurag.edu.in");
        studentUser.setPassword(passwordEncoder.encode("password123"));
        studentUser.setAvatarLetter("G");
        studentUser.setRole("USER");
        userRepository.save(studentUser);

        User geethika = new User();
        geethika.setFullName("Geethika");
        geethika.setEmail("geethika@gmail.com");
        geethika.setPassword(passwordEncoder.encode("password123"));
        geethika.setAvatarLetter("G");
        geethika.setRole("USER");
        geethika = userRepository.save(geethika);

        // 2. Seed Vehicles
        Vehicle civic = new Vehicle();
        civic.setMake("Honda");
        civic.setModel("Civic");
        civic.setYear(2019);
        civic.setRegistrationNumber("TS 09 AB 1234");
        civic.setFuelType("Petrol");
        civic.setEngineCapacity("1.8 L");
        civic.setOdometerKm(42350);
        civic.setTransmission("Manual");
        civic.setIsPrimary(true);
        civic.setStatusBadge("Primary");
        civic.setImageUrl("/images/civic-2019.jpg");
        civic.setNotes("Primary daily driver and active track-day build project.");
        civic.setUser(geethika);
        civic = vehicleRepository.save(civic);

        Vehicle bmw = new Vehicle();
        bmw.setMake("BMW");
        bmw.setModel("330i");
        bmw.setYear(2022);
        bmw.setRegistrationNumber("KA 01 CD 5678");
        bmw.setFuelType("Petrol");
        bmw.setEngineCapacity("2.0 L");
        bmw.setOdometerKm(18420);
        bmw.setTransmission("Automatic");
        bmw.setIsPrimary(false);
        bmw.setStatusBadge("Secondary");
        bmw.setImageUrl("/images/bmw-330i.jpg");
        bmw.setNotes("Weekend cruiser and grand tourer.");
        bmw.setUser(geethika);
        bmw = vehicleRepository.save(bmw);

        // 3. Seed Upcoming Maintenance Reminders
        MaintenanceReminder r1 = new MaintenanceReminder();
        r1.setTitle("Engine Oil Change");
        r1.setIntervalKm(4500);
        r1.setDueDate(LocalDate.of(2025, 10, 15));
        r1.setStatus("Due Soon");
        r1.setIconType("oil");
        r1.setIsOverdue(true);
        r1.setVehicle(civic);
        reminderRepository.save(r1);

        MaintenanceReminder r2 = new MaintenanceReminder();
        r2.setTitle("Tyre Rotation");
        r2.setIntervalKm(8000);
        r2.setDueDate(LocalDate.of(2025, 11, 10));
        r2.setStatus("Due Soon");
        r2.setIconType("tyre");
        r2.setIsOverdue(true);
        r2.setVehicle(civic);
        reminderRepository.save(r2);

        MaintenanceReminder r3 = new MaintenanceReminder();
        r3.setTitle("Brake Inspection");
        r3.setIntervalKm(12000);
        r3.setDueDate(LocalDate.of(2025, 12, 28));
        r3.setStatus("Due Soon");
        r3.setIconType("brake");
        r3.setIsOverdue(false);
        r3.setVehicle(civic);
        reminderRepository.save(r3);

        MaintenanceReminder r4 = new MaintenanceReminder();
        r4.setTitle("Air Filter Replacement");
        r4.setIntervalKm(20000);
        r4.setDueDate(LocalDate.of(2026, 1, 15));
        r4.setStatus("Upcoming");
        r4.setIconType("filter");
        r4.setIsOverdue(false);
        r4.setVehicle(civic);
        reminderRepository.save(r4);

        // 4. Seed Recent Modifications
        Modification m1 = new Modification();
        m1.setPartName("Cold Air Intake");
        m1.setCategory("Performance");
        m1.setStatus("Installed");
        m1.setCost(new BigDecimal("25000"));
        m1.setVehicle(civic);
        modificationRepository.save(m1);

        Modification m2 = new Modification();
        m2.setPartName("BC Racing Coilovers");
        m2.setCategory("Suspension");
        m2.setStatus("Installed");
        m2.setCost(new BigDecimal("85000"));
        m2.setVehicle(civic);
        modificationRepository.save(m2);

        Modification m3 = new Modification();
        m3.setPartName("Rays 57CR Wheels");
        m3.setCategory("Wheels & Tyres");
        m3.setStatus("Planned");
        m3.setCost(new BigDecimal("42000"));
        m3.setVehicle(civic);
        modificationRepository.save(m3);

        Modification m4 = new Modification();
        m4.setPartName("Cat-back Exhaust");
        m4.setCategory("Performance");
        m4.setStatus("Planned");
        m4.setCost(new BigDecimal("70000"));
        m4.setVehicle(civic);
        modificationRepository.save(m4);

        // 5. Seed Recent Activities
        RecentActivity a1 = new RecentActivity();
        a1.setTitle("Fuel entry added");
        a1.setVehicleOrCategory("Honda Civic • 18 Sep 2025, 08:45 PM");
        a1.setTimestamp(LocalDateTime.of(2025, 9, 18, 20, 45));
        a1.setAmount(new BigDecimal("2500"));
        a1.setType("fuel");
        a1.setIconColor("blue");
        activityRepository.save(a1);

        RecentActivity a2 = new RecentActivity();
        a2.setTitle("Modification updated");
        a2.setVehicleOrCategory("BMW 330i • 16 Sep 2025, 02:12 PM");
        a2.setTimestamp(LocalDateTime.of(2025, 9, 16, 14, 12));
        a2.setAmount(new BigDecimal("18750"));
        a2.setType("modification");
        a2.setIconColor("green");
        activityRepository.save(a2);

        RecentActivity a3 = new RecentActivity();
        a3.setTitle("Service completed");
        a3.setVehicleOrCategory("Honda Civic • 12 Sep 2025, 04:21 PM");
        a3.setTimestamp(LocalDateTime.of(2025, 9, 12, 16, 21));
        a3.setType("maintenance");
        a3.setIconColor("green");
        activityRepository.save(a3);

        RecentActivity a4 = new RecentActivity();
        a4.setTitle("Expense added");
        a4.setVehicleOrCategory("Car Accessories • 10 Sep 2025, 02:10 PM");
        a4.setTimestamp(LocalDateTime.of(2025, 9, 10, 14, 10));
        a4.setAmount(new BigDecimal("3200"));
        a4.setType("expense");
        a4.setIconColor("amber");
        activityRepository.save(a4);

        // 6. Seed Build Plan
        BuildPlan plan = new BuildPlan();
        plan.setTitle("Civic Performance Build");
        plan.setEstimatedCost(new BigDecimal("250000"));
        plan.setProgressPercentage(68);
        plan.setStatus("In Progress");
        plan.setImageUrl("/images/build-civic.jpg");
        plan.setTags("Engine,Suspension,Wheels & Tyres,Exhaust,Exterior");
        plan.setVehicle(civic);
        buildPlanRepository.save(plan);

        // 7. Seed Community Posts
        CommunityPost p1 = new CommunityPost();
        p1.setAuthorName("Rohan Mehta");
        p1.setAuthorCar("BMW 330i");
        p1.setTimeAgo("2 days ago");
        p1.setContent("Just got my new M Performance exhaust. The sound is unreal! 🔥");
        p1.setImageUrl("/images/community-bmw-rear.jpg");
        p1.setAdditionalImageUrl("/images/community-bmw-exhaust.jpg");
        p1.setLikesCount(124);
        p1.setCommentsCount(18);
        postRepository.save(p1);

        CommunityPost p2 = new CommunityPost();
        p2.setAuthorName("Sneha Iyer");
        p2.setAuthorCar("Honda Civic");
        p2.setTimeAgo("3 days ago");
        p2.setContent("After 10k km, still loving the reliability of my Civic. What a machine! 🚗");
        p2.setLikesCount(96);
        p2.setCommentsCount(12);
        postRepository.save(p2);

        // 8. Seed Sample Expenses
        ExpenseEntry exp1 = new ExpenseEntry();
        exp1.setTitle("Shell V-Power Premium Fuel");
        exp1.setCategory("Fuel");
        exp1.setAmount(new BigDecimal("7875"));
        exp1.setExpenseDate(LocalDateTime.now().minusDays(3));
        exp1.setVehicle(civic);
        expenseEntryRepository.save(exp1);

        ExpenseEntry exp2 = new ExpenseEntry();
        exp2.setTitle("Scheduled Service & Consumables");
        exp2.setCategory("Maintenance");
        exp2.setAmount(new BigDecimal("3940"));
        exp2.setExpenseDate(LocalDateTime.now().minusDays(10));
        exp2.setVehicle(civic);
        expenseEntryRepository.save(exp2);

        ExpenseEntry exp3 = new ExpenseEntry();
        exp3.setTitle("Suspension Bushing & Strut Upgrades");
        exp3.setCategory("Modifications");
        exp3.setAmount(new BigDecimal("3560"));
        exp3.setExpenseDate(LocalDateTime.now().minusDays(14));
        exp3.setVehicle(civic);
        expenseEntryRepository.save(exp3);

        ExpenseEntry exp4 = new ExpenseEntry();
        exp4.setTitle("Comprehensive Zero-Dep Policy Monthly");
        exp4.setCategory("Insurance");
        exp4.setAmount(new BigDecimal("1875"));
        exp4.setExpenseDate(LocalDateTime.now().minusDays(20));
        exp4.setVehicle(civic);
        expenseEntryRepository.save(exp4);

        ExpenseEntry exp5 = new ExpenseEntry();
        exp5.setTitle("Detailing & Ceramic Coating Top-up");
        exp5.setCategory("Others");
        exp5.setAmount(new BigDecimal("1500"));
        exp5.setExpenseDate(LocalDateTime.now().minusDays(25));
        exp5.setVehicle(civic);
        expenseEntryRepository.save(exp5);
    }
}
