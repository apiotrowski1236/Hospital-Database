package com.company;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.PreparedStatement;

public class MedSql {

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sql_lite/med.db"; //Todo: Create a med.s13 file
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            conn.createStatement().executeUpdate("PRAGMA foreign_keys = ON;"); //turns on foreign keys

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    /**Inserts a new Volunteer, Administrator, Nurse, or Technician into the Worker Table.
     *@param type char specifying the hospital worker's job.
     *@param firstName String person's first name.
     *@param lastName String person's last name & unique identifier.
     */
    public void insertWorker(String type, String firstName, String lastName) {

        String sql = "INSERT INTO Workers(type, firstName, lastName) VALUES (?,?,?);";

        try (Connection conn = this.connect();) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, type); //First ? in sql
            ps.setString(2, firstName); //Second ? in sql
            ps.setString(3, lastName); //Third ? in sql
            ps.executeUpdate();
            ps.close();

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    public void insertDoctor(String firstName, String lastName, String privileges) {
        String sql = "INSERT INTO Doctors(firstName, lastName, privileges) VALUES (?,?,?);";
        try (Connection conn = this.connect();) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, firstName); //First ? in sql
            ps.setString(2, lastName); //Second ? in sql
            ps.setString(3, privileges); //Third ? in sql
            ps.executeUpdate();
            ps.close();

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void insertDoctorPatientRelationship(String doctorName, String patientName) {
        String sql = "INSERT INTO DoctorPatientRelationships(doctorName, patientName) VALUES (?,?);";
        try (Connection conn = this.connect();) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, doctorName); //First ? in sql
            ps.setString(2, patientName); //Second ? in sql
            ps.executeUpdate();
            ps.close();

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void insertPatient(int pid,String firstName, String lastName, String primaryCareDoc) {
        String sql = "INSERT INTO Patients(firstName, lastName, pid, primaryCareDoc) VALUES (?,?,?,?);";
        try (Connection conn = this.connect();) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pid); //First ? in sql
            ps.setString(2, firstName); //Second ? in sql
            ps.setString(3, lastName); //Third ? in sql
            ps.setString(4, primaryCareDoc); //Fourth ? in sql
            ps.executeUpdate();
            ps.close();

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertPatientInsurance(int pid, int number, String company) {
        String sql = "INSERT INTO PatientInsurance(pid, number, company) VALUES (?,?,?);";
        try (Connection conn = this.connect();) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pid); //First ? in sql
            ps.setInt(2, number); //Second ? in sql
            ps.setString(3, company); //Third ? in sql
            ps.executeUpdate();
            ps.close();

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertEmergencyContact(int pid, String name, String number) {
        String sql = "INSERT INTO PatientEmergencyContacts(pid, name, number) VALUES (?,?,?);";
        try (Connection conn = this.connect();) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pid); //First ? in sql
            ps.setString(2, name); //Second ? in sql
            ps.setString(3, number); //Third ? in sql
            ps.executeUpdate();
            ps.close();

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertInpatientInfo(int pid, String admitted, String discharged, String diagnosis) {
        String sql = "INSERT INTO InpatientInfo(pid, admitted, discharged, did) VALUES (?,?,?,?);";
        try (Connection conn = this.connect();) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pid); //First ? in sql
            ps.setString(2, admitted); //Second ? in sql
            ps.setString(3, discharged); //Third ? in sql
            ps.setString(4, diagnosis); //Third ? in sql
            ps.executeUpdate();
            ps.close();

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertOutpatientInfo(int pid, String dateOfService) {
        String sql = "INSERT INTO OutpatientInfo(pid, dateOfService) VALUES (?,?);";
        try (Connection conn = this.connect();) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pid); //First ? in sql
            ps.setString(2, dateOfService); //Second ? in sql
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertDiagnosis(int did, String diagnosis) {
        String sql = "INSERT INTO Diagnoses(did, diagnosis) VALUES (?,?);";
        try (Connection conn = this.connect();) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, did); //First ? in sql
            ps.setString(2, diagnosis); //Second ? in sql
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertFacility(int fid, String facilityName) {
        String sql = "INSERT INTO Facilities(fid, facilityName) VALUES (?,?);";
        try (Connection conn = this.connect();) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, fid); //First ? in sql
            ps.setString(2, facilityName); //Second ? in sql
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    public void insertDoctorUsingFacilities(String lastName, int fid, Date dateOfUse) {
        String sql = "INSERT INTO DoctorsUsingFacilities(lastName, fid, dateOfUse) VALUES (?,?,?);";
        try (Connection conn = this.connect();) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, lastName); //First ? in sql
            ps.setInt(2, fid); //Second ? in sql
            ps.setDate(3, dateOfUse); //Second ? in sql
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void insertTreatmentOrderedByDoctor(String patientLastName, String doctorLastName, String type, String time, String name) {
        String sql = "INSERT INTO TreatmentsOrderedByDoctors(patientLastName, doctorLastName, type, time, tid) VALUES (?,?,?,?,?);";
        try (Connection conn = this.connect();) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, patientLastName); //First ? in sql
            ps.setString(2, doctorLastName); //Second ? in sql
            ps.setString(3, type); //Third ? in sql
            ps.setString(4, time);
            ps.setString(5 ,name);
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertDiagnosisForPatient(String patientLastName, String doctorLastName, int did, Date dateOfDiagnosis) {
        String sql = "INSERT INTO DiagnosisForPatients(patientLastName, doctorLastName, did, dateOfDiagnosis) VALUES (?,?,?,?);";
        try (Connection conn = this.connect();) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, patientLastName); //First ? in sql
            ps.setString(2, doctorLastName); //Second ? in sql
            ps.setInt(3, did); //Third ? in sql
            ps.setDate(4, dateOfDiagnosis ); //Fourth ? in sql
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void insertPatientOccupyingRoom(int pid, int roomNumber, String startDate, String endDate) {
        String sql = "INSERT INTO PatientsOccupyingRooms(pid, roomNumber, startDate, endDate) VALUES (?,?,?,?);";
        try (Connection conn = this.connect();) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pid); //First ? in sql
            ps.setInt(2, roomNumber); //Second ? in sql
            ps.setString(3, startDate); //Third ? in sql
            ps.setString(4, endDate ); //Fourth ? in sql
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

//public void insertAdministeringTreatments!!!!!!!!!!!!!!!
}