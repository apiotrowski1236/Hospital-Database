package com.company;

class Parser {
    private DataCheck checker;
    private MedSql myMedImporter;

    protected Parser() {
        checker = new DataCheck();
        myMedImporter = new MedSql();
    }

    protected void parseWorker(String[] a) {
        //Check the array length
        checker.checkPersonArrayLength(a);
        //Parse the worker!
        String type = a[0];
        String firstName = a[1];
        String lastName = a[2];
        //Check the worker
        checker.checkPersonNameAndType(type, firstName, lastName);
        //Check to make sure the Worker does not contain an extraneous information
        checker.checkOnlyBasicWorker(a);
        //Insert worker
        myMedImporter.insertWorker(type, firstName, lastName);

    }

    protected void parsePatient(String[] a) {
        //Check the array length
        checker.checkPersonArrayLength(a);
        //Parse the patient! Assign all necessary info to variables.
        String type = a[0];
        String firstName = a[1];
        String lastName = a[2];
        String privileges = a[3];
        int pid = Integer.parseInt(a[4]);
        int roomNumber = Integer.parseInt(a[5]);
        String emergencyContact = a[6];
        String contactNumber = a[7];
        int insuranceNumber = Integer.parseInt(a[8]);
        String policyCompany = a[9];
        String primaryDoc = a[10];
        String diagnosis = a[11];
        String admitted = a[12];
        String discharged = a[13];
        //Check the patient!
        checker.checkPersonNameAndType(type, firstName, lastName);
        checker.checkPatientID(pid);
        checker.checkEmergencyContact(emergencyContact, contactNumber);
        checker.checkInsurance(insuranceNumber, policyCompany);
        //checker.checkPrimaryCareDoc(primaryDoc); //TODO: write this function.
        //checker.check(diagnosis); //TODO: write this function
        //Check to make sure that the patient does not contain any extraneous information
        checker.checkOnlyPatient(a);
        //Insert Patient
        myMedImporter.insertPatient(pid, firstName, lastName, primaryDoc);
        myMedImporter.insertPatientInsurance(pid, insuranceNumber, policyCompany);
        myMedImporter.insertEmergencyContact(pid, emergencyContact, contactNumber);
        //parse Inpatient
        if (type.equals("I")) {
            parseInpatient(a);
        }
        //parse Outpatient
        else {
            parseOutpatient(a);
        }
    }

    protected void parseInpatient(String[] a) {
        //Assign all necessary info to variables.
        int pid = Integer.parseInt(a[4]);
        int roomNumber = Integer.parseInt(a[5]);
        String diagnosis = a[11];
        String admitted = a[12];
        String discharged = a[13];

        //Check inpatient-specific information
        checker.checkRoomNumber(roomNumber);
        //checker.check(diagnosis);//TODO: WRITE THIS FUNCTION
        checker.checkDate(admitted);
        checker.checkDate(discharged); //see if this can be null

        //Insert inpatient-specific information
        myMedImporter.insertInpatientInfo(pid, admitted, discharged, diagnosis);
        myMedImporter.insertPatientOccupyingRoom(pid, roomNumber, admitted, discharged);
        //myMedImporter.insertDiagnosisForPatient(String patientLastName, String doctorLastName, int did, Date dateOfDiagnosis) TODO!

    }

    protected void parseOutpatient(String[] a) {
        //Assign all necessary info to variables
        int pid = Integer.parseInt(a[4]);
        String admitted = a[12];

        //Check outpatient-specific information
        checker.checkDate(admitted);

        //Insert outpatient-specific information
        myMedImporter.insertOutpatientInfo(pid, admitted);
    }

    protected void parseDoctor(String[] a) {
        //Check the array length
        checker.checkPersonArrayLength(a);

        //Parse the doctor.
        String type = a[0];
        String firstName = a[1];
        String lastName = a[2];
        String privileges = a[3];

        //Check that doctor attributes are valid
        checker.checkPersonNameAndType(type, firstName, lastName);
        checker.checkDoctorPrivileges(privileges);

        //Check that array contains only valid attributes.
        checker.checkOnlyDoctor(a);

        //Insert the doctor
        myMedImporter.insertDoctor(firstName, lastName, privileges);
    }

    protected void parseRelationship(String[] a) {
        //Check the array's length
        checker.checkRelationshipArrayLength(a);

        //Parse the relationship
        String doctorLastName = a[0];
        String patientLastName = a[1];

        //Check the relationship
        checker.checkNameLength(doctorLastName);
        checker.checkNameLength(patientLastName);

        //Insert the relationship
        myMedImporter.insertDoctorPatientRelationship(doctorLastName, patientLastName);
    }

    protected void parseTreatment(String[] a) {
        //Check the array length.
        checker.checkTreatmentArrayLength(a);
        //Parse the treatment
        String patientLastName = a[0];
        String doctorLastName = a[1];
        String type = a[2];
        String treatment = a[3];
        String timestamp = a[4];
        //Check the treatment
        checker.checkNameLength(patientLastName);
        checker.checkNameLength(doctorLastName);
        checker.checkTreatmentType(type);
        //checker.checkTreatment(treatment); //TODO: write this function
        //checker.checkTimestamp(timestamp); //TODO: write this function
        //Insert the relationship
        myMedImporter.insertTreatmentOrderedByDoctor(patientLastName, doctorLastName, type, timestamp, treatment);
    }

}


