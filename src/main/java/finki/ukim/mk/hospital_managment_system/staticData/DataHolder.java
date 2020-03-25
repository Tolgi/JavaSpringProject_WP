//package finki.ukim.mk.hospital_managment_system.staticData;
//
//import finki.ukim.mk.hospital_managment_system.model.*;
//import lombok.Getter;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.time.Month;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//@Getter
//public class DataHolder {
//
//    public static List<Patient> patients = new ArrayList<>();
//    public static List<Doctor> doctors = new ArrayList<>();
//    public static List<Appointment> appointments = new ArrayList<>();
//    public static List<Specialization> specializations = new ArrayList<>();
//    public static List<MedicalHistory> medicalHistories = new ArrayList<>();
//
//    @PostConstruct
//    public void init(){
//
//        Specialization s1 = new Specialization("1", "Dentist", LocalDateTime.now());
//        Specialization s2 = new Specialization("2", "Dermatologist", LocalDateTime.now());
//        Specialization s3 =new Specialization("3", "Gynecologist", LocalDateTime.now());
//        Specialization s4 = new Specialization("4", "Ear-Nose-Throat(Ent) Specialist", LocalDateTime.now());
//        Specialization s5 = new Specialization("5", "Anesthesiology", LocalDateTime.now());
//        Specialization s6 = new Specialization("6", "Neurology", LocalDateTime.now());
//        Specialization s7 = new Specialization("7", "Pediatrics", LocalDateTime.now());
//        Specialization s8 = new Specialization("8", "Surgery", LocalDateTime.now());
//        Specialization s9 = new Specialization("9", "Cardiology", LocalDateTime.now());
//        Specialization s10 = new Specialization("10", "Orthopaedics", LocalDateTime.now());
//
//        specializations.add(s1);
//        specializations.add(s2);
//        specializations.add(s3);
//        specializations.add(s4);
//        specializations.add(s5);
//        specializations.add(s6);
//        specializations.add(s7);
//        specializations.add(s8);
//        specializations.add(s9);
//        specializations.add(s10);
//
//
//        Doctor d1 = new Doctor("1", "John.R.Lurain", "Northwestern Memorial", 550, "312-695-0990", "jhon_rl@gmail.com",s6, new ArrayList<>());
//        Doctor d2 = new Doctor("2", "Gustavo C. Rodriguez", "NorthShore University HealthSystem", 600, "847-570-2639", "gustavo@gmail.com",s4, new ArrayList<>());
//        Doctor d3 = new Doctor("3", "Philip C. Hoffman", "UChicago Medicine", 750, "773-702-8222", "philipch@gmail.com",s8, new ArrayList<>());
//        Doctor d4 = new Doctor("4", "Michael A. Brusca", "Palos Hospital", 550, "708-873-7775", "michaelab@gmail.com",s2, new ArrayList<>());
//        Doctor d5 = new Doctor("5", "David Crandall", "Advocate Good Shepherd Hospital, Barrington", 400, "847-382-7330", "davidc@gmail.com",s3, new ArrayList<>());
//        Doctor d6 = new Doctor("6", "Gloria Kim", "Advocate Good Samaritan Hospital, Downers Grove", 990, "630-968-2144", "kim@gmail.com",s6, new ArrayList<>());
//
//        doctors.add(d1);
//        doctors.add(d2);
//        doctors.add(d3);
//        doctors.add(d4);
//        doctors.add(d5);
//        doctors.add(d6);
//
//        Patient p1 = new Patient("1", "Victorija Stojanova", "female", "viktorija_s@gmail.com","Hyannis, Masachusets", 22, "072-432-880", LocalDateTime.now().plusDays(3), new ArrayList<>());
//        Patient p2 = new Patient("2", "Michael Curry", "male", "michaelcurry@gmail.com","New York, New York", 42, "721-987-234", LocalDateTime.now().plusHours(13), new ArrayList<>());
//        Patient p3 = new Patient("3", "Alexander Jovanov", "male", "alex@gmail.com","Skopje, Macedonia", 22, "772-662-980", LocalDateTime.now().plusDays(6), new ArrayList<>());
//        Patient p4 = new Patient("4", "Carson Cuppic", "male", "carsoncc@gmail.com","Hyannis, Masachusets", 31, "723-475-001", LocalDateTime.now().plusHours(32), new ArrayList<>());
//        Patient p5 = new Patient("5", "Danielle Aniston", "female", "danielle_a@gmail.com","Boston, Massachusets", 30, "312-987-334", LocalDateTime.now().plusHours(22), new ArrayList<>());
//
//        patients.add(p1);
//        patients.add(p2);
//        patients.add(p3);
//        patients.add(p4);
//        patients.add(p5);
//
//        Appointment ap1 = new Appointment("1", 550, "active", LocalDate.of(2020, Month.JUNE,21), LocalTime.of(11,20), LocalDateTime.now().plusHours(73), p1, d1);
//        Appointment ap2 = new Appointment("2", 400, "active", LocalDate.of(2020, Month.MAY,11), LocalTime.of(15,30), LocalDateTime.now().plusDays(5), p4, d5);
//        Appointment ap3 = new Appointment("3", 600, "active", LocalDate.of(2020, Month.APRIL,25), LocalTime.of(8,00), LocalDateTime.now().plusHours(24), p3, d2);
//        Appointment ap4 = new Appointment("4", 550, "active", LocalDate.of(2020, Month.MARCH,16), LocalTime.of(13,40), LocalDateTime.now().plusHours(20), p5, d1);
//
//        appointments.add(ap1);
//        appointments.add(ap2);
//        appointments.add(ap3);
//        appointments.add(ap4);
//
//        medicalHistories.add(new MedicalHistory("1", "80/120", 85, 120, "38.5", "Abc.tab xyz Syrup", LocalDateTime.now().plusHours(44), p1));
//        medicalHistories.add(new MedicalHistory("2", "85/120", 65, 125, "37.5", "Abc.tab xyz Syrup", LocalDateTime.now().plusHours(80), p4));
//        medicalHistories.add(new MedicalHistory("3", "80/127", 95, 115, "38.0", "Abc.tab xyz Syrup", LocalDateTime.now().plusHours(93), p5));
//
//
//        doctors.get(1).getPatients().add(p1);
//        doctors.get(2).getPatients().add(p2);
//        doctors.get(2).getPatients().add(p3);
//        doctors.get(5).getPatients().add(p4);
//        doctors.get(3).getPatients().add(p5);
//        doctors.get(3).getPatients().add(p1);
//
//
//
//        patients.get(1).getFamilyDoctors().add(d1);
//        patients.get(2).getFamilyDoctors().add(d4);
//        patients.get(4).getFamilyDoctors().add(d5);
//        patients.get(4).getFamilyDoctors().add(d2);
//
//    }
//}